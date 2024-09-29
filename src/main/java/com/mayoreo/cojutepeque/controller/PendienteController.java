package com.mayoreo.cojutepeque.controller;

import com.mayoreo.cojutepeque.domain.entity.Pendientes;
import com.mayoreo.cojutepeque.model.ResponseObject;
import com.mayoreo.cojutepeque.service.IPendienteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pendientes")
public class PendienteController {

    private final IPendienteService pendienteService;

    public PendienteController(IPendienteService pendienteService) {
        this.pendienteService = pendienteService;
    }

    @CrossOrigin
    @PostMapping("/createPedido")
    public ResponseObject createPedido(@RequestBody Pendientes[] pendienteList) {
        return pendienteService.createPedido(pendienteList);
    }

    @CrossOrigin
    @GetMapping("/getPendientesList")
    public ResponseObject getPendientesList() {
        return pendienteService.getPendientesList();
    }

    @CrossOrigin
    @GetMapping("/getPedidosList")
    public ResponseObject getPedidosList() {
        return pendienteService.getPedidosList();
    }

    @CrossOrigin
    @PostMapping("/deletePendientesList")
    public ResponseObject deletePendientesList(@RequestBody Pendientes pendiente) {
        return pendienteService.deletePendientesList(pendiente);
    }

    @CrossOrigin
    @PutMapping("/deletePendienteById")
    public ResponseObject deletePendienteById(@RequestBody Pendientes pendiente) {
        return pendienteService.deletePendienteById(pendiente);
    }
}
