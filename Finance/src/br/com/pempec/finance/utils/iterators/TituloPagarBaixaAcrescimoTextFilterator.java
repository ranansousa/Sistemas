/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.TituloPagarBaixaAcrescimoModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class TituloPagarBaixaAcrescimoTextFilterator implements TextFilterator<TituloPagarBaixaAcrescimoModel> {

    public void getFilterStrings(List<String> baseList, TituloPagarBaixaAcrescimoModel element) {
        final String descricao = element.getTipoAcrescimo().getDescricao();
        baseList.add(descricao);
    }
}
