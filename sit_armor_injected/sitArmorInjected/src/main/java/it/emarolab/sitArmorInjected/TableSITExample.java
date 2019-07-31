package it.emarolab.sitArmorInjected;

import it.emarolab.amor.owlInterface.OWLReferences;
import it.emarolab.sit.example.simpleSpatialScenario.Point3D;
import it.emarolab.sit.example.simpleSpatialScenario.SimpleSIT;
import it.emarolab.sit.example.SITExampleBase;
import it.emarolab.sit.core.SITInterface;
import it.emarolab.sit.example.tableScenario.sceneElement.SpatialObject;
import it.emarolab.sit.example.tableScenario.sceneRelation.ConnectedRelation;
import it.emarolab.sitArmorInjected.Table.Legs;
import it.emarolab.sitArmorInjected.Table.Pins;

import java.util.HashSet;
import java.util.Set;

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
public class TableSITExample  extends SITExampleBase<ConnectedRelation, SimpleSIT> {

    public TableSITExample(OWLReferences ontoRef) {
        super( ontoRef);
    }

    public TableSITExample(String refName, String ontoFilePath, String ontoIRIPath) {
        super(refName, ontoFilePath, ontoIRIPath);
    }

    @Override
    protected SimpleSIT getSIT(Set<ConnectedRelation> relations) {
        return new SimpleSIT( relations, getOntology());
    }

    public static void main(String[] args) {
        // suppress aMOR log
        it.emarolab.amor.owlDebugger.Logger.setPrintOnConsole( false);
        // start evaluateScene
        TableSITExample ex = new TableSITExample("ONTO1", "sitArmorInjected/src/main/java/it/emarolab/sitArmorInjected/table.owl", ONTO_IRI);
        ex.evaluateScene( processScene0( ex), "Scene-1");
        ex.evaluateScene( processScene1( ex), "Scene-2");
        ex.evaluateScene( processScene2( ex), "Scene-3");
        ex.evaluateScene( processScene3( ex), "Scene-4");
        SITInterface sit = ex.evaluateScene( processScene3( ex));

        ex.getOntology().synchronizeReasoner();
        sit.showMemory();

        ex.getOntology().saveOntology( "sitArmorInjected/src/main/java/it/emarolab/sitArmorInjected/tableSITProcessed.owl");
    }

    private static void addTablePin(Set<SpatialObject> sceneElements){
        Pins.Pin_1 p1 = new Pins.Pin_1();
        sceneElements.add( p1);
        Pins.Pin_4 p4 = new Pins.Pin_4();
        sceneElements.add( p4);
        Pins.Pin_7 p7 = new Pins.Pin_7();
        sceneElements.add( p7);
        Pins.Pin_10 p10 = new Pins.Pin_10();
        sceneElements.add( p10);
    }

    private static Set<ConnectedRelation> processScene0(TableSITExample ex){
        Set<SpatialObject> sceneElements = new HashSet<>();

        addTablePin( sceneElements);

        Legs.Chair_Minus_X l = new Legs.Chair_Minus_X();
        l.setCenter( new Point3D( 0.25, -0.18, 0));
        sceneElements.add( l);


        ConpoundTableEvaluator evaluator = new ConpoundTableEvaluator( sceneElements, ex.getOntology());
        evaluator.evaluate();
        return evaluator.getRelations();
    }

    private static Set<ConnectedRelation> processScene1(TableSITExample ex){
        Set<SpatialObject> sceneElements = new HashSet<>();

        addTablePin( sceneElements);

        Legs.Chair_Minus_X l = new Legs.Chair_Minus_X();
        l.setCenter( new Point3D( 0.25, -0.18, 0));
        sceneElements.add( l);

        Legs.Chair_X l2 = new Legs.Chair_X();
        l2.setCenter( new Point3D( -0.31, -0.18, 0));
        sceneElements.add( l2);

        ConpoundTableEvaluator evaluator = new ConpoundTableEvaluator( sceneElements, ex.getOntology());
        evaluator.evaluate();
        return evaluator.getRelations();
    }

    private static Set<ConnectedRelation> processScene2(TableSITExample ex){
        Set<SpatialObject> sceneElements = new HashSet<>();

        addTablePin( sceneElements);

        Legs.Chair_Minus_X l = new Legs.Chair_Minus_X();
        l.setCenter( new Point3D( 0.25, -0.18, 0));
        sceneElements.add( l);

        Legs.Chair_X l2 = new Legs.Chair_X();
        l2.setCenter( new Point3D( -0.31, -0.18, 0));
        sceneElements.add( l2);

        Legs.Chair_Minus_X l3 = new Legs.Chair_Minus_X();
        l3.setCenter( new Point3D( 0.27, 0.20, 0));
        sceneElements.add( l3);

        ConpoundTableEvaluator evaluator = new ConpoundTableEvaluator( sceneElements, ex.getOntology());
        evaluator.evaluate();
        return evaluator.getRelations();
    }

    private static Set<ConnectedRelation> processScene3(TableSITExample ex){
        Set<SpatialObject> sceneElements = new HashSet<>();

        addTablePin( sceneElements);

        Legs.Chair_Minus_X l = new Legs.Chair_Minus_X();
        l.setCenter( new Point3D( 0.25, -0.18, 0));
        sceneElements.add( l);

        Legs.Chair_X l2 = new Legs.Chair_X();
        l2.setCenter( new Point3D( -0.31, -0.18, 0));
        sceneElements.add( l2);

        Legs.Chair_Minus_X l3 = new Legs.Chair_Minus_X();
        l3.setCenter( new Point3D( 0.27, 0.20, 0));
        sceneElements.add( l3);

        Legs.Chair_X l4 = new Legs.Chair_X();
        l4.setCenter( new Point3D( -0.30, 0.22, 0));
        sceneElements.add( l4);

        ConpoundTableEvaluator evaluator = new ConpoundTableEvaluator( sceneElements, ex.getOntology());
        evaluator.evaluate();
        return evaluator.getRelations();
    }
}