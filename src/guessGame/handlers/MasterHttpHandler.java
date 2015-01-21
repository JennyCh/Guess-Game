package guessGame.handlers;

import guessGame.Challenge;
import guessGame.DatabaseConnect;
import guessGame.Task;
import guessGame.TaskFactory;
import guessGame.TaskType;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class MasterHttpHandler extends AbstractHandler {
	private final TaskFactory tf;
	private final HandlerFactory handlerFactory;
	private final DatabaseConnect dbConnect;
	private int points, toPoints;

	public MasterHttpHandler(TaskFactory tf) throws ClassNotFoundException, SQLException {
		this.tf = tf;
		handlerFactory = new HandlerFactory();
		this.dbConnect = new DatabaseConnect();
		points = 0;
	}

	public int getPoints() {
		return points;
	}

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		final String name = request.getParameter("user");
		System.out.println(name);
		final String password = request.getParameter("pwd");
		System.out.println(password);
		final String typeOfRequest = request.getParameter("typeOfRequest");
		System.out.println(typeOfRequest);
		Challenge currentTask = null;
		try {
			switch (typeOfRequest) {
			case "login":
				if (!dbConnect.hasPlayer(name, password)) {
					// send message that name or password is incorrect
					currentTask = new Task(TaskType.JPEG, "UserName or Password is incorrect", "");
				} else {
					currentTask = getTask();
				}
				break;
			case "register":
				if (!dbConnect.insertPlayer(name, password)) {
					// send message that user name already exists
					currentTask = new Task(TaskType.JPEG, "UserName already exists. Try again", "");
				} else {
					currentTask = getTask();
				}
				break;
			case "submit":
				dbConnect.addPoints(name, password);
				currentTask = getTask();
				break;
			case "skip":
				currentTask = getTask();
				break;
			case "points":
				points = dbConnect.getPlayerPoints(name, password);
				toPoints = dbConnect.getTopScore();
				currentTask = new Task(TaskType.JPEG, "You have " + points + " points!\nTop score: " + toPoints,
						"points");

				// JOptionPane.showMessageDialog(points, null);
				// JOptionPane.showMessageDialog(null, points);
				break;
			default:
				// currentTask = new Task(TaskType.TEXT, "Error", "");
				JOptionPane.showMessageDialog(null, "Error");
			}
			handleTask(currentTask, target, baseRequest, request, response);

		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * Enumeration<String> g = session.getAttributeNames();
		 * while(g.hasMoreElements()){ System.out.println(g.nextElement()); }
		 */

		// Challenge currentTask = getTask();
		// request.setAttribute("Task", currentTask);
		// handlerFactory.handleTask(currentTask, target, baseRequest, request,
		// response);
		//
		// System.out.println("Handled");
		//
		// baseRequest.setHandled(true);

	}

	public void handleTask(Challenge currentTask, String target, Request baseRequest, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("Task", currentTask);
		handlerFactory.handleTask(currentTask, target, baseRequest, request, response);

		// System.out.println("Handled");

		baseRequest.setHandled(true);

	}

	private Challenge getTask() {
		return tf.getNextTask();
	}

}
