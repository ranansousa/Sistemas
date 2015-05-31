/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.TesourariaDebitoModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class TesourariaDebitoTextFilterator implements TextFilterator<TesourariaDebitoModel> {

    public void getFilterStrings(List<String> baseList, TesourariaDebitoModel element) {
        final String numero = element.getNumeroDocumento();
        baseList.add(numero);
    }
}
