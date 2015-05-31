package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.MegaContabilDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.ContaBancariaCreditoModel;
import br.com.pempec.finance.models.ContaBancariaDebitoModel;
import br.com.pempec.finance.models.ContaBancariaTransferenciaModel;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.TesourariaCreditoModel;
import br.com.pempec.finance.models.TesourariaDebitoModel;
import br.com.pempec.finance.models.TituloPagarBaixaModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.TituloReceberBaixaModel;
import br.com.pempec.finance.models.TituloReceberModel;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.PempecParse;
import java.util.Collection;
import java.util.Date;
//EQUIPE PEMPEC

public class MegaContabilDAO implements MegaContabilDAOIf {

    @HibernateTransaction
    public void gerarLote(Collection<TituloPagarModel> collTitulosPagar,
            Collection<TituloPagarBaixaModel> collBaixasTitulosPagar,
            Collection<TituloReceberModel> collTitulosReceber,
            Collection<TituloReceberBaixaModel> collBaixasTitulosReceber,
            Collection<TesourariaDebitoModel> collTesourariaDebito,
            Collection<TesourariaCreditoModel> collTesourariaCredito,
            Collection<ContaBancariaDebitoModel> collContaBancariaDebito,
            Collection<ContaBancariaCreditoModel> collContaBancariaCredito,
            Collection<ContaBancariaTransferenciaModel> collContaBancariaTransferencia,
            LoteContabilModel loteContabil) throws SystemException {

        if (loteContabil != null) {
            //registrar as quantidades de documentos exportados

            LoteContabilModel loteContabilModel = loteContabil;

            if (collTitulosPagar != null) {
                loteContabilModel.setQtdTituloPagar(Long.valueOf(collTitulosPagar.size()));
            } else {
                loteContabilModel.setQtdTituloPagar(Long.valueOf(0));
            }
            if (collTitulosReceber != null) {
                loteContabilModel.setQtdTituloReceber(Long.valueOf(collTitulosReceber.size()));
            } else {
                loteContabilModel.setQtdTituloReceber(Long.valueOf(0));
            }
            if (collBaixasTitulosPagar != null) {
                loteContabilModel.setQtdTituloPagarBaixa(Long.valueOf(collBaixasTitulosPagar.size()));
            } else {
                loteContabilModel.setQtdTituloPagarBaixa(Long.valueOf(0));
            }
            if (collBaixasTitulosReceber != null) {
                loteContabilModel.setQtdTituloReceberBaixa(Long.valueOf(collBaixasTitulosReceber.size()));
            } else {
                loteContabilModel.setQtdTituloReceberBaixa(Long.valueOf(0));
            }
            if (collTesourariaCredito != null) {
                loteContabilModel.setQtdTesourariaCredito(Long.valueOf(collTesourariaCredito.size()));
            } else {
                loteContabilModel.setQtdTesourariaCredito(Long.valueOf(0));
            }
            if (collTesourariaDebito != null) {
                loteContabilModel.setQtdTesourariaDebito(Long.valueOf(collTesourariaDebito.size()));
            } else {
                loteContabilModel.setQtdTesourariaDebito(Long.valueOf(0));
            }
            if (collContaBancariaCredito != null) {
                loteContabilModel.setQtdContaBancariaCredito(Long.valueOf(collContaBancariaCredito.size()));
            } else {
                loteContabilModel.setQtdContaBancariaCredito(Long.valueOf(0));
            }
            if (collContaBancariaDebito != null) {
                loteContabilModel.setQtdContaBancariaDebito(Long.valueOf(collContaBancariaDebito.size()));
            } else {
                loteContabilModel.setQtdContaBancariaDebito(Long.valueOf(0));
            }
            if (collContaBancariaTransferencia != null) {
                loteContabilModel.setQtdContaBancariaTransferencia(Long.valueOf(collContaBancariaTransferencia.size()));
            } else {
                loteContabilModel.setQtdContaBancariaTransferencia(Long.valueOf(0));
            }

            HibernateUtil.getCurrentSession().save(loteContabil);
        }

        for (TituloPagarModel tituloPagarModel : collTitulosPagar) {
            tituloPagarModel.setLoteContabil(loteContabil);
            HibernateUtil.getCurrentSession().update(tituloPagarModel);
        }

        for (TituloPagarBaixaModel tituloPagarBaixaModel : collBaixasTitulosPagar) {
            //if (tituloPagarBaixaModel.getExportar()) {
            tituloPagarBaixaModel.setLoteContabil(loteContabil);
            HibernateUtil.getCurrentSession().update(tituloPagarBaixaModel);
            //}
        }

        for (TituloReceberModel tituloReceberModel : collTitulosReceber) {
            tituloReceberModel.setLoteContabil(loteContabil);
            HibernateUtil.getCurrentSession().update(tituloReceberModel);
        }

        for (TituloReceberBaixaModel tituloReceberBaixaModel : collBaixasTitulosReceber) {
            tituloReceberBaixaModel.setLoteContabil(loteContabil);
            HibernateUtil.getCurrentSession().update(tituloReceberBaixaModel);
        }

        for (TesourariaDebitoModel tesourariaDebitoModel : collTesourariaDebito) {
            tesourariaDebitoModel.setLoteContabil(loteContabil);
            HibernateUtil.getCurrentSession().update(tesourariaDebitoModel);
        }

        for (TesourariaCreditoModel tesourariaCreditoModel : collTesourariaCredito) {
            tesourariaCreditoModel.setLoteContabil(loteContabil);
            HibernateUtil.getCurrentSession().update(tesourariaCreditoModel);
        }

        for (ContaBancariaDebitoModel contaBancariaDebitoModel : collContaBancariaDebito) {
            contaBancariaDebitoModel.setLoteContabil(loteContabil);
            HibernateUtil.getCurrentSession().update(contaBancariaDebitoModel);
        }

        for (ContaBancariaCreditoModel contaBancariaCreditoModel : collContaBancariaCredito) {
            contaBancariaCreditoModel.setLoteContabil(loteContabil);
            HibernateUtil.getCurrentSession().update(contaBancariaCreditoModel);
        }


        for (ContaBancariaTransferenciaModel contaBancariaTransferenciaModel : collContaBancariaTransferencia) {
            contaBancariaTransferenciaModel.setLoteContabil(loteContabil);
            HibernateUtil.getCurrentSession().update(contaBancariaTransferenciaModel);
        }

        if (loteContabil != null && loteContabil.getMovimentoDiarioModel() != null) {
            MovimentoDiarioModel mov = loteContabil.getMovimentoDiarioModel();
            HibernateUtil.getCurrentSession().save(mov);
        }

    }

    @HibernateTransaction
    public void removerLote(Collection<TituloPagarModel> collTitulosPagar,
            Collection<TituloPagarBaixaModel> collBaixasTitulosPagar,
            Collection<TituloReceberModel> collTitulosReceber,
            Collection<TituloReceberBaixaModel> collBaixasTitulosReceber,
            Collection<TesourariaDebitoModel> collTesourariaDebito,
            Collection<TesourariaCreditoModel> collTesourariaCredito,
            Collection<ContaBancariaDebitoModel> collContaBancariaDebito,
            Collection<ContaBancariaCreditoModel> collContaBancariaCredito,
            Collection<ContaBancariaTransferenciaModel> collContaBancariaTransferencia,
            LoteContabilModel loteContabil) throws SystemException {

        if (loteContabil != null) {
            //registrar as quantidades de documentos exportados

            loteContabil.setStatus("REMOVIDO");
            loteContabil.setDataAtualizacao(PempecParse.dateToDate(new Date()));
            loteContabil.setUsuario(Controller.getUsuarioLogado());

            HibernateUtil.getCurrentSession().update(loteContabil);
        }


        if (collTitulosPagar != null) {

            for (TituloPagarModel tituloPagarModel : collTitulosPagar) {
                tituloPagarModel.setLoteContabil(null);
                HibernateUtil.getCurrentSession().update(tituloPagarModel);
            }
        }

        if (collBaixasTitulosPagar != null) {

            for (TituloPagarBaixaModel tituloPagarBaixaModel : collBaixasTitulosPagar) {
                tituloPagarBaixaModel.setLoteContabil(null);
                HibernateUtil.getCurrentSession().update(tituloPagarBaixaModel);
            }
        }

        if (collTitulosReceber != null) {

            for (TituloReceberModel tituloReceberModel : collTitulosReceber) {
                tituloReceberModel.setLoteContabil(null);
                HibernateUtil.getCurrentSession().update(tituloReceberModel);
            }
        }

        if (collBaixasTitulosReceber != null) {
            for (TituloReceberBaixaModel tituloReceberBaixaModel : collBaixasTitulosReceber) {
                tituloReceberBaixaModel.setLoteContabil(null);
                HibernateUtil.getCurrentSession().update(tituloReceberBaixaModel);
            }
        }

        if (collTesourariaDebito != null) {
            for (TesourariaDebitoModel tesourariaDebitoModel : collTesourariaDebito) {
                tesourariaDebitoModel.setLoteContabil(null);
                HibernateUtil.getCurrentSession().update(tesourariaDebitoModel);
            }
        }

        if (collTesourariaCredito != null) {
            for (TesourariaCreditoModel tesourariaCreditoModel : collTesourariaCredito) {
                tesourariaCreditoModel.setLoteContabil(null);
                HibernateUtil.getCurrentSession().update(tesourariaCreditoModel);
            }
        }

        if (collContaBancariaDebito != null) {
            for (ContaBancariaDebitoModel contaBancariaDebitoModel : collContaBancariaDebito) {
                contaBancariaDebitoModel.setLoteContabil(null);
                HibernateUtil.getCurrentSession().update(contaBancariaDebitoModel);
            }
        }

        if (collContaBancariaCredito != null) {
            for (ContaBancariaCreditoModel contaBancariaCreditoModel : collContaBancariaCredito) {
                contaBancariaCreditoModel.setLoteContabil(null);
                HibernateUtil.getCurrentSession().update(contaBancariaCreditoModel);
            }
        }

        if (collContaBancariaTransferencia != null) {
            for (ContaBancariaTransferenciaModel contaBancariaTransferenciaModel : collContaBancariaTransferencia) {
                contaBancariaTransferenciaModel.setLoteContabil(null);
                HibernateUtil.getCurrentSession().update(contaBancariaTransferenciaModel);
            }
        }

        if (loteContabil != null && loteContabil.getMovimentoDiarioModel() != null) {

            MovimentoDiarioModel mov = loteContabil.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

    }

    //remove lote sem um lote contabil
    //utilizado na manutencao do banco de dados
    @HibernateTransaction
    public void removerLote(Collection<TituloPagarModel> collTitulosPagar,
            Collection<TituloPagarBaixaModel> collBaixasTitulosPagar,
            Collection<TituloReceberModel> collTitulosReceber,
            Collection<TituloReceberBaixaModel> collBaixasTitulosReceber,
            Collection<TesourariaDebitoModel> collTesourariaDebito,
            Collection<TesourariaCreditoModel> collTesourariaCredito,
            Collection<ContaBancariaDebitoModel> collContaBancariaDebito,
            Collection<ContaBancariaCreditoModel> collContaBancariaCredito,
            Collection<ContaBancariaTransferenciaModel> collContaBancariaTransferencia) throws SystemException {



        if (collTitulosPagar != null) {

            for (TituloPagarModel tituloPagarModel : collTitulosPagar) {
                tituloPagarModel.setLoteContabil(null);
                HibernateUtil.getCurrentSession().update(tituloPagarModel);
            }
        }

        if (collBaixasTitulosPagar != null) {

            for (TituloPagarBaixaModel tituloPagarBaixaModel : collBaixasTitulosPagar) {
                tituloPagarBaixaModel.setLoteContabil(null);
                HibernateUtil.getCurrentSession().update(tituloPagarBaixaModel);
            }
        }

        if (collTitulosReceber != null) {

            for (TituloReceberModel tituloReceberModel : collTitulosReceber) {
                tituloReceberModel.setLoteContabil(null);
                HibernateUtil.getCurrentSession().update(tituloReceberModel);
            }
        }

        if (collBaixasTitulosReceber != null) {
            for (TituloReceberBaixaModel tituloReceberBaixaModel : collBaixasTitulosReceber) {
                tituloReceberBaixaModel.setLoteContabil(null);
                HibernateUtil.getCurrentSession().update(tituloReceberBaixaModel);
            }
        }

        if (collTesourariaDebito != null) {
            for (TesourariaDebitoModel tesourariaDebitoModel : collTesourariaDebito) {
                tesourariaDebitoModel.setLoteContabil(null);
                HibernateUtil.getCurrentSession().update(tesourariaDebitoModel);
            }
        }

        if (collTesourariaCredito != null) {
            for (TesourariaCreditoModel tesourariaCreditoModel : collTesourariaCredito) {
                tesourariaCreditoModel.setLoteContabil(null);
                HibernateUtil.getCurrentSession().update(tesourariaCreditoModel);
            }
        }

        if (collContaBancariaDebito != null) {
            for (ContaBancariaDebitoModel contaBancariaDebitoModel : collContaBancariaDebito) {
                contaBancariaDebitoModel.setLoteContabil(null);
                HibernateUtil.getCurrentSession().update(contaBancariaDebitoModel);
            }
        }

        if (collContaBancariaCredito != null) {
            for (ContaBancariaCreditoModel contaBancariaCreditoModel : collContaBancariaCredito) {
                contaBancariaCreditoModel.setLoteContabil(null);
                HibernateUtil.getCurrentSession().update(contaBancariaCreditoModel);
            }
        }

        if (collContaBancariaTransferencia != null) {
            for (ContaBancariaTransferenciaModel contaBancariaTransferenciaModel : collContaBancariaTransferencia) {
                contaBancariaTransferenciaModel.setLoteContabil(null);
                HibernateUtil.getCurrentSession().update(contaBancariaTransferenciaModel);
            }
        }

    }
}
