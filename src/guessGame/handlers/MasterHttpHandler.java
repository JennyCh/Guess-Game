package guessGame.handlers;

import guessGame.Challenge;
import guessGame.DatabaseConnect;
import guessGame.Task;
import guessGame.TaskFactory;

import java.awt.Canvas;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JPanel;

import org.eclipse.jetty.client.HttpExchange;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;





public class MasterHttpHandler extends AbstractHandler  {
	private TaskFactory tf;
	private HandlerFactory handlerFactory;
	private DatabaseConnect dbConnect;
	
	public MasterHttpHandler(TaskFactory tf) throws ClassNotFoundException, SQLException{
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
			if(dbConnect.isPlayerNew(user, pwd)){
				dbConnect.insertPlayer(user, pwd);
			}else{
				dbConnect.addPoints(user, pwd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		/*
		Enumeration<String> g = session.getAttributeNames();
		while(g.hasMoreElements()){
			System.out.println(g.nextElement());
		}
		*/
		
		Challenge currentTask = getTask();
		request.setAttribute("Task", currentTask);
		handlerFactory.handleTask(currentTask ,target,  baseRequest,
				request, response);

		
		System.out.println("Handled");

		baseRequest.setHandled(true);

	}
	
	private Challenge getTask(){
		return  tf.getTask();
	}

}
