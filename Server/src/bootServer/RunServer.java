package bootServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import controller.Controller;
import controller.PropertiesServer;
import model.ClientHandler;
import model.MyClientHandler;
import model.MyModelServer;
import view.MyServerView;

/**
 * The RunServer program implements an application that contains the main method.
 * The main method start the whole Server project.
 * 
 * @author Mor Basson & Reut Sananes
 * @version 1.0
 */
public class RunServer {

	public static void main(String[] args) {
		
		PropertiesServer propertiesServer= new PropertiesServer();
		ClientHandler clientHandler= new MyClientHandler();
		MyModelServer model= new MyModelServer();
		Controller controller=new Controller(clientHandler,propertiesServer);
		MyServerView view= new MyServerView(controller);
		controller.setModel(model);
		controller.setView(view);
		controller.setClientHandler(clientHandler);
		
		view.start();
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		try {
			while(!(in.readLine()).equals("close the server"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		view.close();
	}

}
