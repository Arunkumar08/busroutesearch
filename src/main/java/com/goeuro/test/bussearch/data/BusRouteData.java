package com.goeuro.test.bussearch.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.goeuro.test.bussearch.DataModels.BusRoute;
import com.goeuro.test.bussearch.DataModels.BusStation;

public class BusRouteData {
	
	private static BusRouteData routeData;
	private BusRouteData() {
		
	}

	public static BusRouteData getInstance() {
		if(routeData == null) {
			synchronized (BusRouteData.class) {
				if(routeData == null) {
					routeData = new BusRouteData();
				}
			}
		}
		return routeData;
	}
	
 	private Map<BusStation, ArrayList<BusStation>> busStationMap;
 	private Map<BusRoute, ArrayList<BusStation>> routeStationsMap;
 	
	public Map<BusStation, ArrayList<BusStation>> getBusStationMap() {
		if(busStationMap == null)
			busStationMap = new HashMap<>();
		return busStationMap;
	}

	public Map<BusRoute, ArrayList<BusStation>> getRouteStationsMap() {
		if(routeStationsMap == null)
			routeStationsMap = new HashMap<>();
		return routeStationsMap;
	}

	/**
	 * @param route
	 * @param station
	 */
	public void addStationForRoute(final BusRoute route,
								   final BusStation station) {
		Map<BusRoute, ArrayList<BusStation>>  routeMap = getRouteStationsMap();
		ArrayList<BusStation> stationList = routeMap.get(route);
		if(stationList == null) {
			stationList = new ArrayList<BusStation>();
			routeMap.put(route, stationList);
		}
		stationList.add(station);
	}
	
	/**
	 * @param staion
	 * @param link
	 */
	public void addStationList(final BusStation station,
							   final BusStation link) {
		Map<BusStation, ArrayList<BusStation>> stationLink = getBusStationMap();
		ArrayList<BusStation> stationList = stationLink.get(station);
		if(stationList == null) {
			stationList = new ArrayList<BusStation>();
			stationLink.put(station, stationList);
		}
		stationList.add(link);
	}
	
	public void printStationLink() {
		for(Entry<BusStation, ArrayList<BusStation>> entry : getBusStationMap().entrySet()) {
			System.out.println("Station: " + entry.getKey() + " Links:");
			for(BusStation station : entry.getValue()) {
				System.out.println(station);
			}
		}
	}
	
	/**
	 * Get the BusStations directly linked from the station1
	 * @param station1
	 * @return
	 */
	public Collection<BusStation> getRouteNeigbours(BusStation station1) {
		List<BusStation> neigbours = new ArrayList<BusStation>();
		if(getBusStationMap() != null) {
			neigbours = getBusStationMap().entrySet().stream()
										 .filter(s -> s.getKey().getStationId().equals(station1.getStationId()))
										 .flatMap(s -> s.getValue().stream())
										 .filter(s -> s.getRoute().equals(station1.getRoute()))
										 .collect(Collectors.toList());
			System.out.println(station1 + "neighbours: " + neigbours);
		}
		return neigbours;
	}
}
