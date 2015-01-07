package guessGame.frontend;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LowerPanel extends JPanel {
	/*
	 * Lower Panel contains a text answer, no matter what type the task is
	 */
	//TODO
	//check that user answer is contained in STring
	// no try again, display correct answer
	//points you earned
	
	private final JTextField typeAnswer;
	private final JLabel laber;
	private final JButton button;
	private String answer;

	public LowerPanel() {
		this.setAnswer("");

		this.typeAnswer = new JTextField(50);
		this.typeAnswer.setText("Type your answer here");
		this.laber = new JLabel("Type in your answer");
		this.button = new JButton("Submit");
		this.button.addActionListener(new CheckAnswerListener());

		this.add(laber, BorderLayout.NORTH);
		this.add(typeAnswer, BorderLayout.CENTER);
		this.add(button, BorderLayout.SOUTH);
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	private class CheckAnswerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(answer);
			if (answer.equals(typeAnswer.getText())) {

				JOptionPane.showMessageDialog(null, "Correct");
			} else {
				JOptionPane.showMessageDialog(null, "Try Again");
			}

		}

	}

}
