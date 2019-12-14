/**
 * 
 */
package com.mystyle.aircraft.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mystyle.aircraft.service.AircraftService;
import com.mystyle.model.AircraftDetails;

/**
 * @author chandan
 *
 */
@RestController
@RequestMapping(value = "service/")
public class AircraftController {
	@Autowired
	AircraftService aircraftService;

	@PostMapping(value = "upload")
	public List<AircraftDetails> fileUploaded(MultipartFile file) throws IOException {
		Path path = Paths.get(multipartToFile(file).toString());
		List<String> lines = null;
		try {

			lines = Files.readAllLines(path);

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return aircraftService.saveAircraftObject(lines);
	}

	public static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
		File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + multipart.getOriginalFilename());
		multipart.transferTo(convFile);
		return convFile;
	}

	@GetMapping(value = "findAll")
	public List<AircraftDetails> findAll() {

		return aircraftService.findAll();
	}

	@PutMapping("update")
	public ResponseEntity<?> updateAircraftDetails(@RequestBody AircraftDetails aircraft) {
		AircraftDetails existAircraft = aircraftService.findByFid(aircraft.getFiid());
		if (existAircraft != null && !existAircraft.getAircraftName().equals(aircraft.getAircraftName())) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(aircraftService.updateAircraftDetails(aircraft), HttpStatus.CREATED);
	}

	@PostMapping("add")
	public ResponseEntity<?> addAircraftDetails(@RequestBody AircraftDetails aircraft) {
		AircraftDetails existAircraft = aircraftService.findByFid(aircraft.getFiid());
		if (existAircraft != null && !existAircraft.getAircraftName().equals(aircraft.getAircraftName())) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(aircraftService.updateAircraftDetails(aircraft), HttpStatus.CREATED);
	}

	@PostMapping("search")
	public ResponseEntity<?> searchAircraftDetails(@RequestBody AircraftDetails aircraft) {

		return new ResponseEntity<>(aircraftService.searchDetails(aircraft), HttpStatus.FOUND);
	}

}
