package com.softcraft.ohhsansibackend.comprobantepago.infraesstructure;

import com.softcraft.ohhsansibackend.comprobantepago.application.ComprobantePagoAppService;
import com.softcraft.ohhsansibackend.comprobantepago.domain.model.ComprobantePago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comprobante-pago")
public class ComprobantePagoAppController {
    private final ComprobantePagoAppService comprobantePagoService;
    @Autowired
    public ComprobantePagoAppController(ComprobantePagoAppService comprobantePagoService) {
        this.comprobantePagoService = comprobantePagoService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Map<String,Object>>> getComprobantesPago() {
        return ResponseEntity.ok(comprobantePagoService.getComprobantesPago());
    }

    @PutMapping("/{idComprobantePago}/estado")
    public ResponseEntity<Map<String, Object>> cambiarEstadoComprobantePago(
            @PathVariable int idComprobantePago,
            @RequestParam("nuevoEstado") int nuevoEstadoId) {
        Map<String, Object> response = comprobantePagoService.cambiarEstadoComprobantePago(idComprobantePago, nuevoEstadoId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findByOlimpiada/{idOlimpiada}")
    public ResponseEntity<Map<String, Object>> findComprobantePagoByOlimpiada(@PathVariable int idOlimpiada) {
        Map<String, Object> response = comprobantePagoService.findComprobantePagoByIdOlimpiada(idOlimpiada);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/comprobante-info/{codUnicoParticipante}")
    public ResponseEntity<Map<String,Object>> getComprobanteInfoByCiParticipante(@PathVariable String codUnicoParticipante) {
        Map<String, Object> response = comprobantePagoService.sendInfoForComprobantePago(codUnicoParticipante);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/comprobante/")
    public ResponseEntity<Map<String, Object>> insertComprobantePago(
            @RequestBody ComprobantePago comprobantePago
            ){
        Map<String,Object> response = new HashMap<>();
        return null;
        //notas adicionales
        //

    }

    //TODO: En la orden de pago
}
