package com.goeuro.test.bussearch.DataModels;

public class BusStation {

	final Integer stationId;
	BusRoute route;

	public Integer getStationId() {
		return stationId;
	}

	public BusRoute getRoute() {
		return route;
	}

	public BusStation(final Integer id) {
		stationId = id;
	}
	
	public BusStation(final Integer id,
					  final BusRoute route) {
		this.stationId = id;
		this.route = route;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) 
			return true;
		if(!(obj instanceof BusStation))
			return false;
		
		BusStation other = (BusStation) obj;
		boolean idEquals = other.stationId == this.stationId;
		boolean routeEquals = (route == other.route) || (route != null && route.equals(other.route));
		return idEquals && routeEquals;
	}
	
	@Override
	public int hashCode() {
		int hashCode = stationId.hashCode();
		if(route != null) {
			hashCode  = hashCode * route.hashCode();
		}
		return hashCode;
	}
	
	@Override
	public String toString() {
		String id = "Station Id: " + stationId.toString();
		if(route != null) {
			id = id + ", Route: " + route.getRouteId();
		}
		return id;
	}
}
