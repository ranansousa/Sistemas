/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.CentroCustoModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class CentroCustoTextFilterator implements TextFilterator<CentroCustoModel> {

    public void getFilterStrings(List<String> baseList, CentroCustoModel element) {
        final String descricao = element.getDescricao();
        baseList.add(descricao);
    }
}
