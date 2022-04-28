package uz.pdp.online.newappwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.newappwarehouse.entity.Input;
import uz.pdp.online.newappwarehouse.entity.InputProduct;
import uz.pdp.online.newappwarehouse.entity.Product;
import uz.pdp.online.newappwarehouse.payload.InputProductDto;
import uz.pdp.online.newappwarehouse.payload.Result;
import uz.pdp.online.newappwarehouse.repository.InputProductRepository;
import uz.pdp.online.newappwarehouse.repository.InputRepository;
import uz.pdp.online.newappwarehouse.repository.ProductRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {
    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputRepository inputRepository;

    public Result add(InputProductDto inputProductDto) throws ParseException {
        final Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Product id not found", false);

        final Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent())
            return new Result("Input id not found", false);

        InputProduct inputProduct = new InputProduct();
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());


        Date expireDate = new SimpleDateFormat("dd.MM.yyyy").parse(inputProductDto.getExpireDate());

        inputProduct.setExpireDate(expireDate);
        inputProductRepository.save(inputProduct);
        return new Result("Inputting product added.", true);
    }

    public Result getSSS() {
        final List<InputProduct> inputProductList = inputProductRepository.findAll();
        return new Result("InputProductList found.", true, inputProductList);
    }

    public Result getById(Integer id) {
        final Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent())
            return new Result("Inputting product id not found", false);
        return new Result("Inputting product found", true, optionalInputProduct.get());
    }

    public Result edit(Integer id,InputProductDto inputProductDto) throws ParseException {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent())
            return new Result("Id not found",false);
        InputProduct inputProduct = optionalInputProduct.get();

        final Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Product id not found", false);

        final Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent())
            return new Result("Input id not found", false);

        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());


        Date expireDate = new SimpleDateFormat("dd.MM.yyyy").parse(inputProductDto.getExpireDate());

        inputProduct.setExpireDate(expireDate);
        inputProductRepository.save(inputProduct);
        return new Result("Inputting product edited.", true);
    }

    public Result delete(Integer id){
        inputProductRepository.deleteById(id);
        return new Result("Deleted",true);
    }
}
