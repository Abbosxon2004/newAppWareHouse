package uz.pdp.online.newappwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.newappwarehouse.entity.*;
import uz.pdp.online.newappwarehouse.payload.OutputDto;
import uz.pdp.online.newappwarehouse.payload.Result;
import uz.pdp.online.newappwarehouse.repository.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    public String generateCode() {
        final List<Output> outputList = outputRepository.findAll();
        int size = outputList.size();
        String code = String.valueOf(size + 1);
        return code;
    }

    public Result addOutputService(OutputDto outputDto) {

        final Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Warehouse not found", false);

        final Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent())
            return new Result("Client not found", false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Currency not found", false);


       Output output = new Output();
        output.setCode(generateCode());
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setWarehouse(optionalWarehouse.get());
        output.setClient(optionalClient.get());
        output.setCurrency(optionalCurrency.get());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(outputDto.getDate()));
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        output.setDate(timestamp);

        outputRepository.save(output);
        return new Result("Output service saved", true);
    }

    public Result getOutput() {
        List<Output> outputList = outputRepository.findAll();
        return new Result("Output list found", true, outputList);
    }

    public Result getOutputById(Integer id) {
        final Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent())
            return new Result("Output id not found", false);
        return new Result("Output id found", true, optionalOutput.get());
    }

    public Result editOutput(Integer id, OutputDto outputDto) {

        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent())
            return new Result("Output id not found",false);
        Output output = optionalOutput.get();
        final Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Warehouse not found", false);

        final Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent())
            return new Result("Client not found", false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Currency not found", false);

        output.setFactureNumber(outputDto.getFactureNumber());
        output.setWarehouse(optionalWarehouse.get());
        output.setClient(optionalClient.get());
        output.setCurrency(optionalCurrency.get());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(outputDto.getDate()));
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        output.setDate(timestamp);

        outputRepository.save(output);
        return new Result("Output service edited", true);
    }

    public Result deleteOutput(Integer id) {
        outputRepository.deleteById(id);
        return new Result("Output deleted.", true);
    }
}
