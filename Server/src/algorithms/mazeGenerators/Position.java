package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.Random;

/**
 * The Position program implements an application that represent a position in
 * Maze3d. Position consist from three int that each one of them represent the
 * location on the axes.
 * @author Mor Basson & Reut Sananes
 * @version 1.0
 */

public class Position implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 85263541L;
	private static final int FACE_0 = 0;
	private static final int FACE_1 = 1;
	private static final int FACE_2 = 2;
	private static final int FACE_3 = 3;
	private static final int FACE_4 = 4;
	private static final int FACE_5 = 5;
	private static final int NUM_OF_FACES = 6;
	
	private int x;
	private int y;
	private int z;

	/**
	 * Constructor
	 * @param y
	 * @param x
	 * @param z
	 */
	public Position(int y, int x, int z) {
		this.y = y;
		this.x = x;
		this.z = z;
	}

	/**
	 * Copy Constructor
	 * @param p
	 */
	public Position(Position p) {
		this.y = p.y;
		this.x = p.x;
		this.z = p.z;
	}

	/**
	 * This method is used to get the value of X location
	 * @return int- This return the value
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * This method is used to get the value of Y location
	 * @return int- This return the value
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * This method is used to get the value of Z location
	 * @return int- This return the value
	 */
	public int getZ() {
		return this.z;
	}

	/**
	 * This method is used to set the value of X location
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * This method is used to set the value of Y location
	 * @param x
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * This method is used to set the value of Z location
	 * @param x
	 */
	public void setZ(int z) {
		this.z = z;
	}

	/**
	 * This method is used to extract position randomly
	 * @param y
	 * @param x
	 * @param z
	 * @return position
	 */
	public Position randPosition(int y, int x, int z) {
		Random rand = new Random();
		// random indices for start
		// set entrance
		int choosFace = rand.nextInt(NUM_OF_FACES);
		// choose face from the cube and then choose random start from the face
		switch (choosFace) {
		case FACE_0:
			this.y = 0;
			do {
				this.x = rand.nextInt(x);
			} while (this.x % 2 == 0);
			do {
				this.z = rand.nextInt(z);
			} while (this.z % 2 == 0);
			break;
		case FACE_1:
			this.x = 0;
			do {
				this.y = rand.nextInt(y);
			} while (this.y % 2 == 0);
			do {
				this.z = rand.nextInt(z);
			} while (this.z % 2 == 0);
			break;
		case FACE_2:
			this.z = 0;
			do {
				this.y = rand.nextInt(y);
			} while (this.y % 2 == 0);
			do {
				this.x = rand.nextInt(x);
			} while (this.x % 2 == 0);
			break;
		case FACE_3:
			this.y = y - 1;
			do {
				this.x = rand.nextInt(x);
			} while (this.x % 2 == 0);
			do {
				this.z = rand.nextInt(z);
			} while (this.z % 2 == 0);
			break;
		case FACE_4:
			this.x = x - 1;
			do {
				this.y = rand.nextInt(y);
			} while (this.y % 2 == 0);
			do {
				this.z = rand.nextInt(z);
			} while (this.z % 2 == 0);
			break;
		case FACE_5:
			this.z = z - 1;
			do {
				this.y = rand.nextInt(y);
			} while (this.y % 2 == 0);
			do {
				this.x = rand.nextInt(x);
			} while (this.x % 2 == 0);
			break;
		}
		return new Position(this.y, this.x, this.z);
	}

	/**
	 * This method is use to override the original method in order to print a
	 * position
	 */
	public String toString() {
		return "{" + this.y + "," + this.x + "," + this.z + "}";
	}

	/**
	 * This method is use to check if two positions are equal.
	 * @return boolean
	 */
	@Override
	public boolean equals(Object p) {
		return this.toString().equals(p.toString());
	}

	/**
	 * This method is use to returns the hash code value.
	 * @return String
	 */
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}

}
