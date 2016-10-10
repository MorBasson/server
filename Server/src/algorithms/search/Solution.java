package algorithms.search;

import java.io.Serializable;
import java.util.Stack;

/**
 * The Solution<T> program implements an application that saves the solution of
 * the search Solution<T> consist from stack
 * @author Mor Basson & Reut Sananes
 * @version 1.0
 */

public class Solution<T> implements Serializable {

	private static final long serialVersionUID = 5858868L;
	private Stack<T> stackState;

	/**
	 * Constructor
	 */
	public Solution() {
		this.stackState = new Stack<T>();
	}

	/**
	 * This method is used to get the stack state
	 * 
	 * @return Stack of states
	 */
	public Stack<T> getStackState() {
		return stackState;
	}

	/**
	 * This method is used to set the stack state
	 * 
	 * @param stackState-
	 *            represent the stack of states
	 */
	public void setStackState(Stack<T> stackState) {
		this.stackState = stackState;
	}

	/**
	 * This method is used to print the stack
	 */
	public void printStack() {
		while (!stackState.isEmpty()) {
			System.out.print(stackState.pop() + " ");
		}
		System.out.println();
	}

	/**
	 * This method is used to push state in to stack state
	 * 
	 * @param state
	 */
	public void getSolution(T state) {
		this.stackState.push(state);
	}

	@Override
	public boolean equals(Object obj) {
		Solution<T> solution = (Solution<T>) obj;
		Stack<T> firstStack = this.getStackState();
		Stack<T> secondStack = solution.getStackState();
		if (firstStack.equals(secondStack))
			return true;
		return false;

	}

}
