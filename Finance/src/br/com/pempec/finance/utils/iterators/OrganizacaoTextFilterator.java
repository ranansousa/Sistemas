package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.OrganizacaoModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class OrganizacaoTextFilterator implements TextFilterator<OrganizacaoModel> {

    public void getFilterStrings(List<String> baseList, OrganizacaoModel element) {
        final String razaoSocial = element.getRazaoSocial();
        baseList.add(razaoSocial);
    }
}
