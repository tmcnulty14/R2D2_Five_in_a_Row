/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mcnultyt
 */
public interface ClientModel {
    /**
     * Called by the GUI when the player clicks a board location.
     * @param x The x-coordinate of the clicked location.
     * @param y The y-coordinate of the clicked location.
     */
    public void requestMove(int x, int y);
}
