/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.ContaBancariaDebitoModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class ContaBancariaDebitoTextFilterator implements TextFilterator<ContaBancariaDebitoModel> {

    public void getFilterStrings(List<String> baseList, ContaBancariaDebitoModel element) {
        final String numeroCheque = element.getContaBancariaCheque().getNumeroCheque();
        baseList.add(numeroCheque);
    }
}
