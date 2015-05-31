package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.FuncionarioDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.ContatoModel;
import br.com.pempec.finance.models.EnderecoModel;
import br.com.pempec.finance.models.FuncionarioModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.Repopulador;
import br.com.pempec.finance.utils.Tela;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
//EQUIPE PEMPEC

public class FuncionarioDAO implements FuncionarioDAOIf {

    @HibernateTransaction
    public void inserir(FuncionarioModel model) throws SystemException {

        if (model.getEndereco() != null) {

            EnderecoModel enderecoModel = model.getEndereco();

            enderecoModel.getPk().setOrganizacao(model.getPk().getOrganizacao());

            HibernateUtil.getCurrentSession().save(enderecoModel);

        }

        if (model.getContato() != null) {

            ContatoModel contatoModel = model.getContato();

            contatoModel.getPk().setOrganizacao(model.getPk().getOrganizacao());

            HibernateUtil.getCurrentSession().save(contatoModel);

        }

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }


        HibernateUtil.getCurrentSession().save(model);

        Repopulador.repopulador(Tela.TELA_PARAMETROS_FUNCIONARIOS, model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(FuncionarioModel model) throws SystemException {

        if (model.getEndereco() != null) {

            EnderecoModel enderecoModel = model.getEndereco();

            enderecoModel.getPk().setOrganizacao(model.getPk().getOrganizacao());

            if (model.getEndereco().getPk().getId().isEmpty()) {
                enderecoModel.getPk().setId(PempecKeyGenerator.generateKey());
                HibernateUtil.getCurrentSession().save(enderecoModel);
                model.getEndereco().getPk().setId(enderecoModel.getPk().getId());
            } else {
                HibernateUtil.getCurrentSession().update(enderecoModel);

            }

        }

        if (model.getContato() != null) {

            ContatoModel contatoModel = model.getContato();

            contatoModel.getPk().setOrganizacao(model.getPk().getOrganizacao());

            if (model.getContato().getPk().getId().isEmpty()) {
                contatoModel.getPk().setId(PempecKeyGenerator.generateKey());
                HibernateUtil.getCurrentSession().save(contatoModel);
                model.getContato().getPk().setId(contatoModel.getPk().getId());
            } else {
                HibernateUtil.getCurrentSession().update(contatoModel);
            }
        }

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().update(model);

        Repopulador.repopulador(Tela.TELA_PARAMETROS_FUNCIONARIOS, model);
        Repopulador.repopulador();

    }

    public FuncionarioModel consultarPorPk(FuncionarioModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                FuncionarioModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.setMaxResults(1);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("endereco", FetchMode.JOIN);
        criteria.setFetchMode("contato", FetchMode.JOIN);

        return (FuncionarioModel) criteria.uniqueResult();

    }

    @HibernateTransaction
    public void excluir(FuncionarioModel model)
            throws SystemException {

        if (model.getEndereco() != null) {

            EnderecoModel enderecoModel = model.getEndereco();

            enderecoModel.getPk().setOrganizacao(model.getPk().getOrganizacao());

            HibernateUtil.getCurrentSession().delete(enderecoModel);

        }

        if (model.getContato() != null) {

            ContatoModel contatoModel = model.getContato();

            contatoModel.getPk().setOrganizacao(model.getPk().getOrganizacao());

            HibernateUtil.getCurrentSession().delete(contatoModel);

        }

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().delete(model);

        Repopulador.repopulador(Tela.TELA_PARAMETROS_FUNCIONARIOS, null);
        Repopulador.repopulador();

    }

    public FuncionarioModel consultarPorTemplate(FuncionarioModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                FuncionarioModel.class);

        if (model.getNome() != null && !model.getNome().isEmpty()) {
            criteria.add(Restrictions.eq("nome", model.getNome()));
        }


        criteria.setMaxResults(1);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("endereco", FetchMode.JOIN);
        criteria.setFetchMode("contato", FetchMode.JOIN);

        return (FuncionarioModel) criteria.uniqueResult();

    }

    public List<FuncionarioModel> obterPorFiltro(FuncionarioModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                FuncionarioModel.class);

        if (model.getNome() != null && !model.getNome().isEmpty()) {
            criteria.add(Restrictions.like("nome", model.getNome(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));
        criteria.addOrder(Order.asc("nome"));

        return criteria.list();

    }

    public List<FuncionarioModel> obterTodos(OrganizacaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                FuncionarioModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));
        criteria.addOrder(Order.asc("nome"));

        return criteria.list();
    }

    public List<FuncionarioModel> obterTodosComEmail(OrganizacaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                FuncionarioModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.createCriteria("contato", CriteriaSpecification.LEFT_JOIN).add(Restrictions.isNotNull("email"));

        criteria.addOrder(Order.asc("nome"));

        return criteria.list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<FuncionarioModel> coll)
            throws SystemException {

        for (FuncionarioModel model : coll) {

            if (model.getMovimentoDiarioModel() != null
                    && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().delete(model);
        }

        Repopulador.repopulador(Tela.TELA_PARAMETROS_FUNCIONARIOS, null);
        Repopulador.repopulador();

    }
}
