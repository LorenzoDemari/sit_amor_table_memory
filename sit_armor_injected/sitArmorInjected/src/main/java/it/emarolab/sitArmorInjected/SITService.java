package it.emarolab.sitArmorInjected;

import it.emarolab.amor.owlInterface.OWLReferences;
import it.emarolab.owloop.descriptor.construction.descriptorEntitySet.DescriptorEntitySet;
import it.emarolab.sit.core.SITInterface;
import it.emarolab.sit.core.owloopDescriptor.ActualSceneDescriptor;
import it.emarolab.sit.core.owloopDescriptor.MemorySceneClassified;
import it.emarolab.sit.core.owloopDescriptor.MemorySceneDescriptor;
import it.emarolab.sit.example.simpleSpatialScenario.Point3D;
import it.emarolab.sit.example.tableScenario.sceneElement.SpatialObject;
import it.emarolab.sit.example.tableScenario.sceneRelation.ConnectedRelation;
import it.emarolab.sitArmorInjected.Table.Pins;
import it.emarolab.sitArmorInjected.Table.Legs;
import it.emarolab.sitArmorInjected.owloopDescriptor.ScoredIndividualDescriptor;
import org.ros.node.ConnectedNode;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLLiteral;
import sit_armor_injected_msgs.ArmorSITSceneResponse;
import sit_armor_injected_msgs.Recognition;
import sit_armor_injected_msgs.RecognitionVector;
import sit_armor_injected_msgs.SceneElement;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static java.lang.StrictMath.exp;

/**
 * ...
 * <p>
 * ...
 * <p>
 * <div style="text-align:center;"><small>
 * <b>File</b>:        ${FILE} <br>
 * <b>Licence</b>:     GNU GENERAL PUBLIC LICENSE. Version 3, 29 June 2007 <br>
 * <b>Author</b>:      Buoncompagni Luca (luca.buoncompagni@edu.unige.it) <br>
 * <b>affiliation</b>: DIBRIS, EMAROLab, University of Genoa. <br>
 * <b>date</b>:        21/07/19 <br>
 * </small></div>
 */
public class SITService {

    private static final String COUNTER_CLASS = "COUNTER_CLASS";
    private static final String HAS_SCENE_COUNTER = "hasSceneCounter";
    private static final double ALPHA = 1;//0.01;          //Sigmoid scale >0
    private static final Long STEP = 1L;           //Added if the scene is recognised and removed if not
    private static final double FORGETTING_TH = 0.4;//20;
    private static final Long INITIAL_COUNTER = 2L;//1000;     //Worst case scenario counter : 2000. 5(#of input for every scene)*4(number of scene )*100 (#of experiment)
    private OWLReferences ontoRef;
    private double learnTh;
    private Set<SpatialObject> elements;


    Map< MemorySceneDescriptor, Double> scoredMap = new HashMap<>();


    private static int cnt = 0;

    public SITService(OWLReferences ontology, double learnTH, List<SceneElement> elementsROS, ArmorSITSceneResponse response,
                      ConnectedNode connectedNode, FileWriter csvDictionary, FileWriter csvForgetting, FileWriter csvRecognition) throws IOException {
        // parse request
        this.ontoRef = ontology;
        this.learnTh = learnTH;
        this.elements = parseElements( elementsROS);

        String csvLearn;

        // evaluate scene
        TableSITExample ex = new TableSITExample(ontoRef);
        ConpoundTableEvaluator evaluator = new ConpoundTableEvaluator( this.elements, ontoRef);
        evaluator.evaluate();
        SITInterface sit = ex.evaluateScene( evaluator.getRelations(), "SCENE-" + cnt++, this.learnTh); // you can the scene name here if you wish
        System.out.println( "SIT evaluate scene with relations " + evaluator.getRelations());
//        ex.getOntology().synchronizeReasoner();
//        sit.showMemory();


        // initialise score on learn
        if ( sit.getLearned() != null){
            MemorySceneDescriptor learnedDescriptor = sit.getLearned();
            csvLearn = System.nanoTime() + "; " + sit.getLearned().getGroundInstanceName() + "; " + learnedDescriptor.getRestrictionConcepts().toString();
            csvDictionary.append(csvLearn);
            csvDictionary.append("\n");
            csvDictionary.flush();
            ScoredIndividualDescriptor scoreDescr = new ScoredIndividualDescriptor(
                    learnedDescriptor.getGroundInstanceName() + "-Srd", ontoRef);
            scoreDescr.addTypeIndividual(COUNTER_CLASS);
            scoreDescr.addTypeIndividual( learnedDescriptor.getGroundInstanceName());
            scoreDescr.addData(HAS_SCENE_COUNTER, INITIAL_COUNTER, true);
            scoreDescr.writeExpressionAxioms();
        }

        //ontoRef.applyOWLManipulatorChanges(); // check max, it could not be in otnology

        consolidatingForgetting(sit, csvForgetting);

        ontoRef.applyOWLManipulatorChanges();
        ex.getOntology().saveOntology("sitArmorInjected/src/main/java/it/emarolab/sitArmorInjected/tableSITProcessed.owl");


        //ex.getOntology().saveOntology("sitArmorInjected/src/main/java/it/emarolab/sitArmorInjected/tableSITProcessed.owl");
        setResponse(sit, response, connectedNode, csvRecognition);

    }


    private Set<SpatialObject> parseElements(List< SceneElement> elements){
        Set<SpatialObject> out = new HashSet<>();
        for ( SceneElement s : elements){
            SpatialObject e = null;

            // CHAIR
            if( s.getType().equals( Legs.Chair_X.TYPE)){ // ie, "X"
                e = new Legs.Chair_X();
            } else if( s.getType().equals( Legs.Chair_Minus_X.TYPE)){ // ie, "Minus_X"
                e = new Legs.Chair_Minus_X();
            } else if( s.getType().equals( Legs.Chair_Y.TYPE)){ // ie, "Y"
                e = new Legs.Chair_Y();
            } else if( s.getType().equals( Legs.Chair_Minus_Y.TYPE)){ // ie, "Minus_Y"
                e = new Legs.Chair_Minus_Y();
            }

            //BED
            else if( s.getType().equals( Legs.Bed_X.TYPE)){ // ie, "X"
                e = new Legs.Bed_X();
            } else if( s.getType().equals( Legs.Bed_Minus_X.TYPE)){ // ie, "Minus_X"
                e = new Legs.Bed_Minus_X();
            } else if( s.getType().equals( Legs.Bed_Y.TYPE)){ // ie, "Y"
                e = new Legs.Bed_Y();
            } else if( s.getType().equals( Legs.Bed_Minus_Y.TYPE)){ // ie, "Minus_Y"
                e = new Legs.Bed_Minus_Y();
            }

            //NOT
            else if( s.getType().equals( Legs.Not_X.TYPE)){ // ie, "X"
                e = new Legs.Not_X();
            } else if( s.getType().equals( Legs.Not_Minus_X.TYPE)){ // ie, "Minus_X"
                e = new Legs.Not_Minus_X();
            } else if( s.getType().equals( Legs.Not_Y.TYPE)){ // ie, "Y"
                e = new Legs.Not_Y();
            } else if( s.getType().equals( Legs.Not_Minus_Y.TYPE)){ // ie, "Minus_Y"
                e = new Legs.Not_Minus_Y();
            }

            //ROOF
            else if( s.getType().equals( Legs.Roof_X.TYPE)){ // ie, "X"
                e = new Legs.Roof_X();
            } else if( s.getType().equals( Legs.Roof_Minus_X.TYPE)){ // ie, "Minus_X"
                e = new Legs.Roof_Minus_X();
            } else if( s.getType().equals( Legs.Roof_Y.TYPE)){ // ie, "Y"
                e = new Legs.Roof_Y();
            } else if( s.getType().equals( Legs.Roof_Minus_Y.TYPE)){ // ie, "Minus_Y"
                e = new Legs.Roof_Minus_Y();
            }

            if( s.getType().equals( Pins.Pin_1.TYPE)){ // ie, "X"
                e = new Pins.Pin_1();
            } else if( s.getType().equals( Pins.Pin_2.TYPE)){ // ie, "X"
                e = new Pins.Pin_2();
            } else if( s.getType().equals( Pins.Pin_3.TYPE)){ // ie, "X"
                e = new Pins.Pin_3();
            } else if( s.getType().equals( Pins.Pin_4.TYPE)){ // ie, "X"
                e = new Pins.Pin_4();
            } else if( s.getType().equals( Pins.Pin_5.TYPE)){ // ie, "X"
                e = new Pins.Pin_5();
            } else if( s.getType().equals( Pins.Pin_6.TYPE)){ // ie, "X"
                e = new Pins.Pin_6();
            } else if( s.getType().equals( Pins.Pin_7.TYPE)){ // ie, "X"
                e = new Pins.Pin_7();
            } else if( s.getType().equals( Pins.Pin_8.TYPE)){ // ie, "X"
                e = new Pins.Pin_8();
            } else if( s.getType().equals( Pins.Pin_9.TYPE)){ // ie, "X"
                e = new Pins.Pin_9();
            } else if( s.getType().equals( Pins.Pin_10.TYPE)){ // ie, "X"
                e = new Pins.Pin_10();
            } else if( s.getType().equals( Pins.Pin_11.TYPE)){ // ie, "X"
                e = new Pins.Pin_11();
            } else if( s.getType().equals( Pins.Pin_12.TYPE)) { // ie, "X"
                e = new Pins.Pin_12();
            }

            if ( e != null) {
                if (s.getFeatures().length >= 3) {
                    Point3D c = new Point3D(s.getFeatures()[0], s.getFeatures()[1], s.getFeatures()[2]);
                    e.setCenter(c);
                }
                if (s.getFeatures().length >= 6) {
                    Point3D a = new Point3D(s.getFeatures()[3], s.getFeatures()[4], s.getFeatures()[5]);
                    //((Orientable) e).setAxis(a);
                }
            }

            out.add( e);
        }
        return out;
    }


    private void setResponse(SITInterface sit, ArmorSITSceneResponse response, ConnectedNode connectedNode, FileWriter csvRecognition) throws IOException {
        Set<MemorySceneClassified> recognitions = sit.getRecognitions();

        MemorySceneDescriptor learned = sit.getLearned();
        if ( learned != null)
            response.setLearnedSceneName( learned.getGroundInstanceName());

        String csvRec = System.nanoTime() +"; " + (recognitions.size()-1) + "; ";


        RecognitionVector recognitionROS = connectedNode.getTopicMessageFactory().newFromType(RecognitionVector._TYPE);
        if ( recognitions != null) {
            for (MemorySceneClassified i : recognitions) {
                if ( ! i.getGroundInstanceName().equals( SITInterface.SCENE_ROOT)) {
                    Recognition r = connectedNode.getTopicMessageFactory().newFromType(Recognition._TYPE);
                    r.setClassName(i.getGroundInstanceName());
                    r.setSimilarity(i.getSimilarity());


                    Double score = scoredMap.get(i);
                    if ( score != null)
                        r.setScore( score);

                    recognitionROS.getRecognitions().add(r);

                    csvRec += r.getClassName() +"; " + r.getSimilarity() + ";" + r.getScore() + "; ";
                }
            }
            csvRecognition.append(csvRec);
            csvRecognition.append("\n");
            csvRecognition.flush();
        }
        System.out.println("Eccomi: --------------------------- " + csvRec);
        response.setRecognitions(recognitionROS);
    }

    private void consolidatingForgetting(SITInterface sit, FileWriter csvForgetting){

        MemorySceneDescriptor root = new MemorySceneDescriptor(SITInterface.SCENE_ROOT, sit.getActualScene().getOntology());
        root.readExpressionAxioms();
        Set<MemorySceneDescriptor> builtSub = root.buildSubConcept();
        Set<MemorySceneClassified> recognitions = sit.getRecognitions();

        int n_nodes=builtSub.size();
        double[] scores= new double[n_nodes];

        String csvFor;
        String csvCon;

        for ( MemorySceneDescriptor item : builtSub) {
            if (!item.getInstance().isOWLNothing()) {
                boolean recognised = false;
                for (MemorySceneDescriptor r : recognitions) {
                    if (item.getGroundInstanceName().equals(r.getGroundInstanceName())) {
                        recognised = true;
                        break;
                    }
                }
                // for all sub classes of SCENE
                // update normalised score
                Set<ActualSceneDescriptor> ind = item.buildIndividualInstances();
                for ( ActualSceneDescriptor i : ind){
                    // i: individual inside r
                    for( OWLClass c : i.getIndividualTypes()){
                        // c: all the classes of i (r,....)
                        if ( c.equals( ontoRef.getOWLClass(COUNTER_CLASS))) {
                            ScoredIndividualDescriptor scoreDescriptor = new ScoredIndividualDescriptor(i.getInstance(), i.getOntology());
                            scoreDescriptor.readExpressionAxioms();

                            Long sceneCounter = 0L;
                            for( DescriptorEntitySet.DataLinks oldScoreProp : scoreDescriptor.getIndividualDataProperties()){
                                //OWLDataProperty hasScoreScene = scoreDescriptor.getOntology().getOWLDataProperty(HAS_SCENE_COUNTER);
                                boolean stop = false;
                                for( OWLLiteral os : oldScoreProp.getValues()){
                                    sceneCounter = Long.valueOf( os.getLiteral());
                                    if (recognised)
                                        sceneCounter += STEP;
                                    else sceneCounter -= STEP;

                                    double score= (2/(1+exp(-1*ALPHA*sceneCounter)))-1;
                                    scoredMap.put( item, score);

                                    if (score<FORGETTING_TH) {
                                        item.getIndividualInstances().clear();
                                        item.getSubConcepts().clear();
                                        item.getRestrictionConcepts().clear();
                                        item.getSuperConcepts().clear();
                                        item.writeExpressionAxioms();
                                        scoreDescriptor.getIndividualDataProperties().clear();
                                        scoreDescriptor.getIndividualTypes().clear();
                                        scoreDescriptor.writeExpressionAxioms();
                                        System.out.println("removing "+ item.getGroundInstanceName()+ " with score:" + score);
                                        csvFor=System.nanoTime() + "; " + item.getGroundInstanceName() +"; " + score;
                                        try {
                                            csvForgetting.append(csvFor);
                                            csvForgetting.append("\n");
                                            csvForgetting.flush();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    else {
                                        scoreDescriptor.removeData(HAS_SCENE_COUNTER);
                                        scoreDescriptor.addData(HAS_SCENE_COUNTER, sceneCounter, true);
                                        scoreDescriptor.writeExpressionAxioms();
                                        csvCon=System.nanoTime() + ", " + item.getGroundInstanceName() +", " + score;
                                        System.out.println("Consolidate: --------------"+csvCon);
                                    }
                                    stop = true;
                                    break;
                                }
                                //}
                                if (stop)
                                    break;
                            }
                        }
                    }
                }
            }
        }

    }
}


