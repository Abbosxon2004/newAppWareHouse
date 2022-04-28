package uz.pdp.online.newappwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.newappwarehouse.entity.Attachment;
import uz.pdp.online.newappwarehouse.entity.Category;
import uz.pdp.online.newappwarehouse.entity.Measurement;
import uz.pdp.online.newappwarehouse.entity.Product;
import uz.pdp.online.newappwarehouse.payload.ProductDto;
import uz.pdp.online.newappwarehouse.payload.Result;
import uz.pdp.online.newappwarehouse.repository.AttachmentRepository;
import uz.pdp.online.newappwarehouse.repository.CategoryRepository;
import uz.pdp.online.newappwarehouse.repository.MeasurementRepository;
import uz.pdp.online.newappwarehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MeasurementRepository measurementRepository;

    public Result addProduct(ProductDto productDto) {
        boolean existsByNameAndCategoryId = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (existsByNameAndCategoryId)
            return new Result("Bunday maxsulot ushbu kategoriyada mavjud.", false);

        //Categoryni tekshirish
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Bunday kategoriya mavjud emas", false);

        //Photoni tekshirish
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isEmpty())
            return new Result("Bunday rasm mavjud emas", false);

        //Measurementni tekshirish
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("Bunday measurement mavjud emas", false);

        //Saqlash
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCode(generateCode());//generatsiya alohida method beriladi
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        productRepository.save(product);
        return new Result("Mahsulot saqlandi", true);
    }

    public Result getProducts() {
        List<Product> productList = productRepository.findAll();
        return new Result("Product list found", true, productList);
    }

    public Result getProductById(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent())
            return new Result("Product found", true, optionalProduct.get());
        return new Result("Product id not found", false);
    }

    public Result editProduct(Integer id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new Result("Product id not found", false);
        Product product = optionalProduct.get();

        boolean existsByNameAndCategoryId = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (existsByNameAndCategoryId)
            return new Result("Bunday maxsulot ushbu kategoriyada mavjud.", false);

        //Categoryni tekshirish
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Bunday kategoriya mavjud emas", false);

        //Photoni tekshirish
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isEmpty())
            return new Result("Bunday rasm mavjud emas", false);

        //Measurementni tekshirish
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("Bunday measurement mavjud emas", false);

        //Saqlash
        product.setName(productDto.getName());
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        productRepository.save(product);
        return new Result("Product edited", true);
    }

    public Result deleteProduct(Integer id) {
        productRepository.deleteById(id);
        return new Result("Product deleted.", true);
    }

    public String generateCode() {
        List<Product> productList = productRepository.findAll();
        int size = productList.size();
        String code = String.valueOf(size + 1);
        return code;
    }
}
