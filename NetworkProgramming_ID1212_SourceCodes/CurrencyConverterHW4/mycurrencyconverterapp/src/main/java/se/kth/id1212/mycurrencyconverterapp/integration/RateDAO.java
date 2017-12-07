/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.mycurrencyconverterapp.integration;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import se.kth.id1212.mycurrencyconverterapp.model.Currency;
import se.kth.id1212.mycurrencyconverterapp.model.RateCurr;

/**
 *
 * @author koszio
 */
@Stateless
public class RateDAO {
     @PersistenceContext(unitName = "currencyPU")
     private EntityManager em;
     
     
     
   public RateCurr getRate(Currency from, Currency to) {
   return null;
   }

     
     
     
}
