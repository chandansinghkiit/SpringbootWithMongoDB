/**
 * 
 */
package com.mystyle.aircraft.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mystyle.model.*;

/**
 * @author chandan
 *
 */

@Repository
public interface AircraftRepository extends MongoRepository<AircraftDetails, Long> {

	/**
	 * @param fiid
	 * @return
	 */
	AircraftDetails findByFiid(Long fiid);



}
