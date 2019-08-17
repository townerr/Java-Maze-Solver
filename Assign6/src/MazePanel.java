/************************************************************
 *                                                          *
 *  CSCI 470-1/502-1    Assign 6               Spring 2019  *
 *                                                          *
 *  Programmer:  Ryan Towner      z1774451                  *  
 *                                                          *
 *  Date Due:    4/12/2019                                  *
 *                                                          *
 ************************************************************/

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

import javax.swing.JPanel;

public class MazePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	//Vars
	boolean solutionAttempted = false;
	boolean solutionFound = false;
	
	//Object
	Maze m = null;
	
	//Function: readMaze()
	//Args: input file
	//Reads the maze and updates panel graphics
	//Returns: nothing
	public void readMaze(File inFile) {
		solutionAttempted = false;
		solutionFound = false;
		m = new Maze();
		m.readMaze(inFile);
		
		repaint();
	}
	
	//Function: clearMazePath()
	//Args: None
	//Clears the solved maze path and updates panel graphics
	//Returns: Nothing
	public void clearMazePath() {
		solutionAttempted = false;
		solutionFound = false;
		
		m.clearMazePath();
		
		repaint();
	}
	
	//Function: solveMaze()
	//Args: None
	//
	//Returns: 
	public void solveMaze() {
		solutionAttempted = true;
		solutionFound = m.solveMaze();
		
		repaint();
	}
	
	//Function: paintComponent()
	//Args: Graphics object
	//Draws the maze and solves/no solution
	//Returns: Nothing
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		getSize();
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, 500, 500);
		
		
		if(m != null) {
			//Draw maze in center of panel
			m.drawMaze(g, (this.getWidth() / 2) - ((15 * m.getColumns()) / 2), (this.getHeight() / 2) - ((15 * m.getRows()) / 2));
		}
		
		//Draw text if solve was sucessfull or not
		if(solutionAttempted == true && solutionFound == true) {
			g.drawString("Solved!", (this.getWidth() / 2) - 50, this.getHeight() - 20);
		}
		else if(solutionAttempted == true && solutionFound == false) {
			g.drawString("No solution found.", (this.getWidth() / 2) - 50, this.getHeight() - 20);
		}
	}
}
