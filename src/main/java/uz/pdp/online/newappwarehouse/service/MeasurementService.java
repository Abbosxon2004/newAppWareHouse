package uz.pdp.online.newappwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.newappwarehouse.entity.Measurement;
import uz.pdp.online.newappwarehouse.payload.Result;
import uz.pdp.online.newappwarehouse.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service  // xizmat ko`rsatuvchi class
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;

    public Result addMeasurementService(Measurement measurement) {
        boolean existsByName = measurementRepository.existsByName(measurement.getName());
        if (existsByName)
            return new Result("Bunday o`lchov birligi mavjud", false);
        measurementRepository.save(measurement);
        return new Result("Muvaffaqiyatli saqlandi", true);
    }

    public Result getMeasurements() {
        final List<Measurement> measurementList = measurementRepository.findAll();
        return new Result("Measurement list found.", true, measurementList);
    }

    public Result getMeasurementById(Integer id) {
        final Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent())
            return new Result("Measurement id not found", false);
        return new Result("Measurement id found.", true, optionalMeasurement.get());
    }

    public Result editMeasurement(Measurement measurement, Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (measurement.getName() != null) {
            if (!optionalMeasurement.isPresent())
                return new Result("Measurement id not found", false);
            Measurement editMeasurement = optionalMeasurement.get();
            if (editMeasurement.getName() != measurement.getName() && measurementRepository.existsByName(measurement.getName()))
                return new Result("New name already exists.", false);
            editMeasurement.setName(measurement.getName());
            measurementRepository.save(editMeasurement);
            return new Result("Measurement edited.",true);
        }
        return new Result("Measurement didn't edited",false);
    }

    public Result deleteMeasurement(Integer id){
        measurementRepository.deleteById(id);
        return new Result("Measurement deleted.",true);
    }
}
