/**
 * 
 */
package com.mystyle.aircraft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.query.Query;
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

	
	public  boolean saveAircraftObject(List<AircraftDetails> listAircrafts) {
		
		boolean br=aircraftRepository.saveAll(listAircrafts) != null;
		return br;
		
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
		return mongoTemplate.find(dynamicQuery, AircraftDetails.class, "AircraftDetails");
		
		
		

		
		
		
		
	}

}
