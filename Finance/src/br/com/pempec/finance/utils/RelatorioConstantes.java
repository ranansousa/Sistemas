/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils;

/**
 *
 * @author Pempec
 */
public interface RelatorioConstantes {

    String RELATORIO_FLUXO_CAIXA = "relatorio_fluxo_caixa.jasper";
    String RELATORIO_CONTAS_PAGAR = "relatorio_contas_pagar.jasper";
    String RELATORIO_CONTAS_PAGAR_ESPELHO = "espelho_titulo_pagar.jasper";
    String RELATORIO_CONTAS_RECEBER_ESPELHO = "espelho_titulo_receber.jasper";
    String RELATORIO_CONTAS_PAGAR_ANALITICO = "relatorio_contas_pagar_analitico.jasper";
    String RELATORIO_CONTAS_RECEBER = "relatorio_contas_receber.jasper";
    String RELATORIO_CONTAS_RECEBER_ANALITICO = "relatorio_contas_receber_analitico.jasper";
    String RELATORIO_EXTRATO_BANCARIO = "relatorio_extrato_bancario.jasper";
    String RELATORIO_MOVIMENTO_DIARIO = "relatorio_movimento_diario.jasper";
    String RELATORIO_MOVIMENTO_DIARIO_ANALITICO = "relatorio_movimento_diario_analitico.jasper";
    String RELATORIO_DMS = "relatorio_dms.jasper";
    String RELATORIO_DMS_ANALITICO = "relatorio_dms_analitico.jasper";
    String RELATORIO_EXTRATO_TESOURARIA = "relatorio_extrato_tesouraria.jasper";
    String RELATORIO_PAGAMENTO_LOTE = "relatorio_pagamento_lote.jasper";
    String RELATORIO_SALDO_BANCARIO = "relatorio_saldo_bancario.jasper";
    String RECIBO_DEPOSITOS_CHEQUES = "recibo_depositos_cheques.jasper";
    String RECIBO_TESOURARIA_DEBITO = "recibo_simplificado_tesouraria_debito.jasper";
    String RECIBO_TESOURARIA_CREDITO = "recibo_simplificado_tesouraria_credito.jasper";
    String RECIBO_TRANSFERENCIA_BANCARIA = "recibo_transferencia_bancaria.jasper";
    String RECIBO_COPIA_CHEQUE = "copia_cheque.jasper";
    String RECIBOS_MULTIPLOS = "recibos_multiplos.jasper";
    String RECIBO_CONTAS_PAGAR = "recibo_contas_pagar.jasper";
    String RECIBO_CONTAS_PAGAR_LOGO = "recibo_contas_pagar_logo.jasper";
    String RECIBO_CONTAS_RECEBER = "recibo_contas_receber.jasper";
    String RECIBO_CONTAS_RECEBER_LOGO = "recibo_contas_receber_logo.jasper";
    String RELATORIO_EXPORTACAO_MEGACONTABIL = "relatorio_exportacao_mega_contabil_analitico.jasper";
    String RELATORIO_EXPORTACAO_MEGACONTABIL_LOTE = "relatorio_exportacao_mega_contabil_lote_analitico.jasper";
    String PARAMETRO_TIPO_LANCAMENTO = "TIPO_LANCAMENTO";
    String PARAMETRO_ID_ORGANIZACAO = "ID_ORGANIZACAO";
    String PARAMETRO_DATA_INICIAL = "DATA_INICIAL";
    String PARAMETRO_DATA_FINAL = "DATA_FINAL";
    String PARAMETRO_DATA_INICIAL_BARRAS = "DATA_INICIAL_BARRA";
    String PARAMETRO_DATA_FINAL_BARRAS = "DATA_FINAL_BARRA";
    String PARAMETRO_ID_HISTORICO = "ID_HISTORICO";
    String PARAMETRO_ID_LOCAL_PAGAMENTO = "ID_LOCAL_PAGAMENTO";
    String PARAMETRO_ID_TIPO_COBRANCA = "ID_TIPO_COBRANCA";
    String PARAMETRO_ID_CENTRO_CUSTO = "ID_CENTRO_CUSTO";
    String PARAMETRO_ID_RESPONSAVEL = "ID_RESPONSAVEL";
    String PARAMETRO_ID_CEDENTE = "ID_CEDENTE";
    String PARAMETRO_ID_SACADO = "ID_SACADO";
    String PARAMETRO_ID_TIPO_STATUS = "ID_TIPO_STATUS";
    String PARAMETRO_ID_CONTA_BANCARIA = "ID_CONTA_BANCARIA";
    String PARAMETRO_ID_TIPO_OPERACAO_BANCARIA = "ID_TIPO_OPERACAO_BANCARIA";
    String PARAMETRO_ID_USUARIO = "ID_USUARIO";
    String PARAMETRO_ID_TITULO_PAGAR_BAIXA = "ID_TITULO_PAGAR_BAIXA";
    String PARAMETRO_ID_TITULO_RECEBER_BAIXA = "ID_TITULO_RECEBER_BAIXA";
    //Parametros adaptados para RECIBOS.
    String PARAMETRO_USER_LOGADO = "USER_LOGADO";
    String PARAMETRO_NUMERO_DOCUMENTO = "NUMERO_DOCUMENTO";
    String PARAMETRO_VALOR = "VALOR";
    String PARAMETRO_VALOR_PAGAR = "VALOR_PAGAR";
    String PARAMETRO_VALOR_EXTENSO = "VALOR_EXTENSO";
    String PARAMETRO_RAZAO_SOCIAL = "RAZAO_SOCIAL";
    String PARAMETRO_ENDERECO = "ENDERECO";
    String PARAMETRO_FAVORECIDO = "FAVORECIDO";
    String PARAMETRO_ENDERECO_FAVORECIDO = "ENDERECO_FAVORECIDO";
    String PARAMETRO_ENDERECO_FAVORECIDO_CIDADE_ESTADO = "CIDADE_ESTADO_FAVORECIDO";
    String PARAMETRO_CNPJ_CPF_FAVORECIDO = "CNPJ_CPF_FAVORECIDO";
    String PARAMETRO_CONTATO_FAVORECIDO = "CONTATO_FAVORECIDO";
    String PARAMETRO_DESCRICAO = "DESCRICAO";
    String PARAMETRO_CNPJ = "CNPJ";
    String PARAMETRO_CIDADE = "CIDADE";
    String PARAMETRO_PORTADOR = "PORTADOR";
    String PARAMETRO_NUMERO_CHEQUE = "NUMERO_CHEQUE";
    String PARAMETRO_NUMERO_CONTA = "NUMERO_CONTA";
    String PARAMETRO_VALOR_TITULO = "VALOR_TITULO";
    String PARAMETRO_STATUS = "STATUS";
    String PARAMETRO_DATA_EMISSAO = "DATA_EMISSAO";
    String PARAMETRO_COLLECTION_CHQ_AVULSO = "COLLECTION_CHQ_AVULSO";
    String PARAMETRO_COLLECTION = "COLLECTION";
    String PARAMETRO_COLLECTION_CROSSTAB = "COLLCROSSTAB";
    String PARAMETRO_COLLECTION_FORMAS = "COLLFORMAS";
    String PARAMETRO_COLLECTION_RATEIO = "COLLRATEIO";
    String PARAMETRO_COLLECTION_HISTORICO_RATEIO = "COLLHISTRATEIO";
    String PARAMETRO_COLLECTION_PAGAR = "COLLPAGAR";
    String PARAMETRO_COLLECTION_RECEBER = "COLLRECEBER";
    String PARAMETRO_DETALHES = "DETALHES";
    String PARAMETRO_TOTAL_ACRESCIMOS = "TOTAL_ACRESCIMO";
    String PARAMETRO_TOTAL_DEDUCOES = "TOTAL_DEDUCAO";
    String PARAMETRO_DATA_PAGAMENTO = "DATA_PAGAMENTO";
    String PARAMETRO_CONTA_ORIGEM = "CONTA_ORIGEM";
    String PARAMETRO_CONTA_DESTINO = "CONTA_DESTINO";
    String PARAMETRO_TITULAR_ORIGEM = "TITULAR_ORIGEM";
    String PARAMETRO_TITULAR_DESTINO = "TITULAR_DESTINO";
    String PARAMETRO_TIPO_OPERACAO = "TIPO_OPERACAO";
    String PARAMETRO_DATA = "DATA";
    String FICHA_CADASTRAL_CEDENTE = "ficha_cadastral_cedente.jasper";
    String FICHA_CADASTRAL_SACADO = "ficha_cadastral_sacado.jasper";
    String IMPRESSAO_CHEQUE_BRADESCO = "chequeBadesco.jasper";
    String IMPRESSAO_CHEQUE_BRASIL = "chequeBrasil.jasper";
    String IMPRESSAO_CHEQUE_CEF = "chequeCaixaFederal.jasper";
    String IMPRESSAO_CHEQUE = "cheque.jasper";
    String RELATORIO_SACADOS = "listagem_sacado.jasper";
    String RELATORIO_CEDENTES = "listagem_cedente.jasper";
}
