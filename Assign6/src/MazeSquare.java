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

enum SquareType {
	WALL, SPACE, PATH, TRIED
}

public class MazeSquare {
	//Vars
	static final int squareX= 15;
	static final int squareY= 15;
	
	int col = 0;
	int row = 0;
	boolean visited = false;
	
	//Enum object
	SquareType st;
	
	//Function: MazeSquare() constructor
	//Args: squares row col and text letter
	//Assigns the square a type
	//Returns: nothing
	public MazeSquare(int row, int col, char c) {
		this.row = row;
		this.col = col;
		if(c == '#') {
			this.st = SquareType.WALL;
		}
		else if(c == '.') {
			this.st = SquareType.SPACE;
		}
		else if(c == 's') {
			this.st = SquareType.SPACE;
		}
		else if(c == 'e') {
			this.st = SquareType.SPACE;
		}
	}
	
	//Function: clearSquare()
	//Args: none
	//Sets square back to regular space
	//Returns: nothing
	public void clearSquare() {
		this.visited = false;
		if(this.st == SquareType.PATH)
			this.st = SquareType.SPACE;
	}
	
	//Function: markVisited()
	//Args: none
	//Marks the square as visited so dont visited again in solve alg
	//Returns: nothing
	public void markVisited() {
		visited = true;
	}
	
	//Function: getVisited()
	//Args: none
	//Getter for if square has been visited
	//Returns: nothing
	public boolean getVisited() {
		return visited;
	}
	
	//Function: isWall()
	//Args: none
	//Returns if cell is a wall or not
	//Returns: true or false
	public boolean isWall() {
		if (this.st == SquareType.WALL) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//Function: setToPath()
	//Args: none
	//Sets a square to path for solve
	//Returns: nothing
	public void setToPath() {
		this.st = SquareType.PATH;
	}
	
	//Function: drawSquare()
	//Args: graphics, starting x and y
	//Sets and fills square color based on type
	//Returns: nothing
	public void drawSquare(Graphics g, int startX, int startY) {
		if(this.st == SquareType.WALL) {
			g.setColor(Color.darkGray);
		}
		else if(this.st == SquareType.SPACE) {
			g.setColor(Color.white);
		}
		else if(this.st == SquareType.TRIED) {
			g.setColor(Color.white);
		}
		else if(this.st == SquareType.PATH) {
			g.setColor(Color.red);
		}
		int x = (startX + (this.col * 15));
		int y = (startX + (this.row * 15));
		g.fillRect(x, y, squareX, squareY);
	}
}
