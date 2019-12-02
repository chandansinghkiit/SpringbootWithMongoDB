/**
 * 
 */
package com.mystyle.aircraft.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
	public String fileUploaded(MultipartFile file) {

		MultipartFile multipartFile = null;

		List<AircraftDetails> listofAircrafts = new ArrayList<AircraftDetails>();
		

			multipartFile = file;
			InputStream is = null;

			List<Map<String, Object>> transformed = new ArrayList<Map<String, Object>>();
			List<AircraftDetails> listObject = new ArrayList<AircraftDetails>();
			try {
				is = multipartFile.getInputStream();

				StringBuilder sb = new StringBuilder();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				
				

				String line;
				int i = 0;
				while ((line = br.readLine()) != null) {
					sb.append(line + System.lineSeparator());
					String arr[] = line.split(",");

					AircraftDetails aiobject = new AircraftDetails(arr[0], arr[1], arr[2], arr[3], arr[4]);
					listofAircrafts.add(aiobject);

				}

				for (AircraftDetails a : listofAircrafts) {
					System.out.println(a.toString());
				}

				System.out.println(sb.toString());
				
				
				aircraftService.saveAircraftObject(listofAircrafts);

			} catch (Exception e) {

			}
		
		return "upload file successfully";
	}
	
	

	@GetMapping(value = "findAll")
	public  List<AircraftDetails> findAll() {
		
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
