/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.ContaContabilModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class ContaContabilTextFilterator implements TextFilterator<ContaContabilModel> {

    public void getFilterStrings(List<String> baseList, ContaContabilModel element) {
        final String descricao = element.getDescricao();
        baseList.add(descricao);
    }
}
