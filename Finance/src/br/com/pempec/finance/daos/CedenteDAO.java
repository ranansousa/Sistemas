package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.CedenteDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.CedenteModel;
import br.com.pempec.finance.models.ContaContabilModel;
import br.com.pempec.finance.models.ContatoModel;
import br.com.pempec.finance.models.EnderecoModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.PKModel;
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

public class CedenteDAO implements CedenteDAOIf {

    @HibernateTransaction
    public void inserir(CedenteModel model) throws SystemException {

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


        //if (model.getContaContabil() != null && model.getContaContabil().getPk().getId().isEmpty()) {
        if (model.getContaContabil() != null) {

            ContaContabilModel contaContabil = model.getContaContabil();


            if (contaContabil.getPk() == null && contaContabil.getPk().getId().isEmpty()) {

                contaContabil.setPk(new PKModel());

                contaContabil.getPk().setId(PempecKeyGenerator.generateKey());

                contaContabil.getPk().setOrganizacao(model.getPk().getOrganizacao());

            }

            HibernateUtil.getCurrentSession().save(contaContabil);

        }


        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().save(model);

        Repopulador.repopulador(Tela.TELA_PARAMETROS_CEDENTES, model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(CedenteModel model) throws SystemException {

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

        if (model.getContaContabil() != null) {

            ContaContabilModel contaContabil = model.getContaContabil();

            contaContabil.getPk().setOrganizacao(model.getPk().getOrganizacao());

            if (model.getContaContabil().getPk().getId().isEmpty()) {
                contaContabil.getPk().setId(PempecKeyGenerator.generateKey());
                HibernateUtil.getCurrentSession().save(contaContabil);
                model.getContaContabil().getPk().setId(contaContabil.getPk().getId());
            } else {
                HibernateUtil.getCurrentSession().update(contaContabil);
            }
        }

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().update(model);

        Repopulador.repopulador(Tela.TELA_PARAMETROS_CEDENTES, model);
        Repopulador.repopulador();

    }

    public CedenteModel consultarPorPk(CedenteModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                CedenteModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.setFetchMode("banco", FetchMode.JOIN);
        criteria.setFetchMode("tipoCedente", FetchMode.JOIN);
        criteria.setFetchMode("contato", FetchMode.JOIN);
        criteria.setFetchMode("contaContabil", FetchMode.JOIN);
        criteria.setFetchMode("cartaoCreditoModel", FetchMode.JOIN);


        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        Criteria subCriteria = criteria.createCriteria("endereco", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("bairro", FetchMode.JOIN);


        criteria.setMaxResults(1);

        return (CedenteModel) criteria.uniqueResult();

    }

    public CedenteModel consultarPorContaContabil(ContaContabilModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                CedenteModel.class);

        criteria.add(Restrictions.eq("contaContabil.pk.id", model.getPk().getId()));

        criteria.setFetchMode("banco", FetchMode.JOIN);
        criteria.setFetchMode("tipoCedente", FetchMode.JOIN);
        criteria.setFetchMode("contato", FetchMode.JOIN);
        criteria.setFetchMode("contaContabil", FetchMode.JOIN);
        criteria.setFetchMode("cartaoCreditoModel", FetchMode.JOIN);


        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        Criteria subCriteria = criteria.createCriteria("endereco", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("bairro", FetchMode.JOIN);

        criteria.setMaxResults(1);

        return (CedenteModel) criteria.uniqueResult();

    }

    @HibernateTransaction
    public void excluir(CedenteModel model)
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

        Repopulador.repopulador(Tela.TELA_PARAMETROS_CEDENTES, null);
        Repopulador.repopulador();
    }

    public CedenteModel consultarPorTemplate(CedenteModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                CedenteModel.class);

        if (model.getNome() != null && !model.getNome().isEmpty()) {
            criteria.add(Restrictions.like("nome", model.getNome(),
                    MatchMode.START));
        }

        criteria.setMaxResults(1);

        Criteria subCriteria = criteria.createCriteria("endereco", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("bairro", FetchMode.JOIN);
        subCriteria.setFetchMode("cidade", FetchMode.JOIN);
        subCriteria.setFetchMode("estado", FetchMode.JOIN);


        criteria.setFetchMode("banco", FetchMode.JOIN);
        criteria.setFetchMode("tipoCedente", FetchMode.JOIN);
        criteria.setFetchMode("contato", FetchMode.JOIN);
        criteria.setFetchMode("contaContabil", FetchMode.JOIN);
        criteria.setFetchMode("cartaoCreditoModel", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.addOrder(Order.asc("nome"));

        return (CedenteModel) criteria.uniqueResult();

    }

    public List<CedenteModel> obterPorFiltro(CedenteModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                CedenteModel.class);

        if (model.getNome() != null && !model.getNome().isEmpty()) {
            criteria.add(Restrictions.like("nome", model.getNome(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));
        criteria.addOrder(Order.asc("nome"));

        return criteria.list();

    }

    public List<CedenteModel> obterTodosCartaoCredito(OrganizacaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                CedenteModel.class);

        criteria.add(Restrictions.isNotNull("cartaoCreditoModel.pk.id"));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.addOrder(Order.asc("nome"));

        criteria.setFetchMode("cartaoCreditoModel", FetchMode.JOIN);


        return criteria.list();

    }

    public List<CedenteModel> obterTodos(OrganizacaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                CedenteModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.setFetchMode("contaContabil", FetchMode.JOIN);


        criteria.addOrder(Order.asc("nome"));

        return criteria.list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<CedenteModel> coll)
            throws SystemException {

        for (CedenteModel model : coll) {
            if (!model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().delete(model);
        }

        Repopulador.repopulador(Tela.TELA_PARAMETROS_CEDENTES, null);
        Repopulador.repopulador();

    }
}
