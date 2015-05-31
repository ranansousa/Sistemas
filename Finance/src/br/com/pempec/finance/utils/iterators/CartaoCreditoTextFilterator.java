/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.CartaoCreditoModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class CartaoCreditoTextFilterator implements TextFilterator<CartaoCreditoModel> {

    public void getFilterStrings(List<String> baseList, CartaoCreditoModel element) {
        final String cartao = element.getCartao();
        baseList.add(cartao);
    }
}
