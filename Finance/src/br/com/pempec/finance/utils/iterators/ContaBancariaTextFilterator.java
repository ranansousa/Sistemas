/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.ContaBancariaModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class ContaBancariaTextFilterator implements TextFilterator<ContaBancariaModel> {

    public void getFilterStrings(List<String> baseList, ContaBancariaModel element) {
        final String conta = element.getConta();
        baseList.add(conta);
    }
}
