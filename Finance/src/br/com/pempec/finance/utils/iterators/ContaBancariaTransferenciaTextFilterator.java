/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.ContaBancariaTransferenciaModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class ContaBancariaTransferenciaTextFilterator implements TextFilterator<ContaBancariaTransferenciaModel> {

    public void getFilterStrings(List<String> baseList, ContaBancariaTransferenciaModel element) {
        final String identificacao = element.getIdentificacao();
        baseList.add(identificacao);
    }
}
