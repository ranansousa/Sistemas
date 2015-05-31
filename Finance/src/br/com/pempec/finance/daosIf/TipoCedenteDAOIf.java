package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TipoCedenteModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TipoCedenteDAOIf {

    public void inserir(TipoCedenteModel model) throws SystemException;

    public void alterar(TipoCedenteModel model) throws SystemException;

    public TipoCedenteModel consultarPorPk(TipoCedenteModel model) throws SystemException;

    public List<TipoCedenteModel> obterPorFiltro(TipoCedenteModel model)
            throws SystemException;

    public List<TipoCedenteModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluirTodos(Collection<TipoCedenteModel> coll)
            throws SystemException;

    public void excluir(TipoCedenteModel model) throws SystemException;

    public TipoCedenteModel consultarPorTemplate(TipoCedenteModel model)
            throws SystemException;
}
