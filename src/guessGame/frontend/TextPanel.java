package guessGame.frontend;

import java.io.IOException;
import javax.swing.JLabel;

public class TextPanel extends TaskPanel{
	
	private JLabel label;
	
	public TextPanel(){
		this.label = new JLabel();
		add(label);
	}

	public void addTask(Object text) throws IOException {
		removeAll();
		label = new JLabel();
		label.setText((String)text);
		System.out.println((String)text);
		add(label);
	}
	
}
