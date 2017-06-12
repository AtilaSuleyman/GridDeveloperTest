

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * Represents a ticket
 * @author Atila
 */
public class Ticket implements Comparable{

    Double price;
    
    public Ticket() {
        Random r = new Random();
        //decide a random price for the ticket
        double amount = 1 + (100 - 1) * r.nextDouble();
        //round the price to 2 decimal places
        price = round(amount,2);
    }

    //Gets the price of a ticket
    public Double getPrice() {
        return price;
    }
    
    //round the price to 2 decimal places
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * used to order the tickets in terms of price ascending (lowest prices first)
     * @param t ticket to compare to
     * @return ordering of tickets
     */
    @Override
    public int compareTo(Object t) {
        
        Ticket t1 = (Ticket)t;
        if(this.price > t1.getPrice()){
            return 1;
        }
        else if(this.price < t1.getPrice()){
            return -1;
        }
        else{
            return 0;
        }
    }
}
