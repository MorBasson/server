package algorithms.demo;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;

import algorithms.mazeGenerators.Position;

import algorithms.search.BFS;
import algorithms.search.CommonSearcher;
import algorithms.search.DFS;


import algorithms.search.MazeSearchable;
import algorithms.search.Solution;

/**
 * The Demo program implements an application that consist from main method,
 * using with reference method (run)
 * @author Mor Basson
 *
 */

public class Demo {

	/**
	 * This method is used to: create a maze3d according to MyMazeGenerator.
	 * print the maze. solve the maze with BFS algorithm. solve the maze with
	 * AStar algorithm, using each heuristic of calculate and print the
	 * solution. print how many state each algorithm create.
	 */
	static Maze3d myMaze;

	public void Run() {

		myMaze = new GrowingTreeGenerator().generate(10,10,10);

		myMaze.print();
	
		MazeSearchable mSearchable = new MazeSearchable(myMaze);
		CommonSearcher<Position> cSearcher = new BFS<Position>();
		Solution<Position> solution = cSearcher.search(mSearchable);
		int bfsState = cSearcher.getNumberOfNodesEvaluated();
		System.out.println("The number of noads evaluated in BFS is: " + bfsState);
		solution.printStack();
		CommonSearcher<Position> cSearcher1 = new DFS<Position>();
		solution = cSearcher1.search(mSearchable);
		int DFSState = cSearcher1.getNumberOfNodesEvaluated();
		System.out.println("The number of noads evaluated in DFS is: " + DFSState);
		solution.printStack();
		
	}
}
