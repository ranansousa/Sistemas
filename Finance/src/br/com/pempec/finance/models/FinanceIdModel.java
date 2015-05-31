/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.models;

import java.io.Serializable;

/**
 *
 * @author Alexandre
 */
public class FinanceIdModel implements Serializable {

    private static final long serialVersionUID = -4860518208550835015L;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
