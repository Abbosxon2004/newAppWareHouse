package uz.pdp.online.newappwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.newappwarehouse.entity.Warehouse;
import uz.pdp.online.newappwarehouse.payload.Result;
import uz.pdp.online.newappwarehouse.service.WarehouseService;

@RestController
@RequestMapping(value = "/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;

    @PostMapping
    public Result addWarehouse(Warehouse warehouse) {
        final Result result = warehouseService.addWarehouse(warehouse);
        return result;
    }

    @GetMapping
    public Result getWarehouse() {
        final Result resultWarehouses = warehouseService.getWarehouses();
        return resultWarehouses;
    }

    @GetMapping("/warehouseId/{id}")
    public Result getCurrencyById(@PathVariable Integer id) {
        final Result resultWarehouseById = warehouseService.getWarehouseById(id);
        return resultWarehouseById;
    }

    @PutMapping("/warehouseId/{id}")
    public Result editWarehouse(@PathVariable Integer id, Warehouse warehouse) {
        final Result result = warehouseService.editWarehouse(id, warehouse);
        return result;
    }

    @DeleteMapping("/warehouseId/{id}")
    public Result deleteWarehouse(@PathVariable Integer id) {
        final Result result = warehouseService.deleteWarehouse(id);
        return result;
    }
}
