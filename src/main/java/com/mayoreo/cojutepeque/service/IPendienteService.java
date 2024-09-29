package com.mayoreo.cojutepeque.service;

import com.mayoreo.cojutepeque.domain.entity.Pendientes;
import com.mayoreo.cojutepeque.model.ResponseObject;

public interface IPendienteService {

    ResponseObject createPedido(Pendientes[] pendienteList);

    ResponseObject getPendientesList();

    ResponseObject getPedidosList();

    ResponseObject deletePendientesList(Pendientes pendiente);

    ResponseObject deletePendienteById(Pendientes pendiente);
}
