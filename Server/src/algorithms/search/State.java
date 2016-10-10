package algorithms.search;

/**
 * The State<T> program implements an application that
 * defined a specific state that consist from:
 * 1. state (position)
 * 2. cost of the pathway 
 * 3. where the current state came from 
 * 
 * @author Mor Basson & Reut Sananes
 * @version 1.0
 * 
 */

public class State<T> {
	private  T state;
	private double cost;
	private State<T> cameFrom;
	
	/**
	 * Constructor
	 * @param state
	 */
	public State (T state)
	{
		this.state=state;
		this.cost=0;
		this.cameFrom=null;
	}
	
	/**
	 * Copy contractor
	 * @param s- represent the state
	 */
	
	public State(State<T> s)
	{
		this.state=s.state;
		this.cost=s.cost;
		this.cameFrom=s.cameFrom;
	}
	
	@Override
	/**
	 * This method is used to compare two states
	 * @param obj- specific element 
	 * @return this returns true is the two states are equal 
	 */
	public boolean equals(Object obj)
	{
		return state.equals(((State<T>) obj).getState());
	}

	/**
	 * This method is used to get the state
	 * @return state (position)
	 */
	public T getState() {
		return state;
	}

	/**
	 * This method is used to set the state
	 * @param state (position)
	 */
	public void setState(T state) {
		this.state = state;
	}

	/**
	 * This method is used to get the cost
	 * @return double this return the total cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 *  This method is used to set the cost
	 * @param cost
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * This method is used to get where the state came from
	 * @return state (position)
	 */
	public State<T> getCameFrom() {
		return cameFrom;
	}

	/**
	 *  This method is used to set where the state came from 
	 * @param cameFrom
	 */
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	

}
