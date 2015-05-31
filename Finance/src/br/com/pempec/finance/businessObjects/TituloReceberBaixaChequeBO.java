package br.com.pempec.finance.businessObjects;

import java.util.Collection;
import java.util.List;

import br.com.pempec.finance.daos.DAOFactory;

import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.BancoModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.LoteDepositoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TituloReceberBaixaChequeModel;
import br.com.pempec.finance.models.TituloReceberBaixaModel;

/**
 * @author PEMPEC
 */
public class TituloReceberBaixaChequeBO {

    public void inserir(TituloReceberBaixaChequeModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloReceberBaixaChequeDAO().inserir(model);
    }

    public void alterar(TituloReceberBaixaChequeModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloReceberBaixaChequeDAO().alterar(model);
    }

    public TituloReceberBaixaChequeModel consultarPorPk(TituloReceberBaixaChequeModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaChequeDAO().consultarPorPk(model);
    }

    public List<TituloReceberBaixaChequeModel> obterPorFiltro(TituloReceberBaixaChequeModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaChequeDAO().obterPorFiltro(model);
    }

    public void excluirTodos(Collection<TituloReceberBaixaChequeModel> coll) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloReceberBaixaChequeDAO().excluirTodos(coll);
    }

    public List<TituloReceberBaixaChequeModel> obterTodos(OrganizacaoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaChequeDAO().obterTodos(model);
    }

    public List<TituloReceberBaixaChequeModel> obterPorTituloReceberBaixa(TituloReceberBaixaModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaChequeDAO().obterPorTituloReceberBaixa(model);
    }

    public TituloReceberBaixaChequeModel consultarPorTemplate(TituloReceberBaixaChequeModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaChequeDAO().consultarPorTemplate(model);
    }

    public void excluir(TituloReceberBaixaChequeModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloReceberBaixaChequeDAO().excluir(model);
    }

    public List<TituloReceberBaixaChequeModel> obterPorBanco(BancoModel model, OrganizacaoModel organizacao)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaChequeDAO().obterPorBanco(model, organizacao);
    }

    public List<TituloReceberBaixaChequeModel> obterPorLoteDeposito(LoteDepositoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaChequeDAO().obterPorLoteDeposito(model);
    }

    public List<TituloReceberBaixaChequeModel> obterDepositaveis(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaChequeDAO().obterDepositaveis(model);
    }

    public Double obterSaldoCheque(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaChequeDAO().obterSaldoCheque(model);
    }

    public void depositoTesourariaBanco(Collection<TituloReceberBaixaChequeModel> cheques, ContaBancariaModel conta) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloReceberBaixaChequeDAO().depositoTesourariaBanco(cheques, conta);
    }
}//fim da class
