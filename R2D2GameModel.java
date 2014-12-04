
public class R2D2GameModel implements GameModel
{
		
		private final int[][] boardArray = new int[15][15];
		private int gameWinner = 0;
		private int currentPlayer = 1; 
		
		
		@Override
		/**
		 * Sets marker in array return bool if valid move */
		public boolean setMarker(int x, int y, int value)
		{
			int curVal = this.boardArray[x][y];	
                        System.out.println(curVal + " " + value + " " + currentPlayer);
			if (curVal == 0 && value == currentPlayer)
			{
			    boardArray[x][y] = value; //position unmarked - set marker!
			    //gameWinner = setMarkerCheck(x, y); //update the game winner check from last marker set
                            updatePlayer(); //changes who's turn it is
                            return true;
			}
			else
			{	// return false marker already used!!!
                            return false;
			} 
		} 
		
		@Override
		/**
		 * Returns the game winner int */
		public int checkStatus(){
			return gameWinner;
		}
		
		@Override
		/**
		 * Return the current player number */
		public int getActivePlayer(){
			return currentPlayer;
		}
		
		
		/**
		 * Methods below updates the current player int var */
		private void updatePlayer() {
			if( currentPlayer == 1)
			{
				currentPlayer = 2;
			}
			else if( currentPlayer == 2) {
				currentPlayer = 1;
			}
				
		}
		
		/**
		 * This checks if there is a winner, will be run after each marker set */
		private int setMarkerCheck(int x, int y)
		{
			//give coordinates is start point of check, should be last marker set.
			// matchVal is the value to be matched 5 in a row uses passed index value
			int matchVal = this.boardArray[x][y];
			// winner 0 = no winner, 1 = player one winner, 2 = player two winner
			int winner = -1;
			int vertCount = vertCheck(x, y, matchVal);
			int diagCount = diagCheck(x, y, matchVal);
			int horzCheck = horzCheck(x, y, matchVal);
			
			if(vertCount >= 5 || diagCount >= 5 || horzCheck >= 5)
			{
				return matchVal;
			}
			else
			{
				return 0;
			}
		}

		
		/**
		 * (1/3) Used for vertcal +/- check */
		private int vertCheck(int x, int y, int matchVal)
		{
		    int count = 1; // one since matchVal is already a match
			boolean flag = true;
			boolean flagPos = true;
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
					flagPos = false;
				}
				// OR  stop once checked all!
				if(yCheck > 14 )
					flagPos = false;
			}
			return count;
		} // end of vertCheck
		
		/**
		 * (2/3) Used for horizontal +/- check */
		private int horzCheck(int x, int y, int matchVal)
		{
		    int count = 0; // one since matchVal is already a match
			boolean flag = true;
			boolean flagPos = true;
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
					flagPos = false;
				}
				// OR  stop once checked all!
				if(xCheck > 14 )
					flagPos = false;
			}
			return count;
		} //end of horzCheck
		
		/**
		 * (3/3) Used for diagnal +/- check in both directions */
		private int diagCheck(int x, int y, int matchVal)
		{
		    int count = 0; // one since matchVal is already a match
			boolean flag = true;
			boolean flagPos = true;
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
					flagPos = false;
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
				flagPos = true;
				yCheck = y;
				xCheck = x;
				count = 0;
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
					flagPos = false;
				}
				// OR  stop once checked all!
				if(xCheck > 14 || yCheck > 14 )
					flagPos = false;
			}
			return count;
		} //end of diag check
		
} 

// end of class
	

/*********************************************************

 ** Some Additional Information: 
 **  Team R2-D2 presents... Five in a Row
----------------------------------------------------------
* Each entry will consist of a position and a value
* The board will be 15X15 squares, each square will get its own entry into the map

* At this location its current state will be represented by an integer:
*  0 = no marker, 1 = player one's marker, 2= player's two marker

* setMarker(String pos, int val) will set the given square to the given value as long as there is no conflict - returns bool 
* will return false if spot is already being used/marked example: setMarker("12-13", 1) player one at 12 (x) 13(y)

* checkStatus will be called after every setMarker call to see if there is a winner returns int (0 no winner, 1 player one wins, 2...)
* this method will use a loop to cycle through each row of the baord
* conditions to add point to p1 or p2 scores will include:
* if player has marker in square add one, if previous position x axis +/- 1 has marker OR prev. position +/- y axis has marker for that player
* check immediate area then follow if x follow x if y follow y if diag follow diag

*********************************************************/	
	
// end of file	