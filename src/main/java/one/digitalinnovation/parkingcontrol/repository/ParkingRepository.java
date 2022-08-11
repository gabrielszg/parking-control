package one.digitalinnovation.parkingcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import one.digitalinnovation.parkingcontrol.model.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Integer> {

}
