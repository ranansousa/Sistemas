package br.com.pempec.finance.utils;

import br.com.pempec.finance.businessObjects.CentroCustoBO;
import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.businessObjects.ContaBancariaCreditoBO;
import br.com.pempec.finance.businessObjects.ContaBancariaDebitoBO;
import br.com.pempec.finance.businessObjects.ContaBancariaTransferenciaBO;
import br.com.pempec.finance.businessObjects.FormaPagamentoBO;
import br.com.pempec.finance.businessObjects.HistoricoBO;
import br.com.pempec.finance.businessObjects.MegaContabilBO;
import br.com.pempec.finance.businessObjects.TesourariaCreditoBO;
import br.com.pempec.finance.businessObjects.TesourariaDebitoBO;
import br.com.pempec.finance.businessObjects.TituloPagarBO;
import br.com.pempec.finance.businessObjects.TituloPagarBaixaBO;
import br.com.pempec.finance.businessObjects.TituloReceberBO;
import br.com.pempec.finance.businessObjects.TituloReceberBaixaBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CentroCustoModel;
import br.com.pempec.finance.models.ContaBancariaCreditoModel;
import br.com.pempec.finance.models.ContaBancariaDebitoModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.ContaBancariaTransferenciaModel;
import br.com.pempec.finance.models.ContaContabilModel;
import br.com.pempec.finance.models.ExportMegaContabilModel;
import br.com.pempec.finance.models.FormaPagamentoModel;
import br.com.pempec.finance.models.HistoricoModel;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TesourariaCreditoModel;
import br.com.pempec.finance.models.TesourariaDebitoModel;
import br.com.pempec.finance.models.TituloPagarBaixaAcrescimoModel;
import br.com.pempec.finance.models.TituloPagarBaixaChequeModel;
import br.com.pempec.finance.models.TituloPagarBaixaDeducaoModel;
import br.com.pempec.finance.models.TituloPagarBaixaFormaPagamentoModel;
import br.com.pempec.finance.models.TituloPagarBaixaInternetModel;
import br.com.pempec.finance.models.TituloPagarBaixaModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.TituloPagarRateioCCModel;
import br.com.pempec.finance.models.TituloPagarRateioHistoricoModel;
import br.com.pempec.finance.models.TituloReceberBaixaAcrescimoModel;
import br.com.pempec.finance.models.TituloReceberBaixaDeducaoModel;
import br.com.pempec.finance.models.TituloReceberBaixaFormaPagamentoModel;
import br.com.pempec.finance.models.TituloReceberBaixaInternetModel;
import br.com.pempec.finance.models.TituloReceberBaixaModel;
import br.com.pempec.finance.models.TituloReceberModel;
import br.com.pempec.finance.models.TituloReceberRateioCCModel;
import br.com.pempec.finance.models.TituloReceberRateioHistoricoModel;
import br.com.pempec.finance.view.TelaPrincipal;
import br.com.pempec.finance.view.TelaProgresso;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 * * @author PEMPEC
 */
public class ExportacaoMegaContabil {

    static String TIPO_HEADER = "00";
    static String TIPO_ARQUIVO = "MEGACONTABIL";
    static String TIPO_IDENTIFICACAO = "01";
    private OrganizacaoModel organizacaoModel;
    private Date dataInicial;
    private Date dataFinal;
    private LoteContabilModel loteContabil;
    private Collection<String> dadosExportados;
    private Collection<CentroCustoModel> collCentroCusto;
    private Collection<HistoricoModel> collHistorico;
    private Collection<TituloPagarModel> collTitulosPagar;
    private Collection<TituloPagarBaixaModel> collBaixasTitulosPagar;
    private Collection<TituloReceberModel> collTitulosReceber;
    private Collection<TituloReceberBaixaModel> collBaixasTitulosReceber;
    private Collection<TesourariaDebitoModel> collTesourariaDebito;
    private Collection<TesourariaCreditoModel> collTesourariaCredito;
    private Collection<ContaBancariaDebitoModel> collContaBancariaDebito;
    private Collection<ContaBancariaCreditoModel> collContaBancariaCredito;
    private Collection<ContaBancariaTransferenciaModel> collContaBancariaTransferencia;
    private Collection<ContaBancariaModel> collContaBancaria;
    private CentroCustoBO centroCustoBO;
    private HistoricoBO historicoBO;
    private TituloPagarBO tituloPagarBO;
    private TituloPagarBaixaBO tituloPagarBaixaBO;
    private TituloReceberBO tituloReceberBO;
    private TituloReceberBaixaBO tituloReceberBaixaBO;
    private TesourariaDebitoBO tesourariaDebitoBO;
    private TesourariaCreditoBO tesourariaCreditoBO;
    private ContaBancariaCreditoBO contaBancariaCreditoBO;
    private ContaBancariaDebitoBO contaBancariaDebitoBO;
    private ContaBancariaTransferenciaBO contaBancariaTransferenciaBO;
    private MegaContabilBO megaContabilBO;
    private ContaBancariaBO contaBancariaBO;
    private FormaPagamentoBO formaPagamentoBO;
    private ContaContabilModel contaCaixa;

    public ExportacaoMegaContabil(OrganizacaoModel organizacao, Date dataInicial, Date dataFinal, LoteContabilModel lote) throws SystemException, ApplicationException {

        this.organizacaoModel = organizacao;
        this.dataInicial = PempecParse.dateToDateMinTime(dataInicial);
        this.dataFinal = PempecParse.dateToDateMaxTime(dataFinal);
        this.loteContabil = lote;
        this.dadosExportados = new ArrayList<String>();
        this.centroCustoBO = new CentroCustoBO();
        this.historicoBO = new HistoricoBO();
        this.tituloPagarBO = new TituloPagarBO();
        this.tituloPagarBaixaBO = new TituloPagarBaixaBO();
        this.tituloReceberBO = new TituloReceberBO();
        this.tituloReceberBaixaBO = new TituloReceberBaixaBO();
        this.tesourariaDebitoBO = new TesourariaDebitoBO();
        this.tesourariaCreditoBO = new TesourariaCreditoBO();
        this.contaBancariaCreditoBO = new ContaBancariaCreditoBO();
        this.contaBancariaDebitoBO = new ContaBancariaDebitoBO();
        this.contaBancariaTransferenciaBO = new ContaBancariaTransferenciaBO();
        this.megaContabilBO = new MegaContabilBO();
        this.contaBancariaBO = new ContaBancariaBO();
        this.formaPagamentoBO = new FormaPagamentoBO();

        this.exportarArquivo();

    }

    private ContaContabilModel getContaContabilByContaBancaria(ContaBancariaModel contaBancaria) {

        for (ContaBancariaModel conta : collContaBancaria) {

            if (conta.getPk().getId().equals(contaBancaria.getPk().getId())) {
                return conta.getContaContabil();
            }

        }

        return null;

    }

    private ContaContabilModel getContaContabilByHistorico(HistoricoModel historico) {

        for (HistoricoModel hist : collHistorico) {

            if (hist.getPk().getId().equals(historico.getPk().getId())) {
                return hist.getContaContabil();
            }

        }

        return null;

    }

    private void montaCabecalho() throws SystemException, ApplicationException {

        String linha = TIPO_HEADER + TIPO_ARQUIVO;

        dadosExportados.add(linha);

    }

    private void montaIdentificacao() throws SystemException, ApplicationException {

        String linha = TIPO_IDENTIFICACAO + String.format("%-18s", organizacaoModel.getCnpj());

        dadosExportados.add(linha);

    }

    private void exportarContaBancariaTransferencia() throws SystemException, ApplicationException {

        collContaBancariaTransferencia = contaBancariaTransferenciaBO.obterContaBancariaTransferenciaExportacao(organizacaoModel, dataInicial, dataFinal);

        for (ContaBancariaTransferenciaModel contaBancariaTransferenciaModel : collContaBancariaTransferencia) {

            ExportMegaContabilModel megaContabilD = new ExportMegaContabilModel();
            ExportMegaContabilModel megaContabilC = new ExportMegaContabilModel();

            megaContabilD.setCentroCusto(0);
            megaContabilC.setCentroCusto(0);
            megaContabilD.setDataLancamento(contaBancariaTransferenciaModel.getDataMovimento());
            megaContabilC.setDataLancamento(contaBancariaTransferenciaModel.getDataMovimento());
            megaContabilD.setCodigoHistoricoPadrao(contaBancariaTransferenciaModel.getTipoOperacaoBancariaModel().getCodigo());
            megaContabilD.setHistorico(PempecUtil.concatenaComEspaco(contaBancariaTransferenciaModel.getTipoOperacaoBancariaModel().getDescricao(), contaBancariaTransferenciaModel.getObservacao()));
            megaContabilC.setCodigoHistoricoPadrao(contaBancariaTransferenciaModel.getTipoOperacaoBancariaModel().getCodigo());
            megaContabilC.setHistorico(PempecUtil.concatenaComEspaco(contaBancariaTransferenciaModel.getTipoOperacaoBancariaModel().getDescricao(), contaBancariaTransferenciaModel.getObservacao()));

            ContaContabilModel contaContabilCreditoAux = this.getContaContabilByContaBancaria(contaBancariaTransferenciaModel.getContaBancariaDebitoModel().getContaBancaria());

            ContaContabilModel contaContabilDebitoAux = this.getContaContabilByContaBancaria(contaBancariaTransferenciaModel.getContaBancariaCreditoModel().getContaBancaria());

            try {

                if (contaContabilDebitoAux != null) {

                    megaContabilD.setContaDebito(contaContabilDebitoAux.getContaReduzida());
                    megaContabilD.setDigitoContaDebito(contaContabilDebitoAux.getDigitoContaReduzida());

                } else {

                    megaContabilD.setContaDebito("");
                    megaContabilD.setDigitoContaDebito("");

                }

            } catch (Exception e) {

                megaContabilD.setContaDebito("");
                megaContabilD.setDigitoContaDebito("");

            }

            try {

                if (contaContabilCreditoAux != null) {

                    megaContabilC.setContaCredito(contaContabilCreditoAux.getContaReduzida());
                    megaContabilC.setDigitoContaCredito(contaContabilCreditoAux.getDigitoContaReduzida());

                } else {

                    megaContabilC.setContaCredito("");
                    megaContabilC.setDigitoContaCredito("");

                }

            } catch (Exception e) {

                megaContabilC.setContaCredito("");
                megaContabilC.setDigitoContaCredito("");

            }

            megaContabilD.setValor(contaBancariaTransferenciaModel.getValor());
            megaContabilC.setValor(contaBancariaTransferenciaModel.getValor());

            if (megaContabilC.getValor() > 0 && megaContabilD.getValor() > 0) {
                dadosExportados.add(megaContabilD.getTipoLancamento());
                dadosExportados.add(megaContabilC.getTipoLancamento());
            }

        }

    }

    private void exportarContaBancariaDebito() throws SystemException, ApplicationException {

        collContaBancariaDebito = contaBancariaDebitoBO.obterContaBancariaDebitoExportacao(organizacaoModel, dataInicial, dataFinal);

        for (ContaBancariaDebitoModel contaBancariaDebitoModel : collContaBancariaDebito) {

            ExportMegaContabilModel megaContabilD = new ExportMegaContabilModel();
            ExportMegaContabilModel megaContabilC = new ExportMegaContabilModel();

            megaContabilD.setCentroCusto(0);
            megaContabilC.setCentroCusto(0);
            megaContabilD.setDataLancamento(contaBancariaDebitoModel.getDataMovimento());
            megaContabilC.setDataLancamento(contaBancariaDebitoModel.getDataMovimento());
            megaContabilD.setCodigoHistoricoPadrao(contaBancariaDebitoModel.getTipoOperacaoBancaria().getCodigo());
            megaContabilD.setHistorico(PempecUtil.concatenaComEspaco(contaBancariaDebitoModel.getTipoOperacaoBancaria().getDescricao(), contaBancariaDebitoModel.getDescricao()));
            megaContabilC.setCodigoHistoricoPadrao(contaBancariaDebitoModel.getTipoOperacaoBancaria().getCodigo());
            megaContabilC.setHistorico(PempecUtil.concatenaComEspaco(contaBancariaDebitoModel.getTipoOperacaoBancaria().getDescricao(), contaBancariaDebitoModel.getDescricao()));

            ContaContabilModel contaContabilDebitoAux = this.getContaContabilByContaBancaria(contaBancariaDebitoModel.getContaBancaria());

            try {

                if (contaBancariaDebitoModel.getTipoOperacaoBancaria() != null
                        && contaBancariaDebitoModel.getTipoOperacaoBancaria().getContaContabil() != null) {

                    megaContabilD.setContaDebito(contaBancariaDebitoModel.getTipoOperacaoBancaria().getContaContabil().getContaReduzida());
                    megaContabilD.setDigitoContaDebito(contaBancariaDebitoModel.getTipoOperacaoBancaria().getContaContabil().getDigitoContaReduzida());

                } else {

                    megaContabilD.setContaDebito("");
                    megaContabilD.setDigitoContaDebito("");

                }

            } catch (Exception e) {

                megaContabilD.setContaDebito("");
                megaContabilD.setDigitoContaDebito("");

            }

            try {

                if (contaContabilDebitoAux != null) {

                    megaContabilC.setContaCredito(contaContabilDebitoAux.getContaReduzida());
                    megaContabilC.setDigitoContaCredito(contaContabilDebitoAux.getDigitoContaReduzida());

                } else {

                    megaContabilC.setContaCredito("");
                    megaContabilC.setDigitoContaCredito("");

                }

            } catch (Exception e) {

                megaContabilC.setContaCredito("");
                megaContabilC.setDigitoContaCredito("");

            }

            megaContabilD.setValor(contaBancariaDebitoModel.getValor());
            megaContabilC.setValor(contaBancariaDebitoModel.getValor());

            // dadosExportados.add(megaContabilD.getTipoLancamento());
            //  dadosExportados.add(megaContabilC.getTipoLancamento());
            if (megaContabilC.getValor() > 0 && megaContabilD.getValor() > 0) {
                dadosExportados.add(megaContabilD.getTipoLancamento());
                dadosExportados.add(megaContabilC.getTipoLancamento());
            }

        }

    }

    private void exportarContaBancariaCredito() throws SystemException, ApplicationException {

        collContaBancariaCredito = contaBancariaCreditoBO.obterContaBancariaCreditoExportacao(organizacaoModel, dataInicial, dataFinal);

        for (ContaBancariaCreditoModel contaBancariaCreditoModel : collContaBancariaCredito) {

            ExportMegaContabilModel megaContabilD = new ExportMegaContabilModel();
            ExportMegaContabilModel megaContabilC = new ExportMegaContabilModel();

            megaContabilD.setCentroCusto(0);
            megaContabilC.setCentroCusto(0);
            megaContabilD.setDataLancamento(contaBancariaCreditoModel.getDataMovimento());
            megaContabilC.setDataLancamento(contaBancariaCreditoModel.getDataMovimento());
            megaContabilD.setCodigoHistoricoPadrao(contaBancariaCreditoModel.getTipoOperacaoBancaria().getCodigo());
            megaContabilD.setHistorico(PempecUtil.concatenaComEspaco(contaBancariaCreditoModel.getTipoOperacaoBancaria().getDescricao(), contaBancariaCreditoModel.getDescricao()));
            megaContabilC.setCodigoHistoricoPadrao(contaBancariaCreditoModel.getTipoOperacaoBancaria().getCodigo());
            megaContabilC.setHistorico(PempecUtil.concatenaComEspaco(contaBancariaCreditoModel.getTipoOperacaoBancaria().getDescricao(), contaBancariaCreditoModel.getDescricao()));

            ContaContabilModel contaContabilCreditoAux = this.getContaContabilByContaBancaria(contaBancariaCreditoModel.getContaBancaria());

            try {

                if (contaContabilCreditoAux != null) {

                    megaContabilD.setContaDebito(contaContabilCreditoAux.getContaReduzida());
                    megaContabilD.setDigitoContaDebito(contaContabilCreditoAux.getDigitoContaReduzida());

                } else {

                    megaContabilD.setContaDebito("");
                    megaContabilD.setDigitoContaDebito("");

                }

            } catch (Exception e) {

                megaContabilD.setContaDebito("");
                megaContabilD.setDigitoContaDebito("");

            }

            try {

                if (contaBancariaCreditoModel.getTipoOperacaoBancaria() != null
                        && contaBancariaCreditoModel.getTipoOperacaoBancaria().getContaContabil() != null) {

                    megaContabilC.setContaCredito(contaBancariaCreditoModel.getTipoOperacaoBancaria().getContaContabil().getContaReduzida());
                    megaContabilC.setDigitoContaCredito(contaBancariaCreditoModel.getTipoOperacaoBancaria().getContaContabil().getDigitoContaReduzida());

                } else {

                    megaContabilC.setContaCredito("");
                    megaContabilC.setDigitoContaCredito("");

                }

            } catch (Exception e) {

                megaContabilC.setContaCredito("");
                megaContabilC.setDigitoContaCredito("");

            }

            megaContabilD.setValor(contaBancariaCreditoModel.getValor());
            megaContabilC.setValor(contaBancariaCreditoModel.getValor());

            //dadosExportados.add(megaContabilD.getTipoLancamento());
            //dadosExportados.add(megaContabilC.getTipoLancamento());
            if (megaContabilC.getValor() > 0 && megaContabilD.getValor() > 0) {
                dadosExportados.add(megaContabilD.getTipoLancamento());
                dadosExportados.add(megaContabilC.getTipoLancamento());
            }

        }

    }

    private void exportarTesourariaDebito() throws SystemException, ApplicationException {

        collTesourariaDebito = tesourariaDebitoBO.obterTesourariaDebitoExportacao(organizacaoModel, dataInicial, dataFinal);

        for (TesourariaDebitoModel tesourariaDebitoModel : collTesourariaDebito) {

            ExportMegaContabilModel megaContabilD = new ExportMegaContabilModel();
            ExportMegaContabilModel megaContabilC = new ExportMegaContabilModel();

            megaContabilD.setCentroCusto(0);
            megaContabilC.setCentroCusto(0);
            megaContabilD.setDataLancamento(tesourariaDebitoModel.getDataMovimento());
            megaContabilC.setDataLancamento(tesourariaDebitoModel.getDataMovimento());
            megaContabilD.setCodigoHistoricoPadrao(tesourariaDebitoModel.getHistorico().getCodigo());
            megaContabilD.setHistorico(PempecUtil.concatenaComEspaco(tesourariaDebitoModel.getHistorico().getDescricao() + " " + tesourariaDebitoModel.getDescricao()));
            megaContabilC.setCodigoHistoricoPadrao(tesourariaDebitoModel.getHistorico().getCodigo());
            megaContabilC.setHistorico(PempecUtil.concatenaComEspaco(tesourariaDebitoModel.getHistorico().getDescricao() + " " + tesourariaDebitoModel.getDescricao()));

            try {

                if (tesourariaDebitoModel.getHistorico() != null
                        && tesourariaDebitoModel.getHistorico().getContaContabil() != null) {

                    megaContabilD.setContaDebito(tesourariaDebitoModel.getHistorico().getContaContabil().getContaReduzida());
                    megaContabilD.setDigitoContaDebito(tesourariaDebitoModel.getHistorico().getContaContabil().getDigitoContaReduzida());

                } else {

                    megaContabilD.setContaDebito("");
                    megaContabilD.setDigitoContaDebito("");

                }

            } catch (Exception e) {

                megaContabilD.setContaDebito("");
                megaContabilD.setDigitoContaDebito("");

            }

            if (contaCaixa != null) {

                megaContabilC.setContaCredito(contaCaixa.getContaReduzida());
                megaContabilC.setDigitoContaCredito(contaCaixa.getDigitoContaReduzida());

            } else {

                megaContabilC.setContaCredito("");
                megaContabilC.setDigitoContaCredito("");

            }

            megaContabilD.setValor(tesourariaDebitoModel.getValorNominal());
            megaContabilC.setValor(tesourariaDebitoModel.getValorNominal());

            // dadosExportados.add(megaContabilD.getTipoLancamento());
            // dadosExportados.add(megaContabilC.getTipoLancamento());
            if (megaContabilC.getValor() > 0 && megaContabilD.getValor() > 0) {
                dadosExportados.add(megaContabilD.getTipoLancamento());
                dadosExportados.add(megaContabilC.getTipoLancamento());
            }

        }

    }

    private void exportarTesourariaCredito() throws SystemException, ApplicationException {

        collTesourariaCredito = tesourariaCreditoBO.obterTesourariaCreditoExportacao(organizacaoModel, dataInicial, dataFinal);

        for (TesourariaCreditoModel tesourariaCreditoModel : collTesourariaCredito) {

            ExportMegaContabilModel megaContabilD = new ExportMegaContabilModel();
            ExportMegaContabilModel megaContabilC = new ExportMegaContabilModel();

            megaContabilD.setCentroCusto(0);
            megaContabilC.setCentroCusto(0);
            megaContabilD.setDataLancamento(tesourariaCreditoModel.getDataMovimento());
            megaContabilC.setDataLancamento(tesourariaCreditoModel.getDataMovimento());
            megaContabilD.setCodigoHistoricoPadrao(tesourariaCreditoModel.getHistorico().getCodigo());
            megaContabilD.setHistorico(PempecUtil.concatenaComEspaco(tesourariaCreditoModel.getHistorico().getDescricao() + " " + tesourariaCreditoModel.getDescricao()));
            megaContabilC.setCodigoHistoricoPadrao(tesourariaCreditoModel.getHistorico().getCodigo());
            megaContabilC.setHistorico(PempecUtil.concatenaComEspaco(tesourariaCreditoModel.getHistorico().getDescricao() + " " + tesourariaCreditoModel.getDescricao()));

            if (contaCaixa != null) {

                megaContabilD.setContaDebito(contaCaixa.getContaReduzida());
                megaContabilD.setDigitoContaDebito(contaCaixa.getDigitoContaReduzida());

            } else {

                megaContabilD.setContaDebito("");
                megaContabilD.setDigitoContaDebito("");

            }

            try {

                if (tesourariaCreditoModel.getHistorico() != null
                        && tesourariaCreditoModel.getHistorico().getContaContabil() != null) {

                    megaContabilC.setContaCredito(tesourariaCreditoModel.getHistorico().getContaContabil().getContaReduzida());
                    megaContabilC.setDigitoContaCredito(tesourariaCreditoModel.getHistorico().getContaContabil().getDigitoContaReduzida());

                } else {

                    megaContabilC.setContaCredito("");
                    megaContabilC.setDigitoContaCredito("");

                }

            } catch (Exception e) {

                megaContabilC.setContaCredito("");
                megaContabilC.setDigitoContaCredito("");

            }

            megaContabilD.setValor(tesourariaCreditoModel.getValorNominal());
            megaContabilC.setValor(tesourariaCreditoModel.getValorNominal());

            //  dadosExportados.add(megaContabilD.getTipoLancamento());
            // dadosExportados.add(megaContabilC.getTipoLancamento());
            if (megaContabilC.getValor() > 0 && megaContabilD.getValor() > 0) {
                dadosExportados.add(megaContabilD.getTipoLancamento());
                dadosExportados.add(megaContabilC.getTipoLancamento());
            }

        }

    }

    private void exportarContasPagar() throws SystemException, ApplicationException {

        collTitulosPagar = tituloPagarBO.obterTitulosExportacao(organizacaoModel, dataInicial, dataFinal);

        Map<String, String> provisionados = new HashMap<String, String>();

        globalTP:
        for (TituloPagarModel tituloPagarModel : collTitulosPagar) {

            if (tituloPagarModel.isProvisao()) {

                if (tituloPagarModel.getRegistroProvisao() != null
                        && !tituloPagarModel.getRegistroProvisao().isEmpty()) {

                    if (provisionados.containsKey(tituloPagarModel.getRegistroProvisao())) {
                        continue globalTP;
                    }

                    Double somaProvisionados = 0d;

                    Double rateioHistAux = 0d;

                    Double rateioCCAux = 0d;

                    for (TituloPagarModel titulo : collTitulosPagar) {
                        if (titulo.isProvisao()) {

                            if (titulo.getRegistroProvisao() != null
                                    && titulo.getRegistroProvisao().equals(tituloPagarModel.getRegistroProvisao())) {
                                somaProvisionados += titulo.getValorNominal();

                                for (TituloPagarRateioHistoricoModel rateio : titulo.getCollHistoricosRateio()) {
                                    rateioHistAux += rateio.getValor();
                                }

                                for (TituloPagarRateioCCModel rateioCC : titulo.getCollCentroCustosRateio()) {
                                    rateioCCAux += rateioCC.getValor();
                                }

                            }
                        }
                    }

                    tituloPagarModel.setValorNominal(somaProvisionados);

                    for (TituloPagarRateioHistoricoModel rateio : tituloPagarModel.getCollHistoricosRateio()) {
                        rateio.setValor(rateioHistAux);
                    }

                    for (TituloPagarRateioCCModel rateioCC : tituloPagarModel.getCollCentroCustosRateio()) {
                        rateioCC.setValor(rateioCCAux);
                    }

                    provisionados.put(tituloPagarModel.getRegistroProvisao(), tituloPagarModel.getRegistroProvisao());

                }

                ExportMegaContabilModel megaContabilD;
                ExportMegaContabilModel megaContabilC = new ExportMegaContabilModel();

                for (TituloPagarRateioHistoricoModel rateio : tituloPagarModel.getCollHistoricosRateio()) {

                    for (TituloPagarRateioCCModel rateioCC : tituloPagarModel.getCollCentroCustosRateio()) {

                        megaContabilD = new ExportMegaContabilModel();

                        ContaContabilModel contaContabil = this.getContaContabilByHistorico(rateio.getHistoricoModel());

                        try {

                            megaContabilD.setCentroCusto(rateioCC.getCentroCustoModel().getCodigo());

                        } catch (Exception e) {

                            for (CentroCustoModel centroCusto : collCentroCusto) {

                                if (rateioCC.getCentroCustoModel().getPk().getId().equals(centroCusto.getPk().getId()) && rateioCC.getCentroCustoModel().getPk().getOrganizacao().getId().equals(centroCusto.getPk().getOrganizacao().getId())) {

                                    megaContabilD.setCentroCusto(centroCusto.getCodigo());

                                    break;

                                }

                            }

                        }

                        try {

                            megaContabilD.setCodigoHistoricoPadrao(rateio.getHistoricoModel().getCodigo());
                            megaContabilD.setHistorico(PempecUtil.concatenaComEspaco(rateio.getHistoricoModel().getDescricao() + " " + tituloPagarModel.getDescricao()));

                        } catch (Exception e) {

                            for (HistoricoModel historico : collHistorico) {

                                if (rateio.getHistoricoModel().getPk().getId().equals(historico.getPk().getId()) && rateio.getHistoricoModel().getPk().getOrganizacao().getId().equals(historico.getPk().getOrganizacao().getId())) {

                                    megaContabilD.setCodigoHistoricoPadrao(historico.getCodigo());

                                    megaContabilD.setHistorico(PempecUtil.concatenaComEspaco(historico.getDescricao() + " " + tituloPagarModel.getDescricao()));

                                    break;

                                }

                            }

                        }

                        megaContabilD.setDataLancamento(tituloPagarModel.getDataEmissao());

                        try {

                            megaContabilD.setCentroCusto(tituloPagarModel.getCentroCusto().getCodigo());

                        } catch (Exception e) {

                            for (CentroCustoModel centroCusto : collCentroCusto) {

                                if (tituloPagarModel.getCentroCusto().getPk().getId().equals(centroCusto.getPk().getId())) {

                                    megaContabilD.setCentroCusto(centroCusto.getCodigo());

                                }

                            }

                        }

                        try {

                            if (contaContabil != null) {

                                megaContabilD.setContaDebito(contaContabil.getContaReduzida());
                                megaContabilD.setDigitoContaDebito(contaContabil.getDigitoContaReduzida());

                            } else {

                                megaContabilD.setContaDebito("NC");
                                megaContabilD.setDigitoContaDebito("NC");

                            }

                        } catch (Exception e) {

                            megaContabilD.setContaDebito("NC");
                            megaContabilD.setDigitoContaDebito("NC");

                        }

                        megaContabilD.setValor((rateioCC.getValor() / tituloPagarModel.getValorNominal()) * rateio.getValor());

                        if (megaContabilD.getValor() > 0) {

                            dadosExportados.add(megaContabilD.getTipoLancamento());

                        }

                    }

                }

                try {

                    megaContabilC.setCentroCusto(tituloPagarModel.getCentroCusto().getCodigo());

                } catch (Exception e) {

                    for (CentroCustoModel centroCusto : collCentroCusto) {

                        if (tituloPagarModel.getCentroCusto().getPk().getId().equals(centroCusto.getPk().getId())) {

                            megaContabilC.setCentroCusto(centroCusto.getCodigo());

                        }

                    }

                }

                megaContabilC.setDataLancamento(tituloPagarModel.getDataEmissao());
                megaContabilC.setCodigoHistoricoPadrao(tituloPagarModel.getHistorico().getCodigo());
                megaContabilC.setHistorico(PempecUtil.concatenaComEspaco(tituloPagarModel.getHistorico().getDescricao() + " " + tituloPagarModel.getDescricao()));

                try {

                    if (tituloPagarModel.getContaContabilCredito() != null) {

                        megaContabilC.setContaCredito(tituloPagarModel.getContaContabilCredito().getContaReduzida());
                        megaContabilC.setDigitoContaCredito(tituloPagarModel.getContaContabilCredito().getDigitoContaReduzida());

                    } else {

                        megaContabilC.setContaCredito("");
                        megaContabilC.setDigitoContaCredito("");

                    }

                } catch (Exception e) {

                    megaContabilC.setContaCredito("");
                    megaContabilC.setDigitoContaCredito("");

                }

                megaContabilC.setValor(tituloPagarModel.getValorNominal());

                dadosExportados.add(megaContabilC.getTipoLancamento());

                if (megaContabilC.getValor() > 0) {

                    dadosExportados.add(megaContabilC.getTipoLancamento());

                }

            }

        }

    }

    private void exportarBaixasContasPagar() throws SystemException, ApplicationException {

        collBaixasTitulosPagar = tituloPagarBaixaBO.obterBaixasExportacao(organizacaoModel, dataInicial, dataFinal);

//        globalTP: for (TituloPagarBaixaModel baixa : collBaixasTitulosPagar) {
        for (TituloPagarBaixaModel baixa : collBaixasTitulosPagar) {

            //Validação para exportação de titulo pago em cheque, somente se o cheque for compensado.
//            if (baixa.getTituloPagarBaixaCheque() != null
//                    && !baixa.getTituloPagarBaixaCheque().isEmpty()) {
//                for (TituloPagarBaixaChequeModel cheque : baixa.getTituloPagarBaixaCheque()) {
//                    if (!cheque.getContaBancariaCheque().getStatus().getPk().getId().equals(Constantes.STATUS_CHEQUE_COMPENSADO)) {
//                        baixa.setExportar(false);
//                        continue globalTP;
//                    }
//                }
//            }
            ExportMegaContabilModel megaContabilC = null;
            ExportMegaContabilModel megaContabilD = null;
            ExportMegaContabilModel megaContabilAC = null;
            ExportMegaContabilModel megaContabilDE = null;

            /*
             for (TituloPagarModel titulo : collTitulosPagar) {
             if (titulo.getPk().getId().equals(baixa.getTitulo().getPk().getId())
             && titulo.getPk().getOrganizacao().getId().equals(baixa.getTitulo().getPk().getOrganizacao().getId())) {
             baixa.setTitulo(titulo);
             break;
             }
             }*/
            for (TituloPagarRateioHistoricoModel rateio : baixa.getTitulo().getCollHistoricosRateio()) {

                for (TituloPagarRateioCCModel rateioCC : baixa.getTitulo().getCollCentroCustosRateio()) {

                    megaContabilD = new ExportMegaContabilModel();

                    if (baixa.getTitulo().isProvisao()) {

                        try {

                            megaContabilD.setCentroCusto(baixa.getTitulo().getCentroCusto().getCodigo());

                        } catch (Exception e) {

                            for (CentroCustoModel centroCusto : collCentroCusto) {

                                if (baixa.getTitulo().getCentroCusto().getPk().getId().equals(centroCusto.getPk().getId())) {

                                    megaContabilD.setCentroCusto(centroCusto.getCodigo());

                                }

                            }

                        }

                        megaContabilD.setDataLancamento(baixa.getTitulo().getDataPagamento());

                        try {

                            megaContabilD.setCodigoHistoricoPadrao(baixa.getTitulo().getHistorico().getCodigo());
                            megaContabilD.setHistorico(PempecUtil.concatenaComEspaco(baixa.getTitulo().getHistorico().getDescricao() + " " + baixa.getTitulo().getDescricao()));

                        } catch (Exception e) {

                            for (HistoricoModel historico : collHistorico) {

                                if (baixa.getTitulo().getHistorico().getPk().getId().equals(historico.getPk().getId()) && baixa.getTitulo().getHistorico().getPk().getOrganizacao().getId().equals(historico.getPk().getOrganizacao().getId())) {

                                    megaContabilD.setCodigoHistoricoPadrao(historico.getCodigo());

                                    megaContabilD.setHistorico(PempecUtil.concatenaComEspaco(historico.getDescricao() + " " + baixa.getTitulo().getDescricao()));

                                }

                            }
                        }

                        try {

                            if (baixa.getTitulo().getContaContabilCredito() != null) {

                                megaContabilD.setContaDebito(baixa.getTitulo().getContaContabilCredito().getContaReduzida());
                                megaContabilD.setDigitoContaDebito(baixa.getTitulo().getContaContabilCredito().getDigitoContaReduzida());

                            } else {

                                megaContabilD.setContaDebito("");
                                megaContabilD.setDigitoContaDebito("");

                            }

                        } catch (Exception e) {

                            megaContabilD.setContaDebito("");
                            megaContabilD.setDigitoContaDebito("");

                        }

                    } else {

                        ContaContabilModel contaContabil = this.getContaContabilByHistorico(rateio.getHistoricoModel());

                        megaContabilD.setCentroCusto(rateioCC.getCentroCustoModel().getCodigo());

                        megaContabilD.setDataLancamento(baixa.getTitulo().getDataPagamento());

                        try {

                            megaContabilD.setCodigoHistoricoPadrao(rateio.getHistoricoModel().getCodigo());
                            megaContabilD.setHistorico(PempecUtil.concatenaComEspaco(rateio.getHistoricoModel().getDescricao() + " " + baixa.getTitulo().getDescricao()));

                        } catch (Exception e) {

                            for (HistoricoModel historico : collHistorico) {

                                if (baixa.getTitulo().getHistorico().getPk().getId().equals(historico.getPk().getId()) && baixa.getTitulo().getHistorico().getPk().getOrganizacao().getId().equals(historico.getPk().getOrganizacao().getId())) {

                                    megaContabilD.setCodigoHistoricoPadrao(historico.getCodigo());

                                    megaContabilD.setHistorico(PempecUtil.concatenaComEspaco(historico.getDescricao() + " " + baixa.getTitulo().getDescricao()));

                                }

                            }
                        }

                        try {

                            if (contaContabil != null) {

                                megaContabilD.setContaDebito(contaContabil.getContaReduzida());
                                megaContabilD.setDigitoContaDebito(contaContabil.getDigitoContaReduzida());

                            } else {

                                megaContabilD.setContaDebito("");
                                megaContabilD.setDigitoContaDebito("");

                            }

                        } catch (Exception e) {

                            megaContabilD.setContaDebito("");
                            megaContabilD.setDigitoContaDebito("");

                        }

                    }

                    megaContabilD.setValor((rateioCC.getValor() / baixa.getTitulo().getValorNominal()) * rateio.getValor());

                    // dadosExportados.add(megaContabilD.getTipoLancamento());
                    if (megaContabilD.getValor() > 0) {

                        dadosExportados.add(megaContabilD.getTipoLancamento());

                    }

                }

            }

            for (TituloPagarBaixaAcrescimoModel acrescimo : baixa.getAcrescimos()) {

                megaContabilAC = new ExportMegaContabilModel();

                for (HistoricoModel historico : collHistorico) {

                    try {

                        try {

                            megaContabilAC.setCentroCusto(baixa.getTitulo().getCentroCusto().getCodigo());

                        } catch (Exception e) {

                            for (CentroCustoModel centroCusto : collCentroCusto) {

                                if (baixa.getTitulo().getCentroCusto().getPk().getId().equals(centroCusto.getPk().getId())) {

                                    megaContabilAC.setCentroCusto(centroCusto.getCodigo());

                                }

                            }

                        }

                        megaContabilAC.setDataLancamento(baixa.getTitulo().getDataPagamento());

                        if (acrescimo.getTipoAcrescimo().getHistorico().getPk().getId().equals(historico.getPk().getId())) {

                            megaContabilAC.setCodigoHistoricoPadrao(historico.getCodigo());
                            megaContabilAC.setHistorico(PempecUtil.concatenaComEspaco(historico.getDescricao()));

                            if (historico.getContaContabil() != null) {

                                megaContabilAC.setContaDebito(historico.getContaContabil().getContaReduzida());
                                megaContabilAC.setDigitoContaDebito(historico.getContaContabil().getDigitoContaReduzida());

                            } else {

                                megaContabilAC.setContaDebito("");
                                megaContabilAC.setDigitoContaDebito("");

                            }

                            break;

                        }

                    } catch (Exception e) {

                        megaContabilAC.setContaDebito("");
                        megaContabilAC.setDigitoContaDebito("");

                    }

                }

                megaContabilAC.setValor(acrescimo.getValor());

                //dadosExportados.add(megaContabilAC.getTipoLancamento());
                if (megaContabilAC.getValor() > 0) {

                    dadosExportados.add(megaContabilAC.getTipoLancamento());

                }

            }

            for (TituloPagarBaixaDeducaoModel deducao : baixa.getDeducoes()) {

                megaContabilDE = new ExportMegaContabilModel();

                for (HistoricoModel historico : collHistorico) {

                    try {

                        try {

                            megaContabilDE.setCentroCusto(baixa.getTitulo().getCentroCusto().getCodigo());

                        } catch (Exception e) {

                            for (CentroCustoModel centroCusto : collCentroCusto) {

                                if (baixa.getTitulo().getCentroCusto().getPk().getId().equals(centroCusto.getPk().getId())) {

                                    megaContabilDE.setCentroCusto(centroCusto.getCodigo());

                                }

                            }

                        }

                        megaContabilDE.setDataLancamento(baixa.getTitulo().getDataPagamento());

                        if (historico.getPk().getId().equals(deducao.getTipoDeducao().getHistorico().getPk().getId())) {

                            megaContabilDE.setCodigoHistoricoPadrao(historico.getCodigo());
                            megaContabilDE.setHistorico(PempecUtil.concatenaComEspaco(historico.getDescricao()));

                            if (historico.getContaContabil() != null) {

                                megaContabilDE.setContaCredito(historico.getContaContabil().getContaReduzida());
                                megaContabilDE.setDigitoContaCredito(historico.getContaContabil().getDigitoContaReduzida());

                            } else {

                                megaContabilDE.setContaCredito("");
                                megaContabilDE.setDigitoContaCredito("");

                            }

                            break;

                        }

                    } catch (Exception e) {
                        megaContabilDE.setContaCredito("");
                        megaContabilDE.setDigitoContaCredito("");
                    }

                }

                megaContabilDE.setValor(deducao.getValor());

                if (megaContabilDE.getValor() > 0) {
                    dadosExportados.add(megaContabilDE.getTipoLancamento());
                }

            }

            for (TituloPagarBaixaFormaPagamentoModel formaPagamento : baixa.getFormasPagamento()) {

                if (formaPagamento.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_ESPECIE)) {

                    megaContabilC = new ExportMegaContabilModel();

                    try {

                        megaContabilC.setCentroCusto(baixa.getTitulo().getCentroCusto().getCodigo());

                    } catch (Exception e) {

                        for (CentroCustoModel centroCusto : collCentroCusto) {

                            if (baixa.getTitulo().getCentroCusto().getPk().getId().equals(centroCusto.getPk().getId())) {

                                megaContabilC.setCentroCusto(centroCusto.getCodigo());

                            }

                        }

                    }

                    megaContabilC.setDataLancamento(baixa.getTitulo().getDataPagamento());

                    try {

                        megaContabilC.setCodigoHistoricoPadrao(baixa.getTitulo().getHistorico().getCodigo());

                        megaContabilC.setHistorico(PempecUtil.concatenaComEspaco(baixa.getTitulo().getHistorico().getDescricao() + " " + baixa.getTitulo().getDescricao()));

                    } catch (Exception e) {

                        for (HistoricoModel historico : collHistorico) {

                            if (baixa.getTitulo().getHistorico().getPk().getId().equals(historico.getPk().getId()) && baixa.getTitulo().getHistorico().getPk().getOrganizacao().getId().equals(historico.getPk().getOrganizacao().getId())) {

                                megaContabilC.setCodigoHistoricoPadrao(historico.getCodigo());

                                megaContabilD.setHistorico(PempecUtil.concatenaComEspaco(historico.getDescricao() + " " + baixa.getTitulo().getDescricao()));

                                break;

                            }

                        }

                    }

                    try {

                        if (formaPagamento.getForma().getContaContabil() != null) {

                            megaContabilC.setContaCredito(contaCaixa.getContaReduzida());
                            megaContabilC.setDigitoContaCredito(contaCaixa.getDigitoContaReduzida());

                        } else {

                            megaContabilC.setContaCredito("");
                            megaContabilC.setDigitoContaCredito("");

                        }

                    } catch (Exception e) {

                        megaContabilC.setContaCredito("");
                        megaContabilC.setDigitoContaCredito("");

                    }

                    megaContabilC.setValor(formaPagamento.getValor());

                    if (megaContabilC.getValor() > 0) {
                        dadosExportados.add(megaContabilC.getTipoLancamento());
                    }

                }

            }

            for (TituloPagarBaixaChequeModel cheque : baixa.getTituloPagarBaixaCheque()) {

                megaContabilC = new ExportMegaContabilModel();

                ContaContabilModel contaContabilAux = this.getContaContabilByContaBancaria(cheque.getContaBancariaCheque().getContaBancaria());

                try {

                    megaContabilC.setCentroCusto(baixa.getTitulo().getCentroCusto().getCodigo());

                } catch (Exception e) {

                    for (CentroCustoModel centroCusto : collCentroCusto) {

                        if (baixa.getTitulo().getCentroCusto().getPk().getId().equals(centroCusto.getPk().getId())) {

                            megaContabilC.setCentroCusto(centroCusto.getCodigo());

                        }

                    }

                }

                megaContabilC.setDataLancamento(baixa.getTitulo().getDataPagamento());

                try {

                    megaContabilC.setCodigoHistoricoPadrao(baixa.getTitulo().getHistorico().getCodigo());

                    megaContabilC.setHistorico(PempecUtil.concatenaComEspaco(baixa.getTitulo().getHistorico().getDescricao() + " " + baixa.getTitulo().getDescricao()));

                } catch (Exception e) {

                    for (HistoricoModel historico : collHistorico) {

                        if (baixa.getTitulo().getHistorico().getPk().getId().equals(historico.getPk().getId()) && baixa.getTitulo().getHistorico().getPk().getOrganizacao().getId().equals(historico.getPk().getOrganizacao().getId())) {

                            megaContabilC.setCodigoHistoricoPadrao(historico.getCodigo());

                            megaContabilD.setHistorico(PempecUtil.concatenaComEspaco(historico.getDescricao() + " " + baixa.getTitulo().getDescricao()));

                            break;

                        }

                    }

                }

                try {

                    if (contaContabilAux != null) {

                        megaContabilC.setContaCredito(contaContabilAux.getContaReduzida());
                        megaContabilC.setDigitoContaCredito(contaContabilAux.getDigitoContaReduzida());

                    } else {

                        megaContabilC.setContaCredito("");
                        megaContabilC.setDigitoContaCredito("");

                    }

                } catch (Exception e) {

                    megaContabilC.setContaCredito("");
                    megaContabilC.setDigitoContaCredito("");

                }

                megaContabilC.setValor(cheque.getValor());

                if (megaContabilC.getValor() > 0) {
                    dadosExportados.add(megaContabilC.getTipoLancamento());
                }

            }

            for (TituloPagarBaixaInternetModel internet : baixa.getTituloPagarBaixaInternet()) {

                megaContabilC = new ExportMegaContabilModel();

                try {

                    megaContabilC.setCentroCusto(baixa.getTitulo().getCentroCusto().getCodigo());

                } catch (Exception e) {

                    for (CentroCustoModel centroCusto : collCentroCusto) {

                        if (baixa.getTitulo().getCentroCusto().getPk().getId().equals(centroCusto.getPk().getId())) {

                            megaContabilC.setCentroCusto(centroCusto.getCodigo());

                        }

                    }

                }

                megaContabilC.setDataLancamento(baixa.getTitulo().getDataPagamento());

                try {

                    megaContabilC.setCodigoHistoricoPadrao(baixa.getTitulo().getHistorico().getCodigo());

                    megaContabilC.setHistorico(PempecUtil.concatenaComEspaco(baixa.getTitulo().getHistorico().getDescricao() + " " + baixa.getTitulo().getDescricao()));

                } catch (Exception e) {

                    for (HistoricoModel historico : collHistorico) {

                        if (baixa.getTitulo().getHistorico().getPk().getId().equals(historico.getPk().getId()) && baixa.getTitulo().getHistorico().getPk().getOrganizacao().getId().equals(historico.getPk().getOrganizacao().getId())) {

                            megaContabilC.setCodigoHistoricoPadrao(historico.getCodigo());

                            megaContabilD.setHistorico(PempecUtil.concatenaComEspaco(historico.getDescricao() + " " + baixa.getTitulo().getDescricao()));

                            break;

                        }

                    }

                }

                try {

                    ContaContabilModel contaContabilAux = this.getContaContabilByContaBancaria(internet.getContaBancaria());

                    if (contaContabilAux != null) {

                        megaContabilC.setContaCredito(contaContabilAux.getContaReduzida());
                        megaContabilC.setDigitoContaCredito(contaContabilAux.getDigitoContaReduzida());

                    } else {

                        megaContabilC.setContaCredito("");
                        megaContabilC.setDigitoContaCredito("");

                    }

                } catch (Exception e) {

                    megaContabilC.setContaCredito("");
                    megaContabilC.setDigitoContaCredito("");

                }

                megaContabilC.setValor(internet.getValor());

                if (megaContabilC.getValor() > 0) {
                    dadosExportados.add(megaContabilC.getTipoLancamento());
                }

            }

        }

    }

    private void exportarContasReceber() throws SystemException, ApplicationException {

        collTitulosReceber = tituloReceberBO.obterTitulosExportacao(organizacaoModel, dataInicial, dataFinal);

        Map<String, String> provisionados = new HashMap<String, String>();

        globalTR:
        for (TituloReceberModel tituloReceberModel : collTitulosReceber) {

            if (tituloReceberModel.isProvisao()) {

                if (tituloReceberModel.getRegistroProvisao() != null
                        && !tituloReceberModel.getRegistroProvisao().isEmpty()) {

                    if (provisionados.containsKey(tituloReceberModel.getRegistroProvisao())) {
                        continue globalTR;
                    }

                    Double somaProvisionados = 0d;

                    Double rateioHistAux = 0d;
                    Double rateioCCAux = 0d;

                    for (TituloReceberModel titulo : collTitulosReceber) {

                        if (titulo.isProvisao()) {
                            if (titulo.getRegistroProvisao() != null
                                    && titulo.getRegistroProvisao().equals(tituloReceberModel.getRegistroProvisao())) {
                                somaProvisionados += titulo.getValorNominal();
                                for (TituloReceberRateioHistoricoModel rateio : titulo.getCollHistoricosRateio()) {
                                    rateioHistAux += rateio.getValor();
                                }
                                for (TituloReceberRateioCCModel rateioCC : titulo.getCollCentroCustosRateio()) {
                                    rateioCCAux += rateioCC.getValor();
                                }
                            }
                        }
                    }

                    tituloReceberModel.setValorNominal(somaProvisionados);

                    for (TituloReceberRateioHistoricoModel rateio : tituloReceberModel.getCollHistoricosRateio()) {
                        rateio.setValor(rateioHistAux);
                    }

                    for (TituloReceberRateioCCModel rateioCC : tituloReceberModel.getCollCentroCustosRateio()) {
                        rateioCC.setValor(rateioCCAux);
                    }

                    provisionados.put(tituloReceberModel.getRegistroProvisao(), tituloReceberModel.getRegistroProvisao());

                }

                ExportMegaContabilModel megaContabilD = new ExportMegaContabilModel();
                ExportMegaContabilModel megaContabilC;

                try {

                    megaContabilD.setCentroCusto(tituloReceberModel.getCentroCusto().getCodigo());

                } catch (Exception e) {

                    for (CentroCustoModel centroCusto : collCentroCusto) {

                        if (tituloReceberModel.getCentroCusto().getPk().getId().equals(centroCusto.getPk().getId())) {

                            megaContabilD.setCentroCusto(centroCusto.getCodigo());

                        }

                    }

                }

                megaContabilD.setDataLancamento(tituloReceberModel.getDataEmissao());
                megaContabilD.setCodigoHistoricoPadrao(tituloReceberModel.getHistorico().getCodigo());
                megaContabilD.setHistorico(PempecUtil.concatenaComEspaco(tituloReceberModel.getHistorico().getDescricao() + " " + tituloReceberModel.getDescricao()));

                try {

                    if (tituloReceberModel.getContaContabilDebito() != null) {

                        megaContabilD.setContaDebito(tituloReceberModel.getContaContabilDebito().getContaReduzida());
                        megaContabilD.setDigitoContaDebito(tituloReceberModel.getContaContabilDebito().getDigitoContaReduzida());

                    } else {

                        megaContabilD.setContaDebito("");
                        megaContabilD.setDigitoContaDebito("");

                    }

                } catch (Exception e) {

                    megaContabilD.setContaDebito("");
                    megaContabilD.setDigitoContaDebito("");

                }

                megaContabilD.setValor(tituloReceberModel.getValorNominal());

                if (megaContabilD.getValor() > 0) {
                    dadosExportados.add(megaContabilD.getTipoLancamento());
                }

                for (TituloReceberRateioHistoricoModel rateio : tituloReceberModel.getCollHistoricosRateio()) {

                    for (TituloReceberRateioCCModel rateioCC : tituloReceberModel.getCollCentroCustosRateio()) {

                        megaContabilC = new ExportMegaContabilModel();

                        ContaContabilModel contaContabil = this.getContaContabilByHistorico(rateio.getHistoricoModel());

                        try {

                            megaContabilC.setCentroCusto(rateioCC.getCentroCustoModel().getCodigo());

                        } catch (Exception e) {

                            for (CentroCustoModel centroCusto : collCentroCusto) {

                                if (rateioCC.getCentroCustoModel().getPk().getId().equals(centroCusto.getPk().getId()) && rateioCC.getCentroCustoModel().getPk().getOrganizacao().getId().equals(centroCusto.getPk().getOrganizacao().getId())) {

                                    megaContabilC.setCentroCusto(centroCusto.getCodigo());

                                    break;

                                }

                            }

                        }

                        try {

                            megaContabilC.setCodigoHistoricoPadrao(rateio.getHistoricoModel().getCodigo());
                            megaContabilC.setHistorico(PempecUtil.concatenaComEspaco(rateio.getHistoricoModel().getDescricao() + " " + tituloReceberModel.getDescricao()));

                        } catch (Exception e) {

                            for (HistoricoModel historico : collHistorico) {

                                if (rateio.getHistoricoModel().getPk().getId().equals(historico.getPk().getId()) && rateio.getHistoricoModel().getPk().getOrganizacao().getId().equals(historico.getPk().getOrganizacao().getId())) {

                                    megaContabilC.setCodigoHistoricoPadrao(historico.getCodigo());

                                    megaContabilC.setHistorico(PempecUtil.concatenaComEspaco(historico.getDescricao() + " " + tituloReceberModel.getDescricao()));

                                    break;

                                }

                            }

                        }

                        megaContabilC.setDataLancamento(tituloReceberModel.getDataEmissao());

                        try {

                            megaContabilC.setCentroCusto(tituloReceberModel.getCentroCusto().getCodigo());

                        } catch (Exception e) {

                            for (CentroCustoModel centroCusto : collCentroCusto) {

                                if (tituloReceberModel.getCentroCusto().getPk().getId().equals(centroCusto.getPk().getId())) {

                                    megaContabilC.setCentroCusto(centroCusto.getCodigo());

                                }

                            }

                        }

                        try {

                            if (contaContabil != null) {

                                megaContabilC.setContaCredito(contaContabil.getContaReduzida());
                                megaContabilC.setDigitoContaCredito(contaContabil.getDigitoContaReduzida());

                            } else {

                                megaContabilC.setContaCredito("");
                                megaContabilC.setDigitoContaCredito("");

                            }

                        } catch (Exception e) {

                            megaContabilC.setContaCredito("");
                            megaContabilC.setDigitoContaCredito("");

                        }

                        megaContabilC.setValor((rateioCC.getValor() / tituloReceberModel.getValorNominal()) * rateio.getValor());

                        if (megaContabilC.getValor() > 0) {
                            dadosExportados.add(megaContabilC.getTipoLancamento());
                        }

                    }

                }

            }

        }

    }

    private void exportarBaixasContasReceber() throws SystemException, ApplicationException {

        collBaixasTitulosReceber = tituloReceberBaixaBO.obterBaixasExportacao(organizacaoModel, dataInicial, dataFinal);

        for (TituloReceberBaixaModel baixa : collBaixasTitulosReceber) {

            ExportMegaContabilModel megaContabilC = null;
            ExportMegaContabilModel megaContabilD = null;
            ExportMegaContabilModel megaContabilAC = null;
            ExportMegaContabilModel megaContabilDE = null;

            /*
             for (TituloReceberModel titulo : collTitulosReceber) {
             if (titulo.getPk().getId().equals(baixa.getTitulo().getPk().getId())
             && titulo.getPk().getOrganizacao().getId().equals(baixa.getTitulo().getPk().getOrganizacao().getId())) {
             baixa.setTitulo(titulo);
             break;
             }
             }*/
            for (TituloReceberBaixaFormaPagamentoModel formaPagamento : baixa.getFormasPagamento()) {

                if (formaPagamento.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_ESPECIE)
                        || formaPagamento.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_CHEQUE)) {

                    megaContabilD = new ExportMegaContabilModel();

                    try {

                        megaContabilD.setCentroCusto(baixa.getTitulo().getCentroCusto().getCodigo());

                    } catch (Exception e) {

                        for (CentroCustoModel centroCusto : collCentroCusto) {

                            if (baixa.getTitulo().getCentroCusto().getPk().getId().equals(centroCusto.getPk().getId())) {

                                megaContabilD.setCentroCusto(centroCusto.getCodigo());

                            }

                        }

                    }

                    megaContabilD.setDataLancamento(baixa.getTitulo().getDataPagamento());

                    try {

                        megaContabilD.setCodigoHistoricoPadrao(baixa.getTitulo().getHistorico().getCodigo());

                        megaContabilD.setHistorico(PempecUtil.concatenaComEspaco(baixa.getTitulo().getHistorico().getDescricao() + " " + baixa.getTitulo().getDescricao()));

                    } catch (Exception e) {

                        for (HistoricoModel historico : collHistorico) {

                            if (baixa.getTitulo().getHistorico().getPk().getId().equals(historico.getPk().getId()) && baixa.getTitulo().getHistorico().getPk().getOrganizacao().getId().equals(historico.getPk().getOrganizacao().getId())) {

                                megaContabilD.setCodigoHistoricoPadrao(historico.getCodigo());

                                megaContabilD.setHistorico(PempecUtil.concatenaComEspaco(historico.getDescricao() + " " + baixa.getTitulo().getDescricao()));

                                break;

                            }

                        }

                    }

                    try {

                        if (contaCaixa != null) {

                            megaContabilD.setContaDebito(contaCaixa.getContaReduzida());
                            megaContabilD.setDigitoContaDebito(contaCaixa.getDigitoContaReduzida());

                        } else {

                            megaContabilD.setContaDebito("");
                            megaContabilD.setDigitoContaDebito("");

                        }

                    } catch (Exception e) {

                        megaContabilD.setContaDebito("");
                        megaContabilD.setDigitoContaDebito("");

                    }

                    megaContabilD.setValor(formaPagamento.getValor());
                    if (megaContabilD.getValor() > 0) {
                        dadosExportados.add(megaContabilD.getTipoLancamento());
                    }

                }

            }

            for (TituloReceberBaixaInternetModel internet : baixa.getTituloReceberBaixaInternet()) {

                megaContabilD = new ExportMegaContabilModel();

                ContaContabilModel contaContabilAux = this.getContaContabilByContaBancaria(internet.getContaBancaria());

                try {

                    megaContabilD.setCentroCusto(baixa.getTitulo().getCentroCusto().getCodigo());

                } catch (Exception e) {

                    for (CentroCustoModel centroCusto : collCentroCusto) {

                        if (baixa.getTitulo().getCentroCusto().getPk().getId().equals(centroCusto.getPk().getId())) {

                            megaContabilD.setCentroCusto(centroCusto.getCodigo());

                        }

                    }

                }

                megaContabilD.setDataLancamento(baixa.getTitulo().getDataPagamento());

                try {

                    megaContabilD.setCodigoHistoricoPadrao(baixa.getTitulo().getHistorico().getCodigo());

                    megaContabilD.setHistorico(PempecUtil.concatenaComEspaco(baixa.getTitulo().getHistorico().getDescricao() + " " + baixa.getTitulo().getDescricao()));

                } catch (Exception e) {

                    for (HistoricoModel historico : collHistorico) {

                        if (baixa.getTitulo().getHistorico().getPk().getId().equals(historico.getPk().getId()) && baixa.getTitulo().getHistorico().getPk().getOrganizacao().getId().equals(historico.getPk().getOrganizacao().getId())) {

                            megaContabilD.setCodigoHistoricoPadrao(historico.getCodigo());

                            megaContabilD.setHistorico(PempecUtil.concatenaComEspaco(historico.getDescricao() + " " + baixa.getTitulo().getDescricao()));

                            break;

                        }

                    }

                }

                try {

                    if (contaContabilAux != null) {

                        megaContabilD.setContaDebito(contaContabilAux.getContaReduzida());
                        megaContabilD.setDigitoContaDebito(contaContabilAux.getDigitoContaReduzida());

                    } else {

                        megaContabilD.setContaDebito("");
                        megaContabilD.setDigitoContaDebito("");

                    }

                } catch (Exception e) {

                    megaContabilD.setContaDebito("");
                    megaContabilD.setDigitoContaDebito("");

                }

                megaContabilD.setValor(internet.getValor());
                if (megaContabilD.getValor() > 0) {
                    dadosExportados.add(megaContabilD.getTipoLancamento());
                }

            }

            for (TituloReceberBaixaAcrescimoModel acrescimo : baixa.getAcrescimos()) {

                megaContabilAC = new ExportMegaContabilModel();

                for (HistoricoModel historico : collHistorico) {

                    try {

                        try {

                            megaContabilAC.setCentroCusto(baixa.getTitulo().getCentroCusto().getCodigo());

                        } catch (Exception e) {

                            for (CentroCustoModel centroCusto : collCentroCusto) {

                                if (baixa.getTitulo().getCentroCusto().getPk().getId().equals(centroCusto.getPk().getId())) {

                                    megaContabilAC.setCentroCusto(centroCusto.getCodigo());

                                }

                            }

                        }

                        megaContabilAC.setDataLancamento(baixa.getTitulo().getDataPagamento());

                        if (historico.getPk().getId().equals(acrescimo.getTipoAcrescimo().getHistorico().getPk().getId())) {

                            megaContabilAC.setCodigoHistoricoPadrao(historico.getCodigo());
                            megaContabilAC.setHistorico(PempecUtil.concatenaComEspaco(historico.getDescricao()));

                            if (historico.getContaContabil() != null) {

                                megaContabilAC.setContaCredito(historico.getContaContabil().getContaReduzida());
                                megaContabilAC.setDigitoContaCredito(historico.getContaContabil().getDigitoContaReduzida());

                            } else {

                                megaContabilAC.setContaCredito("");
                                megaContabilAC.setDigitoContaCredito("");

                            }

                            break;

                        }

                    } catch (Exception e) {

                        megaContabilAC.setContaCredito("");
                        megaContabilAC.setDigitoContaCredito("");

                    }

                }

                megaContabilAC.setValor(acrescimo.getValor());

                if (megaContabilAC.getValor() > 0) {
                    dadosExportados.add(megaContabilAC.getTipoLancamento());
                }

            }

            for (TituloReceberBaixaDeducaoModel deducao : baixa.getDeducoes()) {

                megaContabilDE = new ExportMegaContabilModel();

                for (HistoricoModel historico : collHistorico) {

                    try {

                        try {

                            megaContabilDE.setCentroCusto(baixa.getTitulo().getCentroCusto().getCodigo());

                        } catch (Exception e) {

                            for (CentroCustoModel centroCusto : collCentroCusto) {

                                if (baixa.getTitulo().getCentroCusto().getPk().getId().equals(centroCusto.getPk().getId())) {

                                    megaContabilDE.setCentroCusto(centroCusto.getCodigo());

                                }

                            }

                        }

                        megaContabilDE.setDataLancamento(baixa.getTitulo().getDataPagamento());

                        if (historico.getPk().getId().equals(deducao.getTipoDeducao().getHistorico().getPk().getId())) {

                            megaContabilDE.setCodigoHistoricoPadrao(historico.getCodigo());
                            megaContabilDE.setHistorico(PempecUtil.concatenaComEspaco(historico.getDescricao()));

                            if (historico.getContaContabil() != null) {

                                megaContabilDE.setContaDebito(historico.getContaContabil().getContaReduzida());
                                megaContabilDE.setDigitoContaDebito(historico.getContaContabil().getDigitoContaReduzida());

                            } else {

                                megaContabilDE.setContaDebito("");
                                megaContabilDE.setDigitoContaDebito("");

                            }

                            break;

                        }

                    } catch (Exception e) {

                        megaContabilDE.setContaDebito("");
                        megaContabilDE.setDigitoContaDebito("");

                    }

                }

                megaContabilDE.setValor(deducao.getValor());

                if (megaContabilDE.getValor() > 0) {
                    dadosExportados.add(megaContabilDE.getTipoLancamento());
                }

            }

            for (TituloReceberRateioHistoricoModel rateio : baixa.getTitulo().getCollHistoricosRateio()) {

                for (TituloReceberRateioCCModel rateioCC : baixa.getTitulo().getCollCentroCustosRateio()) {

                    megaContabilC = new ExportMegaContabilModel();

                    if (baixa.getTitulo().isProvisao()) {

                        try {

                            megaContabilC.setCentroCusto(baixa.getTitulo().getCentroCusto().getCodigo());

                        } catch (Exception e) {

                            for (CentroCustoModel centroCusto : collCentroCusto) {

                                if (baixa.getTitulo().getCentroCusto().getPk().getId().equals(centroCusto.getPk().getId())) {

                                    megaContabilC.setCentroCusto(centroCusto.getCodigo());

                                }

                            }

                        }

                        megaContabilC.setDataLancamento(baixa.getTitulo().getDataPagamento());

                        try {

                            megaContabilC.setCodigoHistoricoPadrao(baixa.getTitulo().getHistorico().getCodigo());
                            megaContabilC.setHistorico(PempecUtil.concatenaComEspaco(baixa.getTitulo().getHistorico().getDescricao() + " " + baixa.getTitulo().getDescricao()));

                        } catch (Exception e) {

                            for (HistoricoModel historico : collHistorico) {

                                if (baixa.getTitulo().getHistorico().getPk().getId().equals(historico.getPk().getId()) && baixa.getTitulo().getHistorico().getPk().getOrganizacao().getId().equals(historico.getPk().getOrganizacao().getId())) {

                                    megaContabilC.setCodigoHistoricoPadrao(historico.getCodigo());

                                    megaContabilC.setHistorico(PempecUtil.concatenaComEspaco(historico.getDescricao() + " " + baixa.getTitulo().getDescricao()));

                                }
                            }
                        }

                        try {

                            if (baixa.getTitulo().getContaContabilDebito() != null) {

                                megaContabilC.setContaCredito(baixa.getTitulo().getContaContabilDebito().getContaReduzida());
                                megaContabilC.setDigitoContaCredito(baixa.getTitulo().getContaContabilDebito().getDigitoContaReduzida());

                            } else {

                                megaContabilC.setContaCredito("");
                                megaContabilC.setDigitoContaCredito("");

                            }

                        } catch (Exception e) {

                            megaContabilC.setContaCredito("");
                            megaContabilC.setDigitoContaCredito("");

                        }

                    } else {

                        ContaContabilModel contaContabil = this.getContaContabilByHistorico(rateio.getHistoricoModel());

                        try {

                            megaContabilC.setCentroCusto(baixa.getTitulo().getCentroCusto().getCodigo());

                        } catch (Exception e) {

                            for (CentroCustoModel centroCusto : collCentroCusto) {

                                if (baixa.getTitulo().getCentroCusto().getPk().getId().equals(centroCusto.getPk().getId())) {

                                    megaContabilC.setCentroCusto(centroCusto.getCodigo());

                                }

                            }

                        }

                        megaContabilC.setDataLancamento(baixa.getTitulo().getDataPagamento());

                        try {

                            megaContabilC.setCodigoHistoricoPadrao(baixa.getTitulo().getHistorico().getCodigo());
                            megaContabilC.setHistorico(PempecUtil.concatenaComEspaco(baixa.getTitulo().getHistorico().getDescricao() + " " + baixa.getTitulo().getDescricao()));

                        } catch (Exception e) {

                            for (HistoricoModel historico : collHistorico) {

                                if (baixa.getTitulo().getHistorico().getPk().getId().equals(historico.getPk().getId()) && baixa.getTitulo().getHistorico().getPk().getOrganizacao().getId().equals(historico.getPk().getOrganizacao().getId())) {

                                    megaContabilC.setCodigoHistoricoPadrao(historico.getCodigo());

                                    megaContabilC.setHistorico(PempecUtil.concatenaComEspaco(historico.getDescricao() + " " + baixa.getTitulo().getDescricao()));

                                }
                            }
                        }

                        try {

                            if (contaContabil != null) {

                                megaContabilC.setContaCredito(contaContabil.getContaReduzida());
                                megaContabilC.setDigitoContaCredito(contaContabil.getDigitoContaReduzida());

                            } else {

                                megaContabilC.setContaCredito("");
                                megaContabilC.setDigitoContaCredito("");

                            }

                        } catch (Exception e) {

                            megaContabilC.setContaCredito("");
                            megaContabilC.setDigitoContaCredito("");

                        }

                    }

                    megaContabilC.setValor((rateioCC.getValor() / baixa.getTitulo().getValorNominal()) * rateio.getValor());
                    if (megaContabilC.getValor() > 0) {
                        dadosExportados.add(megaContabilC.getTipoLancamento());
                    }

                }

            }

        }

    }

    private void exportarArquivo() throws SystemException, ApplicationException {

        PrintWriter arquivoTxt = null;

        try {

            JFileChooser jfc = new JFileChooser();

            jfc.setMultiSelectionEnabled(false);

            jfc.setAcceptAllFileFilterUsed(false);

            FileFilter flf = new FileFilter() {
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    }

                    return false;
                }

                public String getDescription() {
                    return "Exportação MegaContabil";
                }
            };

            jfc.addChoosableFileFilter(flf);

            if (jfc.showSaveDialog(null) == JFileChooser.CANCEL_OPTION) {
                return;
            }

            this.collCentroCusto = this.centroCustoBO.obterTodos(organizacaoModel);

            this.collHistorico = this.historicoBO.obterTodos(organizacaoModel);

            this.collContaBancaria = this.contaBancariaBO.obterTodos(organizacaoModel);

            FormaPagamentoModel formaPagamentoAux = new FormaPagamentoModel();

            formaPagamentoAux.setPk(new PKModel());

            formaPagamentoAux.getPk().setOrganizacao(organizacaoModel);

            formaPagamentoAux.getPk().setId(Constantes.FORMA_PAGAMENTO_ESPECIE);

            this.contaCaixa = formaPagamentoBO.consultarPorPk(formaPagamentoAux).getContaContabil();

            this.montaCabecalho();

            this.montaIdentificacao();

            this.exportarContaBancariaTransferencia();

            this.exportarContaBancariaDebito();

            this.exportarContaBancariaCredito();

            this.exportarTesourariaDebito();

            this.exportarTesourariaCredito();

            this.exportarContasPagar();

            this.exportarBaixasContasPagar();

            this.exportarContasReceber();

            this.exportarBaixasContasReceber();

            int qtd = 0;

            qtd += (collTitulosPagar != null) ? collTitulosPagar.size() : 0;
            qtd += (collBaixasTitulosPagar != null) ? collBaixasTitulosPagar.size() : 0;
            qtd += (collTitulosReceber != null) ? collTitulosReceber.size() : 0;
            qtd += (collBaixasTitulosReceber != null) ? collBaixasTitulosReceber.size() : 0;
            qtd += (collTesourariaDebito != null) ? collTesourariaDebito.size() : 0;
            qtd += (collTesourariaCredito != null) ? collTesourariaCredito.size() : 0;
            qtd += (collContaBancariaDebito != null) ? collContaBancariaDebito.size() : 0;
            qtd += (collContaBancariaCredito != null) ? collContaBancariaCredito.size() : 0;
            qtd += (collContaBancariaTransferencia != null) ? collContaBancariaTransferencia.size() : 0;

            
             TelaProgresso progresso = new TelaProgresso(qtd + 2000);
             TelaPrincipal.desktopPane.add(progresso);
             progresso.show();
             progresso.moveToFront();
             
            if (qtd == 0) {
                throw new ApplicationException("Não existem dados para serem exportados para o periodo selecionado.");
            }

            if (loteContabil.getExportarDefinitivo()) {
                megaContabilBO.gerarLote(collTitulosPagar, collBaixasTitulosPagar, collTitulosReceber, collBaixasTitulosReceber, collTesourariaDebito,
                        collTesourariaCredito, collContaBancariaDebito, collContaBancariaCredito, collContaBancariaTransferencia, loteContabil);
            }

            File arquivo = jfc.getSelectedFile();

            arquivoTxt = new PrintWriter(new OutputStreamWriter(new FileOutputStream(arquivo.getPath() + ".txt"), Charset.forName("US-ASCII")));

            for (String linha : dadosExportados) {
                arquivoTxt.println(linha);
            }

            JOptionPane.showMessageDialog(null, "Arquivo Gerado com Sucesso!");

        } catch (Exception e) {

            e.printStackTrace();

            if (e instanceof ApplicationException) {
                throw new ApplicationException(e.getMessage());
            }

            throw new SystemException(e.getMessage());

        } finally {

            try {

                arquivoTxt.flush();
                arquivoTxt.close();

            } catch (Exception e) {
            }
        }
    }
}
