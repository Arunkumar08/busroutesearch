package com.goeuro.test.bussearch.DataModels;

public class BusRoute {

	final Integer routeId;
	
	public Integer getRouteId() {
		return routeId;
	}

	public BusRoute(final Integer id) {
		routeId = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(!(obj instanceof BusRoute))
			return false;
		
		BusRoute that = (BusRoute) obj;
		return this.routeId.equals(that.routeId);
	}
	
	@Override
	public int hashCode() {
		return routeId.hashCode();
	}
	
	@Override
	public String toString() {
		return "Route: " + getRouteId();
	}
}
