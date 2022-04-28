package uz.pdp.online.newappwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.newappwarehouse.payload.OutputDto;
import uz.pdp.online.newappwarehouse.payload.Result;
import uz.pdp.online.newappwarehouse.service.OutputService;

@RestController
@RequestMapping(value = "/output")
public class OutputController {
    @Autowired
    OutputService outputService;

    @PostMapping
    public Result addOutput(OutputDto outputDto) {
        final Result result = outputService.addOutputService(outputDto);
        return result;
    }

    @GetMapping
    public Result getOutput() {
        final Result resultOutput = outputService.getOutput();
        return resultOutput;
    }

    @GetMapping("/outputById/{id}")
    public Result getOutputId(@PathVariable Integer id){
        final Result resultOutputById = outputService.getOutputById(id);
        return resultOutputById;
    }

    @PutMapping("/outputId/{id}")
    public Result editOutput(@PathVariable Integer id,@RequestBody OutputDto outputDto){
        final Result result = outputService.editOutput(id, outputDto);
        return result;

    }

    @DeleteMapping("/outputId/{id}")
    public Result deleteOutput(@PathVariable Integer id){
        final Result result = outputService.deleteOutput(id);
        return result;
    }

}
