package guessGame.frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AnswerPanel extends JPanel {
	/*
	 * Lower Panel contains a text answer, no matter what type the task is
	 */
	// TODO
	// check that user answer is contained in STring
	// no try again, display correct answer
	// points you earned

	private final JTextField typeAnswer;
	private final JLabel laber;
	private final JButton button;
	private String answer;
	private final Client client;

	private final JButton nextTask;

	private final JLabel pointsLabel;

	private final JLabel submitResponse;

	private int points;

	public AnswerPanel(Client client) {
		this.client = client;
		this.setLayout(new GridLayout(4, 2));

		this.setAnswer("");
		points = 0;
		this.typeAnswer = new JTextField(50);
		this.typeAnswer.setText("Type your answer here");
		this.typeAnswer.addMouseListener(new AnswerMouseListener());
		this.laber = new JLabel("Type in your answer");
		this.button = new JButton("Submit");
		this.nextTask = new JButton("Next");
		this.pointsLabel = new JLabel("Today's Score: " + points);
		this.pointsLabel.setForeground(Color.DARK_GRAY);
		this.pointsLabel.setFont(new Font("Verdana", Font.BOLD, 14));
		this.submitResponse = new JLabel("");
		this.button.addActionListener(new CheckAnswerListener());
		this.nextTask.addActionListener(new CheckAnswerListener());

		this.add(laber);
		this.add(new JLabel(""));
		this.add(typeAnswer, BorderLayout.CENTER);
		this.add(submitResponse);
		this.add(button, BorderLayout.SOUTH);
		this.add(nextTask);
		this.add(pointsLabel);

	}

	public void setAnswer(String answer) {
		this.answer = answer;
		System.out.println(answer);
	}

	private class AnswerMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			typeAnswer.setText("");
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	private class CheckAnswerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String typeOfRequest = null;

			if (e.getSource() == button) {
				if (answer.equals(typeAnswer.getText())) {
					// JOptionPane.showMessageDialog(null, "Correct");
					submitResponse.setText("Correct");
					submitResponse.setForeground(Color.green);
					points += 5;
					pointsLabel.setText("Today's Score: " + points);
					typeOfRequest = "submit";
				} else {
					// JOptionPane.showMessageDialog(null, "Incorrect");
					typeOfRequest = "skip";
					submitResponse.setText("Incorrect");
					submitResponse.setForeground(Color.red);
				}
			} else if (e.getSource() == nextTask) {
				if (answer.equals(typeAnswer.getText())) {
					typeOfRequest = "submit";
				} else {
					typeOfRequest = "skip";
				}
				try {
					submitResponse.setText("");
					client.readInTask(client.getHttpClient(), typeOfRequest);
				} catch (InterruptedException | ExecutionException | TimeoutException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			// System.out.println("COMPARE ANSWERS " + answer + " == " +
			// typeAnswer.getText());

		}

	}

}
