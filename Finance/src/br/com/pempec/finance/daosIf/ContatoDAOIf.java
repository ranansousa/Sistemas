package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContatoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface ContatoDAOIf {

    public void inserir(ContatoModel model) throws SystemException;

    public void alterar(ContatoModel model) throws SystemException;

    public ContatoModel consultarPorPk(ContatoModel model)
            throws SystemException;

    public List<ContatoModel> obterPorFiltro(ContatoModel model)
            throws SystemException;

    public List<ContatoModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluir(ContatoModel model) throws SystemException;

    public ContatoModel consultarPorTemplate(ContatoModel model)
            throws SystemException;

    public void excluirTodos(Collection<ContatoModel> coll)
            throws SystemException;
}
