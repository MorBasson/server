package algorithms.mazeGenerators;

import java.util.Random;

/**
 * The SimpleMaze3dGenerator program implements an application that create Maze3d randomly.
 * @author Mor Basson & Reut Sananes
 * @version 1.0
 */

public class SimpleMaze3dGenerator extends Maze3dGeneratorAbstract {
	private static final int SPACE = 0;
	private static final int WALL = 1;
	private static final int NUMOFSTEPS = 6;
	private static final int RIGHT = 0;
	private static final int LEFT = 1;
	private static final int FORWARD = 2;
	private static final int BACKWARD = 3;
	private static final int UP = 4;
	private static final int DOWN = 5;
	private static final int Y_INDEX = 0;
	private static final int X_INDEX = 1;
	private static final int Z_INDEX = 2;
	private static final int WALL_OR_SPACE = 2;

	/**
	 * This method is use to create a random path of free cells.
	 * @return Maze3d- This return the randomly maze
	 */
	@Override
	public Maze3d generate(int sizeY, int sizeX, int sizeZ) {
		Random rand = new Random();
		Maze3d simpleMaze = new Maze3d(sizeY, sizeX, sizeZ, WALL);
		Position startPos = new Position(sizeY, sizeX, sizeZ);
		startPos.randPosition(sizeY, sizeX, sizeZ);
		simpleMaze.setStartPosition(startPos);

		int randStartX = startPos.getX();
		int randStartY = startPos.getY();
		int randStartZ = startPos.getZ();
		simpleMaze.setCell(randStartY, randStartX, randStartZ, SPACE);

		// current index
		int[] currIndex = { randStartY, randStartX, randStartZ };
		boolean flagExit = false;
		while (!flagExit) {
			// random the next play
			int step = rand.nextInt(NUMOFSTEPS);
			switch (step) {
			// case we random right
			case RIGHT:
				// case we reached exit from the right
				if (currIndex[X_INDEX] == sizeX - 1) {
					flagExit = true;
				} else {
					// set space in the maze
					currIndex[X_INDEX]++;
					simpleMaze.setCell(currIndex[Y_INDEX], currIndex[X_INDEX], currIndex[Z_INDEX], SPACE);
				}
				break;
			// case we random left
			case LEFT:
				// case we reached exit from the left
				if (currIndex[X_INDEX] == 0) {
					flagExit = true;
				} else {
					// set space in the maze
					currIndex[X_INDEX]--;
					simpleMaze.setCell(currIndex[Y_INDEX], currIndex[X_INDEX], currIndex[Z_INDEX], SPACE);
				}
				break;
			case FORWARD:
				// case we reached exit from the forward
				if (currIndex[Z_INDEX] == sizeZ - 1) {
					flagExit = true;
				} else {
					// set space in the maze
					currIndex[Z_INDEX]++;
					simpleMaze.setCell(currIndex[Y_INDEX], currIndex[X_INDEX], currIndex[Z_INDEX], SPACE);
				}
				break;
			case BACKWARD:
				// case we reached exit from the backward
				if (currIndex[Z_INDEX] == 0) {
					flagExit = true;
				} else {
					// set space in the maze
					currIndex[Z_INDEX]--;
					simpleMaze.setCell(currIndex[Y_INDEX], currIndex[X_INDEX], currIndex[Z_INDEX], SPACE);
				}
				break;
			case DOWN:
				// case we reached exit from the down
				if (currIndex[Y_INDEX] == sizeY - 1) {
					flagExit = true;
				} else {
					// set space in the maze
					currIndex[Y_INDEX]++;
					simpleMaze.setCell(currIndex[Y_INDEX], currIndex[X_INDEX], currIndex[Z_INDEX], SPACE);
				}
				break;
			case UP:
				// case we reached exit from the up
				if (currIndex[Y_INDEX] == 0) {
					flagExit = true;
				} else {
					// set space in the maze
					currIndex[Y_INDEX]--;
					simpleMaze.setCell(currIndex[Y_INDEX], currIndex[X_INDEX], currIndex[Z_INDEX], SPACE);
				}
				break;
			}
		}

		// set goal position
		Position goal = new Position(currIndex[Y_INDEX], currIndex[X_INDEX], currIndex[Z_INDEX]);
		simpleMaze.setGoalPosition(goal);
		// set walls randomly
		int i, j, k = 0;
		for (i = 0; i < sizeY; i++) {
			for (j = 0; j < sizeX; j++) {
				for (k = 0; k < sizeZ; k++) {
					if (simpleMaze.getCell(i, j, k) != 0) {
						int randWallOrSpace = rand.nextInt(WALL_OR_SPACE);
						simpleMaze.setCell(i, j, k, randWallOrSpace);
					}
				}
			}
		}

		return simpleMaze;
	}

}
