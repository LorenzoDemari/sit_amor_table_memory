package it.emarolab.sitArmorInjected.owloopDescriptor;

import it.emarolab.amor.owlInterface.OWLReferences;
import it.emarolab.owloop.descriptor.construction.descriptorGround.ConceptGround;
import it.emarolab.owloop.descriptor.construction.descriptorGround.DataPropertyGround;
import it.emarolab.owloop.descriptor.construction.descriptorGround.IndividualGround;
import it.emarolab.owloop.descriptor.construction.descriptorEntitySet.DescriptorEntitySet;
import it.emarolab.owloop.descriptor.construction.descriptorExpression.IndividualExpression;
import it.emarolab.owloop.descriptor.utility.conceptDescriptor.FullConceptDesc;
import it.emarolab.owloop.descriptor.utility.dataPropertyDescriptor.FullDataPropertyDesc;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.ArrayList;
import java.util.List;


public class ScoredIndividualDescriptor extends IndividualGround
        implements IndividualExpression.Type<UnusedConceptDesccriptor>,
        IndividualExpression.DataLink<UnusedDataPropDesccriptor> {

    private DescriptorEntitySet.Concepts concepts = new DescriptorEntitySet.Concepts();
    private DescriptorEntitySet.DataLinksSet dataLinks = new DescriptorEntitySet.DataLinksSet();

    public ScoredIndividualDescriptor(OWLNamedIndividual instance, OWLReferences onto) {
        super(instance, onto);
    }
    public ScoredIndividualDescriptor(String instanceName, OWLReferences onto) {
        super(instanceName, onto);
    }

    @Override
    public List<MappingIntent> readExpressionAxioms() {
        List<MappingIntent> r =  IndividualExpression.Type.super.readExpressionAxioms();
        r.addAll( IndividualExpression.DataLink.super.readExpressionAxioms());
        return r;
    }

    @Override
    public List<MappingIntent> writeExpressionAxioms() {
        List<MappingIntent> r = IndividualExpression.Type.super.writeExpressionAxioms();
        r.addAll( IndividualExpression.DataLink.super.writeExpressionAxioms());
        return r;
    }

    @Override
    public UnusedConceptDesccriptor getNewIndividualType(OWLClass instance, OWLReferences ontology) {
        return new UnusedConceptDesccriptor( instance, ontology);
    }
    @Override
    public DescriptorEntitySet.Concepts getIndividualTypes() {
        return concepts;
    }

    @Override
    public UnusedDataPropDesccriptor getNewIndividualDataProperty(DescriptorEntitySet.DataLinks instance, OWLReferences ontology) {
        return new UnusedDataPropDesccriptor( instance.getExpression(), ontology);
    }
    @Override
    public DescriptorEntitySet.DataLinksSet getIndividualDataProperties() {
        return dataLinks;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + "\n" +
                "\n" +
                "\t" + getGround() + ":" + "\n" +
                "\n" +
                "\t\t∈ " + concepts + "\n" +
                "\t\t⊢ " + dataLinks + "\n" +
                "}" + "\n";
    }
}



class UnusedConceptDesccriptor extends ConceptGround {

    public UnusedConceptDesccriptor(OWLClass instance, OWLReferences onto) {
        super(instance, onto);
    }

    @Override
    public List<MappingIntent> readExpressionAxioms() {
        System.err.println( this.getClass().getSimpleName() + " cannot be read!");
        return new ArrayList<>();
    }

    @Override
    public List<MappingIntent> writeExpressionAxioms() {
        System.err.println( this.getClass().getSimpleName() + " cannot be written!");
        return new ArrayList<>();
    }
}

class UnusedDataPropDesccriptor extends DataPropertyGround {


    public UnusedDataPropDesccriptor(OWLDataProperty instance, OWLReferences onto) {
        super(instance, onto);
    }

    @Override
    public List<MappingIntent> readExpressionAxioms() {
        System.err.println( this.getClass().getSimpleName() + " cannot be read!");
        return new ArrayList<>();
    }

    @Override
    public List<MappingIntent> writeExpressionAxioms() {
        System.err.println( this.getClass().getSimpleName() + " cannot be written!");
        return new ArrayList<>();
    }
}
