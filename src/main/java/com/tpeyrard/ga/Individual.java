package com.tpeyrard.ga;

public class Individual {

    private static final int DEFAULT_GENE_LENGTH = 64;
    private byte[] genes = new byte[DEFAULT_GENE_LENGTH];
    // Cache
    private int fitness = 0;

    public void generateIndividual() {
        for (int i = 0; i < genomeSize(); i++) {
            byte gene = (byte) Math.round(Math.random());
            genes[i] = gene;
        }
    }


    public byte geneAt(int index) {
        return genes[index];
    }

    public void setGene(int index, byte value) {
        genes[index] = value;
        fitness = 0;
    }

    public int genomeSize() {
        return genes.length;
    }

    public int fitness() {
        if (fitness == 0) {
            fitness = FitnessCalc.getFitness(this);
        }
        return fitness;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(genomeSize());
        for (byte gene : genes) {
            output.append(gene);
        }
        return output.toString();
    }
}