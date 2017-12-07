/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.mycurrencyconverterapp.integration;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import se.kth.id1212.mycurrencyconverterapp.model.Currency;

/**
 *
 * @author koszio
 */
@Stateless
public class CurrencyDAO {
    
      @PersistenceContext(unitName = "currencyPU")

      private EntityManager em;
      
     public List<Currency> getCurrencies() {
    return em.createNamedQuery("getCurrencies", Currency.class).getResultList();
  }

  public Currency findCurrencyById(long fromId) {
    Currency currency = em.find(Currency.class, fromId);

    if (currency == null){
        return null;
    }
//      throw new EntityNotFoundException....something instead of return null
                   

    return currency;
  }
}
