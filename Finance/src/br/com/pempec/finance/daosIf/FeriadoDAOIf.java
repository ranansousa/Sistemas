package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.FeriadoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface FeriadoDAOIf {

    public void inserir(Collection<FeriadoModel> model) throws SystemException;

    public void alterar(FeriadoModel model) throws SystemException;

    public void excluir(FeriadoModel model) throws SystemException;

    public List<FeriadoModel> obterTodos() throws SystemException;

    public List<FeriadoModel> obterPorFiltro(FeriadoModel model) throws SystemException;
}
