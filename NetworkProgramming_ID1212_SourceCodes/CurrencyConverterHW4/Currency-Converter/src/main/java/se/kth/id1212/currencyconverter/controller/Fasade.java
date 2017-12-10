/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.currencyconverter.controller;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import se.kth.id1212.currencyconverter.integration.CurConvDAO;

/**
 *
 * @author koszio
 */


@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class Fasade {
    @EJB CurConvDAO curConvDAO;
    
         public String converter(String fromCurr, String toCurr, Double amount){

             return curConvDAO.converter(fromCurr, toCurr, amount);
         }    
    
   

    
}
