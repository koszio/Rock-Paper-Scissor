/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.mycurrencyconverterapp.controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import se.kth.id1212.mycurrencyconverterapp.model.Currency;

/**
 *
 * @author koszio
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class Fasade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "currencyPU")
    private EntityManager em;

//Need to persist currencies ....
    public long convertCurrency(long amount, String fromCurr, String toCurr) {
        long rateIn = 0;
        long rateOut = 0;
    Currency curr = null;
  
    
//loop through a list that has all th currency types 
   // for(Currency curr : currencyTypes){
        
        
        if(curr.getName().equals(fromCurr)){
            rateIn = curr.getRate();
        }
        
        if(curr.getName().equals(toCurr)){
            rateOut = curr.getRate();
        }
     //}
       return amount *(rateIn/rateOut);
    
    }

   /* public long getCurrencyRate(String currencyType) {

    }*/

   

}
