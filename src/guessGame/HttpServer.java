package guessGame;

import javax.swing.JPanel;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.session.HashSessionIdManager;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;

public class HttpServer {

	private final Server server;
	private final HashSessionIdManager sessionIdManager;
	private final ContextHandler context;
	final HashSessionManager manager;
	final SessionHandler sessions;

	public HttpServer() throws Exception {
		server = new Server(8080);
		sessionIdManager = new HashSessionIdManager();
		context = new ContextHandler("/");
		manager = new HashSessionManager();
		sessions = new SessionHandler(manager);
		context.setHandler(sessions);

		server.setSessionIdManager(sessionIdManager);

		server.setHandler(new MasterHttpHandler(new TaskFactory()));
		server.start();
		server.join();
	}

	public static void main(String args[]) throws Exception {
		new HttpServer();
	}

}
