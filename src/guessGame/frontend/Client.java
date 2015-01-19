package guessGame.frontend;

import guessGame.Task;
import guessGame.TaskType;
import guessGame.paint.message.PaintMessage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.http.HttpField;
import org.eclipse.jetty.http.HttpFields;

public class Client extends JFrame {

	private ObjectInputStream in;
	private ObjectOutputStream out;
	private final TaskPanel taskPanel;
	private final AnswerPanel lowerPanel;
	private final JButton nextButton;
	private final JButton pointsButton;
	private final HttpClient client;
	private final TaskPanelFactory taskPanelFactory;
	private final LogIn logIn;
	private String userName;
	private String password;

	public Client() throws Exception {

		this.setTitle("Client Game");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// this.setLocationRelativeTo(null);
		this.setSize(800, 600);

		this.taskPanel = new TaskPanel();
		this.taskPanelFactory = new TaskPanelFactory();
		this.taskPanel.setPreferredSize(new Dimension(600, 450));
		this.add(taskPanel, BorderLayout.NORTH);

		// JPanel pan = new JPanel();
		// pan.setLayout(new GridLayout(1, 3));
		// pan.add(new TextPanel());
		// pan.add(new PictureTaskPanel());
		// pan.add(new PaintMessagePanel());
		// add(pan, BorderLayout.NORTH);

		this.lowerPanel = new AnswerPanel(this);

		this.nextButton = new JButton("Next");
		this.nextButton.addActionListener(new NextTaskListener());

		this.pointsButton = new JButton("How many points do I have?");
		this.pointsButton.addActionListener(new PointsListener());

		lowerPanel.add(nextButton, BorderLayout.WEST);
		lowerPanel.add(pointsButton);
		lowerPanel.setPreferredSize(new Dimension(600, 100));
		this.add(lowerPanel, BorderLayout.SOUTH);

		System.out.println("works? ");
		this.setVisible(true);
		client = new HttpClient();
		client.start();

		this.userName = "";
		this.password = "";

		logIn = new LogIn(this);
		logIn.setVisible(true);

	}

	public HttpClient getHttpClient() {
		return client;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void readInTask(HttpClient client, String typeOfRequest) throws InterruptedException, ExecutionException,
			TimeoutException {
		// this.taskPanel.repaint(new ClearMessage());

		// Request req = client.POST("http://localhost:8080/?user=rfriedman");

		final ContentResponse res = client.GET("http://localhost:8080/?user=" + userName + "&pwd=" + password
				+ "&typeOfRequest=" + typeOfRequest);
		final HttpFields headers = res.getHeaders();
		final Iterator<HttpField> iter = headers.iterator();
		while (iter.hasNext()) {
			System.out.println("HEADERS " + iter.next());
		}
		System.out.println(res.getRequest().getAttributes());
		final Object m = res.getHeaders();
		Object obj = null;

		try {
			final ObjectInputStream inStream = new ObjectInputStream(new ByteArrayInputStream(res.getContent()));
			obj = inStream.readObject();
			final Task task = (Task) obj;
			System.out.println("ANSWER " + task.getAnswer());
			if (TaskType.TEXT.equals(task.getTaskType()) && "".equals(task.getAnswer())) {
				JOptionPane.showMessageDialog(this, task.getChallenge());
				if ('U' == ((String) task.getChallenge()).charAt(0)) {
					logIn.setVisible(true);
					this.disable();
				}
			} else {
				addTask(task);
			}
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void addPaintTask(Object obj) {
		this.taskPanel.removeAll();
		final Task g = (Task) obj;
		final PaintMessage h = (PaintMessage) g.getChallenge();
		final String answer = g.getAnswer();
		this.lowerPanel.setAnswer(answer);
		// this.taskPanel.repaint(h); commented out mmandel
		this.taskPanel.repaint();
		this.repaint();
	}

	private void addTask(Task task) throws IOException {
		this.taskPanel.removeAll();
		final TaskPanel p = taskPanelFactory.generatePanel(task.getChallenge(), task.getTaskType());
		this.taskPanel.add(p);
		this.lowerPanel.setAnswer(task.getAnswer());
		p.repaint();

	}

	private class NextTaskListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				readInTask(client, "skip");
			} catch (InterruptedException | ExecutionException | TimeoutException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	private class PointsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				readInTask(client, "points");
			} catch (InterruptedException | ExecutionException | TimeoutException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public static void main(String[] main) throws UnknownHostException, IOException, ClassNotFoundException {

		try {
			new Client();
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
