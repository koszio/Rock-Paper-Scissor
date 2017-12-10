/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.currencyconverter.view;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import se.kth.id1212.currencyconverter.controller.Fasade;

/**
 *
 * @author koszio
 */
@Named("convManager")
@ConversationScoped
public class ConvManager implements Serializable {

    @EJB
    private Fasade fasade;
    private String fromCurr;
    private String toCurr;
    private double amount;

    private String converted;

    @Inject
    private Conversation conversation;
    
    
    private void startConversation(){
        if(conversation.isTransient()){
        conversation.begin();
        }
    }
    
    private void stopConversation(){
        if(!conversation.isTransient()){
            conversation.end();
        }
    }
    
    
    //Conversion Result

    public String getConverted() {
        return converted;
    }
    
    
    public void convertCurrency(){
        
        try {
            startConversation();
            converted=fasade.converter(fromCurr,toCurr,amount);
        } catch (Exception e) {
        }
    }

    public String getFromCurr() {
        return fromCurr;
    }

    public void setFromCurr(String fromCurr) {
        this.fromCurr = fromCurr;
    }

    public String getToCurr() {
        return toCurr;
    }

    public void setToCurr(String toCurr) {
        this.toCurr = toCurr;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    
    
    
    
    
    
   

}
