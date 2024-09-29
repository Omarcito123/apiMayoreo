package com.mayoreo.cojutepeque.service;

import com.mayoreo.cojutepeque.domain.entity.Ventas;
import com.mayoreo.cojutepeque.model.ResponseObject;

public interface IVentaService {

    ResponseObject createVenta(Ventas[] ventaList);

    ResponseObject getVentasByDate(Ventas venta);

    ResponseObject getVentasMensuales(Ventas venta);

    ResponseObject deleteVentaById(Ventas venta);
}
