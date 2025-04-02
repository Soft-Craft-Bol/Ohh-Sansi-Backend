package com.softcraft.ohhsansibackend.tutor.application.usecases;

import com.softcraft.ohhsansibackend.exception.DuplicateResourceException;
import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.tutor.domain.models.Tutor;
import com.softcraft.ohhsansibackend.tutor.application.ports.TutorAdapter;
import com.softcraft.ohhsansibackend.tutor.domain.repository.implementation.TutorDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TutorService {
    private final TutorAdapter tutorAdapter;
    @Autowired
    public TutorService(TutorAdapter tutorAdapter) {
        this.tutorAdapter = tutorAdapter;
    }
    public Map<String, Object> save(Tutor tutor) {
        try {
            tutorAdapter.save(tutor);
        } catch (DuplicateKeyException e) {
            throw new DuplicateResourceException("Email o carnet de identidad del tutor ya registrados");
        } catch (Exception e) {
            throw new RuntimeException("Error al registrar el tutor", e);
        }
        return Map.of("message", "Tutor registrado exitosamente");
    }

    public Map<String, Object> findByIdTutor(int idTutor) {
        try {
            Tutor tutor = tutorAdapter.findByIdTutor(idTutor);
            return Map.of("tutor", tutor);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error al buscar el tutor");
        }
    }
    public Tutor findByEmailOrCarnet(String email, int carnetIdentidad) {
        Tutor tutor = tutorAdapter.findByEmail(email);
        if (tutor == null) {
            tutor = tutorAdapter.findByCarnetIdentidad(carnetIdentidad);
        }
        return tutor;
    }

    public Map<String, Object> findAllTutor() {
        List<Tutor> tutores = tutorAdapter.findAllTutor();
        if (tutores.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron tutores");
        }
        return Map.of("tutores", tutores);
    }
}
