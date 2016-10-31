package com.goeuro.test.bussearch.dataloaders;

import java.io.File;

import com.goeuro.test.bussearch.errorhandling.RouteSearchException;

public interface IDataLoader {

	public void loadRoutesFromSource() throws RouteSearchException;
	
	public void loadRoutesFromSource(File file) throws RouteSearchException;
}
