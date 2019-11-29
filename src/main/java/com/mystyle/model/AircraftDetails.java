/**
 * 
 */
package com.mystyle.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author chandan
 *
 */
@Document(collection = "AircraftDetails")
public class AircraftDetails {
	
	    @Id
	    private long fiid;

	    @Indexed(unique = true)
	    private String aircraftName;

	    private String departure;
	    private String arival;
	    private Date flightDate;
		public long getFiid() {
			return fiid;
		}
		public void setFiid(long fiid) {
			this.fiid = fiid;
		}
		public String getAircraftName() {
			return aircraftName;
		}
		public void setAircraftName(String aircraftName) {
			this.aircraftName = aircraftName;
		}
		public String getDeparture() {
			return departure;
		}
		public void setDeparture(String departure) {
			this.departure = departure;
		}
		public String getArival() {
			return arival;
		}
		public void setArival(String arival) {
			this.arival = arival;
		}
		public Date getFlightDate() {
			return flightDate;
		}
		public void setFlightDate(Date flightDate) {
			this.flightDate = flightDate;
		}
	    
	    

}

