package algorithms.mazeGenerators;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

/**
 * The Maze3d program implements an application that create some maze3d Maze3d
 * consist from the sizes of the axes, the maze3d and the start and goal
 * positions.
 * @author Mor Basson & Reut Sananes
 * @version 1.0
 *
 */

public class Maze3d implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8966L;
	private static final int MAX_POSSIBLE_MOVES = 6;
	public static final int MAZE_VALUE = 9;
	public static final int WALL = 1;

	private int[][][] maze3d;
	private Position start;
	private Position goal;
	private int sizeY;
	private int sizeX;
	private int sizeZ;

	/**
	 * Constructor
	 * 
	 * @param sizeY
	 * @param sizeX
	 * @param sizeZ
	 * @param value
	 */

	public Maze3d(int sizeY, int sizeX, int sizeZ, int value) {
		this.sizeY = sizeY * 2 + 1;
		this.sizeX = sizeX * 2 + 1;
		this.sizeZ = sizeZ * 2 + 1;
		this.maze3d = new int[this.sizeY][this.sizeX][this.sizeZ];
		resetCells(value);
		this.start = null;
		this.goal = null;

	}

	/**
	 * Constructor that receive a byte array and builds through the array
	 * Maze3d.
	 * 
	 * @param b
	 * @throws IOException
	 */
	public Maze3d(byte[] b) throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream(b);
		DataInputStream dataIn = new DataInputStream(in);

		this.sizeY = dataIn.readInt();
		this.sizeX = dataIn.readInt();
		this.sizeZ = dataIn.readInt();

		this.start = new Position(dataIn.readInt(), dataIn.readInt(), dataIn.readInt());
		this.goal = new Position(dataIn.readInt(), dataIn.readInt(), dataIn.readInt());

		maze3d = new int[this.sizeY][this.sizeX][this.sizeZ];

		for (int i = 0; i < this.sizeY; i++) {
			for (int j = 0; j < this.sizeX; j++) {
				for (int k = 0; k < this.sizeZ; k++) {
					maze3d[i][j][k] = dataIn.read();
				}
			}
		}
	}

	/**
	 * This method is used to reset the cells of the maze with value that we
	 * get.
	 * 
	 * @param value
	 */
	private void resetCells(int value) {
		int i, j, k = 0;
		for (i = 0; i < sizeY; i++) {
			for (j = 0; j < sizeX; j++) {
				for (k = 0; k < sizeZ; k++) {
					this.maze3d[i][j][k] = value;
				}
			}
		}
	}

	/**
	 * This method is used to get the size of x axis
	 * 
	 * @return int This return size
	 */
	public int getSizeX() {
		return sizeX;
	}

	/**
	 * This method is used to get the size of y axis
	 * 
	 * @return int This return size
	 */
	public int getSizeY() {
		return sizeY;
	}

	/**
	 * This method is used to get the size of z axis
	 * 
	 * @return int This return size
	 */
	public int getSizeZ() {
		return sizeZ;
	}

	/**
	 * This method is used to set the size of Y axis
	 * 
	 * @param sizeY
	 */
	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	/**
	 * This method is used to set the size of X axis
	 * 
	 * @param sizeX
	 */
	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	/**
	 * This method is used to set the size of Z axis
	 * 
	 * @param sizeZ
	 */
	public void setSizeZ(int sizeZ) {
		this.sizeZ = sizeZ;
	}

	/**
	 * This method is used to get the start position
	 * 
	 * @return Position
	 */
	public Position getStartPosition() {
		return start;
	}

	/**
	 * This method is used to get the goal position
	 * 
	 * @return Position
	 */
	public Position getGoalPosition() {
		return goal;
	}

	/**
	 * This method is used to set the start position
	 * 
	 * @param start-
	 *            Position
	 */
	public void setStartPosition(Position start) {
		this.start = start;
	}

	/**
	 * This method is used to set the goal position
	 * 
	 * @param goal-
	 *            Position
	 */
	public void setGoalPosition(Position goal) {
		this.goal = goal;
	}

	/**
	 * This method is use to get the value of specific cell.
	 * 
	 * @param indexY
	 * @param indexX
	 * @param indexZ
	 * @return int- This return the value of cell
	 */
	public int getCell(int indexY, int indexX, int indexZ) {
		return this.maze3d[indexY][indexX][indexZ];
	}

	/**
	 * This method is used to update some cell of the maze with value that we
	 * get.
	 * 
	 * @param indexY
	 * @param indexX
	 * @param indexZ
	 * @param value
	 */
	public void setCell(int indexY, int indexX, int indexZ, int value) {
		this.maze3d[indexY][indexX][indexZ] = value;
	}

	/**
	 * This method is use to check the all possible moves from some position
	 * 
	 * @param p
	 * @return String[] - This return array with all the moves
	 */
	public String[] getPossibleMoves(Position p) {
		int index = 0;
		String[] arr = new String[MAX_POSSIBLE_MOVES];
		// checking right step
		if (p.getZ() + 1 < sizeZ) {
			if (getCell(p.getY(), p.getX(), p.getZ()+1) == 0) {
				arr[index] = "Right";
				index++;
			}
		}
		// checking left step
		if (p.getZ() - 1 >= 0) {
			if (getCell(p.getY(), p.getX(), p.getZ()-1) == 0) {
				arr[index] = "Left";
				index++;
			}
		}
		// checking up step
		if (p.getY() - 1 >= 0) {
			if (getCell(p.getY() - 1, p.getX(), p.getZ()) == 0) {
				arr[index] = "Up";
				index++;
			}
		}
		// checking down step
		if (p.getY() + 1 < sizeY) {
			if (getCell(p.getY() + 1, p.getX(), p.getZ()) == 0) {
				arr[index] = "Down";
				index++;
			}
		}
	
		// checking forward step
		if (p.getX() + 1 < this.sizeX) {
			if (getCell(p.getY(), p.getX()+1, p.getZ()) == 0) {
				arr[index] = "Forward";
				index++;
			}
		}
		// checking backward step
		if (p.getX() - 1 >= 0) {
			if (getCell(p.getY(), p.getX()-1, p.getZ()) == 0) {
				arr[index] = "Backward";
				index++;
			}
		}
		// insert "null" to the tail of the array
		for (int i = index; i < MAX_POSSIBLE_MOVES; i++) {
			arr[i] = null;
		}
		String[] possibleSteps = new String[index];
		// copy old array to new one
		for (int i = 0; i < index; i++) {
			possibleSteps[i] = arr[i];
		}
		return possibleSteps;
	}

	/**
	 * This method is used to get the maze
	 * 
	 * @return int, this return maze
	 */
	public int[][][] getMaze3d() {
		return maze3d;
	}

	/**
	 * This method is used to set the maze
	 * 
	 * @param maze3d
	 */
	public void setMaze3d(int[][][] maze3d) {
		this.maze3d = maze3d;
	}

	/**
	 * This method is use to get cross section by X axis.
	 * 
	 * @param index
	 * @return int array- This return 2D array
	 */
	public int[][] getCrossSectionByX(int index) {
		if (index < 0 || index > this.sizeX) {
			throw new IndexOutOfBoundsException();
		}
		int[][] crossSection = new int[this.sizeY][this.sizeZ];
		// copy the relevant cross section
		for (int i = 0; i < this.sizeY; i++) {
			for (int j = 0; j < this.sizeZ; j++) {
				crossSection[i][j] = this.maze3d[i][index][j];
			}
		}
		return crossSection;
	}

	/**
	 * This method is use to get cross section by Y axis.
	 * 
	 * @param index
	 * @return int array- This return 2D array
	 */
	public int[][] getCrossSectionByY(int index) {
		if (index < 0 || index >= this.sizeY) {
			throw new IndexOutOfBoundsException();
		}
		int[][] crossSection = new int[this.sizeX][this.sizeZ];
		// copy the relevant cross section
		for (int i = 0; i < this.sizeX; i++) {
			for (int j = 0; j < this.sizeZ; j++) {
				crossSection[i][j] = this.maze3d[index][i][j];
			}
		}
		return crossSection;
	}

	/**
	 * This method is use to get cross section by Z axis.
	 * 
	 * @param index
	 * @return int[][]- This return 2D array
	 */
	public int[][] getCrossSectionByZ(int index) {
		if (index < 0 || index >= this.sizeZ) {
			throw new IndexOutOfBoundsException();
		}
		int[][] crossSection = new int[this.sizeY][this.sizeX];
		// copy the relevant cross section
		for (int i = 0; i < this.sizeY; i++) {
			for (int j = 0; j < this.sizeX; j++) {
				crossSection[i][j] = this.maze3d[i][j][index];

			}
		}
		return crossSection;
	}

	/**
	 * This method is use to print the maze.
	 */
	public void print() {
		for (int i = 0; i < sizeY; i++) {
			for (int j = 0; j < sizeX; j++) {
				for (int k = 0; k < sizeZ; k++) {
					System.out.print(maze3d[i][j][k]);
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	/**
	 * This method is use to convert the data of Maze3D to byte array
	 * 
	 * @return byte[]- This return byte array that present the all information
	 *         of Maze3d
	 */
	public byte[] toByteArray() {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DataOutputStream dataOut = new DataOutputStream(out);

		try {
			dataOut.writeInt(this.sizeY);
			dataOut.writeInt(this.sizeX);
			dataOut.writeInt(this.sizeZ);
			dataOut.writeInt(this.getStartPosition().getY());
			dataOut.writeInt(this.getStartPosition().getX());
			dataOut.writeInt(this.getStartPosition().getZ());
			dataOut.writeInt(this.getGoalPosition().getY());
			dataOut.writeInt(this.getGoalPosition().getX());
			dataOut.writeInt(this.getGoalPosition().getZ());

			for (int i = 0; i < this.getSizeY(); i++) {
				for (int j = 0; j < this.getSizeX(); j++) {
					for (int k = 0; k < this.getSizeZ(); k++) {
						dataOut.write(maze3d[i][j][k]);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return out.toByteArray();
	}

	/**
	 * This method is use to check if two mazes are equals.
	 * 
	 * @return boolean
	 */
	public boolean equals(Object obj) {
		Maze3d tempMaze = (Maze3d) obj;
		if ((this.sizeY != tempMaze.sizeY) || (this.sizeX != tempMaze.sizeX) || (this.sizeZ != tempMaze.sizeZ))
			return false;
		else if (!(this.start.equals(tempMaze.getStartPosition()) || !(this.goal.equals(tempMaze.getGoalPosition()))))
			return false;
		else {
			for (int i = 0; i < sizeY; i++) {
				for (int j = 0; j < sizeX; j++) {
					for (int k = 0; k < sizeZ; k++) {
						if (this.maze3d[i][j][k] != tempMaze.getCell(i, j, k))
							return false;
					}
				}
			}
			return true;
		}
	}

	/**
	 * This method is used to override the original in order to print maze
	 */
	@Override
	public String toString() {
		String newString = "";
		for (int i = 0; i < sizeY; i++) {
			newString += i + "\n\n";

			for (int j = 0; j < sizeX; j++) {
				newString += "";

				for (int k = 0; k < sizeZ; k++) {
					if (k == sizeZ - 1) {
						newString += maze3d[i][j][k];
					} else {
						newString += maze3d[i][j][k];
					}
				}
				newString += "\n";
			}
			newString += "\n\n";
		}
		return newString;
	}

}
