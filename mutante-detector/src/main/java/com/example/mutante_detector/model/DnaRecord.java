package com.example.mutante_detector.model;

import jakarta.persistence.*;


@Entity

@Table(name = "dna_records")
public class DnaRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String dnaSequence;
    private boolean isMutant;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDnaSequence() {
        return dnaSequence;
    }

    public void setDnaSequence(String dnaSequence) {
        this.dnaSequence = dnaSequence;
    }

    public boolean isMutant() {
        return isMutant;
    }

    public void setMutant(boolean mutant) {
        isMutant = mutant;
    }
}
