package com.softcraft.ohhsansibackend.inscripcion.application.usecases;

import com.softcraft.ohhsansibackend.area.application.usecases.AreaService;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.application.usecases.CatalogoOlimpiadaService;
import com.softcraft.ohhsansibackend.inscripcion.domain.models.Inscripcion;
import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.inscripcion.application.ports.InscripcionAdapter;
import com.softcraft.ohhsansibackend.inscripcion.domain.repository.implementation.InscripcionDomainRepository;
import com.softcraft.ohhsansibackend.inscripcion.domain.services.InscripcionDomainService;
import com.softcraft.ohhsansibackend.inscripcion.infraestructure.dto.inscripcionmasiva.ExcelInscriptionDTO;
import com.softcraft.ohhsansibackend.inscripcion.infraestructure.dto.inscripcionmasiva.MasiveTutorDTO;
import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteCatalogoInscriptionService;
import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteService;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import com.softcraft.ohhsansibackend.participante.infraestructure.request.AreaCatalogoDTO;
import com.softcraft.ohhsansibackend.periodosolimpiada.application.usecases.PeriodoOlimpiadaService;
import com.softcraft.ohhsansibackend.periodosolimpiada.domain.models.PeriodoOlimpiada;
import com.softcraft.ohhsansibackend.tutor.application.usecases.TutorService;
import com.softcraft.ohhsansibackend.tutor.domain.models.Tutor;
import com.softcraft.ohhsansibackend.utils.UniqueCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

@Service
public class InscripcionService {
    private final InscripcionAdapter inscripcionAdapter;
    private final InscripcionDomainService inscripcionDomainService;
    private final UniqueCodeGenerator uniqueCodeGenerator;
    private final InscripcionDomainRepository inscripcionDomainRepository;
    private final PeriodoOlimpiadaService periodoOlimpiadaService;
    private final ParticipanteService participanteService;
    private final TutorService tutorService;
    private final CatalogoOlimpiadaService catalogoOlimpiadaService;
    private final AreaService areaService;
    private final ParticipanteCatalogoInscriptionService participanteCatalogoInscriptionService;

    @Autowired
    public InscripcionService(@Lazy InscripcionAdapter inscripcionAdapter,
                              InscripcionDomainService inscripcionDomainService,
                              UniqueCodeGenerator uniqueCodeGenerator,
                              InscripcionDomainRepository inscripcionDomainRepository,
                              PeriodoOlimpiadaService periodoOlimpiadaService,
                              @Lazy ParticipanteService participanteService,
                              @Lazy TutorService tutorService,
                              CatalogoOlimpiadaService catalogoOlimpiadaService,
                              AreaService areaService,
                              @Lazy ParticipanteCatalogoInscriptionService participanteCatalogoInscriptionService
    ) {
        this.inscripcionAdapter = inscripcionAdapter;
        this.inscripcionDomainService = inscripcionDomainService;
        this.uniqueCodeGenerator = uniqueCodeGenerator;
        this.inscripcionDomainRepository = inscripcionDomainRepository;
        this.periodoOlimpiadaService = periodoOlimpiadaService;
        this.participanteService = participanteService;
        this.tutorService = tutorService;
        this.areaService = areaService;
        this.catalogoOlimpiadaService = catalogoOlimpiadaService;
        this.participanteCatalogoInscriptionService = participanteCatalogoInscriptionService;
    }

    public Inscripcion saveInscripcion() {
        try {
            Inscripcion inscripcion = new Inscripcion();
            inscripcion.setFechaInscripcion(Date.valueOf(LocalDate.now()));
            inscripcion.setHoraInscripcion(Time.valueOf(LocalTime.now()));
            String uniqueCode = uniqueCodeGenerator.generate();
            inscripcion.setCodigoUnicoInscripcion(uniqueCode);
            return inscripcionAdapter.saveInscripcion(inscripcion);
        } catch (Exception e) {
            throw new RuntimeException("Error al registrar la inscripción: " + e.getMessage());
        }
    }

    public Inscripcion findInscripcionById(int id) {
        Inscripcion inscripcion = inscripcionAdapter.findInscripcionById(id);
        if (inscripcion == null) {
            throw new ResourceNotFoundException("Inscripcion con ID " + id + " no encontrada");
        }
        return inscripcion;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripcionAdapter.findAllInscripciones();
    }


    public int createInscripcionAndReturnId(Inscripcion inscripcion) {
        inscripcion.setFechaInscripcion(Date.valueOf(LocalDate.now()));
        inscripcion.setHoraInscripcion(Time.valueOf(LocalTime.now()));
        Inscripcion savedInscripcion = inscripcionAdapter.saveInscripcion(inscripcion);
        return savedInscripcion.getIdInscripcion();
    }
    //codigo
    public Long findIdByCodigoUnico(String codigoUnico) {
        return inscripcionAdapter.findIdByCodigoUnico(codigoUnico);
    }
    //nuevos sql
    public List<Map<String, Object>> getInscripcionById(int idInscripcion) {
        return inscripcionDomainService.getInscripcionById(idInscripcion);
    }

    public List<Map<String, Object>> getParticipantesByInscripcionId(int idInscripcion) {
        return inscripcionDomainService.getParticipantesByInscripcionId(idInscripcion);
    }

    public List<Map<String, Object>> getInscripcionAreasByInscripcionId(int idInscripcion) {
        return inscripcionDomainService.getInscripcionAreasByInscripcionId(idInscripcion);
    }

    public List<Map<String, Object>> getAreasByInscripcionId(int idInscripcion) {
        return inscripcionDomainService.getAreasByInscripcionId(idInscripcion);
    }

    public List<Map<String, Object>> getTutoresByInscripcionId(int idInscripcion) {
        return inscripcionDomainService.getTutoresByInscripcionId(idInscripcion);
    }
    public Map<String, Object> getInscripcionDetails(String codigoUnico) {
        int idInscripcion = findIdByCodigoUnico(codigoUnico).intValue();
        System.out.println("ID Inscripcion: " + idInscripcion);
        Participante participante = inscripcionAdapter.findParticipanteByIdInscripcion(idInscripcion);
        System.out.println(participante.toString());
        int edadParticipante = inscripcionAdapter.calculateEdad(participante);

        List<Map<String,Object>> areas = getAreasByInscripcionId(idInscripcion);
        if (areas.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron áreas para la inscripción con código único: " + codigoUnico);
        }
        List<Map<String,Object>> tutores = getTutoresByInscripcionId(idInscripcion);
//        if(tutores.isEmpty() && edadParticipante<15){
//            throw new ResourceNotFoundException("No se puede generar la orden de pago, necesitas al menos un tutor para terminar el registro, edad menor a 15 años: " + codigoUnico);
//        }
        return Map.of(
                "inscripcion", getInscripcionById(idInscripcion),
                "participantes", getParticipantesByInscripcionId(idInscripcion),
                "areas", areas,
                "tutores", tutores,
                "olimpiada", periodoOlimpiadaService.encontrarPeriodoInscripcionActualMap(),
                "edadParticipante", edadParticipante,
                "ordenDePagoGenerada",inscripcionAdapter.verificarEstadoOrdenPago(idInscripcion)
        );
    }
    public boolean deleteInscripcionById(int idInscripcion) {
        return inscripcionAdapter.deleteInscripcionById(idInscripcion);
    }

    public List<Map<String, Object>> getReporteInscripcionByArea(int idArea, int idOlimpiada) {
        try {
            return inscripcionDomainRepository.getReporteInscripcionByArea(idArea, idOlimpiada);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el reporte de inscripción por área: " + e.getMessage());
        }
    }
    public Map<String, Object> saveInscripcionExcelMasiva(List<ExcelInscriptionDTO> inscripcionMasiva){
        try{
            //TODO: TODAS LAS COSAS POR HACER
            //incripcion mde los tutores
            //realacion de tutores participantes
            //relacion con las areas catalogo
            //tutor area, profesores
            for(ExcelInscriptionDTO participanteDataFilaExcel: inscripcionMasiva){
                Participante participante = participanteDataFilaExcel.getParticipante();
                //inscripcion del participante
                participanteService.save(participante);
                MasiveTutorDTO masivetutor = participanteDataFilaExcel.getTutor();
                Tutor tutor = mapMasiveTutorDTOToTutorModel(masivetutor);
                List<Tutor> tutors = List.of(tutor);
                //inscripcion del tutor
                tutorService.save(tutors,participante.getCarnetIdentidadParticipante(), masivetutor.getIdTutorParentesco());
                //inscribir areas
                //TODO: get catalogo by id area
                PeriodoOlimpiada periodoOlimpiada = periodoOlimpiadaService.encontrarPeriodoInscripcionActual();
                if(periodoOlimpiada == null){
                    throw new ResourceNotFoundException("No se encontró un período de inscripción actual");
                }
                Map<String, Object> responseOfCatalogoComposition =
                        participanteCatalogoInscriptionService.
                                registerParticipantWithCatalogoComposition(
                                        participante.getCarnetIdentidadParticipante(),
                                        catalogoOlimpiadaService.getParticipantesByAreaAndOlimpiada(participanteDataFilaExcel.getAreas().getIdArea(),
                                        participanteDataFilaExcel.getAreas().getIdArea2(),
                                        periodoOlimpiada.getIdOlimpiada(),participante.getIdGrado())
                                );


            }
            //inscribir profesores
        }catch (RuntimeException e){
            return null;
        }
        return null;
    }

    private Tutor mapMasiveTutorDTOToTutorModel(MasiveTutorDTO masiveTutorDTO){
        Tutor tutor = new Tutor();
        tutor.setIdTipoTutor(masiveTutorDTO.getIdTipoTutor());
        tutor.setEmailTutor(masiveTutorDTO.getEmailTutor());
        tutor.setNombresTutor(masiveTutorDTO.getNombresTutor());
        tutor.setApellidosTutor(masiveTutorDTO.getApellidosTutor());
        tutor.setTelefono(masiveTutorDTO.getTelefono());
        tutor.setCarnetIdentidadTutor(masiveTutorDTO.getCarnetIdentidadTutor());
        tutor.setComplementoCiTutor(masiveTutorDTO.getComplementoCiTutor());
        return tutor;
    }



}
