package com.softcraft.ohhsansibackend.tutor.application.usecases;

import com.softcraft.ohhsansibackend.tutor.application.ports.TipoTutorAdapter;
import com.softcraft.ohhsansibackend.tutor.domain.models.TipoTutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TipoTutorService {
    private final TipoTutorAdapter tipoTutorAdapter;
    private final Map<String, Object> response;

    @Autowired
    public TipoTutorService(TipoTutorAdapter tipoTutorAdapter) {
        this.tipoTutorAdapter = tipoTutorAdapter;
        this.response = new HashMap<>();
    }

    public Map<String, Object> save(TipoTutor tipoTutor) {
        response.clear();
        try {
            tipoTutorAdapter.save(tipoTutor);
            response.put("message", "Tipo de tutor registrado exitosamente");
        } catch (Exception e) {
            throw new RuntimeException("Error al registrar el tipo de tutor", e);
        }
        return response;
    }

    public Map<String, Object> update(TipoTutor tipoTutor) {
        response.clear();
        try {
            tipoTutorAdapter.update(tipoTutor);
            response.put("message", "Tipo de tutor actualizado exitosamente");
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el tipo de tutor", e);
        }
        return response;
    }

    public Map<String, Object> delete(int idTipoTutor) {
        response.clear();
        try {
            tipoTutorAdapter.delete(idTipoTutor);
            response.put("message", "Tipo de tutor eliminado exitosamente");
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el tipo de tutor", e);
        }
        return response;
    }

    public Map<String, Object> findByIdTipoTutor(int idTipoTutor) {
        response.clear();
        try {
            TipoTutor tipoTutor = tipoTutorAdapter.findByIdTipoTutor(idTipoTutor);
            response.put("tipoTutor", tipoTutor);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error al obtener el tipo de tutor");
            response.put("error", e.getMessage());
        }
        return response;
    }

    public Map<String, Object> findAllTipoTutor() {
        response.clear();
        try {
            response.put("tipoTutores", tipoTutorAdapter.findAllTipoTutor());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de tipos de tutor", e);
        }
        return response;
    }
}