package uz.pdp.online.newappwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.newappwarehouse.payload.InputDto;
import uz.pdp.online.newappwarehouse.payload.Result;
import uz.pdp.online.newappwarehouse.service.InputService;

@RestController
@RequestMapping(value = "/input")
public class InputController {
    @Autowired
    InputService inputService;

    @PostMapping
    public Result addInput(InputDto inputDto) {
        final Result result = inputService.addInputService(inputDto);
        return result;
    }

    @GetMapping
    public Result getInput() {
        final Result resultInput = inputService.getInput();
        return resultInput;
    }

    @GetMapping("/inputById/{id}")
    public Result getInputId(@PathVariable Integer id){
        final Result resultInputById = inputService.getInputById(id);
        return resultInputById;
    }

    @PutMapping("/inputId/{id}")
    public Result editInput(@PathVariable Integer id,@RequestBody InputDto inputDto){
        final Result result = inputService.editInput(id, inputDto);
        return result;

    }

    @DeleteMapping("/inputId/{id}")
    public Result deleteInput(@PathVariable Integer id){
        final Result result = inputService.deleteInput(id);
        return result;
    }

}
