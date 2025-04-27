package com.softcraft.ohhsansibackend.grado.domain.services;

import com.softcraft.ohhsansibackend.grado.domain.models.Grade;
import com.softcraft.ohhsansibackend.grado.domain.repository.abstraction.IGradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradoDomainService {
    private final IGradoRepository IGradoRepository;

    @Autowired
    public GradoDomainService(IGradoRepository IGradoRepository) {
        this.IGradoRepository = IGradoRepository;
    }

    public Grade createGrade(Grade grade) {
        return IGradoRepository.save(grade);
    }

    public boolean updateGrade(Grade grade) {
        return IGradoRepository.update(grade);
    }

    public boolean deleteGrade(int id) {
        return IGradoRepository.delete(id);
    }

    public Grade findGradeById(int id) {
        return IGradoRepository.findById(id);
    }

    public List<Grade> getGrades() {
        return IGradoRepository.findAll();
    }
}
