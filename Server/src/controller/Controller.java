package controller;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import algorithms.mazeGenerators.Maze3d;
import model.ClientHandler;
import model.Model;
import model.MyServer;
import view.View;

/**
 * The Controller program implements an application that get command from the view
 * and send the command to the model and the model returns his calculation to controller.
 * Controller consist from view, model, MyServer, ClientHandler and PropertiesServer.
 * @author Mor Basson & Reut Sananes
 * @version 1.0
 */
public class Controller {

	private View view;
	private Model model;
	private MyServer myServer;
	private ClientHandler clientHandler;
	private PropertiesServer propertiesServer;

	/**
	 * Constructor
	 * @param clientHandler
	 * @param propertiesServer
	 */
	public Controller(ClientHandler clientHandler, PropertiesServer propertiesServer) {
		setClientHandler(clientHandler);
		this.myServer = new MyServer(5400, clientHandler, 5, this);
		this.propertiesServer = propertiesServer;
		
		XMLDecoder dXml;
		try {
			dXml = new XMLDecoder(new BufferedInputStream(new FileInputStream("PropertiesServer.xml")));
			propertiesServer = (PropertiesServer) dXml.readObject();
			dXml.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * This method is use to begin the project.
	 */
	public void start() {
		myServer.start();
	}

	/**
	 * This method is use to close the project.
	 */
	public void close() {
		try {
			model.close();
			myServer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is use to display string massage. 
	 * @param massage
	 */
	public void displayMessage(String message) {
		view.sendMessage(message);
	}

	/**
	 * This method is used to start the methods in server side
	 * @param command
	 * @return boolean
	 */
	public boolean update(String command) {
		String[] tempArr = command.split(" ");
		if (tempArr[0].equals("generate")) {
			int y = Integer.parseInt(tempArr[2]);
			int x = Integer.parseInt(tempArr[3]);
			int z = Integer.parseInt(tempArr[4]);
			model.generate(tempArr[1], y, x, z);
			return true;
		}
		if (tempArr[0].equals("solve")) {
			model.solve(tempArr[1], tempArr[2]);
			return true;
		}
//		if (tempArr[0].equals("open") && tempArr[1].equals("server")) {
//			// model.start();
//		}
//		if (tempArr[0].equals("close") && tempArr[1].equals("server")) {
//			model.close();
//		}
		return false;

	}

	/**
	 * This method is used to get the view
	 * @return view
	 */
	public View getView() {
		return view;
	}

	/**
	 * This method is used to set the view
	 * @param view
	 */
	public void setView(View view) {
		this.view = view;
	}

	/**
	 * This method is used to get the model
	 * @return model
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * This method is used to set the model
	 * @param model
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * This method is used to get the server
	 * @return MyServer
	 */
	public MyServer getMyServer() {
		return myServer;
	}

	/**
	 * This method is used to set the server
	 * @param myServer
	 */
	public void setMyServer(MyServer myServer) {
		this.myServer = myServer;
	}

	/**
	 * This method is used to get the clientHendler
	 * @return ClientHendler
	 */
	public ClientHandler getClientHandler() {
		return clientHandler;
	}

	/**
	 * This method is used to set the clientHendler
	 * @param clientHandler
	 */
	public void setClientHandler(ClientHandler clientHandler) {
		this.clientHandler = clientHandler;
		clientHandler.setController(this);
	}


	/**
	 * This method is used to get the maze according to the received name
	 * @param nameMaze
	 * @return Maze3d
	 */
	public Maze3d getMyMaze(String nameMaze) {
		Maze3d maze= model.getMaze3d(nameMaze);
		return maze;
	}
	
	/**
	 * This method is used to get the solution according to the received name
	 * @param nameMaze
	 * @return String
	 */
	public String getSolutionMaze(String nameMaze){
		String solution= model.getSolutionMaze(nameMaze);
		return solution;
	}


	/**
	 * This method is used to get the propertiesServer
	 * @return PropertiesServer
	 */
	public PropertiesServer getPropertiesServer() {
		return propertiesServer;
	}

	/**
	 * This method is used to set the propertiesServer
	 * @param propertiesServer
	 */
	public void setPropertiesServer(PropertiesServer propertiesServer) {
		this.propertiesServer = propertiesServer;
	}

}
