/************************************************************
 *                                                          *
 *  CSCI 470-1/502-1    Assign 6               Spring 2019  *
 *                                                          *
 *  Programmer:  Ryan Towner      z1774451                  *  
 *                                                          *
 *  Date Due:    4/12/2019                                  *
 *                                                          *
 ************************************************************/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MazeApp extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	//Objects
	private JButton openButton;
	private JButton solveButton;
	private JButton clearButton;
	
	MazePanel mp = new MazePanel();
	final JFileChooser j = new JFileChooser();
	File inFile = null;

	//Main method
	//Creates MazeApp and init gui
	public static void main(String[] args) {
		MazeApp ma = new MazeApp("Maze Solver");
		ma.init();
	}
	
	//MazeApp Constructor
	//Sets title
	public MazeApp(String title) {
		super(title);
	}
	
	//Sets deafualt gui settings, sets listeners, and calls gui()
	public void init() {
		//build gui comps
		gui();
		
		//action listeners
		openButton.addActionListener(this);
		solveButton.addActionListener(this);
		clearButton.addActionListener(this);
		
		//default gui configs
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 600);
		
	}
	
	//Creates Gui components
	public void gui() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		openButton = new JButton("Open Maze File");
		openButton.setBounds(30, 520, 120, 25);
		panel.add(openButton);
		
		solveButton = new JButton("Solve Maze");
		solveButton.setBounds(195, 520, 100, 25);
		panel.add(solveButton);
		
		clearButton = new JButton("Clear Solution");
		clearButton.setBounds(340, 520, 120, 25);
		panel.add(clearButton);
		
		mp = new MazePanel();
		mp.setBounds(0, 0, 500, 500);
		panel.add(mp);
		
		add(panel);
	}

	//Button actions when clicked
	@Override
	public void actionPerformed(ActionEvent e) {
		//Open file dialog and read the maze file
		if(e.getSource() == openButton) {
			solveButton.setEnabled(true);
			clearButton.setEnabled(false);
			
			int r = j.showOpenDialog(this);
			if (r == JFileChooser.APPROVE_OPTION) {
				File inFile = j.getSelectedFile();
				mp.readMaze(inFile);
			}
		}
		//Solve the maze
		else if (e.getSource() == solveButton) {
			solveButton.setEnabled(false);
			clearButton.setEnabled(true);
			
			mp.solveMaze();
		}
		//Clear the maze
		else if (e.getSource() == clearButton) {
			solveButton.setEnabled(true);
			clearButton.setEnabled(false);
			
			mp.clearMazePath();
		}
	}
}
