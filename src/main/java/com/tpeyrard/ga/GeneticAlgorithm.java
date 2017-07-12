package com.tpeyrard.ga;

import com.tpeyrard.ga.simple.PopulationImp;

public interface GeneticAlgorithm {
    PopulationImp evolvePopulation(PopulationImp pop);
}
