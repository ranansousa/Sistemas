/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.TituloReceberModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class TituloReceberTextFilterator implements TextFilterator<TituloReceberModel> {

    public void getFilterStrings(List<String> baseList, TituloReceberModel element) {
        final String numeroDocumento = element.getNumeroDocumento();
        baseList.add(numeroDocumento);
    }
}
