package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * The BFS<T> program implements an application that use BFS algorithm
 * @author Mor Basson & Reut Sananes
 * @version 1.0
 */
public class BFS<T> extends CommonSearcher<T> {

	/**
	 * This method is used to solve the problem with the BFS algorithm.
	 * @param s- represent a Searchable
	 * @return Solution<T> this returns the stack of parents path
	 */
	public Solution<T> search(Searchable<T> s) {
		addToOpenList(s.getStartState());
		HashSet<State<T>> closedSet = new HashSet<State<T>>();

		// do while the open list is not empty
		while (openList.size() > 0) {
			State<T> n = popOpenList();
			closedSet.add(n);
			
			// if the state is our goal
			if (n.equals(s.getGoalState()))
				return backTrace(n, s.getStartState());

			ArrayList<State<T>> successors = s.getAllPossibleStates(n);
			for (State<T> state : successors) {
				state.setCost(calculateCost(n, s));
				// just if the state in the open list and not in the HashSet
				if (!closedSetContains(state, closedSet) && !openListContains(state)) {
					state.setCameFrom(n);
					addToOpenList(state);
				} else {
					// check if the open list contains the state
					if (openListContains(state)) {
						Iterator<State<T>> itrState = openList.iterator();
						while (itrState.hasNext()) {
							State<T> tempState = itrState.next();
							if ((tempState.getState()).equals(state.getState())) {
								// if the cost of the next state and the cost of the edge smaller than the current cost
								if (state.getCost() + s.getMoveCost() < tempState.getCost()) {
									itrState.remove();
									state.setCameFrom(n);
									addToOpenList(state);
								}
								break;
							}
						}
					} else {
						state.setCameFrom(n);
						addToOpenList(state);
					}
				}
			}
		}
		return null;
	}
	
	@Override
	/**
	 * This method is used to calculate the sum of state cost and edge cost
	 * @param state
	 * @param search- represent a Searchable
	 * @return double this returns the total sum
	 */
	protected double calculateCost(State<T> state, Searchable<T> search) {
		 return state.getCost()+ search.getMoveCost();
	}

}
