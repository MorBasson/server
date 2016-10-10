package algorithms.search;

import java.util.ArrayList;

/**
 * The Searchable<T> is an Interface class that has following methods.
 * @author Mor Basson & Reut Sananes
 * @version 1.0
 */
public interface Searchable<T> {
	
	public State<T> getStartState();
	public State<T> getGoalState();
	public ArrayList<State<T>> getAllPossibleStates(State<T> s);
	public double getMoveCost();

}
