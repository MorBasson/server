package controller;

import java.io.Serializable;

/**
 * The PropertiesServer program implements Serializable.
 * PropertiesServer consist from int.
 * @author Mor Basson & Reut Sananes
 * @version 1.0
 *
 */

public class PropertiesServer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 966L;
	
	private int port;
	private int numOfClient;
	
	/**
	 * Constructor
	 */
	public PropertiesServer() {
		super();
	}
	
	/**
	 * This method is used to update default properties data
	 */
	public void defaultPropServer(){
		this.port = 5400;
		this.numOfClient= 5;
	}

	/**
	 * This method is used to get the port
	 * @return int
	 */
	public int getPort() {
		return port;
	}

	/**
	 * This method is used to set the port
	 * @param port
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * This method is used to get the number of client
	 * @return int
	 */
	public int getNumOfClient() {
		return numOfClient;
	}

	/**
	 * This method is used to set the number of client
	 * @param numOfClient
	 */
	public void setNumOfClient(int numOfClient) {
		this.numOfClient = numOfClient;
	}

}
