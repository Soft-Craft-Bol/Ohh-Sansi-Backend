package com.softcraft.ohhsansibackend.ordenPago.infraestructure.rest;

import com.softcraft.ohhsansibackend.ordenPago.application.usecases.OrdenPagoService;
import com.softcraft.ohhsansibackend.ordenPago.domain.models.OrdenDePago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orden-pago")
public class OrdenPagoController {

    private final OrdenPagoService ordenPagoService;

    @Autowired
    public OrdenPagoController(OrdenPagoService ordenPagoService) {
        this.ordenPagoService = ordenPagoService;
    }

    @PostMapping
    public ResponseEntity<OrdenDePago> insertarOrdenDePago(@RequestBody OrdenDePago ordenDePago) {
        OrdenDePago nuevaOrden = ordenPagoService.insertarOrdenDePago(ordenDePago);
        return new ResponseEntity<>(nuevaOrden, HttpStatus.CREATED);
    }

    @GetMapping("/inscripcion/{codUnico}")
    public ResponseEntity<List<OrdenDePago>> obtenerOrdenesPorIdInscripcion(@PathVariable String codUnico) {
        List<OrdenDePago> ordenes = ordenPagoService.obtenerOrdenesPorIdInscripcion(codUnico);
        return ResponseEntity.ok(ordenes);
    }
}