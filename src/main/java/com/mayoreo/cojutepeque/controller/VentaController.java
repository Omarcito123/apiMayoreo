package com.mayoreo.cojutepeque.controller;

import com.mayoreo.cojutepeque.domain.entity.Ventas;
import com.mayoreo.cojutepeque.model.ResponseObject;
import com.mayoreo.cojutepeque.service.IVentaService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/ventas")
public class VentaController {

    private final IVentaService ventaService;

    public VentaController(IVentaService ventaService) {
        this.ventaService = ventaService;
    }

    @CrossOrigin
    @PostMapping("/createVenta")
    public ResponseObject createVenta(@RequestBody Ventas[] ventaList) {
        return ventaService.createVenta(ventaList);
    }

    @CrossOrigin
    @PostMapping("/getVentasByDate")
    public ResponseObject getVentasByDate(@RequestBody Ventas venta) {
        return ventaService.getVentasByDate(venta);
    }

    @CrossOrigin
    @PostMapping("/getVentasMensuales")
    public ResponseObject getVentasMensuales(@RequestBody Ventas venta) {
        return ventaService.getVentasMensuales(venta);
    }

    @CrossOrigin
    @PutMapping("/deleteVentaById")
    public ResponseObject deleteVentaById(@RequestBody Ventas venta) {
        return ventaService.deleteVentaById(venta);
    }
}
