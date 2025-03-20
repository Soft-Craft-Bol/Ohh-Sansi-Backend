package com.softcraft.ohhsansibackend.domain.services;

import com.softcraft.ohhsansibackend.domain.models.Grade;
import com.softcraft.ohhsansibackend.domain.repository.abstraction.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeDomainService {
    private final GradeRepository gradeRepository;

    @Autowired
    public GradeDomainService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public void createGrade(Grade grade) {
        gradeRepository.save(grade);
    }

    public void updateGrade(Grade grade) {
        gradeRepository.update(grade);
    }

    public void deleteGrade(Long id) {
        gradeRepository.delete(id);
    }

    public Optional<Grade> findGradeById(Long id) {
        return gradeRepository.findById(id);
    }

    public List<Grade> getGrades() {
        return gradeRepository.findAll();
    }
}
