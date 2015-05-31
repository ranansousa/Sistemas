/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.TituloPagarBaixaFormaPagamentoModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class TituloPagarBaixaFormaPagamentoTextFilterator implements TextFilterator<TituloPagarBaixaFormaPagamentoModel> {

    public void getFilterStrings(List<String> baseList, TituloPagarBaixaFormaPagamentoModel element) {
        final String descricao = element.getForma().getDescricao();
        baseList.add(descricao);
    }
}
