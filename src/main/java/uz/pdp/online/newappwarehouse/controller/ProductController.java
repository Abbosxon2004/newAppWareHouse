package uz.pdp.online.newappwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.newappwarehouse.payload.ProductDto;
import uz.pdp.online.newappwarehouse.payload.Result;
import uz.pdp.online.newappwarehouse.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto) {
        final Result result = productService.addProduct(productDto);
        return result;
    }

    @GetMapping
    public Result get() {
        final Result resultProducts = productService.getProducts();
        return resultProducts;
    }

    @GetMapping("/byId/{id}")
    public Result get(@PathVariable Integer id) {
        final Result result = productService.getProductById(id);
        return result;
    }

    @PutMapping("/productId/{id}")
    public Result editProduct(@PathVariable Integer id,ProductDto productDto){
        final Result result = productService.editProduct(id, productDto);
        return result;
    }

    @DeleteMapping("/productId/{id}")
    public Result deleteProduct(@PathVariable Integer id){
        final Result result = productService.deleteProduct(id);
        return result;
    }
}
