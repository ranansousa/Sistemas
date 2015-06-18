package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.ContaContabilDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.ContaContabilModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.Repopulador;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class ContaContabilDAO implements ContaContabilDAOIf {

    @HibernateTransaction
    public void inserir(ContaContabilModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }
        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(ContaContabilModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }
        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    public ContaContabilModel consultarPorPk(ContaContabilModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaContabilModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (ContaContabilModel) criteria.uniqueResult();

    }

    public List<ContaContabilModel> obterPorFiltro(ContaContabilModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaContabilModel.class);

        if (model.getDescricao() != null && !model.getDescricao().isEmpty()) {
            criteria.add(Restrictions.like("descricao", model.getDescricao(),
                    MatchMode.ANYWHERE));
        }

        if (model.getConta() != null && !model.getConta().isEmpty()) {
            criteria.add(Restrictions.like("conta", model.getConta(),
                    MatchMode.START));
        }

        if (model.getContaReduzida() != null && !model.getContaReduzida().isEmpty()) {
            criteria.add(Restrictions.like("contaReduzida", model.getContaReduzida(),
                    MatchMode.START));
        }

        if (model.getTipo() != null && !model.getTipo().isEmpty()) {
            criteria.add(Restrictions.eq("tipo", model.getTipo()));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.add(Restrictions.eq("grau", "5"));

        criteria.addOrder(Order.asc("descricao"));

        return criteria.list();

    }

    public List<ContaContabilModel> obterTodos(OrganizacaoModel model)
            throws SystemException {

        Query query = HibernateUtil.getCurrentSession().createQuery("update br.com.pempec.finance.models.ContaContabilModel c set c.grau = '5'");

        query.executeUpdate();

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaContabilModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.add(Restrictions.eq("grau", "5"));

        criteria.addOrder(Order.asc("descricao"));

        return criteria.list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<ContaContabilModel> coll)
            throws SystemException {

        for (ContaContabilModel model : coll) {

            if (model.getMovimentoDiarioModel() != null
                    && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().delete(model);
        }

        Repopulador.repopulador();

    }

    @HibernateTransaction
    public void excluir(ContaContabilModel model)
            throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();
    }

    public ContaContabilModel consultarPorTemplate(ContaContabilModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaContabilModel.class);

        if (model.getDescricao() != null && !model.getDescricao().isEmpty()) {
            criteria.add(Restrictions.eq("descricao", model.getDescricao()));
        }

        if (model.getConta() != null && !model.getConta().isEmpty()) {
            criteria.add(Restrictions.eq("conta", model.getConta()));
        }

        if (model.getContaReduzida() != null && !model.getContaReduzida().isEmpty()) {
            criteria.add(Restrictions.eq("contaReduzida", model.getContaReduzida()));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (ContaContabilModel) criteria.uniqueResult();

    }

    @HibernateTransaction
    public void sincronizeMegaContabil(Collection<ContaContabilModel> collContaContabilInsert, Collection<ContaContabilModel> collContaContabilUpdate) throws SystemException {

        if (collContaContabilInsert != null && !collContaContabilInsert.isEmpty()) {
            for (ContaContabilModel contaContabil : collContaContabilInsert) {
                HibernateUtil.getCurrentSession().save(contaContabil);
            }
        }


        if (collContaContabilUpdate != null && !collContaContabilUpdate.isEmpty()) {
            for (ContaContabilModel contaContabil : collContaContabilUpdate) {
                HibernateUtil.getCurrentSession().merge(contaContabil);
            }
        }

    }



    public Integer getQtdRegistros(OrganizacaoModel model) throws SystemException {

        Integer retorno = 0;
            
            try {

            Query query = HibernateUtil.getCurrentSession().createSQLQuery("SELECT COUNT(*) FROM CONTA_CONTABIL WHERE GRAU = 5 AND ID_ORGANIZACAO =:org " );
             query.setParameter("org", model.getId());  
                        
            retorno = (Integer) query.uniqueResult();
            

        } catch (Exception e) {
            throw new SystemException(e);
        }
            
            

        return retorno;

    }


}
