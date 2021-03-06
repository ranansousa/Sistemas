package br.com.pempec.finance.utils;

public interface Constantes {

    final String CPF_PADRAO = "111.111.111-11";
    final String CNPJ_PADRAO = "11.111.111/1111-11";
    final String SENHA_PADRAO = "123456";
    final String SENHA_PADRAO_CRIPTOGRAFADA = "E10ADC3949BA59ABBE56E057F20F883E";
    final String MSG_ACESSO_NEGADO = "Acesso Negado!";
    final String STATUS_TITULO_PAGO = "QUITADO";
    final String STATUS_TITULO_NOVO = "ABERTO";
    final String STATUS_TITULO_PARCIAL = "PARCIAL";
    final String STATUS_TITULO_EXCLUIDO = "EXCLUIDO";
    final String STATUS_TITULO_FUNDO_PERDIDO = "FUNDO PERDIDO";
    //STATUS CHEQUES
    final String STATUS_CHEQUE_NOVO = "BLOQUEADO";
    final String STATUS_CHEQUE_EXCLUIDO = "DESATIVADO";
    final String STATUS_CHEQUE_COMPENSADO = "COMPENSADO";
    final String STATUS_CHEQUE_EMITIDO = "EMITIDO";
    final String STATUS_CHEQUE_AVULSO = "AVULSO";
    //Tipo Cedente Obrigatorio
    final String TIPO_CEDENTE_PS = "PRESTADOR DE SERVICOS";
    final String TIPO_CEDENTE_FORNECEDOR = "FORNECEDOR";
    //FORMAS DE PAGAMENTO
    final String FORMA_PAGAMENTO_INTERNET = "INTERNET BANK";
    final String FORMA_PAGAMENTO_CHEQUE = "CHEQUE";
    final String FORMA_PAGAMENTO_ESPECIE = "ESPECIE";
    //TIPOS DE TIPO OPERACAO BANCARIA
    final String TIPO_OPERACAO_BANCARIA_TIPO_ESPECIE = "Especie";
    final String TIPO_OPERACAO_BANCARIA_TIPO_CHEQUE = "Cheque";
    final String TIPO_OPERACAO_BANCARIA_TIPO_INTERNET = "Internet";
    //TIPO OPERACAO BANCARIA
    final String TIPO_OPERACAO_BANCARIA_TRANSFERENCIA_CONTAS = "TRANSFERENCIA ENTRE CONTAS";
    final String TIPO_OPERACAO_BANCARIA_DEPOSITO_ESPECIE = "DEPOSITO EM ESPECIE";
    final String TIPO_OPERACAO_BANCARIA_DEPOSITO_CHEQUE = "DEPOSITO EM CHEQUE";
    final String TIPO_OPERACAO_BANCARIA_TED = "TED";
    final String TIPO_OPERACAO_BANCARIA_DOC = "DOC";
    final String TIPO_OPERACAO_BANCARIA_QUITACAO_NET = "PAGTO VIA BANK-LINE";
    final String TIPO_OPERACAO_BANCARIA_TESOURARIA_DEPOSITO = "DEPOSITO TESOURARIA/BANCO";
    final String TIPO_OPERACAO_BANCARIA_TRANSFERENCIA_TESOURARIA = "TRANSFERE BANCO/TESOURARIA";
//TIPO COBRANCA
    final String TIPO_COBRANCA_BOLETO = "BOLETO";
    //Status Tipo Baixa Titulo
    final String TIPO_BAIXA_TITULO_PARCIAL = "PARCIAL";
    final String TIPO_BAIXA_TITULO_TOTAL = "TOTAL";
    final String HISTORICO_TESOURARIA_CHEQUE_RECEBIDO = "CHEQUE RECEBIDO POR TITULO";
    final String HISTORICO_TESOURARIA_ESPECIE_PAGO = "PAGTO TITULO ESPECIE";
    final String HISTORICO_TESOURARIA_ESPECIE_RECEBIDO = "RECBTO TITULO ESPECIE";
    final String HISTORICO_TESOURARIA_DEPOSITO = "DEPOSITO TESOURARIA/BANCO";
    final String HISTORICO_TESOURARIA_TRANSFERE_BCO_TESOURARIA = "TRANSFERE BANCO/TESOURARIA";
    final String DESCRICAO_BANCO_TESOURARIA_CR = "CR TRANSF CTA BANCARIA.";
    final String DESCRICAO_BANCO_TESOURARIA_DB = "DB TRANSF P/ TESOURARIA.";
    final String TIPO_NOTA_FISCAL_PRESTACAO_SERVICOS = "pservicos";
    final String TIPO_NOTA_FISCAL_SERIE_UNICA = "sunica";
    //Lote Pagamento
    final String LOTE_PAGAMENTO_PAGO = "LOTE-PAGO";
    final String LOTE_PAGAMENTO_REMOVIDO = "LOTE-REMOVIDO";
    final String ENVIAR_POR_EMAIL = "E-Mail";
    final String PARAMETRIZACAO_COLUNA_VALOR = "P(VALOR)";
    final String PARAMETRIZACAO_COLUNA_PORCENTAGEM = "P(PORCENTAGEM)";
    final String PARAMETRIZACAO_COLUNA_DATA = "P(DATA)";
    final String PARAMETRIZACAO_COLUNA_STATUS = "P(STATUS)";
    final String CONSTANTES_PERMISSOES_MODIFICADA = "PERMISSOES_MODIFICADA";
    final String CONSTANTES_PARAMETROS_MODIFICADO = "PARAMETROS_MODIFICADO";
    final String CONSTANTES_FERIADO_CALENDARIO_MODIFICADO = "FERIADO_CALENDARIO_MODIFICADO";
    final String CONSTANTES_FERIADO_INICIO_CALENDARIO = "FERIADO_INICIO_CALENDARIO";
    final String CONSTANTES_FERIADO_FIM_CALENDARIO = "FERIADO_FIM_CALENDARIO";
    
    final String CONSTANTES_ORDEM_TITULOS_1 = "Valor";
    final String CONSTANTES_ORDEM_TITULOS_2 = "Cedente";
    final String CONSTANTES_ORDEM_TITULOS_3 = "Responsável";
    final String CONSTANTES_ORDEM_TITULOS_4 = "Data Vcto";
    
    final String CONSTANTES_PATH_FILE_CONFIG = "C:\\finance\\config\\";
    final String CONSTANTES_NAME_FILE_CONFIG="config.properties";
    
    
    
}
