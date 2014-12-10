package Server;

/**
 * This game model class handles core game functions such as tracking
 * current player, initializing the board array and storing, as well as 
 * determining winning conditions and the associated winner
 * @author AnthonyMorla
 */

public class R2D2GameModel implements GameModel
{
    private final int[][] boardArray = new int[15][15];
    private int gameWinner = 0;
    private int currentPlayer = 1; 


    @Override
    /**
     * If the move is valid, sets the marker in the array and returns true.
     * Otherwise, returns false.
     **/
    public boolean setMarker(int x, int y, int value)
    {
        int curVal = boardArray[x][y];	
        if (curVal == 0 && value == currentPlayer)
        {
            boardArray[x][y] = value; //position unmarked - set marker!

            gameWinner = setMarkerCheck(x, y); //update the game winner check from last marker set
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
     * Returns the game winner int. 0 = game ongoing, 1 or 2 = winner */
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
     * Updates current player variable. */
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
        int matchVal = boardArray[x][y];

        System.out.println("set marker called... match val is "+matchVal+".");

        // winner 0 = no winner, 1 = player one winner, 2 = player two winner
        int vertCount = vertCheck(x, y, matchVal);
        int horizCount = horizCheck(x, y, matchVal);
        int diagCount = diagCheck(x, y, matchVal);

        System.out.println("Checker vert: "+vertCount+" diag: "+diagCount+" horz: "+horizCount);

        // If any counts return 5 in a row, then return the match value (player number)
        if(vertCount > 4 || horizCount > 4 || diagCount > 4)
        {
            return matchVal;
        }
        else
        {
            // if non of the counts are greater than 5 return 0
            return 0;
        }
    }

    /**
     * (1/3) Used for vertical (y) check */
    private int vertCheck(int x, int y, int matchVal)
    {
        int count = 0; // running counter for checker
        boolean flag = true; 
        y = 0; // vertical check start y at 0 - x will not change
        // check positive vertical values above starting point

        while(flag)
        {
            if( boardArray[x][y] == matchVal){
                count++;
            }
            else{
                // did not find match reset count
                count = 0;
            }

            if( count > 4 || y == 14)
            {
                // if we find 5 in a row or we are at the end then stop...
                flag = false;
            }

            //keep checking
            y++;
        }

        return count;
    } // end of vertCheck

    /**
     * (2/3) Used for horizontal (x) check */
    private int horizCheck(int x, int y, int matchVal)
    {
        int count = 0; // running counter for checker
        boolean flag = true; 
        x = 0; // vertical check start y at 0 - x will not change
        // check positive vertical values above starting point

        while(flag)
        {
            if( boardArray[x][y] == matchVal){
                count++;
            }
            else{
                    // did not find match stop if...
                count = 0;
            }

            if( count > 4 || x == 14)
            {
                // if we find 5 in a row or we are at the end then stop...
                flag = false;
            }

            //keep checking
            x++;
        }

        return count;

    } // end of horizCheck

    /**
     * (3/3) Used for diagonal (x/y) check */
    private int diagCheck(int x, int y, int matchVal)
    {
        int count = 0; // running counter for checker
        boolean flag = true; 
        int initialX = x; // this variable and below
        int initialY = y; // save initial value for later

        // first diagonal check left to right set starting point
        while(x!=0 && y!=0)
        {
            x--;
            y--;
        }

        // start left to right check with new starting points
        while(flag)
        {
            if( boardArray[x][y] == matchVal){
                count++;
                //System.out.println("Checking left to right:" +x+", "+y);
            }
            else{
                // did not find match stop if...
                count = 0;
            }

            if( count > 4 || y == 14 || x == 14)
            {
                // if we find 5 in a row or we are at the end then stop...
                flag = false;

            }

            //keep checking
            x++;
            y++;
        }

        if( count > 4)
        {
            return count;
        }

        // left to right diagonal check failed, try right to left from initial points
        x = initialX;
        y = initialY;
        flag = true;
        count = 0;

        // first diagonal check left to right set starting point
        while( x !=14 && y!=0)
        {
            x++;
            y--;
        }

        // start right to left check with new starting points
        while(flag)
        {
            if( boardArray[x][y] == matchVal) 
            {
                count++;
                //System.out.println("Checking right to left:" +x+", "+y);
            }
            else 
            {
                // did not find match stop if...
                count = 0;
            }

            if( count > 4 || y == 14 || x == 0)
            {
                // if we find 5 in a row or we are at the end then stop...
                flag = false;
            }

            //keep checking
            x--;
            y++;
        }

        return count;
    } // end of diagCheck
		
} // end of class
	

/*********************************************************

 ** Some Additional Information: 
 **  Team R2-D2 presents... Five in a Row
----------------------------------------------------------
* Each entry will consist of a position and a value
* The board will be 15X15 squares, each square will get its own entry into the map

* At this location its current state will be represented by an integer:
*  0 = no marker, 1 = player one's marker, 2= player's two marker
*********************************************************/	
	
// end of file	