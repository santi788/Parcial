package com.example.mutante_detector.repository;

import com.example.mutante_detector.model.DnaRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DnaRepository extends JpaRepository<DnaRecord, Long> {
    Optional<DnaRecord> findByDnaSequence(String dnaSequence);
}
