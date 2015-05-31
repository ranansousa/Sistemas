package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.LotePagamentoTituloModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TituloPagarBaixaModel;
import br.com.pempec.finance.models.TituloPagarModel;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author PEMPEC
 */
public class TituloPagarBaixaBO {

    public void salvar(TituloPagarBaixaModel model, TituloPagarModel titulo) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTituloPagarBaixaDAO().salvar(model, titulo);
    }

    public TituloPagarBaixaModel consultarPorPk(TituloPagarBaixaModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaDAO().consultarPorPk(
                model);
    }

    public void excluir(TituloPagarBaixaModel model, TituloPagarModel titulo)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarBaixaDAO().excluir(model, titulo);
    }

    public TituloPagarBaixaModel consultarPorTemplate(TituloPagarBaixaModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaDAO().consultarPorTemplate(model);
    }

    public TituloPagarBaixaModel consultarPorTitulo(TituloPagarModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaDAO().consultarPorTitulo(model);
    }

    public List<TituloPagarBaixaModel> obterPorFiltro(TituloPagarBaixaModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaDAO().obterPorFiltro(
                model);
    }

    public List<TituloPagarBaixaModel> obterBaixasExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaDAO().obterBaixasExportacao(
                model, dataInicial, dataFinal);
    }

    public List<TituloPagarBaixaModel> obterBaixasExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaDAO().obterBaixasExportacaoRelatorio(
                model, dataInicial, dataFinal);
    }

    public void excluirTodos(Collection<TituloPagarBaixaModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarBaixaDAO().excluirTodos(coll);
    }

    public List<TituloPagarBaixaModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaDAO().obterTodos(model);
    }

    public List<TituloPagarBaixaModel> obterTitulosExportados(LoteContabilModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaDAO().obterTitulosExportados(model);
    }

    public void salvarLote(LotePagamentoTituloModel lote, Collection<TituloPagarBaixaModel> collBaixa, Collection<TituloPagarModel> collTitulo) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTituloPagarBaixaDAO().salvarLote(lote, collBaixa, collTitulo);
    }
}
