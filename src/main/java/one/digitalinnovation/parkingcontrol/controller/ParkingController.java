package one.digitalinnovation.parkingcontrol.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import one.digitalinnovation.parkingcontrol.model.Parking;
import one.digitalinnovation.parkingcontrol.model.dto.ParkingCreateDTO;
import one.digitalinnovation.parkingcontrol.model.dto.ParkingDTO;
import one.digitalinnovation.parkingcontrol.model.mapper.ParkingMapper;
import one.digitalinnovation.parkingcontrol.service.ParkingService;

@RestController
@RequestMapping("/parking")
@Tag(name = "Parking Controller")
public class ParkingController {

	private final ParkingService parkingService;
	private final ParkingMapper parkingMapper;
	
	public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
		super();
		this.parkingService = parkingService;
		this.parkingMapper = parkingMapper;
	}

	@GetMapping
	@Operation(summary = "search all parked cars", description = "find all")
	public ResponseEntity<List<ParkingDTO>> findAll() {
		List<Parking> parkingList = parkingService.findAll();
		List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
		return ResponseEntity.ok().body(result);
	}
	
	@PostMapping
	@Operation(summary = "register the car in the parking lot", description = "create")
	public ResponseEntity<ParkingDTO> create(@Valid @RequestBody ParkingCreateDTO parkingDto) {
		Parking parkingCreate = parkingMapper.toParkingCreate(parkingDto);
		Parking parking = parkingService.save(parkingCreate);
		ParkingDTO result = parkingMapper.toParkingDTO(parking);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "find parked car by id", description = "find by id")
	public ResponseEntity<ParkingDTO> findById(@PathVariable Integer id) {
		Parking parking = parkingService.findById(id);
		ParkingDTO result = parkingMapper.toParkingDTO(parking);
		return ResponseEntity.ok().body(result);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "update the registration", description = "update")
	public ResponseEntity<ParkingDTO> update(@PathVariable Integer id, @RequestBody ParkingCreateDTO parkingUpdate) {
		Parking parkingCreate = parkingMapper.toParkingCreate(parkingUpdate);
		Parking parking = parkingService.update(id, parkingCreate);
		return ResponseEntity.ok().body(parkingMapper.toParkingDTO(parking));
	}
	
	@PostMapping("/{id}/exit")
	@Operation(summary = "records the time of departure from the parking lot", description = "check out")
	public ResponseEntity<ParkingDTO> checkOut(@PathVariable Integer id) {
		Parking parking = parkingService.checkOut(id);
		return ResponseEntity.ok().body(parkingMapper.toParkingDTO(parking));
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "delete the record", description = "delete")
	public ResponseEntity<ParkingDTO> delete(@PathVariable Integer id) {
		parkingService.findById(id);
		parkingService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
