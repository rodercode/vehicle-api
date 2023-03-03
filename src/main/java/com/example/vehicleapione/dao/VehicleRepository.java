package com.example.vehicleapione.dao;
import com.example.vehicleapione.bean.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    List<Vehicle> getVehiclesByGroupId(Long groupId);
}
