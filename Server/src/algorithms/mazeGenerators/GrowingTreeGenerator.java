package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GrowingTreeGenerator extends Maze3dGeneratorAbstract {
	
	private Random rand = new Random();	
		
	@Override
	public Maze3d generate(int floors, int rows, int cols) {		
		Maze3d maze = new Maze3d(floors,rows, cols, 1);
		List<Position> cells = new ArrayList<Position>();

		// Initialize all the maze with walls
		initialize(maze);		

		// Choose a random starting cell (must be in an even row and column)		
		Position startPos = chooseRandomPosition(maze);
		maze.setStartPosition(startPos);
		maze.setCell(startPos.getY(),startPos.getX(), startPos.getZ(),0);

		cells.add(startPos);
		
		while (!cells.isEmpty()) {
			// Choose the last cell from the list
	
			Position pos = cells.get(cells.size() - 1);	
			
			// Find the unvisited neighbors of this cell
			List<Position> neighbors = findUnvisitedNeighbors(maze, pos);	
			
			if (!neighbors.isEmpty()) {
				// Choose a random neighbor
				int idx = rand.nextInt(neighbors.size());
				Position neighbor = neighbors.get(idx);
				
				// Carve a passage between current cell and the neighbor
				carvePassageBetweenCells(maze, pos, neighbor);				
				cells.add(neighbor);
			} 
			else {
				cells.remove(pos);
			}	
		}		
		
		Position goalPosition = chooseRandomGoalPosition(maze);
		maze.setGoalPosition(goalPosition);
		
		return maze;
	}
	
	private void initialize(Maze3d maze) {
		for (int z = 0; z < maze.getSizeY(); z++) {
			for (int x = 0; x < maze.getSizeX(); x++) {
				for (int y = 0; y < maze.getSizeZ(); y++) {
					maze.setCell(z, y, x,1);
				}
			}
		}
	}
	
	private Position chooseRandomPosition(Maze3d maze3d) {	
		int z = rand.nextInt(maze3d.getSizeY());
		while (z % 2 != 0) {
			z = rand.nextInt(maze3d.getSizeY());
		}
		int x = rand.nextInt(maze3d.getSizeZ());
		while (x % 2 != 0) {
			x = rand.nextInt(maze3d.getSizeZ());
		}

		int y = rand.nextInt(maze3d.getSizeX());
		while (y % 2 != 0) {
			y = rand.nextInt(maze3d.getSizeX());
		}
		
		return new Position(z, y, x);
	}
	
	private List<Position> findUnvisitedNeighbors(Maze3d maze, Position pos) {
		int[][][] mat = maze.getMaze3d();
		List<Position> neighbors = new ArrayList<Position>();
		if (pos.getY() - 2 >= 0 && mat[pos.getY() - 2][pos.getX()][pos.getZ()] == Maze3d.WALL) {
			neighbors.add(new Position(pos.getY() - 2, pos.getX(),pos.getZ()));
		}	
		
		if (pos.getY() + 2 < maze.getSizeX() && mat[pos.getY() + 2][pos.getX()][pos.getZ()] == Maze3d.WALL) {
			neighbors.add(new Position(pos.getY() + 2, pos.getX(),pos.getZ()));
		}
		
		if (pos.getZ() - 2 >= 0 && mat[pos.getY()][pos.getX()][pos.getZ() - 2] == Maze3d.WALL) {
			neighbors.add(new Position(pos.getY(), pos.getX(),pos.getZ() - 2));
		}
		
		if (pos.getZ() + 2 < maze.getSizeZ() && mat[pos.getY()][pos.getX()][pos.getZ() + 2] == Maze3d.WALL) {
			neighbors.add(new Position(pos.getY(), pos.getX(),pos.getZ() + 2));
		}
		
		if (pos.getX() - 2 >= 0 && mat[pos.getY()][pos.getX() - 2][pos.getZ()] == Maze3d.WALL) {
			neighbors.add(new Position(pos.getY(), pos.getX() - 2,pos.getZ()));
		}	
		
		if (pos.getX() + 2 < maze.getSizeX() && mat[pos.getY()][pos.getX() + 2][pos.getZ()] == Maze3d.WALL) {
			neighbors.add(new Position(pos.getY(), pos.getX() + 2,pos.getZ()));
		}
		
		return neighbors;
	}	
	
	private void carvePassageBetweenCells(Maze3d maze, Position pos, Position neighbor) {
		if (neighbor.getZ() == pos.getZ() + 2) {
			maze.setCell(pos.getY(), pos.getX(),pos.getZ() + 1,0);
			maze.setCell(pos.getY(), pos.getX(),pos.getZ() + 2,0);
		}
		else if (neighbor.getZ() == pos.getZ() - 2) {
			maze.setCell(pos.getY(), pos.getX(),pos.getZ() - 1,0);
			maze.setCell(pos.getY(), pos.getX(),pos.getZ() - 2,0);
		}
		else if (neighbor.getX() == pos.getX() + 2) {
			maze.setCell(pos.getY(), pos.getX()+ 1,pos.getZ() ,0);
			maze.setCell(pos.getY(), pos.getX() + 2,pos.getZ(),0);
		}
		else if (neighbor.getX() == pos.getX() - 2) {
			maze.setCell(pos.getY(), pos.getX()- 1,pos.getZ() ,0);
			maze.setCell(pos.getY(), pos.getX() - 2,pos.getZ(),0);
		}
		else if (neighbor.getY() == pos.getY() + 2) {
			maze.setCell(pos.getY()+ 1, pos.getX(),pos.getZ() ,0);
			maze.setCell(pos.getY() + 2, pos.getX(),pos.getZ(),0);
		}
		else if (neighbor.getY() == pos.getY() - 2) {
			maze.setCell(pos.getY()- 1, pos.getX(),pos.getZ() ,0);
			maze.setCell(pos.getY() - 2, pos.getX(),pos.getZ(),0);
		}
	}
	
	
	private Position chooseRandomGoalPosition(Maze3d maze) {	
		int[][][] mat = maze.getMaze3d();
		int z = rand.nextInt(maze.getSizeY());
		int x = rand.nextInt(maze.getSizeZ());
		int y = rand.nextInt(maze.getSizeX());
		while (mat[z][y][x] == Maze3d.WALL) {
			z = rand.nextInt(maze.getSizeY());
			x = rand.nextInt(maze.getSizeZ());
			y = rand.nextInt(maze.getSizeX());
		}		
						
		return new Position(z, y,x);	
	}	
	



}