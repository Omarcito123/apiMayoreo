package com.mayoreo.cojutepeque.service;

import com.mayoreo.cojutepeque.domain.entity.Products;
import com.mayoreo.cojutepeque.model.ResponseObject;

public interface IProductoService {
    ResponseObject getListProducts();

    ResponseObject saveProduct(Products products);

    ResponseObject updateProduct(Products products);

    ResponseObject deleteProduct(Products products);

    ResponseObject findProductByName(Products products);
}
