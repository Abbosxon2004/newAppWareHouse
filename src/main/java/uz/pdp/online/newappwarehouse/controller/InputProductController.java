package uz.pdp.online.newappwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.newappwarehouse.payload.InputProductDto;
import uz.pdp.online.newappwarehouse.payload.Result;
import uz.pdp.online.newappwarehouse.service.InputProductService;

import java.text.ParseException;

@RestController
@RequestMapping(value = "/inputProduct")
public class InputProductController {
    @Autowired
    InputProductService inputProductService;

    @PostMapping
    public Result add(InputProductDto inputProductDto) throws ParseException {
        final Result result = inputProductService.add(inputProductDto);
        return result;
    }

    @GetMapping
    public Result getSSS() {
        final Result result = inputProductService.getSSS();
        return result;
    }

    @GetMapping("/byId/{id}")
    public Result getById(@PathVariable Integer id) {
        final Result result = inputProductService.getById(id);
        return result;
    }

    @PutMapping("/byId/{id}")
    public Result edit(@RequestBody InputProductDto inputProductDto,@PathVariable Integer id) throws ParseException {
        final Result result = inputProductService.edit(id, inputProductDto);
        return result;
    }

    @DeleteMapping("/byId/{id}")
    public Result delete(@PathVariable Integer id){
        final Result result = inputProductService.delete(id);
        return result;
    }
}
