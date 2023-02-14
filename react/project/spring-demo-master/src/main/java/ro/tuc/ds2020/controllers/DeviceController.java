package ro.tuc.ds2020.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.NewDeviceDTO;
import ro.tuc.ds2020.exceptions.ApiExceptionResponse;
import ro.tuc.ds2020.services.DeviceService;


@RestController
@RequestMapping("/device")
@CrossOrigin
public class DeviceController {

    //injectam serviceul
    private final DeviceService deviceService;
    //private final JavaMailSender javaMailSender;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
        //this.javaMailSender = javaMailSender;
    }

    @ApiOperation(value = "Return a list of all clients")
    @GetMapping()
    public ResponseEntity findAllDevices(){
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity findDeviceById(@ApiParam(value = "Requires an id") @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.findDeviceById(id));
    }

    @GetMapping("/clientDevice/{clientId}")
    public ResponseEntity findDeviceByClientId(@ApiParam(value = "Requires an id") @PathVariable Long clientId){
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.findDevicesByClient(clientId));
    }

    @PutMapping()
    public ResponseEntity updateDeviceInfo(@RequestBody DeviceDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.updateDevice(dto));
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity deleteDeviceInfo(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.deleteDevice(id));
    }

    @PostMapping("/newDevice")
    public ResponseEntity newDevice(@RequestBody NewDeviceDTO dto) throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.addDevice(dto));
    }

}
