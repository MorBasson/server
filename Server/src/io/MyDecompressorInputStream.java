package io;

import java.io.IOException;
import java.io.InputStream;

/**
 * The MyDecompressorInputStream program implements an application that read the
 * maze from file. The reading is performed by the number of letters appearance
 * from file. MyDecompressorInputStream consist from: InputStream
 * @author Mor Basson & Reut Sananes
 * @version 1.0
 */
public class MyDecompressorInputStream extends InputStream {

	private InputStream in;
	private int count;
	private int lastByte;

	/**
	 * Constructor
	 * @param in
	 */
	public MyDecompressorInputStream(InputStream in) {
		this.in = in;
	}

	/**
	 * This method is used to read the letter and check exeptions
	 * @return int
	 */

	@Override
	public int read() throws IOException {
		if (this.count <= 0) {
			this.lastByte = in.read();
			if (this.lastByte == -1) {
				return -1;
			}
			this.count = in.read();
			if ((this.count == -1)) {
				throw (new IOException("Invalid byte"));
			}
			if (this.count == 0) {
				throw (new IOException("Invalid counter"));
			}
		}
		this.count--;
		return this.lastByte;
	}
}
