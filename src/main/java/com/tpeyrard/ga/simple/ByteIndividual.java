package com.tpeyrard.ga.simple;

import com.tpeyrard.ga.FitnessComputation;
import com.tpeyrard.ga.Individual;

public class ByteIndividual implements Individual {

    private static final int DEFAULT_GENE_LENGTH = 64;
    private final byte[] genes;
    // Cache
    private int fitness = 0;

    private ByteIndividual(byte[] genes) {
        this.genes = genes;
    }

    public static ByteIndividual newIndividual() {
        return new ByteIndividual(new byte[DEFAULT_GENE_LENGTH]);
    }

    public static ByteIndividual newRandomIndividual() {
        return new ByteIndividual(randomGenes());
    }

    private static byte[] randomGenes() {
        final byte[] genes = new byte[DEFAULT_GENE_LENGTH];
        for (int i = 0; i < genes.length; i++) {
            byte gene = (byte) Math.round(Math.random());
            genes[i] = gene;
        }
        return genes;
    }


    public byte geneAt(int index) {
        return genes[index];
    }

    public void setGene(int index, byte value) {
        genes[index] = value;
        fitness = 0;
    }

    @Override
    public int genomeSize() {
        return genes.length;
    }

    @Override
    public int fitness(FitnessComputation fitnessCalc) {
        if (fitness == 0) {
            fitness = fitnessCalc.getFitness(this);
        }
        return fitness;
    }

    @Override
    public void geneFrom(int index, Individual fromIndividual) {
        setGene(index, ((ByteIndividual) fromIndividual).geneAt(index));
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