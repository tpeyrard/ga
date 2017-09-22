package com.tpeyrard.ga.tsp;

import java.util.concurrent.ThreadLocalRandom;

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
      final Range range = Range.newRange(firstParent.genomeSize());

      for (int i = range.start; i < range.end; i++) {
         offspring.setCity(i, firstParent.gene(i));
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

   private static final class Range {
      private int start;
      private int end;

      private Range(int start, int end) {
         this.start = start;
         this.end = end;
      }

      static Range newRange(int genomeSize) {
         final ThreadLocalRandom localRandom = ThreadLocalRandom.current();
         final int tempStartPos = localRandom.nextInt(genomeSize + 1);
         final int tempEndPos = localRandom.nextInt(genomeSize + 1);

         int startPos = Math.min(tempStartPos, tempEndPos);
         int endPos = Math.max(tempStartPos, tempEndPos);
         return new Range(startPos, endPos);
      }
   }
}
