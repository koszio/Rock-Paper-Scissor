/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.currencyconverter.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 *
 * @author koszio
 */
@Entity
@Table(name = "CURRENCY")
@NamedQueries({
    @NamedQuery(name = "Currency.findAll", query = "SELECT c FROM Currency c")
    ,
    @NamedQuery(name = "Currency.findByfromCurr", query = "SELECT c FROM Currency c WHERE c.fromCurr = :fromCurr")
    ,
    @NamedQuery(name = "Currency.findBySEK", query = "SELECT c FROM Currency c WHERE c.SEK = :SEK")
        ,
    @NamedQuery(name = "Currency.findByEURO", query = "SELECT c FROM Currency c WHERE c.EURO = :EURO")
    ,
    @NamedQuery(name = "Currency.findByUSD", query = "SELECT c FROM Currency c WHERE c.USD = :USD"),
    
    @NamedQuery(name = "Currency.findByGBP", query = "SELECT c FROM Currency c WHERE c.GBP = :GBP")
    
})
public class Currency implements Serializable {
    @Id
    @Basic(optional = false)
    @Size(min = 1, max = 64)
    @Column(name = "fromCurr")
    private String fromCurr;
    @Basic(optional = false)
    @Column(name = "SEK")
    private double SEK;
    @Basic(optional = false)
    @Column(name = "EURO")
    private double EURO;
    @Basic(optional = false)
    @Column(name = "USD")
    private double USD;
    @Basic(optional = false)
    @Column(name = "GBP")
    private double GBP;
    public Currency() {
    }

    public Currency(String fromCurr) {
        this.fromCurr = fromCurr;
    }

    public Currency(String fromCurr, Double SEK, Double EURO, Double USD, Double POUNDS) {
        this.fromCurr = fromCurr;
        this.SEK = SEK;
        this.EURO = EURO;
        this.USD = USD;
        this.GBP = POUNDS;

    }

    public String convert(String toCurr, Double amount) {
        double result = 0;

        switch (toCurr) {
            case "SEK":
                result = conversionOfCurrency(SEK, amount);
                break;
            case "EURO":
                result = conversionOfCurrency(EURO, amount);
                break;
            case "USD":
                result = conversionOfCurrency(USD, amount);
                break;
            case "GBP":
                result = conversionOfCurrency(GBP, amount);
                break;

        }
        return String.valueOf(result);
    }

    public Double conversionOfCurrency(Double currency, double amount) {
        return amount * currency;

    }

    public String getFromCurr() {
        return fromCurr;
    }

    public void setFromCurr(String fromCurr) {
        this.fromCurr = fromCurr;
    }

    public double getSEK() {
        return SEK;
    }

    public void setSEK(double SEK) {
        this.SEK = SEK;
    }

    public double getEURO() {
        return EURO;
    }

    public void setEURO(double EURO) {
        this.EURO = EURO;
    }

    public double getUSD() {
        return USD;
    }

    public void setUSD(double USD) {
        this.USD = USD;
    }

    public double getPOUNDS() {
        return GBP;
    }

    public void setPOUNDS(double POUNDS) {
        this.GBP = POUNDS;
    }

   

    @Override
    public String toString() {
        return "se.kth.id1212.mycurrencyconverterapp.model.Currency[fromCurr=" + fromCurr + "]";
    }

    @Override
    public boolean equals(Object obj) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(obj instanceof Currency)) {
            return false;
        }
        Currency other = (Currency) obj;
        if ((this.fromCurr == null && other.fromCurr != null) || (this.fromCurr != null && !this.fromCurr.equals(other.fromCurr))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fromCurr != null ? fromCurr.hashCode() : 0);
        return hash;

    }
    
    
    

}
