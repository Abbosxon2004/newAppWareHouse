package uz.pdp.online.newappwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.newappwarehouse.entity.Currency;
import uz.pdp.online.newappwarehouse.entity.Input;
import uz.pdp.online.newappwarehouse.entity.Supplier;
import uz.pdp.online.newappwarehouse.entity.Warehouse;
import uz.pdp.online.newappwarehouse.payload.InputDto;
import uz.pdp.online.newappwarehouse.payload.Result;
import uz.pdp.online.newappwarehouse.repository.CurrencyRepository;
import uz.pdp.online.newappwarehouse.repository.InputRepository;
import uz.pdp.online.newappwarehouse.repository.SupplierRepository;
import uz.pdp.online.newappwarehouse.repository.WarehouseRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    public String generateCode() {
        final List<Input> inputList = inputRepository.findAll();
        int size = inputList.size();
        String code = String.valueOf(size + 1);
        return code;
    }

    public Result addInputService(InputDto inputDto) {

        final Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Warehouse not found", false);

        final Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("Supplier not found", false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Currency not found", false);


        Input input = new Input();
        input.setCode(generateCode());
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(inputDto.getDate()));
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        input.setDate(timestamp);

        inputRepository.save(input);
        return new Result("Input service saved", true);
    }

    public Result getInput() {
        List<Input> inputList = inputRepository.findAll();
        return new Result("Input list found", true, inputList);
    }

    public Result getInputById(Integer id) {
        final Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent())
            return new Result("Input id not found", false);
        return new Result("Input id found", true, optionalInput.get());
    }

    public Result editInput(Integer id, InputDto inputDto) {

        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent())
            return new Result("Input id not found",false);
        Input input = optionalInput.get();
        final Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Warehouse not found", false);

        final Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("Supplier not found", false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Currency not found", false);

        input.setFactureNumber(inputDto.getFactureNumber());
        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(inputDto.getDate()));
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        input.setDate(timestamp);

        inputRepository.save(input);
        return new Result("Input service edited", true);
    }

    public Result deleteInput(Integer id) {
        inputRepository.deleteById(id);
        return new Result("Input deleted.", true);
    }
}
