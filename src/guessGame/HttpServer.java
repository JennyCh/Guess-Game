package guessGame;

import javax.swing.JPanel;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.session.HashSessionIdManager;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;


public class HttpServer {

	public static void main(String args[]) throws Exception {
		Server server = new Server(8080);
		 // Specify the Session ID Manager
        HashSessionIdManager idmanager = new HashSessionIdManager();
        server.setSessionIdManager(idmanager);

        // Sessions are bound to a context.
        ContextHandler context = new ContextHandler("/");
        server.setHandler(context);

        // Create the SessionHandler (wrapper) to handle the sessions
        HashSessionManager manager = new HashSessionManager();
        SessionHandler sessions = new SessionHandler(manager);
        context.setHandler(sessions);
	    //ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		//ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        //context.setContextPath("/");
        //server.setHandler(h);
        //server.setSessionIdManager(sessionIdManager);
 /*
        context.addServlet(new ServletHolder(new HelloServlet()),"/*");
        context.addServlet(new ServletHolder(new HelloServlet("Buongiorno Mondo")),"/it/*");
        context.addServlet(new ServletHolder(new HelloServlet("Bonjour le Monde")),"/fr/*");
 */	
        context.setAttribute("LL", new JPanel());
		sessions.setHandler(new HttpHandler());
		server.start();
		server.join();
	}
	
}
