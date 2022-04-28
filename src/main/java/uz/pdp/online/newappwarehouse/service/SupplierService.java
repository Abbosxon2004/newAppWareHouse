package uz.pdp.online.newappwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.newappwarehouse.entity.Supplier;
import uz.pdp.online.newappwarehouse.payload.Result;
import uz.pdp.online.newappwarehouse.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public Result addSupplier(Supplier supplier) {
        boolean existsByName = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (existsByName)
            return new Result("Bunday telefon raqami mavjud", false);
        supplierRepository.save(supplier);
        return new Result("Muvaffaqiyatli saqlandi", true);
    }

    public Result getSuppliers() {
        final List<Supplier> supplierList = supplierRepository.findAll();
        return new Result("Supplier list found.", true, supplierList);
    }

    public Result getSupplierById(Integer id) {
        final Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent())
            return new Result("Supplier id not found", false);
        return new Result("Supplier id found.", true, optionalSupplier.get());
    }

    public Result editSupplier(Integer id, Supplier supplier) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (supplier.getName() != null) {
            if (!optionalSupplier.isPresent())
                return new Result("Supplier id not found", false);
            Supplier editSupplier = optionalSupplier.get();
            if (supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber()))
                return new Result("New phone number already exists.", false);
            editSupplier.setName(supplier.getName());
            editSupplier.setPhoneNumber(supplier.getPhoneNumber());
            supplierRepository.save(editSupplier);
            return new Result("Supplier edited.", true);
        }
        return new Result("Supplier didn't edited", false);
    }

    public Result deleteSupplier(Integer id) {
        supplierRepository.deleteById(id);
        return new Result("Supplier deleted.", true);
    }
}
