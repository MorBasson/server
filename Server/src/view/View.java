package view;

/**
 * The View is an Interface class that has following methods.
 * 
 * @author Mor Basson & Reut Sananes
 * @version 1.0
 */
public interface View {

	public void start();
	public void close();
	public void sendMessage(String message);
	
}
