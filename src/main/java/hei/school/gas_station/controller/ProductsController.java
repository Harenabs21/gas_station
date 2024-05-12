package hei.school.gas_station.controller;

import hei.school.gas_station.models.Products;
import hei.school.gas_station.services.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductsController {
    private final ProductsService productsService;

    @GetMapping
    public List<Products> getAllProducts() {
        return productsService.findAllProducts();
    }

    @GetMapping("{id}")
    public Products getProductByHisId(@PathVariable long id) {
        return productsService.findSingleProductByHisId(id);
    }

    @PostMapping
    public Products postNewProduct(@RequestBody Products toSave) {
        return productsService.createNewProduct(toSave);
    }
    @PutMapping
    public Products putProduct(@RequestBody Products toUpdate) {
        return productsService.updateProduct(toUpdate);
    }
}
