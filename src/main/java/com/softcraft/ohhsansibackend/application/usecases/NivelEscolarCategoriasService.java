package com.softcraft.ohhsansibackend.nivelescolar.application.usecases;

import com.softcraft.ohhsansibackend.nivelescolar.application.ports.NivelEscolarCategoriasAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NivelEscolarCategoriasService {
    private final NivelEscolarCategoriasAdapter nivelEscolarCategoriasAdapter;

    @Autowired
    public NivelEscolarCategoriasService(NivelEscolarCategoriasAdapter nivelEscolarCategoriasAdapter) {
        this.nivelEscolarCategoriasAdapter = nivelEscolarCategoriasAdapter;
    }

    public NivelEscolarCategoriasAdapter getNivelEscolarCategoriasAdapter() {
        return nivelEscolarCategoriasAdapter;
    }
}
