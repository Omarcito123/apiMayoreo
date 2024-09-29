package com.mayoreo.cojutepeque.service.impl;

import com.mayoreo.cojutepeque.domain.entity.Pendientes;
import com.mayoreo.cojutepeque.domain.entity.Products;
import com.mayoreo.cojutepeque.domain.repository.PendienteRepository;
import com.mayoreo.cojutepeque.domain.repository.ProductoRepository;
import com.mayoreo.cojutepeque.model.ResponseObject;
import com.mayoreo.cojutepeque.service.IPendienteService;
import com.mayoreo.cojutepeque.utils.MetodosGenericos;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PendienteService implements IPendienteService {

    private final PendienteRepository pendienteRepository;
    private final ProductoRepository productoRepository;

    public PendienteService(PendienteRepository pendienteRepository, ProductoRepository productoRepository) {
        this.pendienteRepository = pendienteRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public ResponseObject createPedido(Pendientes[] pendienteList) {
        ResponseObject response = new ResponseObject();
        MetodosGenericos fechaSal = new MetodosGenericos();

        try {
            if(pendienteList != null) {
                Products producto;
                for (Pendientes pendiente: pendienteList) {
                    producto = productoRepository.findProductByName(pendiente.getProductname());

                    if(producto != null) {
                        if(producto.getExistence() >= pendiente.getQuantity()) {
                            pendiente.setDateadd(fechaSal.getFecha());
                            pendiente.setExistence(producto.getExistence() - pendiente.getQuantity());
                            pendienteRepository.save(pendiente);
                            double existencia = producto.getExistence() - pendiente.getQuantity();
                            producto.setExistence(existencia);
                            producto.setDatemod(fechaSal.getFecha());
                            producto.setIdusermod(pendiente.getIdusermod());
                            productoRepository.save(producto);

                            response.setSuccess(true);
                            response.setMessage("Pendiente creada exitosamente");
                        }else {
                            response.setSuccess(false);
                            response.setMessage("No hay suficientes productos disponibles");
                        }
                    }
                }
            }
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseObject getPendientesList() {
        ResponseObject response = new ResponseObject();
        List<Pendientes> pendientesList = new ArrayList<>();

        try {
            List<Object[]> objectsList = pendienteRepository.getPendientesList();

            for (int i = 0; i < objectsList.size(); i++) {
                Pendientes pendienteObj = new Pendientes();
                Object[] obj = objectsList.get(i);
                pendienteObj.setNombredeudor(obj[2].toString());
                pendienteObj.setTotalsaleprice(obj[0] == null ? 0 : Double.parseDouble(obj[0].toString()));
                pendienteObj.setGanancia(obj[1] == null ? 0 : Double.parseDouble(obj[1].toString()));
                pendienteObj.setDateadd(obj[3].toString());
                pendientesList.add(pendienteObj);
            }

            response.setData(pendientesList);
            response.setSuccess(true);
            response.setMessage("Pendientes cargados correctamente");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseObject getPedidosList() {
        ResponseObject response = new ResponseObject();

        try {
            List<Pendientes> objectsList = pendienteRepository.findAll();
            response.setData(objectsList);
            response.setSuccess(true);
            response.setMessage("Pendientes cargados correctamente");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseObject deletePendientesList(Pendientes pendiente) {
        ResponseObject response = new ResponseObject();
        MetodosGenericos fechaSal = new MetodosGenericos();

        try {
            List<Pendientes> pendientesList = pendienteRepository.findList(pendiente.getNombredeudor(), pendiente.getDateadd());

            for (Pendientes item : pendientesList) {
                Products producto = productoRepository.findProductByName(item.getProductname());

                if(producto != null) {
                    pendienteRepository.deleteById(item.getIdpendiente());
                    double existencia = producto.getExistence() + item.getQuantity();
                    producto.setExistence(existencia);
                    producto.setDatemod(fechaSal.getFecha());
                    producto.setIdusermod(pendiente.getIdusermod());
                    productoRepository.save(producto);
                    response.setData(null);
                    response.setSuccess(true);
                    response.setMessage("Pendientes eliminado correctamente");
                }
            }
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseObject deletePendienteById(Pendientes pendiente) {
        ResponseObject response = new ResponseObject();
        MetodosGenericos fechaSal = new MetodosGenericos();

        try {
            Products producto = productoRepository.findProductByName(pendiente.getProductname());

            if(producto != null) {
                pendienteRepository.deleteById(pendiente.getIdpendiente());
                double existencia = producto.getExistence() + pendiente.getQuantity();
                producto.setExistence(existencia);
                producto.setDatemod(fechaSal.getFecha());
                producto.setIdusermod(pendiente.getIdusermod());
                productoRepository.save(producto);
                response.setData(null);
                response.setSuccess(true);
                response.setMessage("Pendientes eliminado correctamente");
            }
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error " + e.getMessage());
        }

        return response;
    }
}
