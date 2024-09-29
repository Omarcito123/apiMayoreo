package com.mayoreo.cojutepeque.service.impl;

import com.mayoreo.cojutepeque.domain.entity.Products;
import com.mayoreo.cojutepeque.domain.entity.Ventas;
import com.mayoreo.cojutepeque.domain.repository.ProductoRepository;
import com.mayoreo.cojutepeque.domain.repository.VentaRepository;
import com.mayoreo.cojutepeque.model.ResponseObject;
import com.mayoreo.cojutepeque.service.IVentaService;
import com.mayoreo.cojutepeque.utils.MetodosGenericos;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService {

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;

    public VentaService(VentaRepository ventaRepository, ProductoRepository productoRepository) {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public ResponseObject createVenta(Ventas[] ventaList) {
        ResponseObject response = new ResponseObject();
        MetodosGenericos fechaSal = new MetodosGenericos();

        try {
            if(ventaList != null) {
                Products producto;
                for (Ventas venta: ventaList) {
                    producto = productoRepository.findProductByName(venta.getProductname());

                    if(producto != null) {
                        if(producto.getExistence() >= venta.getQuantity()) {
                            venta.setDateadd(fechaSal.getFecha());
                            venta.setExistence(producto.getExistence() - venta.getQuantity());
                            ventaRepository.save(venta);
                            double existencia = producto.getExistence() - venta.getQuantity();
                            producto.setExistence(existencia);
                            producto.setDatemod(fechaSal.getFecha());
                            producto.setIdusermod(venta.getIdusermod());
                            productoRepository.save(producto);

                            response.setSuccess(true);
                            response.setMessage("Venta creada exitosamente");
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
    public ResponseObject getVentasByDate(Ventas venta) {
        ResponseObject response = new ResponseObject();

        try {
            List<Ventas> ventasList = ventaRepository.getVentasByDate(venta.getDateadd());
            response.setData(ventasList);
            response.setSuccess(true);
            response.setMessage("Ventas del dia cargadas correctamente");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseObject getVentasMensuales(Ventas venta) {
        ResponseObject response = new ResponseObject();
        List<Ventas> ventasList = new ArrayList<>();

        try {
            String[] fecha = venta.getDatemod().split(" ");
            String anio = fecha[1];
            List<Object[]> ventasObjectList = ventaRepository.getVentasMensuales(venta.getDateadd(), anio);

            for (int i = 0; i < ventasObjectList.size(); i++) {
                Ventas ventaObj = new Ventas();
                Object[] obj = ventasObjectList.get(i);
                ventaObj.setTotalsaleprice(obj[0] == null ? 0 : Double.parseDouble(obj[0].toString()));
                ventaObj.setGanancia(obj[1] == null ? 0 : Double.parseDouble(obj[1].toString()));
                ventaObj.setDateadd(obj[2].toString());
                ventasList.add(ventaObj);
            }

            response.setData(ventasList);
            response.setSuccess(true);
            response.setMessage("Ventas mensuales cargadas correctamente");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseObject deleteVentaById(Ventas venta) {
        ResponseObject response = new ResponseObject();
        MetodosGenericos fechaSal = new MetodosGenericos();

        try {
            Products producto = productoRepository.findProductByName(venta.getProductname());

            if(producto != null) {
                ventaRepository.deleteById(venta.getIdventa());
                double existencia = producto.getExistence() + venta.getQuantity();
                producto.setExistence(existencia);
                producto.setDatemod(fechaSal.getFecha());
                producto.setIdusermod(venta.getIdusermod());
                productoRepository.save(producto);
                response.setData(null);
                response.setSuccess(true);
                response.setMessage("Ventas eliminada correctamente");
            }
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error " + e.getMessage());
        }

        return response;
    }
}
