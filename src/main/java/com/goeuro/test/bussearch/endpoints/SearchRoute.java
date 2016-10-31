package com.goeuro.test.bussearch.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.goeuro.test.bussearch.DataModels.BusStation;
import com.goeuro.test.bussearch.DataModels.DirectSearchResponse;
import com.goeuro.test.bussearch.services.BusRouteSearchServiceImpl;
import com.goeuro.test.bussearch.services.IBusRouteSearchService;

@Path("/")
public class SearchRoute {

	@GET
	@Path("direct")
	@Produces(MediaType.APPLICATION_JSON)
	public DirectSearchResponse directRouteSearch(@QueryParam("dep_sid") Integer depStation,
												  @QueryParam("arr_sid") Integer arrStation) {
		
		System.out.println("Came in -- Success");
		IBusRouteSearchService search = new BusRouteSearchServiceImpl();
		Boolean response = search.searchDirectRoute(new BusStation(depStation),
								 					new BusStation(arrStation));
		
		DirectSearchResponse resp = new DirectSearchResponse();
		resp.setArr_sid(arrStation);
		resp.setDep_sid(depStation);	
		resp.setDirect_bus_route(response);
		return resp;
	}
	
}
