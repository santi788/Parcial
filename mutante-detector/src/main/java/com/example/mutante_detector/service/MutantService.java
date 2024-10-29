package com.example.mutante_detector.service;

import com.example.mutante_detector.model.DnaRecord;
import com.example.mutante_detector.repository.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantService {

    private static final int SEQUENCE_LENGTH = 4;
    private final DnaRepository dnaRepository;

    @Autowired
    public MutantService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    public boolean isMutant(String[] dna) {
        int n = dna.length;
        int mutantSequences = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (checkHorizontal(dna, i, j, n) ||
                        checkVertical(dna, i, j, n) ||
                        checkDiagonal(dna, i, j, n)) {
                    mutantSequences++;
                    if (mutantSequences > 1) {
                        saveDnaRecord(dna, true);
                        return true;
                    }
                }
            }
        }

        saveDnaRecord(dna, false);
        return false;
    }

    private boolean checkHorizontal(String[] dna, int row, int col, int n) {
        if (col + SEQUENCE_LENGTH > n) return false;
        char base = dna[row].charAt(col);
        for (int i = 1; i < SEQUENCE_LENGTH; i++) {
            if (dna[row].charAt(col + i) != base) return false;
        }
        return true;
    }

    private boolean checkVertical(String[] dna, int row, int col, int n) {
        if (row + SEQUENCE_LENGTH > n) return false;
        char base = dna[row].charAt(col);
        for (int i = 1; i < SEQUENCE_LENGTH; i++) {
            if (dna[row + i].charAt(col) != base) return false;
        }
        return true;
    }

    private boolean checkDiagonal(String[] dna, int row, int col, int n) {
        if (row + SEQUENCE_LENGTH > n || col + SEQUENCE_LENGTH > n) return false;
        char base = dna[row].charAt(col);
        for (int i = 1; i < SEQUENCE_LENGTH; i++) {
            if (dna[row + i].charAt(col + i) != base) return false;
        }
        return true;
    }

    private void saveDnaRecord(String[] dna, boolean isMutant) {
        String dnaSequence = String.join(",", dna);
        if (dnaRepository.findByDnaSequence(dnaSequence).isEmpty()) {
            DnaRecord record = new DnaRecord();
            record.setDnaSequence(dnaSequence);
            record.setMutant(isMutant);
            dnaRepository.save(record);
        }
    }
}
