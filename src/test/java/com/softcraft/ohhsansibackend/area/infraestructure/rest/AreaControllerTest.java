package com.softcraft.ohhsansibackend.area.infraestructure.rest;

import com.softcraft.ohhsansibackend.area.application.usecases.AreaService;
import com.softcraft.ohhsansibackend.area.domain.models.Area;
import com.softcraft.ohhsansibackend.config.TestSecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Map;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@Import(TestSecurityConfig.class)
@ActiveProfiles("test")
public class AreaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AreaService areaService;

    @InjectMocks
    private AreaController areaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(areaController).build();
    }

    // ✅ Prueba exitosa: Crear un área correctamente
    @Test
    public void testCreateArea_Success() throws Exception {
        Area area = new Area();
        area.setIdArea(1);
        area.setNombreArea("Matemáticas");

        Map<String, Object> response = Map.of("success", true, "message", "Área creada");

        when(areaService.saveArea(any(Area.class))).thenReturn(response);

        mockMvc.perform(post("/areas/register-area")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idArea\":1,\"nombreArea\":\"Matemáticas\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Área creada"));
    }

    // ❌ Prueba fallida: Crear un área sin nombre (debe fallar por validación @Valid)
    @Test
    public void testCreateArea_Fail_MissingName() throws Exception {
        mockMvc.perform(post("/areas/register-area")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idArea\":1}"))  // No tiene nombreArea
                .andExpect(status().isBadRequest());
    }

    // ✅ Prueba exitosa: Obtener todas las áreas
    @Test
    public void testGetAllAreas_Success() throws Exception {
        Map<String, Object> response = Map.of("success", true, "data", "Lista de áreas");

        when(areaService.getAreas()).thenReturn(response);

        mockMvc.perform(get("/areas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").value("Lista de áreas"));
    }

    // ✅ Prueba exitosa: Obtener un área por ID
    @Test
    public void testGetAreaById_Success() throws Exception {
        Map<String, Object> response = Map.of("success", true, "data", Map.of("idArea", 1, "nombreArea", "Matemáticas"));

        when(areaService.findAreaById(1)).thenReturn(response);

        mockMvc.perform(get("/areas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.idArea").value(1))
                .andExpect(jsonPath("$.data.nombreArea").value("Matemáticas"));
    }

    // ❌ Prueba fallida: Obtener un área que no existe (debe retornar 404)
    @Test
    public void testGetAreaById_NotFound() throws Exception {
        when(areaService.findAreaById(999)).thenReturn(Map.of("success", false, "message", "Área no encontrada"));

        mockMvc.perform(get("/areas/999"))
                .andExpect(status().isOk()) // El código de estado es 200 en este caso, pero el mensaje indica que no se encontró
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Área no encontrada"));
    }

    // ✅ Prueba exitosa: Actualizar un área
    @Test
    public void testUpdateArea_Success() throws Exception {
        Map<String, Object> response = Map.of("success", true, "message", "Área actualizada");

        when(areaService.updateArea(any(Area.class))).thenReturn(response);

        mockMvc.perform(put("/areas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idArea\":1,\"nombreArea\":\"Física\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Área actualizada"));
    }

    // ❌ Prueba fallida: Actualizar un área con nombre vacío (debe fallar)
    @Test
    public void testUpdateArea_Fail_EmptyName() throws Exception {
        mockMvc.perform(put("/areas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idArea\":1,\"nombreArea\":\"\"}")) // Nombre vacío
                .andExpect(status().isBadRequest());
    }

    // ✅ Prueba exitosa: Eliminar un área
    @Test
    public void testDeleteArea_Success() throws Exception {
        Map<String, Object> response = Map.of("success", true, "message", "Área eliminada");

        when(areaService.deleteArea(1)).thenReturn(response);

        mockMvc.perform(delete("/areas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Área eliminada"));
    }

    // ❌ Prueba fallida: Eliminar un área inexistente (debe retornar mensaje de error)
    @Test
    public void testDeleteArea_NotFound() throws Exception {
        when(areaService.deleteArea(999)).thenReturn(Map.of("success", false, "message", "Área no encontrada"));

        mockMvc.perform(delete("/areas/999"))
                .andExpect(status().isOk()) // Estado 200 con mensaje de error
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Área no encontrada"));
    }
}
