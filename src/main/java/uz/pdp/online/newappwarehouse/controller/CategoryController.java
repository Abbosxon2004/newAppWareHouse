package uz.pdp.online.newappwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.newappwarehouse.payload.CategoryDto;
import uz.pdp.online.newappwarehouse.payload.Result;
import uz.pdp.online.newappwarehouse.service.CategoryService;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public Result addCategory(@RequestBody CategoryDto categoryDto) {
        Result result = categoryService.addCategory(categoryDto);
        return result;
    }

    @GetMapping
    public Result getCategories() {
        final Result resultCategories = categoryService.getCategories();
        return resultCategories;
    }

    @GetMapping("/categoryId/{id}")
    public Result getCategoryById(@PathVariable Integer id) {
        final Result resultCategoryById = categoryService.getCategoryById(id);
        return resultCategoryById;
    }

    @PutMapping("/categoryId/{id}")
    public Result editCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer id) {
        final Result result = categoryService.editCategory(categoryDto, id);
        return result;
    }

    @DeleteMapping("/category/{id}")
    public Result deleteCategory(@PathVariable Integer id) {
        final Result result = categoryService.deleteCategory(id);
        return result;
    }
}
