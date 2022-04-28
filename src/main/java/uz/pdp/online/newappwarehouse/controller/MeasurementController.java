package uz.pdp.online.newappwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.newappwarehouse.entity.Measurement;
import uz.pdp.online.newappwarehouse.payload.Result;
import uz.pdp.online.newappwarehouse.service.MeasurementService;

@RestController
@RequestMapping(value = "/measurement")
public class MeasurementController {
    @Autowired
    MeasurementService measurementService;

    @PostMapping
    public Result addMeasurementController(@RequestBody Measurement measurement) {
        final Result result = measurementService.addMeasurementService(measurement);
        return result;
    }

    @GetMapping
    public Result getMeasurements() {
        final Result result = measurementService.getMeasurements();
        return result;
    }

    @GetMapping("/measurementId/{id}")
    public Result getMeasurementById(@PathVariable Integer id) {
        Result result = measurementService.getMeasurementById(id);
        return result;
    }

    @PutMapping("/measurement/{id}")
    public Result editMeasurement(@PathVariable Integer id, @RequestBody Measurement measurement) {
        Result result = measurementService.editMeasurement(measurement, id);
        return result;
    }

    @DeleteMapping("/measurement/{id}")
    public Result deleteMeasurement(@PathVariable Integer id) {
        final Result result = measurementService.deleteMeasurement(id);
        return result;
    }
}
