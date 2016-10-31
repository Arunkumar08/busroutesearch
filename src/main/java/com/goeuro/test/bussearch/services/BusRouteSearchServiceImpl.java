package com.goeuro.test.bussearch.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.stream.Collectors;

import com.goeuro.test.bussearch.DataModels.BusStation;
import com.goeuro.test.bussearch.data.BusRouteData;

public class BusRouteSearchServiceImpl implements IBusRouteSearchService {

	public Boolean searchDirectRoute(BusStation station1,
									 BusStation station2) {
		boolean routeExist = false;
		System.out.println("Request:" + station1 + station2 );
		final Map<BusStation, ArrayList<BusStation>> stationMap = BusRouteData.getInstance().getBusStationMap();
		
		/* Get the entry object for the input station1 as key */
		try {
			List<BusStation> stationList = stationMap.entrySet().stream()
								 					.filter(s -> s.getKey().getStationId().equals(station1.getStationId()))
								 					.map(s -> s.getKey())
								 					.collect(Collectors.toList());
			System.out.println("Matched stations for dep: " + stationList);
			for(BusStation station : stationList) {
				routeExist = isRouteExists(station, station2);
				if(routeExist)
					break;
			}
			
		} catch(NoSuchElementException exception) {
			routeExist = false;
			System.out.println("Exception: " + exception.getMessage());
		}
		return routeExist;
	}
	
	/**
	 * @param station
	 * @return
	 */
	private boolean isRouteExists(final BusStation station1,
								  final BusStation station2) {
		
		
		final HashSet<BusStation> visitedElements = new HashSet<BusStation>();
		final Queue<BusStation> toVisitQueue = new LinkedList<BusStation>();

		toVisitQueue.add(station1);
		
		while(!toVisitQueue.isEmpty()) {
			
			BusStation stationToBeChecked = toVisitQueue.poll();
			System.out.println("Check: " + stationToBeChecked);
			
			if(stationToBeChecked.getStationId().equals(station2.getStationId())) {
				return true;
			}
			visitedElements.add(stationToBeChecked);

			final Collection<BusStation> neighbours = BusRouteData.getInstance().getRouteNeigbours(stationToBeChecked);
			
			for(final BusStation neighbour : neighbours) {
				if(!visitedElements.contains(neighbour)) {
					visitedElements.add(neighbour);
					toVisitQueue.add(neighbour);
				}
			}
		}

		return false;
		
	}

}
