package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.OrganizacaoDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.CedenteModel;
import br.com.pempec.finance.models.CentroCustoModel;
import br.com.pempec.finance.models.ContaContabilModel;
import br.com.pempec.finance.models.FormaPagamentoModel;
import br.com.pempec.finance.models.HistoricoModel;
import br.com.pempec.finance.models.LocalPagamentoModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.SacadoModel;
import br.com.pempec.finance.models.TipoCedenteModel;
import br.com.pempec.finance.models.TipoCobrancaModel;
import br.com.pempec.finance.models.TipoNotaFiscalModel;
import br.com.pempec.finance.models.TipoOperacaoBancariaModel;
import br.com.pempec.finance.models.TipoSacadoModel;
import br.com.pempec.finance.models.TipoStatusModel;
import br.com.pempec.finance.models.UsuarioModel;
import br.com.pempec.finance.utils.Repopulador;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
//EQUIPE PEMPEC

public class OrganizacaoDAO implements OrganizacaoDAOIf {

    @HibernateTransaction
    public void inserir(OrganizacaoModel model) throws SystemException {

        HibernateUtil.getCurrentSession().save(model);

//        //conta caixa padrao
//        ContaContabilModel cct = new ContaContabilModel();
//        cct.setPk(new PKModel());
//        cct.getPk().setOrganizacao(model);
//        cct.getPk().setId("CAIXA");
//        cct.setDescricao("CAIXA");
//        cct.setGrau("5");
//        cct.setConta("1.1.1.01.001");
//        cct.setDigitoConta("2");
//        cct.setContaReduzida("5");
//        cct.setDigitoContaReduzida("8");
//        cct.setTipo("D");
//        cct.setNatureza(1);
//
//        HibernateUtil.getCurrentSession().save(cct);

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }


        if (model.getUsuarioPadrao() != null) {

            UsuarioModel bean = model.getUsuarioPadrao();

            HibernateUtil.getCurrentSession().save(bean);


        }


        if (model.getLHistorico() != null) {

            for (HistoricoModel bean : model.getLHistorico()) {

                bean.getPk().setOrganizacao(model);

                HibernateUtil.getCurrentSession().save(bean);

                System.out.println("HST  " + bean.getDescricao() + "  Lista " + model.getLHistorico().size());
            }

        }



        if (model.getLTipoCedente() != null) {

            for (TipoCedenteModel bean : model.getLTipoCedente()) {

                bean.getPk().setOrganizacao(model);

                HibernateUtil.getCurrentSession().save(bean);

                System.out.println("TipoCe " + bean.getDescricao() + "  Lista " + model.getLTipoCedente().size());
            }

        }

        if (model.getLTipoSacado() != null) {

            for (TipoSacadoModel bean : model.getLTipoSacado()) {
                bean.getPk().setOrganizacao(model);

                HibernateUtil.getCurrentSession().save(bean);


                System.out.println("TipoSac " + bean.getDescricao() + "  Lista " + model.getLTipoSacado().size());
            }

        }


        if (model.getLCobranca() != null) {

            for (TipoCobrancaModel bean : model.getLCobranca()) {

                bean.getPk().setOrganizacao(model);

                HibernateUtil.getCurrentSession().save(bean);

                System.out.println("getLCobranca " + bean.getDescricao() + "  Lista " + model.getLCobranca().size());



            }
        }



        if (model.getLStatus() != null) {

            for (TipoStatusModel bean : model.getLStatus()) {

                bean.getPk().setOrganizacao(model);

                HibernateUtil.getCurrentSession().save(bean);

                System.out.println("getLStatus " + bean.getDescricao() + "  Lista " + model.getLStatus().size());


            }

        }


        if (model.getLCentroCusto() != null) {

            for (CentroCustoModel bean : model.getLCentroCusto()) {

                bean.getPk().setOrganizacao(model);

                HibernateUtil.getCurrentSession().save(bean);


                System.out.println("getLCentroCusto " + bean.getDescricao() + "  Lista " + model.getLCentroCusto().size());


            }

        }



        if (model.getLTipoNotaFiscal() != null) {

            for (TipoNotaFiscalModel bean : model.getLTipoNotaFiscal()) {

                bean.getPk().setOrganizacao(model);

                HibernateUtil.getCurrentSession().save(bean);

                System.out.println("getLTipoNotaFiscal " + bean.getDescricao() + "  Lista " + model.getLTipoNotaFiscal().size());


            }

        }


        if (model.getLLocalPG() != null) {

            for (LocalPagamentoModel bean : model.getLLocalPG()) {

                bean.getPk().setOrganizacao(model);

                HibernateUtil.getCurrentSession().save(bean);

                System.out.println("LOCALPG " + bean.getDescricao() + "  Lista " + model.getLLocalPG().size());
            }
        }

        if (model.getLFormaPG() != null) {

            for (FormaPagamentoModel bean : model.getLFormaPG()) {

                bean.getPk().setOrganizacao(model);

                HibernateUtil.getCurrentSession().save(bean);

                System.out.println("getLFormaPG " + bean.getDescricao() + "  Lista " + model.getLFormaPG().size());
            }
        }

        if (model.getLOperacaoBancaria() != null) {

            for (TipoOperacaoBancariaModel bean : model.getLOperacaoBancaria()) {

                bean.getPk().setOrganizacao(model);

                HibernateUtil.getCurrentSession().save(bean);

                System.out.println("getLOperacaoBancaria " + bean.getDescricao() + "  Lista " + model.getLOperacaoBancaria().size());
            }
        }



        if (model.getLCedente() != null) {

            for (CedenteModel bean : model.getLCedente()) {

                bean.getPk().setOrganizacao(model);

                HibernateUtil.getCurrentSession().save(bean);

                System.out.println("CEDENTE " + bean.getNome() + "  Lista " + model.getLCedente().size());
            }
        }

        if (model.getLSacado() != null) {

            for (SacadoModel bean : model.getLSacado()) {

                bean.getPk().setOrganizacao(model);

                HibernateUtil.getCurrentSession().save(bean);

                System.out.println("SACADO " + bean.getNome() + "  Lista " + model.getLCedente().size());
            }
        }


        Repopulador.repopulador();

    }

    @HibernateTransaction
    public void alterar(OrganizacaoModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    public OrganizacaoModel consultarPorPk(OrganizacaoModel model)
            throws SystemException {
        return (OrganizacaoModel) HibernateUtil.getCurrentSession().get(
                OrganizacaoModel.class, model.getId());
    }

    public List<OrganizacaoModel> obterPorFiltro(OrganizacaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                OrganizacaoModel.class);

        if (model.getRazaoSocial() != null && !model.getRazaoSocial().isEmpty()) {
            criteria.add(Restrictions.like("razao_social", model.getRazaoSocial(), MatchMode.START));
        }

        criteria.addOrder(Order.asc("razao_social"));

        return criteria.list();

    }

    public List<OrganizacaoModel> obterTodos() throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                OrganizacaoModel.class);

        criteria.setFetchMode("estado", FetchMode.JOIN);
        criteria.setFetchMode("cidade", FetchMode.JOIN);
        criteria.setFetchMode("bairro", FetchMode.JOIN);


        return criteria.list();
    }

    @HibernateTransaction
    public void excluirTodos(String[] array) throws SystemException {

        OrganizacaoModel model = null;

        for (String id : array) {
            model = new OrganizacaoModel();
            model.setId(id);

            if (model.getMovimentoDiarioModel() != null
                    && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    public OrganizacaoModel consultar() throws SystemException {

        return (OrganizacaoModel) HibernateUtil.getCurrentSession().createCriteria(OrganizacaoModel.class).uniqueResult();

    }

    public OrganizacaoModel consultarPorTemplate(OrganizacaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                OrganizacaoModel.class);

        if (model.getRazaoSocial() != null && !model.getRazaoSocial().isEmpty()) {
            criteria.add(Restrictions.eq("razaoSocial", model.getRazaoSocial()));
        }

        criteria.setFetchMode("estado", FetchMode.JOIN);
        criteria.setFetchMode("cidade", FetchMode.JOIN);
        criteria.setFetchMode("bairro", FetchMode.JOIN);


        criteria.setMaxResults(1);

        return (OrganizacaoModel) criteria.uniqueResult();

    }
}
