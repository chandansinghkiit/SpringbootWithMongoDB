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
	    private String flightDate;
	    
	    
	    
		/**
		 * 
		 */
		public AircraftDetails() {
		}
		/**
		 * @param string
		 * @param string2
		 * @param string3
		 * @param string4
		 * @param string5
		 */
		public AircraftDetails(String fiid, String aircraftName, String departure, String arival, String flightDate) {
			this.fiid= new Long(fiid);
			this.aircraftName=aircraftName;
			this.departure=departure;
			this.arival=arival;
			this.flightDate=flightDate;
			
		}
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
		public String getFlightDate() {
			return flightDate;
		}
		public void setFlightDate(String flightDate) {
			this.flightDate = flightDate;
		}
		@Override
		public String toString() {
			return "AircraftDetails [fiid=" + fiid + ", aircraftName=" + aircraftName + ", departure=" + departure
					+ ", arival=" + arival + ", flightDate=" + flightDate + "]";
		}
	    
	    
}



