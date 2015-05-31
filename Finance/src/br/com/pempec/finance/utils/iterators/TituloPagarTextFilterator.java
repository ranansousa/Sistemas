/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.TituloPagarModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class TituloPagarTextFilterator implements TextFilterator<TituloPagarModel> {

    public void getFilterStrings(List<String> baseList, TituloPagarModel element) {
        final String numero = element.getNumeroDocumento();
        baseList.add(numero);
    }
}
