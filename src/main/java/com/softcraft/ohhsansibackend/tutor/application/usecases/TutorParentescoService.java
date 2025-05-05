package com.softcraft.ohhsansibackend.tutor.application.usecases;

import com.softcraft.ohhsansibackend.tutor.application.ports.TutorParentescoAdapter;
import com.softcraft.ohhsansibackend.tutor.domain.models.TutorParentesco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TutorParentescoService {
    private final TutorParentescoAdapter tutorParentescoAdapter;
    private final Map<String, Object> response;

    @Autowired
    public TutorParentescoService(TutorParentescoAdapter tutorParentescoAdapter){
        this.tutorParentescoAdapter = tutorParentescoAdapter;
        this.response = new HashMap<>();
    }

    public Map<String, Object> save(TutorParentesco tutorParentesco) {
        response.clear();
        try {
            tutorParentescoAdapter.save(tutorParentesco);
            response.put("message", "Parentesco de tutor registrado");
        } catch (Exception e) {
            throw new RuntimeException("Error al registrar el parentesco de tutor", e);
        }
        return response;
    }

    public Map<String, Object> update(TutorParentesco tutorParentesco) {
        response.clear();
        try {
            tutorParentescoAdapter.update(tutorParentesco);
            response.put("message", "Parentesco de tutor actualizado");
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el parentesco de tutor", e);
        }
        return response;
    }

    public Map<String, Object> delete(int idTutorParentesco) {
        response.clear();
        try {
            tutorParentescoAdapter.delete(idTutorParentesco);
            response.put("message", "Parentesco eliminado");
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el parentesco de tutor", e);
        }
        return response;
    }

    public Map<String, Object> findByIdTutorParentesco(int idTutorParentesco) {
        response.clear();
        try {
            TutorParentesco tutorParentesco = tutorParentescoAdapter.findByIdTutorParentesco(idTutorParentesco);
            response.put("tutorParentesco", tutorParentesco);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error al obtener el parentesco");
            response.put("error", e.getMessage());
        }
        return response;
    }

    public Map<String, Object> findAllParentescos() {
        response.clear();
        try {
            response.put("tutorParentescos", tutorParentescoAdapter.findAllParentescos());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener todos los parentescos", e);
        }
        return response;
    }
}
