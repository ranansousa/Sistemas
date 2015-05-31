/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.ContaBancariaDebitoModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 * Utilizado para preencher os combos da tela de lancamentos na conta bancaria
 *
 * @author PEMPEC
 */
public class LancamentoContaBancariaDebitoTextFilterator implements TextFilterator<ContaBancariaDebitoModel> {

    public void getFilterStrings(List<String> baseList, ContaBancariaDebitoModel element) {
        final String identificacao = element.getIdentificacao();
        baseList.add(identificacao);
    }
}
