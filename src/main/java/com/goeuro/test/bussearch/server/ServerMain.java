package com.goeuro.test.bussearch.server;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import com.goeuro.test.bussearch.dataloaders.IDataLoader;
import com.goeuro.test.bussearch.dataloaders.LoadRouteDataFromFile;
import com.goeuro.test.bussearch.errorhandling.RouteSearchException;

public class ServerMain {

	Tomcat tomcat;
	public void startTomcat() {
		tomcat = new Tomcat();
		tomcat.setPort(8088);
		tomcat.setBaseDir(".");
		
		try {
			final Context context = tomcat.addWebapp("", new File("src/main/webapp").getAbsolutePath());
			context.init();	
			tomcat.start();
		} catch (Exception e) {
			System.out.println("Failed to start the server");
		}
	}
	
	/**
	 * @param a
	 */
	public static void main(String a[]) {
		ServerMain main = new ServerMain();
		main.startTomcat();
		try {
			loadRouteData("");
		} catch (Exception exception) {
			System.out.println("Failed to load the data: reason, " + exception.getMessage());
		}
	}

	/**
	 * @param filePath
	 * @throws RouteSearchException 
	 */
	private static void loadRouteData(final String filePath) throws RouteSearchException {
		IDataLoader loader = new LoadRouteDataFromFile(filePath);
		loader.loadRoutesFromSource(new File("C:\\Arun\\bus.txt"));
	}
	
}
