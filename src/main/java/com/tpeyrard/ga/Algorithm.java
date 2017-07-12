package com.tpeyrard.ga;

import com.tpeyrard.ga.simple.PopulationImp;

public interface Algorithm {
    PopulationImp evolvePopulation(PopulationImp pop);
}
