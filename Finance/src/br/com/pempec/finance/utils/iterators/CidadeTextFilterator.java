/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.CidadeModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class CidadeTextFilterator implements TextFilterator<CidadeModel> {

    public void getFilterStrings(List<String> baseList, CidadeModel element) {
        final String cidade = element.getDescricao();
        baseList.add(cidade);
    }
}
