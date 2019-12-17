package com.mystyle.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFilter;

/**
 * @author chandan
 *
 */
@Document(collection = "AircraftDetails")
public class AircraftDetails {
	
	    @Id
	    private long fiid;
	    private String aircraftName;
	    private String departure;
	    private String arival;
	    @DateTimeFormat(pattern = "yyyyMMdd")
	    private Date flightDate;
	    
	    
	    
	    
		/**
		
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
			  
			this.fiid=Long.parseLong(fiid);
			this.aircraftName=aircraftName;
			this.departure=departure;
			this.arival=arival;
			try {
				this.flightDate=new SimpleDateFormat("ddMMyyyy").parse(flightDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
		public Date getFlightDate() {
			return flightDate;
		}
		public void setFlightDate(Date flightDate) {
		
				this.flightDate = flightDate;
			
		}
		@Override
		public String toString() {
			return "AircraftDetails [fiid=" + fiid + ", aircraftName=" + aircraftName + ", departure=" + departure
					+ ", arival=" + arival + ", flightDate=" + flightDate + "]";
		}
	    
	    
}



