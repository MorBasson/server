package io;

import java.io.IOException;
import java.io.OutputStream;

/**
 * The MyCompressorOutputStream program implements an application that write the
 * maze into a file. The writing is performed by the letters and their counters
 * MyCompressorOutputStream consist from: OutputStream, counter and lastByte.
 * @author Mor Basson & Reut Sananes
 * @version 1.0
 */

public class MyCompressorOutputStream extends OutputStream {
	public static final int FIRST_ORGAN = 0;
	public static final int INITIALIZE_COUNT = 1;

	private OutputStream out;
	private int count;
	private int lastByte;

	/**
	 * Constructor
	 * @param out
	 */
	public MyCompressorOutputStream(OutputStream out) {
		this.out = out;
		this.count = 0;
	}

	/**
	 * This method is used to use the write method that get int parameter.
	 * @param bArr
	 */

	@Override
	public void write(byte[] bArr) throws IOException {
		super.write(bArr);
		write(this.lastByte);
		write(this.count);
		this.count=0;
		out.close();

	}

	/**
	 * This method is used to write into the file from the byte array
	 * @param b
	 */
	@Override
	public void write(int b) throws IOException {
		if (this.count==0) {
			this.count=1;
			this.lastByte= b;
			return;
		}
		if (b==this.lastByte) {
			this.count++;
			if (this.count>255){
				out.write(lastByte);
				out.write(255);
				this.count =1;
			}
		} else {
			out.write(this.lastByte);
			out.write(this.count);
			this.count = INITIALIZE_COUNT;
			this.lastByte = b;
		}
	}
}
