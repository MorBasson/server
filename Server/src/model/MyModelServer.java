package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;

import algorithms.mazeGenerators.Position;

import algorithms.search.BFS;
import algorithms.search.DFS;

import algorithms.search.MazeSearchable;
import algorithms.search.Searchable;
import algorithms.search.Searcher;
import algorithms.search.Solution;

/**
 * The MyModelServer program implements an application that realize the
 * methods from AbstractModelObservableServer.
 * MyModelServer consist from HashMap and StringBuilder.
 * 
 * @author Mor Basson & Reut Sananes
 * @version 1.0
 * 
 *
 */

public class MyModelServer extends AbstractModelObservableServer {

	private HashMap<String, Position> hashPosition;
	private StringBuilder sBuild;
	
	/**
	 * Constructor
	 */
	public MyModelServer() {
		this.hashPosition = new HashMap<String, Position>();
		this.sBuild= new StringBuilder();
		loadFromZip();
	}


	/**
	 * This method is used to create maze3d by Recursive Backtracker algorithm.
	 * @param nameMaze
	 * @param y
	 * @param x
	 * @param z
	 * @return boolean
	 */
	
	@Override
	public boolean generate(String nameMaze, int y, int x, int z) {
		Future<Maze3d> fCallMaze = threadPool.submit(new Callable<Maze3d>() {

			@Override
			public Maze3d call() throws Exception {
				Maze3d myMaze = new GrowingTreeGenerator().generate(y, x, z);
				return myMaze;
			}
		});
		try {
			hashMaze.put(nameMaze, fCallMaze.get());
			hashPosition.put(nameMaze, fCallMaze.get().getStartPosition());
			System.out.println("generate is finished");
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		} catch (ExecutionException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * This method is used to get the maze
	 * @param nameMaze
	 * @return Maze3d
	 */
	@Override
	public Maze3d getMaze3d(String nameMaze) {
		Maze3d maze= hashMaze.get(nameMaze);
		return maze;
	}
	
	
	/**
	 * This method is use to solve the maze by some algorithm.
	 * @param nameMaze
	 * @param nameAlgorithm
	 * @return boolean
	 */
	@Override
	public boolean solve(String nameMaze, String nameAlgorithm) {
		Maze3d myMaze = hashMaze.get(nameMaze);
		Solution<Position> solution = mazeSolutionMap.get(myMaze);
		if (solution == null) {
				Future<Solution<Position>> fCallSolution = threadPool.submit(new Callable<Solution<Position>>() {

					@Override
					public Solution<Position> call() throws Exception {
						System.out.println("solving the maze");
						Searcher<Position> algorithms;
						Searchable<Position> mazeSearchable = new MazeSearchable(myMaze);
						Solution<Position> solution = new Solution<Position>();
						
						switch (nameAlgorithm) {

						case "BFS":
							algorithms = new BFS<Position>();
							solution = algorithms.search(mazeSearchable);
							break;

						case "DFS":
							algorithms = new DFS<Position>();
							solution = algorithms.search(mazeSearchable);
							break;

						default:
							System.out.println("Invalid algorithm");
							return null;
						}
						return solution;
					}
				});

				try {
					mazeSolutionMap.put(myMaze, fCallSolution.get());
					System.out.println("solve is finished");
					return true;
				} catch (InterruptedException e) {
					e.printStackTrace();
					return false;
				} catch (ExecutionException e) {
					e.printStackTrace();
					return false;
				}
			}else{
				return true;
			}
	}

	/**
	 * This method is used to get the solution
	 * @param nameMaze
	 * @return String
	 */
	@Override
	public String getSolutionMaze(String nameMaze) {
		Maze3d maze = hashMaze.get(nameMaze);
		if (maze != null) {
			Solution<Position> solution = mazeSolutionMap.get(maze);
			Stack<Position> stackSolution = solution.getStackState();
			while (!(stackSolution.isEmpty())) {
				sBuild.append(stackSolution.pop() + " ");
			}
			return sBuild.toString();
		}
		return null;
	}
	
	
	/**
	 * This method is used to save the maze details to zip file
	 */
	@Override
	public void saveToZip() {
		try {
			ObjectOutputStream mazeOut = new ObjectOutputStream(
					new GZIPOutputStream(new FileOutputStream("fileMazeZip.zip")));
			mazeOut.writeObject(hashMaze);
			mazeOut.writeObject(mazeSolutionMap);
			mazeOut.flush();
			mazeOut.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This method is used to load the maze details from zip file
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void loadFromZip() {

		try {

			FileInputStream fileMaze = new FileInputStream("fileMazeZip.zip");
			ObjectInputStream mazeIn = new ObjectInputStream(new GZIPInputStream(fileMaze));
			hashMaze = (HashMap<String, Maze3d>) mazeIn.readObject();
			mazeSolutionMap = (HashMap<Maze3d, Solution<Position>>) mazeIn.readObject();
			mazeIn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This method is use to close the project orderly.
	 */
	@Override
	public void close() {
			saveToZip();
			threadPool.shutdownNow();
			try {
				while (!(threadPool.awaitTermination(10, TimeUnit.SECONDS)))
					;
//				setChanged();
//				notifyObservers("exit");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

}
