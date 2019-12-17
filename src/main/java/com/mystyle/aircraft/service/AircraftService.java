/**
 * 
 */
package com.mystyle.aircraft.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.mystyle.aircraft.repository.AircraftRepository;
import com.mystyle.model.AircraftDetails;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author chandan
 *
 */
@Service
public class AircraftService {
	
	@Autowired
	AircraftRepository aircraftRepository;
	
	@Autowired
    MongoTemplate mongoTemplate;

	
	public List<AircraftDetails> saveAircraftObject(List<String> lines) {
		
		List<AircraftDetails> listAircrafts=setToObject(lines);
		
		return aircraftRepository.saveAll(listAircrafts);
		
	}
	
	public List<AircraftDetails> findAll() {
		return aircraftRepository.findAll();	
	}
	
public AircraftDetails findByFid(Long fiid) {
		
		return aircraftRepository.findByFiid(fiid);
	}
	
	public AircraftDetails updateAircraftDetails(AircraftDetails aircraftDetails) {
		
		return aircraftRepository.save(aircraftDetails);	
	}
	
	public List<AircraftDetails> searchAll() {
		return aircraftRepository.findAll();	
	}



	/**
	 * @param aircraft
	 * @return
	 */
	public List<AircraftDetails> searchDetails(AircraftDetails aircraft) {
		

		
		Query dynamicQuery = new Query();
		if(StringUtils.isNotBlank(aircraft.getAircraftName()))
		{
		   Criteria nameCriteria = Criteria.where("AircraftName").is(aircraft.getAircraftName());
		   dynamicQuery.addCriteria(nameCriteria);
		}
		if (StringUtils.isNotBlank(aircraft.getDeparture()))
		{
		   Criteria phoneCriteria =     Criteria.where("departure").is(aircraft.getDeparture());
		   dynamicQuery.addCriteria(phoneCriteria);
		}
		
		if (aircraft.getFlightDate()!=null)
		{
			
			
			SimpleDateFormat sdf;
			sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
			sdf.setTimeZone(TimeZone.getTimeZone("CET"));
			String text = sdf.format(aircraft.getFlightDate());
			
			
			
			 //SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-mm-ddTHH:MM:ssZ");
			 Date s=(aircraft.getFlightDate());
			 System.out.println("date......"+s+"   text"+text);
			
		   Criteria phoneCriteria =  Criteria.where("flightDate").is(text);
		   dynamicQuery.addCriteria(phoneCriteria);
		}
		//mongoTemplate.executeQuery("select * from Aircraftdetails",AircraftDetails.class,null);
		
		return mongoTemplate.find(dynamicQuery, AircraftDetails.class, "AircraftDetails");
		
		
		
	}
	
	public List<AircraftDetails> setToObject(List<String> lines)
	{
		List<AircraftDetails> listofAircrafts = new ArrayList<AircraftDetails>();
		for (String l : lines) {
			String line[] = l.split(",");
			AircraftDetails aiobject = new AircraftDetails(line[0], line[1], line[2], line[3], line[4]);
			listofAircrafts.add(aiobject);
		}

		listofAircrafts.forEach(System.out::println);
		
		return listofAircrafts;
		
	}

}
