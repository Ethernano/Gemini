package view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class SwingView extends JPanel {

	private JProgressBar progressBar;
	private JTextField progressTextField;
	
	public SwingView() {
		super();
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressTextField = new JTextField();
		progressTextField.setPreferredSize( new Dimension( 300, 24 ) );
		this.add(progressBar);
		this.add(progressTextField);
	}
	
	public JProgressBar getProgressBar() {
		return this.progressBar;
	}
	
	public JTextField getProgressTextField() {
		return progressTextField;
	}
}
