package ro.tuc.ds2020.controllers;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.NewSensorDTO;
import ro.tuc.ds2020.dtos.SensorDTO;
import ro.tuc.ds2020.exceptions.ApiExceptionResponse;
import ro.tuc.ds2020.services.SensorService;


@RestController
@RequestMapping("/sensor")
@CrossOrigin
public class SensorController {

    //injectam serviceul
    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @ApiOperation(value = "Return a list of all clients")
    @GetMapping()
    public ResponseEntity findAllSensors(){
        return ResponseEntity.status(HttpStatus.OK).body(sensorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity findSensorById(@ApiParam(value = "Requires an id") @PathVariable Long id) throws ApiExceptionResponse{
        return ResponseEntity.status(HttpStatus.OK).body(sensorService.findSensorById(id));
    }

    @PutMapping()
    public ResponseEntity updateSensorInfo(@RequestBody SensorDTO dto)throws ApiExceptionResponse{
        return ResponseEntity.status(HttpStatus.OK).body(sensorService.updateSensor(dto));
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity deleteSensorInfo(@PathVariable Long id)throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(sensorService.deleteSensor(id));
    }

    @PostMapping("/newSensor")
    public ResponseEntity newSensor(@RequestBody NewSensorDTO dto) throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(sensorService.addSensor(dto));
    }

    @GetMapping("/clientSensorInfo/{clientId}")
    public ResponseEntity findDeviceByClientId(@ApiParam(value = "Requires an id") @PathVariable Long clientId) throws ApiExceptionResponse{
        return ResponseEntity.status(HttpStatus.OK).body(sensorService.findSensorInfoByClient(clientId));
    }

}
