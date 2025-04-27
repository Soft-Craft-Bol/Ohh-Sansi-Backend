package com.softcraft.ohhsansibackend.grado.application.ports;

import com.softcraft.ohhsansibackend.grado.domain.models.Grade;
import com.softcraft.ohhsansibackend.grado.domain.services.GradoDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GradoAdapter {
    private final GradoDomainService gradoDomainService;

    @Autowired
    public GradoAdapter(GradoDomainService gradoDomainService) {
        this.gradoDomainService = gradoDomainService;
    }

    public Grade saveGrade(Grade grade) {
        return gradoDomainService.createGrade(grade);
    }

    public boolean updateGrade(Grade grade) {
        return gradoDomainService.updateGrade(grade);
    }

    public boolean deleteGrade(int id) {
        return gradoDomainService.deleteGrade(id);
    }

    public Grade findGradeById(int id) {
        return gradoDomainService.findGradeById(id);
    }

    public List<Grade> getGrades() {
        return gradoDomainService.getGrades();
    }

}
