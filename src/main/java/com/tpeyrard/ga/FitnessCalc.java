package com.tpeyrard.ga;

public final class FitnessCalc {

    static byte[] solution = new byte[64];

    private FitnessCalc() {
    }

    public static void setSolution(byte[] newSolution) {
        solution = newSolution;
    }

    static void setSolution(String newSolution) {
        solution = new byte[newSolution.length()];

        for (int i = 0; i < newSolution.length(); i++) {
            String character = newSolution.substring(i, i + 1);
            if (character.contains("0") || character.contains("1")) {
                solution[i] = Byte.parseByte(character);
            } else {
                solution[i] = 0;
            }
        }
    }

    static int getFitness(Individual individual) {
        int fitness = 0;
        for (int i = 0; i < individual.genomeSize() && i < solution.length; i++) {
            if (individual.geneAt(i) == solution[i]) {
                fitness++;
            }
        }
        return fitness;
    }

    static int maxFitness() {
        return solution.length;
    }
}
