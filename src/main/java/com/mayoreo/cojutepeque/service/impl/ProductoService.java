package com.mayoreo.cojutepeque.service.impl;

import com.mayoreo.cojutepeque.domain.entity.Products;
import com.mayoreo.cojutepeque.domain.repository.ProductoRepository;
import com.mayoreo.cojutepeque.model.ResponseObject;
import com.mayoreo.cojutepeque.service.IProductoService;
import com.mayoreo.cojutepeque.utils.MetodosGenericos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public ResponseObject getListProducts() {
        ResponseObject response = new ResponseObject();

        try {
            List<Products> productsList = productoRepository.findAll();
            response.setData(productsList);
            response.setSuccess(true);
            response.setMessage("Productos cargados correctamente");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseObject saveProduct(Products products) {
        ResponseObject response = new ResponseObject();
        MetodosGenericos fechaSal = new MetodosGenericos();

        try {
            products.setDateadd(fechaSal.getFecha());
            Products saveProducts = productoRepository.saveAndFlush(products);
            response.setData(saveProducts);
            response.setSuccess(true);
            response.setMessage("Producto guardado correctamente");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseObject updateProduct(Products products) {
        ResponseObject response = new ResponseObject();
        MetodosGenericos fechaSal = new MetodosGenericos();

        try {
            products.setDatemod(fechaSal.getFecha());
            Products saveProducts = productoRepository.saveAndFlush(products);
            response.setData(saveProducts);
            response.setSuccess(true);
            response.setMessage("Producto modificado correctamente");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseObject deleteProduct(Products products) {
        ResponseObject response = new ResponseObject();

        try {
            productoRepository.deleteById(products.getIdproduct());
            response.setData(products);
            response.setSuccess(true);
            response.setMessage("Producto eliminado correctamente");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseObject findProductByName(Products products) {
        ResponseObject response = new ResponseObject();

        try {
            Products findProduct = productoRepository.findProductByName(products.getProductname());
            if(findProduct != null) {
                response.setData(findProduct);
                response.setSuccess(true);
                response.setMessage("Producto encontrado correctamente");
            } else  {
                response.setData(null);
                response.setSuccess(false);
                response.setMessage("Producto no encontrado");
            }

        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error " + e.getMessage());
        }
        return response;
    }
}
