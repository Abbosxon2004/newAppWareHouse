package uz.pdp.online.newappwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.newappwarehouse.entity.Supplier;
import uz.pdp.online.newappwarehouse.payload.Result;
import uz.pdp.online.newappwarehouse.service.SupplierService;

@RestController
@RequestMapping(value = "/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @PostMapping
    public Result addSupplier(Supplier supplier) {
        final Result result = supplierService.addSupplier(supplier);
        return result;
    }

    @GetMapping
    public Result getSuppliers() {
        final Result resultSupplier = supplierService.getSuppliers();
        return resultSupplier;
    }

    @GetMapping("/supplierId/{id}")
    public Result getSupplierById(@PathVariable Integer id) {
        final Result resultSupplierById = supplierService.getSupplierById(id);
        return resultSupplierById;
    }

    @PutMapping("/supplierId/{id}")
    public Result editSupplier(@PathVariable Integer id, Supplier supplier) {
        final Result result = supplierService.editSupplier(id, supplier);
        return result;
    }

    @DeleteMapping("/supplierId/{id}")
    public Result deleteSupplier(@PathVariable Integer id) {
        final Result result = supplierService.deleteSupplier(id);
        return result;
    }
}
