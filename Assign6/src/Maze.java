/************************************************************
 *                                                          *
 *  CSCI 470-1/502-1    Assign 6               Spring 2019  *
 *                                                          *
 *  Programmer:  Ryan Towner      z1774451                  *  
 *                                                          *
 *  Date Due:    4/12/2019                                  *
 *                                                          *
 ************************************************************/

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

public class Maze {
	
	//Vars
	private int rows = 0;
	private int columns = 0;
	private static final int maxRow = 30;
	private static final int maxCol = 30;
	
	int startRow = 0;
	int startCol = 0;
	int endRow = 0;
	int endCol = 0;
	
	//Objects
	BufferedReader reader = null;
	MazeSquare[][] ms = new MazeSquare[maxRow][maxCol];

	//Function: readMaze()
	//Args: the input txt file
	//Parses the text file for the maze size and the cells
	//Gets the start and end cell locations and creates the maze squares
	//Returns: Nothing
	public void readMaze(File infile) {
		try {
			reader = new BufferedReader(new FileReader(infile));
			String text = null;
			
			text = reader.readLine();
			rows = Integer.parseInt(text);
			text = reader.readLine();
			columns = Integer.parseInt(text);
			
			for(int i = 0; i < rows; i++) {
				for(int j = 0; j <= columns+1; j++) {
					char c = (char) reader.read();
					//Get start square
					if(c == 's') {
						startRow = i;
						startCol = j;
					}
					//Get end square
					else if (c == 'e') {
						endRow = i;
						endCol = j;
					}
					//Create square
					ms[i][j] = new MazeSquare(i,j,c);
				}
			}
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException  n) {
			JOptionPane.showMessageDialog(null, "Number Format Exception. File is not correct format.", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (NoSuchElementException s) {
			JOptionPane.showMessageDialog(null, "No Such Element Exception. File is not correct format.", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "IO Exception.", "Error", JOptionPane.ERROR_MESSAGE);
		} finally {
			if (reader != null) {
	            try {
					reader.close();
				} catch (IOException e) {
				}
	        }
		}
	}
	
	//Function: clearMazePath()
	//Args: None
	//Clears all the squares from the solve
	//Returns: Nothing
	public void clearMazePath() {
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				ms[i][j].clearSquare();
			}
		}
	}
	
	//Function: drawMaze()
	//Args: Graphics, starting square x and y
	//Draws all of the mazes squares
	//Returns: Nothing
	public void drawMaze(Graphics g, int startX, int startY) {
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				ms[i][j].drawSquare(g, startX, startY);
			}
		}
	}
	
	//Function: solveMaze()
	//Args: None
	//Solves the maze and returns true if solved or false if no solve
	//Returns: True or False
	public boolean solveMaze() {
		boolean result = false;
		result = solveMaze(startRow, startCol);
		
		return result;
	}
	
	//Function: solveMaze()
	//Args: square row and col
	//Recursively traverses maze to find exit. And Creates the correct path if available 
	//Returns: True or False if maze could be solved
	private boolean solveMaze(int row, int col) {
		 if(ms[row][col].st == SquareType.WALL)
			 return false;
		 if(ms[row][col].st == SquareType.PATH)
			 return false;
		 if(ms[row][col] == ms[endRow][endCol]) {
			 ms[row][col].setToPath();
			 return true;
		 }
		 
	     ms[row][col].st = SquareType.PATH;

	     //Down
	     if(ms[row][col].row == rows) {
	    	 ms[row][col].row -= 1;
	     }
	     else if ((solveMaze(row + 1, col)) == true) {
	         return true;
	     }
	     //Left
	     if(ms[row][col].col == 0) {
	    	 ms[row][col].col += 1;
	     }
	     else if ((solveMaze(row, col - 1)) == true) {
	         return true;
	     }
	     //Right
	     if(ms[row][col].col == columns) {
	    	 ms[row][col].col -= 1;
	     }
	     else if ((solveMaze(row , col + 1)) == true) {
	         return true;
	     }
	     //Up
	     if(ms[row][col].row == 0) {
	    	 ms[row][col].row += 1;
	     }
	     else if ((solveMaze(row - 1 , col)) == true) {
	         return true;
	     }       

	     ms[row][col].st = SquareType.TRIED;
	      
	     return false;
	}
	
	//Getters
	public int getRows() {
		return rows;
	}
	
	public int getColumns() {
		return columns;
	}
}
