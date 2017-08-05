package com.tpeyrard.ga.advanced;
/*
 * Java Genetic Algorithm Library (@__identifier__@).
 * Copyright (c) @__year__@ Franz Wilhelmstötter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Author:
 *    Franz Wilhelmstötter (franz.wilhelmstoetter@gmx.at)
 *  Modified:
 *    Thomas Peyrard
 */

import org.jenetics.EnumGene;
import org.jenetics.Optimize;
import org.jenetics.PartiallyMatchedCrossover;
import org.jenetics.SwapMutator;
import org.jenetics.engine.Engine;
import org.jenetics.engine.EvolutionResult;
import org.jenetics.engine.EvolutionStatistics;
import org.jenetics.engine.codecs;

import java.util.stream.IntStream;

import static java.lang.Math.*;
import static org.jenetics.engine.EvolutionResult.toBestEvolutionResult;

public class TravelingSalesman {

    private static final int STOPS = 20;
    private static final double RADIUS = 100.0;
    private static final double[][] ADJACENCY = matrix(STOPS, RADIUS);

    private static double[][] matrix(int stops, double radius) {
        double[][] matrix = new double[stops][stops];

        for (int i = 0; i < stops; ++i) {
            for (int j = 0; j < stops; ++j) {
                matrix[i][j] = chord(stops, abs(i - j), radius);
            }
        }
        return matrix;
    }

    private static double chord(int stops, int i, double r) {
        return 2.0 * r * abs(sin(PI * i / stops));
    }

    // Calculate the path length of the current genotype.
    private static double dist(final int[] path) {
        // Calculate the path distance.
        return IntStream.range(0, STOPS)
                .mapToDouble(i ->
                        ADJACENCY[path[i]][path[(i + 1) % STOPS]])
                .sum();
    }

    public static void main(String[] args) {
        final Engine<EnumGene<Integer>, Double> engine = Engine
                .builder(
                        TravelingSalesman::dist,
                        codecs.ofPermutation(STOPS))
                .optimize(Optimize.MINIMUM)
                //.maximalPhenotypeAge(110)
                .populationSize(500)
                .alterers(
                        new SwapMutator<>(0.2),
                        new PartiallyMatchedCrossover<>(0.35))
                .build();

        // Create evolution statistics consumer.
        final EvolutionStatistics<Double, ?>
                statistics = EvolutionStatistics.ofNumber();

        final EvolutionResult<EnumGene<Integer>, Double> best =
                engine.stream()
                        //.limit(bySteadyFitness(15))
                        .limit(200)
                        // Update the evaluation statistics after each generation
                        .peek(statistics)
                        .collect(toBestEvolutionResult());

        System.out.println(statistics);
        System.out.println(best.getBestPhenotype());
    }

}