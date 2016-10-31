package com.goeuro.test.bussearch.dataloaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.goeuro.test.bussearch.DataModels.BusRoute;
import com.goeuro.test.bussearch.DataModels.BusStation;
import com.goeuro.test.bussearch.data.BusRouteData;
import com.goeuro.test.bussearch.errorhandling.RouteSearchException;

/**
 * @author akumars
 *
 */
public class LoadRouteDataFromFile implements IDataLoader {
	
	final String inputFilePath;
	
	/**
	 * @param file
	 */
	public LoadRouteDataFromFile(final String file) {
		inputFilePath = file;
	}

	/* (non-Javadoc)
	 * @see com.goeuro.test.bussearch.dataloaders.IDataLoader#loadRoutesFromSource()
	 */
	public void loadRoutesFromSource()
		throws RouteSearchException {
		
		try {
			FileReader fr = new FileReader(inputFilePath);
			BufferedReader br = new BufferedReader(fr);
		
			String totalRoutes = br.readLine();
			Integer totalRoutesInt = Integer.parseInt(totalRoutes);
			
			while(totalRoutesInt > 0) {
				parseRouteLineAndPopulateDate(br.readLine());
				totalRoutesInt--;
			}
			
			fr.close();
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	public void loadRoutesFromSource(File file)
			throws RouteSearchException {
			
			try {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
			
				String totalRoutes = br.readLine();
				Integer totalRoutesInt = Integer.parseInt(totalRoutes);
				
				while(totalRoutesInt > 0) {
					parseRouteLineAndPopulateDate(br.readLine());
					totalRoutesInt--;
				}
				
				fr.close();
				br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}

	/**
	 * @param busRouteInfo
	 * @throws RouteSearchException 
	 */
	private void parseRouteLineAndPopulateDate(final String busRouteInfo)
			throws RouteSearchException {
		if(busRouteInfo != null && !busRouteInfo.equals("")) {
			String[] info = busRouteInfo.split(" ");
			if(info.length < 3) {
				throw new RouteSearchException("Invalid bus route data: " + busRouteInfo);
			} else {
				Integer busRouteId = Integer.parseInt(info[0]);
				BusRoute route = new BusRoute(busRouteId);
				BusRouteData data = BusRouteData.getInstance();
				
				BusStation stationFirst = null;
				
				for(int i = 1; i < info.length; i++) {
					Integer stationId = Integer.parseInt(info[i]);
					BusStation station = new BusStation(stationId, route);
					data.addStationForRoute(route, station);
					if(stationFirst != null
							&& i < info.length) {
						data.addStationList(stationFirst, station);
					}
					stationFirst = station;
				}
				
			}
		}
	}

}
