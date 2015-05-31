package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.HistoricoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface HistoricoDAOIf {

    public void inserir(HistoricoModel model) throws SystemException;

    public void alterar(HistoricoModel model) throws SystemException;

    public HistoricoModel consultarPorPk(HistoricoModel model)
            throws SystemException;

    public List<HistoricoModel> obterPorFiltro(HistoricoModel model)
            throws SystemException;

    public List<HistoricoModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public List<HistoricoModel> obterTodosPorTipo(OrganizacaoModel model, String tipo)
            throws SystemException;

    public void excluir(HistoricoModel model) throws SystemException;

    public HistoricoModel consultarPorTemplate(HistoricoModel model)
            throws SystemException;

    public void excluirTodos(Collection<HistoricoModel> coll)
            throws SystemException;
}
