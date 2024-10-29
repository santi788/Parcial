package com.example.mutante_detector.controller;

import com.example.mutante_detector.model.DnaRecord;
import com.example.mutante_detector.repository.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final DnaRepository dnaRepository;

    @Autowired
    public StatsController(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    @GetMapping
    public Map<String, Object> getStats() {
        long countMutantDna = dnaRepository.findAll().stream().filter(DnaRecord::isMutant).count();
        long countHumanDna = dnaRepository.count();
        double ratio = countHumanDna > 0 ? (double) countMutantDna / countHumanDna : 0;

        return Map.of(
                "count_mutant_dna", countMutantDna,
                "count_human_dna", countHumanDna,
                "ratio", ratio
        );
    }
}
