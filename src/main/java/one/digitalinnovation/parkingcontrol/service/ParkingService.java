package one.digitalinnovation.parkingcontrol.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import one.digitalinnovation.parkingcontrol.exception.ParkingNotFoundException;
import one.digitalinnovation.parkingcontrol.model.Parking;
import one.digitalinnovation.parkingcontrol.repository.ParkingRepository;

@Service
public class ParkingService {

	private final ParkingRepository parkingRepository;

	public ParkingService(ParkingRepository parkingRepository) {
		this.parkingRepository = parkingRepository;
	}

	@Transactional
	public Parking save(Parking parking) {
		parking.setEntryDate(LocalDateTime.now());
		return parkingRepository.save(parking);
	}
	
	public List<Parking> findAll() {
		return parkingRepository.findAll();
	}
	
	public Parking findById(Integer id) {
		return parkingRepository.findById(id).orElseThrow(
				() -> new ParkingNotFoundException(id)); 
	}
	
	@Transactional
	public Parking update(Integer id, Parking parkingUpdate) {
		Parking parking = findById(id);
		if (parkingUpdate.getColor() != null) parking.setColor(parkingUpdate.getColor());
		if (parkingUpdate.getState() != null) parking.setState(parkingUpdate.getState());
		if (parkingUpdate.getModel() != null) parking.setModel(parkingUpdate.getModel());
		if (parkingUpdate.getLicense() != null) parking.setLicense(parkingUpdate.getLicense());
		parkingRepository.save(parking);
		return parking;
	}
	
	@Transactional
	public void delete(Integer id) {
		findById(id);
		parkingRepository.deleteById(id);
	}
	
	@Transactional
	public Parking checkOut(Integer id) {
		Parking parking = findById(id);
		parking.setExitDate(LocalDateTime.now());
		parking.setBill(ParkingCheckOut.getBill(parking));
		return parkingRepository.save(parking);
	}

}
