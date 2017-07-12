package com.tpeyrard.ga.simple;

import com.tpeyrard.ga.FitnessComputation;

public final class BinaryFitness implements FitnessComputation {

    private final byte[] solution;

    private BinaryFitness(String newSolution) {
        this.solution = new byte[newSolution.length()];

        for (int i = 0; i < newSolution.length(); i++) {
            String character = newSolution.substring(i, i + 1);
            if (character.contains("0") || character.contains("1")) {
                solution[i] = Byte.parseByte(character);
            } else {
                solution[i] = 0;
            }
        }
    }

    public static BinaryFitness newSolution(String newSolution) {
        return new BinaryFitness(newSolution);
    }

    @Override
    public int getFitness(ByteIndividual individual) {
        int fitness = 0;
        for (int i = 0; i < individual.genomeSize() && i < solution.length; i++) {
            if (individual.geneAt(i) == solution[i]) {
                fitness++;
            }
        }
        return fitness;
    }

    @Override
    public int maxFitness() {
        return solution.length;
    }
}
