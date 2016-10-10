package algorithms.mazeGenerators;

/**
 * The Maze3dGenerator is an Interface class that has following methods.
 * @author Mor Basson & Reut Sananes
 * @version 1.0
 */

public interface Maze3dGenerator {
	public Maze3d generate(int ySize, int xSize, int zSize);

	public String measureAlgorithmTime(int ySize, int xSize, int zSize);
}
