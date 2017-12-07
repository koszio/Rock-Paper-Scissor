/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.mycurrencyconverterapp.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author koszio
 */
@Entity
public class Currency implements Serializable{

    @Id
    
    private String name;
    private long rate;

    public Currency() {
    }

    public Currency(String name, long rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public long getRate() {
        return rate;
    }
    
    
    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
