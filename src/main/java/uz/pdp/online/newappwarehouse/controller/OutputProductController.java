package uz.pdp.online.newappwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.newappwarehouse.payload.OutputProductDto;
import uz.pdp.online.newappwarehouse.payload.Result;
import uz.pdp.online.newappwarehouse.service.OutputProductService;

import java.text.ParseException;

@RestController
@RequestMapping(value = "/outputProduct")
public class OutputProductController {
    @Autowired
    OutputProductService outputProductService;

    @PostMapping
    public Result add(OutputProductDto outputProductDto) throws ParseException {
        final Result result = outputProductService.add(outputProductDto);
        return result;
    }

    @GetMapping
    public Result getSSS() {
        final Result result = outputProductService.getSSS();
        return result;
    }

    @GetMapping("/byId/{id}")
    public Result getById(@PathVariable Integer id) {
        final Result result = outputProductService.getById(id);
        return result;
    }

    @PutMapping("/byId/{id}")
    public Result edit(@RequestBody OutputProductDto outputProductDto,@PathVariable Integer id) throws ParseException {
        final Result result = outputProductService.edit(id, outputProductDto);
        return result;
    }

    @DeleteMapping("/byId/{id}")
    public Result delete(@PathVariable Integer id){
        final Result result = outputProductService.delete(id);
        return result;
    }
}
