package com.goeuro.test.bussearch.services;

import com.goeuro.test.bussearch.DataModels.BusStation;

public interface IBusRouteSearchService {

	/**
	 * @param station1
	 * @param station2
	 * @return
	 */
	public Boolean searchDirectRoute(final BusStation station1,
								      final BusStation station2);
}
