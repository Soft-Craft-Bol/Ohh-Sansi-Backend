package com.softcraft.ohhsansibackend.application.usecases;

import com.softcraft.ohhsansibackend.domain.models.Grade;
import com.softcraft.ohhsansibackend.domain.services.GradeDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {
    private final GradeDomainService gradeDomainService;

    public GradeService(GradeDomainService gradeDomainService) {
        this.gradeDomainService = gradeDomainService;
    }

    public void saveGrade(Grade grade) {
        gradeDomainService.createGrade(grade);
    }

    public void updateGrade(Grade grade) {
        gradeDomainService.updateGrade(grade);
    }

    public void deleteGrade(Long id) {
        gradeDomainService.deleteGrade(id);
    }

    public Optional<Grade> findGradeById(Long id) {
        return gradeDomainService.findGradeById(id);
    }

    public List<Grade> getGrades() {
        return gradeDomainService.getGrades();
    }
}
