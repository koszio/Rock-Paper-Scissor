/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.currencyconverter.integration;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import se.kth.id1212.currencyconverter.model.Currency;

/**
 *
 * @author koszio
 */

@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class CurConvDAO {
    
    
     @PersistenceContext(unitName = "Currency-ConverterPU")
    private EntityManager em;
    
    
     public Currency converter(String fromCurr, String toCurr, Double amount) {

        Currency currency = em.find(Currency.class, fromCurr);
        if (currency == null) {
            throw new EntityNotFoundException("Currency does not exist " + fromCurr);
        }
        
        return currency;
    }
    
}
