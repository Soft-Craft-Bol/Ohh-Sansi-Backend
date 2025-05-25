package com.softcraft.ohhsansibackend.comprobantepago.infraesstructure;

import com.softcraft.ohhsansibackend.comprobantepago.application.ComprobantePagoAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/rechazados")
    public ResponseEntity<List<Map<String,Object>>> getComprobantesPagoRechazados() {
        return ResponseEntity.ok(comprobantePagoService.getComprobantesPagoRechazados());
    }
}
