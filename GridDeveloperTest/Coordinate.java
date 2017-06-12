

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Atila
 * Represents a coordinate to be used with the grid
 */
public class Coordinate {

    int x;
    int y;
    
    //initialses a coordinate with an x an y value
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //get the x coordinate
    public int getX() {
        return x;
    }

    //get the y coordinate
    public int getY() {
        return y;
    }

    //set the x coordinate
    public void setX(int x) {
        this.x = x;
    }

    ///set the y coordinate
    public void setY(int y) {
        this.y = y;
    }

}
