/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.mycurrencyconverterapp.view;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import se.kth.id1212.mycurrencyconverterapp.controller.Fasade;

/**
 *
 * @author koszio
 */
@Named("convManager")
//@RequestScoped 
public class ConvManager implements Serializable {

    @EJB
    
    private Fasade Fasade;
    private String newCurrencyName;
    private long newCurrencyrate;
    private String fromCurr;
    private String toCurr;
    private long askForConversion;
    private long getAnswerForConversion;
    public List<String> currencyTypes;

    public void init() {
        
    }

    public long getNewCurrencyrate() {
        return newCurrencyrate;
    }

    public void setNewCurrencyrate(long newCurrencyrate) {
        this.newCurrencyrate = newCurrencyrate;
    }

    public String getNewCurrencyName() {
        return newCurrencyName;
    }

    public void setNewCurrencyName(String newCurrencyName) {
        this.newCurrencyName = newCurrencyName;
    }

    public String getCurrencyAsInput() {
        return fromCurr;
    }

    public String getCurrencyAsOutput() {
        return toCurr;
    }

    public void setCurrencyAsInput(String currencyAsInput) {
        this.fromCurr = currencyAsInput;
    }

    public void setCurrencyAsOutput(String currencyAsOutput) {
        this.toCurr = currencyAsOutput;
    }

    public long getAskForConversion() {
        return askForConversion;
    }

    public long getGetAnswerForConversion() {
        return getAnswerForConversion;
    }

    public void setAskForConversion(long askForConversion) {
        this.askForConversion = askForConversion;
    }

    public void setGetAnswerForConversion(long getAnswerForConversion) {
        this.getAnswerForConversion = getAnswerForConversion;
    }

    public List<String> getCurrencies() {
        return currencyTypes;
    }

    public void setCurrencies(List<String> currencyTypes) {
        this.currencyTypes = currencyTypes;
    }

}
