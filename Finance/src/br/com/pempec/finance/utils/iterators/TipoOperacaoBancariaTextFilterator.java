/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.TipoOperacaoBancariaModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class TipoOperacaoBancariaTextFilterator implements TextFilterator<TipoOperacaoBancariaModel> {

    public void getFilterStrings(List<String> baseList, TipoOperacaoBancariaModel element) {
        final String descricao = element.getDescricao();
        baseList.add(descricao);
    }
}
