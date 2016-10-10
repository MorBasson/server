package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import controller.Controller;

/**
 * The MyServer program implements an application that create the server and handle with clients.
 * MyServer consist from int, boolean , ServerSocket, ClientHandler, ExecutorService- threadpool,
 * Thread and Controller. 
 * @author Mor Basson & Reut Sananes
 * @version 1.0
 */
public class MyServer{

	volatile boolean stop;
	int port, numOfClients;
	ServerSocket server;
	ClientHandler clientHandler;
	ExecutorService threadpool;
	Thread mainServerThread;
	Controller controller;

	/**
	  * Constructor
	  * @param port
	  * @param clientHandler
	  * @param numOfClients
	  * @param controller
	  */
	public MyServer(int port,ClientHandler clientHandler, int numOfClients, Controller controller) {
		this.port = port;
		this.clientHandler = clientHandler;
		this.numOfClients = numOfClients;
		this.stop=false;
		this.controller=controller;
	}

	/**
	 * This method is use to begin the project.
	 */
	public void start(){
		try {
			server = new ServerSocket(port);
			server.setSoTimeout(10 * 1000);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		threadpool = Executors.newFixedThreadPool(numOfClients);

		mainServerThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!stop) {
					try {
						final Socket someClient = server.accept();
						if (someClient != null) {
							threadpool.execute(new Runnable() {
								@Override
								public void run() {
									try {
										controller.displayMessage("handling client");
										clientHandler.handleClient(someClient.getInputStream(),someClient.getOutputStream());
										someClient.close();
										controller.displayMessage("done");
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							});
						}
					} catch (SocketTimeoutException e) {
						//System.out.println("no client connected...");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				//System.out.println("done accepting new clients.");
			} // end of the mainServerThread task
		});

		mainServerThread.start();

	}


	/**
	 * This method is use to close the project.
	 */
	public void close() throws Exception{
		stop = true;
		// do not execute jobs in queue, continue to execute running threads
		System.out.println("shutting down");
		threadpool.shutdown();
		// wait 10 seconds over and over again until all running jobs have
		// finished
		try {
			while (!(threadpool.awaitTermination(10, TimeUnit.SECONDS))){
				threadpool.shutdownNow();
			}
			System.out.println("all the tasks have finished");
			mainServerThread.join();
			System.out.println("main server thread is done");
			
			System.out.println("server is safely closed");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		server.close();

	}
}
