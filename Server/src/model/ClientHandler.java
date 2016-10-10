package model;

import java.io.InputStream;
import java.io.OutputStream;

import controller.Controller;

/**
 * The ClientHandler is an Interface class that has the following methods.
 * 
 * @author Mor Basson & Reut Sananes
 * @version 1.0
 */

public interface ClientHandler {
	 public void handleClient(InputStream inFromClient, OutputStream outToClient);
	 public void setController(Controller controller);
}
