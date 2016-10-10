package view;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import controller.Controller;

/**
 * The AbstractViewObservableServer program implements View and inherits Observable.
 * AbstractViewObservableServer consist from BufferedReader, PrintWriter ,ServerSocket and Controller.
 * 
 * @author Mor Basson & Reut Sananes
 * @version 1.0
 *
 */
public abstract class AbstractViewObservableServer extends Observable implements View {

	protected BufferedReader in;
	protected PrintWriter out;
	protected ServerSocket serverSocket;
	protected Controller controller;
	
	/**
	 * Constructor
	 * @param controller
	 */
	public AbstractViewObservableServer(Controller controller) {
		this.controller=controller;
	}
	
	public abstract void start();
	public abstract void close();
	public abstract void sendMessage(String message);
	
}
