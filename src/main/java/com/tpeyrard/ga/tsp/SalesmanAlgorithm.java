package com.tpeyrard.ga.tsp;

import com.tpeyrard.ga.FitnessComputation;
import com.tpeyrard.ga.GeneticAlgorithm;
import com.tpeyrard.ga.Individual;

public class SalesmanAlgorithm extends GeneticAlgorithm {

    private final TourManager tourManager;

    public SalesmanAlgorithm(FitnessComputation fitnessCalc, TourManager tourManager) {
        super(fitnessCalc);
        this.tourManager = tourManager;
    }

    @Override
    public Individual crossover(Individual firstParent, Individual secondParent, double crossoverRate) {
        Tour offspring = Tour.tourWithNoCity(tourManager);

        return orderedCrossover(offspring, firstParent, secondParent);
    }

    private Individual orderedCrossover(Tour offspring, Individual firstParent, Individual secondParent) {
        final int startPos = (int) (Math.random() * firstParent.genomeSize());
        final int endPos = (int) (Math.random() * firstParent.genomeSize());

        for (int i = 0; i < offspring.genomeSize(); i++) {
            if (startPos < endPos && startPos < i && i < endPos) {
                offspring.setCity(i, firstParent.gene(i));
            } else if (endPos < startPos) {
                boolean positionInSubset = endPos < i && i < startPos;
                if (!positionInSubset) {
                    offspring.setCity(i, firstParent.gene(i));
                }
            }
        }

        for (int i = 0; i < secondParent.genomeSize(); i++) {
            if (!offspring.containsCity(((City) secondParent.gene(i)))) {
                for (int sparePosition = 0; sparePosition < offspring.genomeSize(); sparePosition++) {
                    if (offspring.gene(sparePosition) == City.NO_CITY) {
                        offspring.setCity(sparePosition, secondParent.gene(i));
                        break;
                    }
                }
            }
        }
        return offspring;
    }

    @Override
    public void mutate(Individual tour) {
        swapMutation((Tour) tour);
    }

    private void swapMutation(Tour offspring) {
        for (int tourPos1 = 0; tourPos1 < offspring.genomeSize(); tourPos1++) {
            if (Math.random() < MUTATION_RATE) {
                int tourPos2 = (int) (offspring.genomeSize() * Math.random());

                offspring.swap(tourPos1, tourPos2);
            }
        }
    }
}
