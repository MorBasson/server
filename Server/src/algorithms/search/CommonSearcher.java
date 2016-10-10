package algorithms.search;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * The CommonSearcher program implements an application that
 * organize all the common to the different searchers and implement the methods of the Searcher
 * interface.
 * CommonSearcher consist from:
 * 1.openList (priority queue)
 * 2.number of evaluated nodes
 * @author Mor Basson & Reut Sananes
 * @version 1.0
 */
public abstract class CommonSearcher<T> implements Searcher<T> {

	protected PriorityQueue<State<T>> openList; 
	private int evaluatedNodes;
	
	public int getEvaluatedNodes() {
		return evaluatedNodes;
	}

	public void setEvaluatedNodes(int evaluatedNodes) {
		this.evaluatedNodes = evaluatedNodes;
	}

	/**
	 * Constructor 
	 * The priority queue initialized according to the better cost 
	 */
	public CommonSearcher()
	{
		this.openList=new PriorityQueue<State<T>>(new Comparator<State<T>>() {

			@Override
			public int compare(State<T> s1, State<T> s2) {
				return (int)(s1.getCost()-s2.getCost());
			}
		});
		this.evaluatedNodes=0;
	}
	
	protected abstract double calculateCost (State<T> state, Searchable<T> search);
	
	@Override
	public abstract Solution<T> search(Searchable<T> s);
	
	/**
	 * this method is used to remove state from priority queue and update the number of nodes
	 * @return state this returns the state that removed
	 */
	protected State<T> popOpenList()
	{
		this.evaluatedNodes++;
		return openList.poll();	
	}
	
	
	@Override
	/**
	 * This method is used to get evaluated nodes
	 * @return int this return the numbers of evaluated nodes
	 */
	public int getNumberOfNodesEvaluated() {
		return this.evaluatedNodes;
	}
	
	
	/**
	 * This method is use to through the path of the parents
	 * @param goalState- represent the goal state
	 * @param startState- represent the start state
	 * @return Solution<T> this returns the stack of parents path
	 */
	protected Solution<T> backTrace(State<T> goalState, State<T> startState) {
		Solution<T> stackSolution = new Solution<T>();
		State<T> s = goalState;
		while (s.getCameFrom() != null) {
			stackSolution.getSolution(s.getState());
			s = s.getCameFrom();
		}
		stackSolution.getSolution(startState.getState());
		return stackSolution;
	}

	
	/**
	 * This method is used to check if the openList contains the specific state
	 * @param state
	 * @return true if the state is contained
	 */
	protected boolean openListContains(State<T> state) {
		Iterator<State<T>> itrState = openList.iterator();
		while (itrState.hasNext()) {
			State<T> nextTempState = itrState.next();
			if ((nextTempState.getState()).equals(state.getState()))
			{
				return true;
			}
		}
		return false;
	}

	
	/**
	 * This method is used to check if the haseSet contains the specific state
	 * @param state
	 * @param hs- represent hash set
	 * @return true if the state is contained
	 */
	protected boolean closedSetContains(State<T> state, HashSet<State<T>> hs) {
		Iterator<State<T>> itrState = hs.iterator();
		while (itrState.hasNext()) {
			State<T> nextTempState = itrState.next();
			if ((nextTempState.getState()).equals(state.getState()))
			{
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * This method is used to add specific state in to the openList
	 * @param state
	 */
	protected void addToOpenList(State<T> state) {
		this.openList.add(state);
	}
	
}
