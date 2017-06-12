

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Atila
 * Runs the world, it takes an input coordinate from the user and prints out the
 * 5 closet events to this coordinate with their ID, cheapest ticket price and
 * distance from the location
 */
public class EventLocator {
    
    
    public static void main(String [] args) throws IOException{
        
        //Boolean to see if coordiantes are accepted
        boolean inputCheck = true;
        
        //x and y coordinates of which will be overwritten by input
        int x = 0;
        int y = 0;
        
        //To hold user input
        String userInput;
        
        BufferedReader stdIn;
        //Instantiate ability to get user input
        stdIn = new BufferedReader(new InputStreamReader(System.in));
        
        //Retrieve inputs from user until a valid one is inputted
        do{
            
            System.out.println("Please input Coordiantes:");
        
            userInput = stdIn.readLine();
            
            //Split the read in string by using a comma as a seperater
            String[] coordinate = userInput.split(",");
            
            //Get the x coordiante
            x = Integer.parseInt(coordinate[0]);
            //Get the y coordiate
            y = Integer.parseInt(coordinate[1]);
   
            //Check if the numbers retrieved can be used in the world
            if((x <= 10 && x >= -10) && (y<=10 && y >= -10) ){
                inputCheck = false;
            }
       
        }while(inputCheck);
        
            
        //Create the world 
        Grid map = new Grid(21,21);
            
        //Get the location of the coordinate inputted by the user
        Coordinate location = Grid.getLocation(x, y);
        //Set the distances for all events realtive to this coordinate
        map.setDistances(location);
        //Get the 5 closest events
        ArrayList<Event> events = map.getClosetEvents();
        
        System.out.println("\nHere are the 5 closest events to your chosen location (" + x+","+y+") !");
        System.out.println("Each event shows the cheapest ticket and also their distance away "+
                "from your chosen location:\n");
        //Print out a list of the 5 closest events with cheapest ticket and their distance
        for(Event event : events){
            String cheapestPrice = String.format("%.2f", event.getCheapestTicket());
            System.out.println("Event "+event.getId()+" - $"+
                    cheapestPrice+", Distance " + event.getDistance());
        }   
    }
}
    

