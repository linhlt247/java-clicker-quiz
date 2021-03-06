package com.github.farhan3.jclickerquiz.server;

import java.net.URISyntaxException;

import com.github.farhan3.jclickerquiz.model.ClassManager;

/**
 * The Server class is the main class to start the server for
 * JClickerQuiz
 * 
 * @author farhan
 *
 */
public class Server {
	
	public static void main(String[] args) {
		
		if (args.length != 1 && args.length != 2) {
			System.err.println(
					"Usage: java -jar server.jar <port number> <student list file path>\n\n"
					+ "You must provide the port number on which to run the server.\n"
					+ "However, the student file is optional.\n"
					+ "If you would like to provide a list of student numbers in a file, \n"
					+ "then provide the path to the file. The file must contain one student \n"
					+ "number per line, the student number must be less than " + Integer.MAX_VALUE
					+ ".\nIf instead, you'd like to add the student numbers later,\n"
					+ "leave the parameter blank.\n");
			System.exit(1);
		}
		
		// the port on which the server should run
		int portNumber = Integer.parseInt(args[0]);

		try {
			// if the second arg is provided, then read the list of student numbers
			if (args.length == 2) {
				String studentListFilePath = args[1];
				ClassManager.getInstance().addStudents(studentListFilePath);;
			}
		} catch (URISyntaxException e) {
			System.err.println("Error while processing the student list file."
					+ " Ensure that it exists and that each line contains only one student number. ");
			e.printStackTrace();
		}
		
		// run ServerControl; this handles the user's input cmds
		new ServerControl(portNumber).run();
	}
	
	
}
