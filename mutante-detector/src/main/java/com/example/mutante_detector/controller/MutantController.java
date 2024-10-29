package com.example.mutante_detector.controller;

import com.example.mutante_detector.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/mutant")
public class MutantController {

    private final MutantService mutantService;

    @Autowired
    public MutantController(MutantService mutantService) {
        this.mutantService = mutantService;
    }

    @PostMapping("/")
    public ResponseEntity<?> isMutant(@RequestBody Map<String, String[]> dnaRequest) {
        String[] dna = dnaRequest.get("dna");
        if (mutantService.isMutant(dna)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
