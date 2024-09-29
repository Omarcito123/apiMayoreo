package com.mayoreo.cojutepeque.controller;

import com.mayoreo.cojutepeque.domain.entity.Products;
import com.mayoreo.cojutepeque.domain.entity.Users;
import com.mayoreo.cojutepeque.model.ResponseObject;
import com.mayoreo.cojutepeque.service.IProductoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductoController {

    private final IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @CrossOrigin
    @GetMapping("/getListProducts")
    public ResponseObject getListProducts() {
        return productoService.getListProducts();
    }

    @CrossOrigin
    @PostMapping("/saveProduct")
    public ResponseObject saveProduct(@RequestBody Products products) {
        return productoService.saveProduct(products);
    }

    @CrossOrigin
    @PutMapping("/updateProduct")
    public ResponseObject updateProduct(@RequestBody Products products) {
        return productoService.updateProduct(products);
    }

    @CrossOrigin
    @PostMapping("/findProductByName")
    public ResponseObject findProductByName(@RequestBody Products products) {
        return productoService.findProductByName(products);
    }

    @CrossOrigin
    @PostMapping("/deleteProduct")
    public ResponseObject deleteProduct(@RequestBody Products products) {
        return productoService.deleteProduct(products);
    }
}
