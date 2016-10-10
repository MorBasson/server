package algorithms.search;

/**
 * The Searcher<T> is an Interface class that has following methods.
 * @author Mor Basson & Reut Sananes
 * @version 1.0
 */
public interface Searcher<T> {
	public Solution<T> search (Searchable<T> s);
	public int getNumberOfNodesEvaluated();
}
