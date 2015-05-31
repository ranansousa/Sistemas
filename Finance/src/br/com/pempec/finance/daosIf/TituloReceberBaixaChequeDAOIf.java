package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.BancoModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.LoteDepositoModel;
import br.com.pempec.finance.models.TituloReceberBaixaChequeModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TituloReceberBaixaModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TituloReceberBaixaChequeDAOIf {

    public void inserir(TituloReceberBaixaChequeModel model) throws SystemException;

    public void alterar(TituloReceberBaixaChequeModel model) throws SystemException;

    public void depositoTesourariaBanco(Collection<TituloReceberBaixaChequeModel> cheques, ContaBancariaModel conta) throws SystemException;

    public TituloReceberBaixaChequeModel consultarPorPk(TituloReceberBaixaChequeModel model) throws SystemException;

    public List<TituloReceberBaixaChequeModel> obterPorFiltro(TituloReceberBaixaChequeModel model)
            throws SystemException;

    public List<TituloReceberBaixaChequeModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluirTodos(Collection<TituloReceberBaixaChequeModel> coll)
            throws SystemException;

    public TituloReceberBaixaChequeModel consultarPorTemplate(TituloReceberBaixaChequeModel model)
            throws SystemException;

    public List<TituloReceberBaixaChequeModel> obterPorTituloReceberBaixa(TituloReceberBaixaModel model)
            throws SystemException;

    public Double obterSaldoCheque(OrganizacaoModel model)
            throws SystemException;

    public void excluir(TituloReceberBaixaChequeModel model) throws SystemException;

    public List<TituloReceberBaixaChequeModel> obterPorBanco(BancoModel model, OrganizacaoModel organizacao)
            throws SystemException;

    public List<TituloReceberBaixaChequeModel> obterPorLoteDeposito(LoteDepositoModel model)
            throws SystemException;

    public List<TituloReceberBaixaChequeModel> obterDepositaveis(OrganizacaoModel model)
            throws SystemException;
}
