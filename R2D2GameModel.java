/* By: Anthony Morla
 * Team: R2 - D2
 * GameModel.java Store data, make simple logical 
 * decisions about that data, and bundle board 
 * state to pass to the GameController.
 */
package com.skilledmonster.examples.util.map;
import java.util.Set;
import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;


public class R2D2GameModel 
{
	
	// creates map for gamboard
	public bool initializeBoard {
		
		int gridSize = 15;
		int x = gridSize;
		int y = gridSize;
		MultiMap gameBoardMap = new MultiValueMap();
		
		while( x < 0 && y < 0)
		{
			String key = ""+x+"-"+y+"";
			gameBoardMap.put(key, 0);
			
			y --;
			
			if ( y < 0)
			{
				//reset y and decrease x
				y = gridSize;
				x--;
			}
			
		}
		
	}
	
	
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
	
	
	
}