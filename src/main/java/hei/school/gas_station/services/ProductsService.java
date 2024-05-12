package hei.school.gas_station.services;


import hei.school.gas_station.models.Products;
import hei.school.gas_station.repository.ProductsRepository;
import hei.school.gas_station.utils.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;

    public List<Products> findAllProducts() {
        return productsRepository.findAll();
    }

    public Products findSingleProductByHisId(long id) {
        Products product = productsRepository.findById(id);
        if (product == null) {
            throw new NotFoundException("product doesn't exist");
        }
        return product;
    }

    public Products createNewProduct(Products product) {
        return productsRepository.save(product);
    }

    public Products updateProduct(Products toUpdate) {
        Products product = productsRepository.findById(toUpdate.getId());
        if (product == null) {
            throw new NotFoundException("product doesn't exist");
        }
        return productsRepository.update(toUpdate);
    }

}
