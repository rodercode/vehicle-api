package com.example.vehicleapione.service;
import com.example.vehicleapione.bean.Vehicle;
import com.example.vehicleapione.dao.VehicleRepository;
import com.example.vehicleapione.handler.ListEmptyException;
import com.example.vehicleapione.handler.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepo;
    @Autowired
    public VehicleService(VehicleRepository vehicleRepo) {
        this.vehicleRepo = vehicleRepo;
    }
    // Create Vehicle
    public void addVehicle(Vehicle vehicle) {
        vehicleRepo.save(vehicle);
    }
    // Get Vehicle
    public Optional<Vehicle> getVehicle(Long vehicleId){
        if (vehicleRepo.findById(vehicleId).isEmpty())
            throw new ResourceNotFoundException("Vehicle does not exist by this id");
        else
            return vehicleRepo.findById(vehicleId);
    }
    // Get Vehicles
    public List<Vehicle> getVehicles(){
        if (vehicleRepo.findAll().isEmpty())
            throw new ListEmptyException("There are none vehicles in vehicle database");
        else
            return vehicleRepo.findAll();
    }
    // Get Vehicle By GroupId
    public List<Vehicle> getVehiclesById(Long groupId){
        if (vehicleRepo.getVehiclesByGroupId(groupId).isEmpty())
            throw new ListEmptyException("Group with this id has no vehicles");
        else
            return vehicleRepo.getVehiclesByGroupId(groupId);
    }
    // Update Vehicle Group iD
    public void updateVehicleGroupId(Long vehicleId, Long groupId){
        if (vehicleRepo.findById(vehicleId).isEmpty())
            throw new ResourceNotFoundException("Vehicle does not exist by this id");
        else {
            Vehicle vehicle = vehicleRepo.findById(vehicleId).get();
            vehicle.setGroupId(groupId);
            vehicleRepo.save(vehicle);
        }
    }
    // Update Vehicle State and assigne time
    public Vehicle updateVehicleState(Long vehicleId, boolean state,int time){
        Vehicle vehicle = vehicleRepo.findById(vehicleId).get();
        vehicle.setAvailable(state);
        vehicle.setAvailableIn(time);
        vehicleRepo.save(vehicle);
        return vehicle;
    }
    // Remove Vehicle
    public void removeVehicle(Long vehicleId) {
        if (vehicleRepo.findById(vehicleId).isEmpty())
            throw new ResourceNotFoundException("Vehicle does not exist by this id");
        else
            vehicleRepo.deleteById(vehicleId);
    }
}
