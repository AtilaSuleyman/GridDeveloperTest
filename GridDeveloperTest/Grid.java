

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Atila
 * represents the world as a 2-D array, all methods used in this class are to
 * interact with the world
 */
public class Grid {

    //The world
    Event[][] grid;
    //Holds a list of all event locations
    private final ArrayList<Coordinate> eventLocations;
    
    public Grid(int row, int col){
        //initialse a grid to a specified size
        grid = new Event[row][col];
        eventLocations  = new ArrayList<>();
        setEvents();
    }

    /**
     * Gets the list of all coordinates for all events 
     * @return event locations
     */
    public ArrayList<Coordinate> getEventLocations() {
        return eventLocations;
    }

    /**
     * Gets an 2-D array representation of the world
     * @return the grid
     */
    public Event[][] getGrid() {
        return grid;
    }
    
    /**
     * Gets the middle of the world 
     * @return the coordinates that represent the middle of the world
     */
    public static Coordinate getOrigin(){
        Coordinate origin = new Coordinate(10,10);
        return origin;
    }
    
    /**
     * Gets the location of the coordinate inputted by the user
     * @param x x coordinate
     * @param y y coordinate
     * @return the location of the coordinate on the world
     */
    public static Coordinate getLocation(int x, int y){
        //All coordinates are relative to the origin
        Coordinate origin = getOrigin();
        int column = origin.getY();
        int row = origin.getX();
        //Location gets reassigned during the if statement
        Coordinate location = null;
        //Statement covers cases where the inputted coordinates are 0's
        if(row == 0){
            location = new Coordinate(10, column - y);
        }
        else if(column == 0){
            location = new Coordinate(row + x, 10);           
        }
        else if(row == 0 && column ==0){
            location = new Coordinate(10, 10);   
        }
        //if coordinate does not contain 0
        else{
            location = new Coordinate(row + x, column - y);
        }
        return location;
    }
    
    /**
     * Populates the world randomly with events 
     */
    private void setEvents(){
      
        int i = 1;
        /**
         * Generate a random number between 100 and 201, this will be the number
         * of events in the world
         */
        int randomNum = ThreadLocalRandom.current().nextInt(100, 201);
        while(i<=randomNum){
            //Generate a random x and y coordinate for an event
            int x = ThreadLocalRandom.current().nextInt(0, 21);
            int y = ThreadLocalRandom.current().nextInt(0, 21);
            //if there is not an event already in the location in the world
            if(grid[x][y]==null){
                //populate it
                Event event = new Event();
                grid[x][y] = event;
                Coordinate eventLocation = new Coordinate(x,y);
                //add it to event locations
                eventLocations.add(eventLocation);
                i++;
            }
        }
    }
    
    /**
     * Set the distance of all events relative the the coordinate inputted by the user
     * @param inputCoord the users inputted coordinate
     */
    public void setDistances(Coordinate inputCoord){
        //for each coordinate of all event locations
        for(Coordinate eventLocation : eventLocations){
            int x = eventLocation.getX();
            int y = eventLocation.getY();
            //Calculate Manhattan distance, sum of the absolute horizonatal and vertical distances
            int distance = Math.abs(inputCoord.getX() - x) + Math.abs((inputCoord.getY()) - y);
            //Set the distance
            grid[x][y].setDistance(distance);
        }
    }
    
    /**
     * Gets the 5 closet events relative to the inputted coordinate
     * @return 5 closest events
     */
    public ArrayList<Event> getClosetEvents(){
        ArrayList<Event> events = new ArrayList<>();
        ArrayList<Event> closetEvents = new ArrayList<>();
        //add all events that have tickets to the event list
        for(Coordinate eventLocation : eventLocations){
            int x = eventLocation.getX();
            int y = eventLocation.getY();
            if(!grid[x][y].getTickets().isEmpty()){
                events.add(grid[x][y]);
            }
        }
        //prioritise events in terms of their distance
        Collections.sort(events);
        
        //get the 5 closest events
        for(int i = 0; i<=4; i++){
            closetEvents.add(events.get(i));
        }
        return closetEvents;
    }
}
