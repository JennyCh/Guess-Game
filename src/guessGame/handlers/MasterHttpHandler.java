package guessGame.handlers;

import guessGame.Challenge;
import guessGame.DatabaseConnect;
import guessGame.TaskFactory;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class MasterHttpHandler extends AbstractHandler {
	private TaskFactory tf;
	private HandlerFactory handlerFactory;
	private DatabaseConnect dbConnect;

	public MasterHttpHandler(TaskFactory tf) throws ClassNotFoundException,
			SQLException {
		this.tf = tf;
		handlerFactory = new HandlerFactory();
		this.dbConnect = new DatabaseConnect();
	}

	@Override
	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String user = request.getParameter("user");
		System.out.println(request.getAttribute(user));
		String pwd = request.getParameter("pwd");
		System.out.println(request.getAttribute(pwd));
		try {
			if (dbConnect.isPlayerNew(user, pwd)) {
				dbConnect.insertPlayer(user, pwd);
			} else {
				String correct = request.getParameter("correct");
				if ("true".equals(correct)) {
					dbConnect.addPoints(user, pwd);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * Enumeration<String> g = session.getAttributeNames();
		 * while(g.hasMoreElements()){ System.out.println(g.nextElement()); }
		 */

		Challenge currentTask = getTask();
		request.setAttribute("Task", currentTask);
		handlerFactory.handleTask(currentTask, target, baseRequest, request,
				response);

		System.out.println("Handled");

		baseRequest.setHandled(true);

	}

	private Challenge getTask() {
		return tf.getTask();
	}

}
