package algorithms.search;

import java.util.ArrayList;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

/**
 * The MazeSearcheable program implements an application that
 * update the start state and the goal state and implement the methods of the Searchable interface.
 * MazeSearchable consist from:
 * 1. maze3d
 * 2. start state
 * 3. end state 
 * @author Mor Basson & Reut Sananes
 * @version 1.0
 */
public class MazeSearchable implements Searchable<Position> {

	public static final int Free = 0;
	public static final int constantCost= 10;

	private Maze3d maze3d;
	private State<Position> startState;
	private State<Position> endState;

	/**
	 * Constructor 
	 * @param maze3d
	 */
    public MazeSearchable(Maze3d maze3d) { 
		this.maze3d = maze3d;
		setStartState();
		setEndState();
	}

	@Override
	/**
	 * This method is used to get the start state
	 * @return start state (position)
	 */
	public State<Position> getStartState() {
		return this.startState;
	}
	
	/**
	 * This method is used to set the start state
	 */
	public void setStartState() {
		this.startState =new State<Position>(maze3d.getStartPosition());
	}
	
	@Override
	/**
	 * This method is used to get the goal state
	 * @return end state (position)
	 */
	public State<Position> getGoalState() {
		return this.endState;
	}

	/**
	 * This method is used to set the end state
	 */
	public void setEndState() {
		this.endState=new State<Position>(maze3d.getGoalPosition());
	}
	
	@Override
	/**
	 * This method is used to check the possible steps of specific state.
	 * @param s- specific state
	 * @return ArrayList of all possible states
	 */
	public ArrayList<State<Position>> getAllPossibleStates(State<Position> s) {
		Position pos = s.getState();
		String[] tempMoves = maze3d.getPossibleMoves(pos);
		ArrayList<State<Position>> possibleStates = new ArrayList<State<Position>>();
		// check legal moves and update the ArrayList
		for (int i = 0; i < tempMoves.length; i++) {

			if (tempMoves[i] == "Down") {
				Position tempPos = new Position(pos.getY() + 1, pos.getX(), pos.getZ());
				possibleStates.add(new State<Position>(tempPos));
			} else if (tempMoves[i] == "Up") {
				Position tempPos = new Position(pos.getY() - 1, pos.getX(), pos.getZ());
				possibleStates.add(new State<Position>(tempPos));
			} else if (tempMoves[i] == "Right") {
				Position tempPos = new Position(pos.getY(), pos.getX(), pos.getZ() + 1);
				possibleStates.add(new State<Position>(tempPos));
			} else if (tempMoves[i] == "Left") {
				Position tempPos = new Position(pos.getY(), pos.getX(), pos.getZ() - 1);
				possibleStates.add(new State<Position>(tempPos));
			} else if (tempMoves[i] == "Forward") {
				Position tempPos = new Position(pos.getY(), pos.getX() + 1, pos.getZ());
				possibleStates.add(new State<Position>(tempPos));
			} else if (tempMoves[i] == "Backward") {
				Position tempPos = new Position(pos.getY(), pos.getX() - 1, pos.getZ());
				possibleStates.add(new State<Position>(tempPos));
			}
		}
		return possibleStates;
	}

	@Override
	/**
	 * This method is used to get the cost 
	 * @return double this return constant cost for each edge
	 */
	public double getMoveCost() {
		return constantCost;
	}
}
