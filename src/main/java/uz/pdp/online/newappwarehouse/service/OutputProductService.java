package uz.pdp.online.newappwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.newappwarehouse.entity.*;
import uz.pdp.online.newappwarehouse.payload.OutputProductDto;
import uz.pdp.online.newappwarehouse.payload.Result;
import uz.pdp.online.newappwarehouse.repository.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {
    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OutputRepository outputRepository;

    public Result add(OutputProductDto outputProductDto) throws ParseException {
        final Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Product id not found", false);

        final Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent())
            return new Result("Output id not found", false);

        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());

        outputProductRepository.save(outputProduct);
        return new Result("Output product added.", true);
    }

    public Result getSSS() {
        final List<OutputProduct> outputProductList = outputProductRepository.findAll();
        return new Result("OutputProductList found.", true, outputProductList);
    }

    public Result getById(Integer id) {
        final Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent())
            return new Result("Output product id not found", false);
        return new Result("Output product found", true, optionalOutputProduct.get());
    }

    public Result edit(Integer id,OutputProductDto outputProductDto) throws ParseException {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent())
            return new Result("Id not found",false);
        OutputProduct outputProduct = optionalOutputProduct.get();

        final Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Product id not found", false);

        final Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent())
            return new Result("Output id not found", false);

        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());

        outputProductRepository.save(outputProduct);
        return new Result("Output product edited.", true);
    }

    public Result delete(Integer id){
        outputProductRepository.deleteById(id);
        return new Result("Deleted",true);
    }
}
