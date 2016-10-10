package model;

import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import controller.PropertiesServer;
/**
 * The AbstractModelObservableServer program implements Model and inherits Observable.
 * AbstractModelObservableServer consist from HashMap, PropertiesServer and ExecutorService - threadPool.
 * @author Mor Basson & Reut Sananes
 * @version 1.0
 */

public abstract class AbstractModelObservableServer extends Observable implements Model {

	protected HashMap<Maze3d, Solution<Position>> mazeSolutionMap;
	protected ExecutorService threadPool;
	protected PropertiesServer propertiesServer;
	private HashMap<String, Object> commandMap;
	protected HashMap<String, Maze3d> hashMaze;

	/**
	 * Constructor
	 */
	public AbstractModelObservableServer() {
		this.mazeSolutionMap= new HashMap<Maze3d,Solution<Position>>();
		this.propertiesServer= new PropertiesServer();
		propertiesServer.defaultPropServer();
		threadPool = Executors.newFixedThreadPool(propertiesServer.getNumOfClient());
		this.commandMap= new HashMap<String, Object>();
		this.hashMaze= new HashMap<String , Maze3d>();
		
	}
	
	public abstract boolean generate (String nameMaze, int y, int x, int z);
	public abstract Maze3d getMaze3d (String nameMaze);
	public abstract boolean solve(String nameMaze, String nameAlgorithm);
	public abstract String getSolutionMaze(String nameMaze);
	public abstract void saveToZip();
	public abstract void loadFromZip();
	public abstract void close();
	

	
	/**
	 * This method is used to set the notifyObservers with message and object
	 * @param command
	 * @param obj
	 */
	protected void setNotifyObserversName(String command, Object obj) {
		if (obj != null) {
			commandMap.put(command, obj);
		}
		setChanged();
		notifyObservers(command);
	}
	
	/**
	 * This method is used to get user command
	 * @param command
	 * @return Object
	 */
	@Override
	public Object getUserCommand(String command) {
		return commandMap.get(command);
	}

}
