package boot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;

import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

/**
 * The Run program implements an application that contains the main method. The
 * main method writing the maze to a file and reading the maze to a new byte
 * array.
 * @author Mor Basson & Reut Sananes
 * @version 1.0
 */
public class Run {

	public static void main(String[] args) {
		Maze3d maze = (new GrowingTreeGenerator().generate(7, 7, 7));

		// save it to a file
		try {
			OutputStream out = new MyCompressorOutputStream(new FileOutputStream("1.maz"));
			out.write(maze.toByteArray());
			out.flush();
			out.close();
			InputStream in = new MyDecompressorInputStream(new FileInputStream("1.maz"));
			byte b[] = new byte[maze.toByteArray().length];
			b=maze.toByteArray();
			in.read(b);
			in.close();
			Maze3d loaded = new Maze3d(b);
			System.out.println(loaded.equals(maze));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
