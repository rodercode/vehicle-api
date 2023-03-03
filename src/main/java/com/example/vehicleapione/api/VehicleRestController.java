package com.example.vehicleapione.api;
import com.example.vehicleapione.bean.Vehicle;
import com.example.vehicleapione.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("vehicles")
public class VehicleRestController {
    private final VehicleService vehicleService;
    @Autowired
    public VehicleRestController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    // Get Vehicle Rest API
//    @GetMapping("test")
//    private VehicleRoute vehicleDrive(){
//        Vehicle vehicle = new Vehicle(1L,"volvo","Pajala",true,0,0L);
//        Route route = new Route(1L, "Stockholm","Malmö",100);
//        return new VehicleRoute(vehicle,route);
//    }
//     Create Vehicle Rest API
    @PostMapping
    private ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle){
        vehicleService.addVehicle(vehicle);
        return new ResponseEntity<>(vehicle, HttpStatus.CREATED);
    }
    // Get Vehicle
    @GetMapping("{vehicleId}")
    private ResponseEntity<Vehicle> getVehicle(@PathVariable Long vehicleId){
       return ResponseEntity.ok(vehicleService.getVehicle(vehicleId).get());
    }
    // Get Vehicles
    @GetMapping
    private ResponseEntity<List<Vehicle>> getVehicles(){
        List<Vehicle> vehicles = vehicleService.getVehicles();
        return ResponseEntity.ok(vehicles);
    }
//     Get Vehicle By groupId
    @GetMapping("guilds/{groupId}")
    public ResponseEntity<List<Vehicle>> getVehiclesById(@PathVariable Long groupId){
        List<Vehicle> vehicleList = vehicleService.getVehiclesById(groupId);
        return ResponseEntity.ok(vehicleList);
    }
    // Update Vehicle Group Id
    @PutMapping("/{vehicleId}/groups/{groupId}")
    private ResponseEntity<Vehicle> updateVehicle(@PathVariable Long vehicleId,@PathVariable Long groupId){
        vehicleService.updateVehicleGroupId(vehicleId,groupId);
        return ResponseEntity.ok(vehicleService.getVehicle(vehicleId).get());
    }
    // Update Vehicle State
    @PutMapping("/{vehicleId}/state/{state}/duration/{time}")
    private ResponseEntity<Vehicle> changeVehicleState(@PathVariable Long vehicleId, @PathVariable boolean state,@PathVariable int time){
        vehicleService.updateVehicleState(vehicleId, state, time);
        return ResponseEntity.ok(vehicleService.getVehicle(vehicleId).get());
    }
    // Delete Vehicle
    @DeleteMapping("{vehicleId}")
    private ResponseEntity<String> deleteVehicle(@PathVariable Long vehicleId){
        vehicleService.removeVehicle(vehicleId);
        return ResponseEntity.ok("Vehicle was deleted");
    }
}
