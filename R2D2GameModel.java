/* By: Anthony Morla
 * Team: R2 - D2
 * GameModel.java Store data, make simple logical 
 * decisions about that data, and bundle board 
 * state to pass to the GameController.
 */

public class R2D2GameModel implements GameModel
{
}

public class Board
{	

	// A 2D int array [xcoord.][ycoord.]
	public static int[][] boardArray;
	
	// Resets boardArray to a 2D array all 0's - 15x15
	public void newBoard() 
	{
		this.boardArray = new int[15][15];
	} // end on newBoard()
	
	public boolean setMarker(int x, int y, int value)
	{
		int curVal = this.boardArray[x][y];	
		if (curVal == 0)
		{
			//position unmarked - set marker!
			this.boardArray[x][y] = value;
			return true;
		}
		else
		{	// Generate a specific error given issue with marker...
			switch(curVal)
			{
			case 1:
				if(value == 1){ 
					System.out.println("You already placed a marker here!");
					return false;
				} else {
					System.out.println("Your opponent already placed a marker here!");
					return false;
				}
				break;
				
			case 2:
				if(value == 2){ 
					System.out.println("You already placed a marker here!");
					return false;
				} else {
					System.out.println("Your opponent already placed a marker here!");
					return false;
				}
				break;
				
			default: return false;
				break;
			} //end of switch
		} //end of else
	} // end of setMarker()
	
	public int checkStatus(int x, int y)
	{
		//give coordinates is start point of check, should be last marker set.
		// matchVal is the value to be matched 5 in a row uses passed index value
		int matchVal = this.boardArray[x][y];
		// winner 0 = no winner, 1 = player one winner, 2 = player two winner
		int winner = -1;
		int vertCount = vertCheck(x, y, matchVal);
		int diagCount = diagCheck(x, y, matchVal);
		int horzCount = horzCheck(x, y, matchVal);
		
		if(vertCount >= 5 || diagCount >= 5 || horizCount >= 5)
		{
			return matchVal;
		}
		else
		{
			return 0;
		}
	}

	
/* Methods below used for checkStatus */
	public int vertCheck(int x, int y, int matchVal)
	{
	    int count = 1; // one since matchVal is already a match
		boolean flag = true;
		int xCheck = x;
		int yCheck = y;
		
		// check positive vertial values above starting point
		while(flag)
		{
			if( this.boardArray[xCheck][yCheck] == matchVal){
				count++;
				yCheck--;
			}
			else{
				// match not found/not consecutive stop search!!
				flag = false;
			}
			// Stop once checked all!
			if(yCheck < 0 )
				flag = false;
		}
		// reset variables
		flag = true;
		yCheck = y;
		// check all negative vertical values below starting point
		while(flag)
		{
			if( this.boardArray[xCheck][yCheck] == matchVal){
				count++;
				yCheck++;
			}
			else{
				// match not found/not consecutive stop search!!
				flagPos == false;
			}
			// OR  stop once checked all!
			if(yCheck > 14 )
				flagPos = false;
		}
		return count;
	} // end of vertCheck
	
	
	public int horzCheck(int x, int y, int matchVal)
	{
	    int count = 0; // one since matchVal is already a match
		boolean flag = true;
		int xCheck = x;
		int yCheck = y;
		
		// check positive vertial values above starting point
		while(flag)
		{
			if( this.boardArray[xCheck][yCheck] == matchVal){
				count++;
				xCheck--;
			}
			else{
				// match not found/not consecutive stop search!!
				flag = false;
			}
			// Stop once checked all!
			if(yCheck < 0 )
				flag = false;
		}
		// reset variables
		flag = true;
		xCheck = x;
		// check all negative vertical values below starting point
		while(flag)
		{
			if( this.boardArray[xCheck][yCheck] == matchVal){
				count++;
				xCheck++;
			}
			else{
				// match not found/not consecutive stop search!!
				flagPos == false;
			}
			// OR  stop once checked all!
			if(xCheck > 14 )
				flagPos = false;
		}
		return count;
	} //end of horzCheck
	
	public int diagCheck(int x, int y, int matchVal)
	{
	    int count = 0; // one since matchVal is already a match
		boolean flag = true;
		int xCheck = x;
		int yCheck = y;
		
		// check positive vertial right direction values above starting point
		while(flag)
		{
			if( this.boardArray[xCheck][yCheck] == matchVal){
				count++;
				yCheck--;
				xCheck++;
			}
			else{
				// match not found/not consecutive stop search!!
				flag = false;
			}
			// Stop once checked all!
			if(yCheck < 0 || xCheck > 14 )
				flag = false;
		}
		// reset variables
		flag = true;
		xCheck = x;
		yCheck = y;
		// check all negative vertical values below starting point
		while(flag)
		{
			if( this.boardArray[xCheck][yCheck] == matchVal){
				count++;
				yCheck++;
				xCheck--;
			}
			else{
				// match not found/not consecutive stop search!!
				flagPos == false;
			}
			// OR  stop once checked all!
			if(xCheck < 0 || yCheck > 14 )
				flagPos = false;
		}
		
		if (count > 4){
		return count;
		} else {
			//reset all variables for left diag direction!
			flag = true;
			yCheck = y;
			xCheck = x;
			counter = 0;
		}
	
		// check positive vertial left direction values above starting point
		while(flag)
		{
			if( this.boardArray[xCheck][yCheck] == matchVal){
				count++;
				yCheck--;
				xCheck--;
			}
			else{
				// match not found/not consecutive stop search!!
				flag = false;
			}
			// Stop once checked all!
			if(yCheck < 0 || xCheck < 0 )
				flag = false;
		}
		// reset variables
		flag = true;
		xCheck = x;
		yCheck = y;
		// check all negative vertical values below starting point
		while(flag)
		{
			if( this.boardArray[xCheck][yCheck] == matchVal){
				count++;
				yCheck++;
				xCheck++;
			}
			else{
				// match not found/not consecutive stop search!!
				flagPos == false;
			}
			// OR  stop once checked all!
			if(xCheck > 14 || yCheck > 14 )
				flagPos = false;
		}
		return count;
	} //end of diag check

/* Methods above used for checkStatus */	
	
	
	
	
} //end of Board class
	

//Class called Board within we want:
	//- getBoard - array
	//- winner - 0, 1, 2
	//- currentTurn 1, 2
	//- 
	
	
	// How to store game board...
	// Array of arrays [0][9]
	
	// Each entry will consist of a position and a value
	// The board will be 15X15 squares, each square will get its own entry into the map
	
	// At this location its current state will be represented by an integer:
	// 0 = no marker, 1 = player one's marker, 2= player's two marker
	
	// GUI will have to read this 2d array and represent the sqares accordingly
	// getBoard() will return the map to the GUI
	// newBoard() sets all positions to 0 -- clear
	
	// setMarker(String pos, int val) will set the given square to the given value as long as there is no conflict - returns bool 
	// will return false if spot is already being used/marked example: setMarker("12-13", 1) player one at 12 (x) 13(y)
	
	// checkStatus will be called after every setMarker call to see if there is a winner returns int (0 no winner, 1 player one wins, 2...)
	
	// this method will use a loop to cycle through each row of the baord
	//conditions to add point to p1 or p2 scores will include:
	// if player has marker in square add one, if previous position x axis +/- 1 has marker OR prev. position +/- y axis has marker for that player
	
	// check immediate area then follow if x follow x if y follow y if diag follow diag
	
	
	