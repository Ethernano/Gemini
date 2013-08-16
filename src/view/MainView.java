package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.tree.DefaultTreeModel;

public class MainView extends JFrame {

    private JTextArea textArea;
    private JTextField textField;
    private JProgressBar progressBar;
    private JTree tree;
    private JPanel mainPanel;
    private JPanel oldPanel = new JPanel();
    
    private SwingView swingView = new SwingView();
    
    public MainView() {
        /* Construction de l'interface graphique. */
        super("SwingWorkerDemo");
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        textArea = new JTextArea(12, 40);
//        textArea.setEnabled(false);
//        textField = new JTextField(5);
//        textField.setEnabled(false);
//        progressBar = new JProgressBar();
//        JPanel content = new JPanel(new BorderLayout());
//        content.add(new JScrollPane(textArea,
//		                            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
//				                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));

        //south.add(textField, BorderLayout.EAST);
//        content.add(south, BorderLayout.SOUTH);
//        JPanel content2 = new JPanel(new BorderLayout());
        mainPanel = new JPanel();
        mainPanel.add(swingView);
        
//        
//        tree = new JTree(new DefaultTreeModel(null));
//        JScrollPane scrollPane = new JScrollPane(tree);
//        scrollPane.setPreferredSize(new Dimension(350, 500));
//        content2.add(scrollPane);
//        mainPanel.add(content2, BorderLayout.WEST);
        setContentPane(mainPanel);

        setLocation(100, 100);
        this.setSize(800, 600);
        setVisible(true);
    }

    

	public JTextArea getTextArea() {
		return textArea;
	}

	public JTextField getTextField() {
		return textField;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}



	public JTree getTree() {
		return tree;
	}



	public void setTree(JTree tree) {
		this.tree = tree;
	}

	public void setEastPanel(JPanel panel) {
		panel.setPreferredSize(new Dimension(400,500));
		mainPanel.remove(oldPanel);
		oldPanel = panel;
		mainPanel.add(panel, BorderLayout.EAST);
		this.validate();
		this.repaint();
	}



	public SwingView getSwingView() {
		return swingView;
	}
	
	
}