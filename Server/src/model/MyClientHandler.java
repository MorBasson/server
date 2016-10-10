package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import algorithms.mazeGenerators.Maze3d;
/**
 * The MyClientHandler program implements an application that realize the
 * methods from AbstractObservableClientHandler.
 * 
 * @author Mor Basson & Reut Sananes
 * @version 1.0
 *
 */


public class MyClientHandler extends AbstractObservableClientHandler {

	/**
	 * Constructor
	 */
	public MyClientHandler() {
		
	}

	/**
	 * This method is used to handle the command that received from the client
	 * @param inFromClient
	 * @param outToClient
	 */
	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(inFromClient));
			PrintWriter out = new PrintWriter(outToClient);

			String line;

			while (!(line = in.readLine()).endsWith("exit")) {
				if (line.equals("generate")) {
					checkForGenerate(in, out);
					out.println("done");
					out.flush();
				}
				if (line.equals("get maze")) {
					checkForGetMaze(in, out);
					out.println("done");
					out.flush();
				}
				if (line.equals("solve")) {
					checkForSolve(in, out);
					out.println("done");
					out.flush();
				}
				if (line.equals("get solution")) {
					checkForSolution(in, out);
					out.println("done");
					out.flush();
				}
			}
			out.println("good bye");
			out.flush();
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * This method is used to begin protocol with the client in order to create maze.
	 * @param inFromClient
	 * @param outToServer
	 */
	public void checkForGenerate(BufferedReader inFromClient, PrintWriter outToServer) {
		String nameMaze, temp;
		int y, x, z;
		try {
			outToServer.println("What is the name of the maze?");
			outToServer.flush();
			nameMaze = inFromClient.readLine().split(": ")[1];
			outToServer.println("What is the number of floors?");
			outToServer.flush();
			temp = inFromClient.readLine().split(": ")[1];
			y= Integer.parseInt(temp);
			outToServer.println("What is the number of lines?");
			outToServer.flush();
			temp = inFromClient.readLine().split(": ")[1];
			x= Integer.parseInt(temp);
			outToServer.println("What is the number of cloumns?");
			outToServer.flush();
			temp = inFromClient.readLine().split(": ")[1];
			z= Integer.parseInt(temp);
			controller.update("generate " + nameMaze + " " + y + " " + x + " "+ z);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	/**
	 * This method is used to begin protocol with the client in order to send the maze.
	 * @param inFromClient
	 * @param outToServer
	 */
	public void checkForGetMaze(BufferedReader inFromClient, PrintWriter outToServer) {
		String nameMaze;
		try {
			outToServer.println("What is the name of the maze?");
			outToServer.flush();
			nameMaze = inFromClient.readLine();
			System.out.println(nameMaze);
			
			Maze3d myMaze= controller.getMyMaze(nameMaze);
			
			if(myMaze!=null){
				byte[] bArr = myMaze.toByteArray();
				for(byte bNum : bArr){
					outToServer.write((int) bNum);
					outToServer.flush();
				}
				outToServer.write(127);
				outToServer.flush();
			}else{
				System.out.println("error");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

	/**
	 * This method is used to begin protocol with the client in order to solve the maze.
	 * @param inFromClient
	 * @param outToServer
	 */
	public void checkForSolve(BufferedReader inFromClient, PrintWriter outToServer) {
		String nameMaze, nameAlgorithm;
		try {
			outToServer.println("What is the name of the maze?");
			outToServer.flush();
			nameMaze = inFromClient.readLine().split(": ")[1];
			outToServer.println("What is the name of ths algorithm?");
			outToServer.flush();
			nameAlgorithm = inFromClient.readLine().split(": ")[1];
			System.out.println(nameAlgorithm);
			
			controller.update("solve " + nameMaze + " " + nameAlgorithm);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	/**
	 * This method is used to begin protocol with the client in order to send the solution.
	 * @param inFromClient
	 * @param outToServer
	 */
	@SuppressWarnings("unused")
	public void checkForSolution(BufferedReader inFromClient, PrintWriter outToServer) {
		String nameMaze, solution, line;
		try {
			outToServer.println("What is the name of the maze?");
			outToServer.flush();
			nameMaze = inFromClient.readLine().split(": ")[1];
			System.out.println(nameMaze);
			
			solution= controller.getSolutionMaze(nameMaze);
			String[] solArr= solution.split(" ");
			if(solution != null){
				for(int i= solArr.length-1; i>=0;i--){
					line = solArr[i];
					outToServer.println(line);
					outToServer.flush();	
				}
				outToServer.println("end");
				outToServer.flush();
			}else{
				System.out.println("error");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

}
