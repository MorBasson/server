package algorithms.search;

import java.util.ArrayList;
/**
 * This class represent the DFS algorithm search it is a generic class
 * @author Mor Basson
 *
 * @param <T>
 */
public class DFS<T> extends CommonSearcher<T>{
	private Solution<T> solution;
	private ArrayList<State<T>> visit=new ArrayList<State<T>>();
	private int counter=0;
	@Override

	public Solution<T> search(Searchable<T> s) {
		dfs(s,s.getStartState());
		return solution;

	}
	public void dfs(Searchable<T>s,State<T>state){
		if (state.equals(s.getGoalState())){
			solution=backTrace(s.getStartState(),s.getGoalState());
			setEvaluatedNodes(counter);
			return;
		}


		visit.add(state);
		ArrayList<State<T>>successors=s.getAllPossibleStates(state);
		for(State<T> successor:successors){
			counter++;
			if(!visit.contains(successor)){

				successor.setCameFrom(state);
				dfs(s,successor);
			}
		}
		return;


	}
	@Override
	protected double calculateCost(State<T> state, Searchable<T> search) {
		// TODO Auto-generated method stub
		return 0;
	}

}