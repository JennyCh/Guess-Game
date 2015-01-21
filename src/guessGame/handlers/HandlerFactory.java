package guessGame.handlers;

import guessGame.Challenge;
import guessGame.TaskType;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class HandlerFactory {

	private final HashMap<TaskType, AbstractHandler> handlerMap;

	public HandlerFactory() {
		handlerMap = new HashMap<TaskType, AbstractHandler>();
		handlerMap.put(TaskType.BINARY, new BinaryResponseHandler());
		handlerMap.put(TaskType.JPEG, new JPEGResponseHandler());
		handlerMap.put(TaskType.GIF, new GIFResponseHandler());
		handlerMap.put(TaskType.TEXT, new TextResponseHandler());
		handlerMap.put(TaskType.PNG, new PNGResponseHandler());
	}

	public void handleTask(Challenge currentTask, String target, Request baseRequest, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			System.out.println(currentTask.getTaskType());
			System.out.println(target);
			System.out.println(baseRequest);
			System.out.println(request);
			System.out.println(response);
			handlerMap.get(currentTask.getTaskType()).handle(target, baseRequest, request, response);
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
