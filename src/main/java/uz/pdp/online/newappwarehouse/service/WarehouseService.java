package uz.pdp.online.newappwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.newappwarehouse.entity.Warehouse;
import uz.pdp.online.newappwarehouse.payload.Result;
import uz.pdp.online.newappwarehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addWarehouse(Warehouse warehouse){
        final boolean existsByName = warehouseRepository.existsByName(warehouse.getName());
        if (existsByName)
            return new Result("This warehouse name already exists",false);
        warehouseRepository.save(warehouse);
        return new Result("Warehouse added",true);
    }

    public Result getWarehouses(){
        final List<Warehouse> warehouseList = warehouseRepository.findAll();
        return new Result("Warehouses list found",true,warehouseList);
    }

    public Result getWarehouseById(Integer id){
        final Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent())
            return new Result("Warehouse not found",false);
        final Warehouse warehouse = optionalWarehouse.get();
        return new Result("Warehouse found.",true,warehouse);
    }

    public Result editWarehouse(Integer id,Warehouse warehouse){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent())
            return new Result("Warehouse id not found",false);
        Warehouse editingWarehouse = optionalWarehouse.get();
        final boolean existsByName = warehouseRepository.existsByName(warehouse.getName());
        if (existsByName)
            return new Result("This name already exists",false);
        editingWarehouse.setName(warehouse.getName());
        return new Result("Warehouse edited.",true);
    }

    public Result deleteWarehouse(Integer id){
        warehouseRepository.deleteById(id);
        return new Result("Warehouse deleted",true);
    }
}
