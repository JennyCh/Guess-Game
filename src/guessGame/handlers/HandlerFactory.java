package guessGame.handlers;

import guessGame.Challenge;
import guessGame.TaskFactoryType;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class HandlerFactory {
	
	private HashMap<TaskFactoryType,AbstractHandler> handlerMap;
	public HandlerFactory(){
		handlerMap = new HashMap<TaskFactoryType,AbstractHandler>();
		handlerMap.put(TaskFactoryType.BINARY, new BinaryResponseHandler());
		handlerMap.put(TaskFactoryType.JPEG, new JPEGResponseHandler());
		handlerMap.put(TaskFactoryType.GIF, new GIFResponseHandler());
		handlerMap.put(TaskFactoryType.TEXT, new TextResponseHandler());
	}
	
	public void handleTask(Challenge currentTask,String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response){
		 try {
			handlerMap.get(currentTask.getTFType()).handle(target,  baseRequest,
						request, response);
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
