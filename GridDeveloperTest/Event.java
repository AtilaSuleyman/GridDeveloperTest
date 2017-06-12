

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents an Event of which can store 0 or more tickets
 * the amount of tickets an event has is randomly generated
 * @author Atila
 */
public class Event implements Comparable{

    static AtomicInteger nextId = new AtomicInteger();
    private final int id;
    private final ArrayList<Ticket> tickets;
    private int distance;
    
    public Event() {
        distance = 0;
        id = nextId.incrementAndGet();
        tickets = new ArrayList<>();
        generateTickets();
    }

    //sets the distance of an event relative to the coordinate inputted
    public void setDistance(int distance) {
        this.distance = distance;
    }

    //gets an events distance
    public int getDistance() {
        return distance;
    }

    //gets an events id
    public int getId() {
        return id;
    }

    /**
     * Gets the list of tickets that this event holds
     * @return list of tickets an event holds
     */
    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
    
    /**
     * Generates tickets for an event and sorts the tickets in terms of price.
     * Lowest priced tickets come first
     */
    private void generateTickets(){
        
        //Random generator
        Random rand = new Random();
        //Generate a number between 0 and 10
        int  n = rand.nextInt(11);
        
        //Add the amount of tickets to the event specified by the number generated
        if(n > 0){
            for(int i = 0; i <= n; i++){
                Ticket eventTicket = new Ticket();
                tickets.add(eventTicket);
            }
        }
        //sort the tickets, lowest price have highest priority
        Collections.sort(tickets);
    }

    //gets the cheapest ticket for an event
    public double getCheapestTicket(){
        return tickets.get(0).getPrice();
    }
    
    /**
     * used in order to sort events in terms of distance Ascending
     * (shortest distance first)
     * @param t to compare
     * @return ordering of events
     */
    @Override
    public int compareTo(Object t) {
        Event e = (Event) t;
        if(this.distance > e.getDistance()){
            return 1;
        }
        else if(this.distance < e.getDistance()){
            return -1;
        }
        else{
            return 0;
        }
    }
}
