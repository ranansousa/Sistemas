package br.com.pempec.finance.utils;

public enum Parametro {

    TA001("TA001"),
    TA002("TA002"),
    TA003("TA003"),
    TA004("TA004"),
    TA005("TA005"),
    TA006("TA006"),
    TA007("TA007"),
    TA008("TA008"),
    TA009("TA009"),
    TA010("TA010"),
    G001("G001"),
    G002("G002"),
    G003("G003"),
    G004("G004"),
    G005("G005"),
    CCADR001("CCADR001"),
    CCADR002("CCADR002"),
    CCADR003("CCADR003"),
    CCADR004("CCADR004"),
    CCADR006("CCADR006"),
    CBR001("CBR001"),
    CBR002("CBR002"),
    CBR003("CBR003"),
    CBR004("CBR004"),
    CBR005("CBR005"),
    CBR00501("CBR00501"),
    CBR00502("CBR00502"),
    CBR00503("CBR00503"),
    CBR006("CBR006"),
    CBR007("CBR007"),
    CBRFPE001("CBRFPE001"),
    CBRFPE002("CBRFPE002"),
    CBRFPC001("CBRFPC001"),
    CBRFPC002("CBRFPC002"),
    CBRFPI001("CBRFPI001"),
    CBRFPI002("CBRFPI002"),
    CCANR001("CCANR001"),
    CCANR002("CCANR002"),
    CCANR003("CCANR003"),
    CCANR00301("CCANR00301"),
    CCANR00302("CCANR00302"),
    CCANR0030201("CCANR0030201"),
    CCANR00303("CCANR00303"),
    CCANR004("CCANR004"),
    CCANR005("CCANR005"),
    CRR001("CRR001"),
    CRR002("CRR002"),
    CRR00201("CRR00201"),
    CRR00202("CRR00202"),
    CRR00203("CRR00203"),
    CRR003("CRR003"),
    CRR004("CRR004"),
    CCADP001("CCADP001"),
    CCADP002("CCADP002"),
    CCADP003("CCADP003"),
    CCADP004("CCADP004"),
    CCADP005("CCADP005"),
    CCADP006("CCADP006"),
    CBPS001("CBPS001"),
    CBPS002("CBPS002"),
    CBPS003("CBPS003"),
    CBPS004("CBPS004"),
    CBPS005("CBPS005"),
    CBPS00501("CBPS00501"),
    CBPS00502("CBPS00502"),
    CBPS00503("CBPS00503"),
    CBPS006("CBPS006"),
    CBPS007("CBPS007"),
    CBPSFPE001("CBPSFPE001"),
    CBPSFPE002("CBPSFPE002"),
    CBPSFPE003("CBPSFPE003"),
    CBPSFPC001("CBPSFPC001"),
    CBPSFPC002("CBPSFPC002"),
    CBPSFPC003("CBPSFPC003"),
    CBPSFPI001("CBPSFPI001"),
    CBPSFPI002("CBPSFPI002"),
    CBPSFPI003("CBPSFPI003"),
    CBPL001("CBPL001"),
    CBPL002("CBPL002"),
    CBPL003("CBPL003"),
    CBPL004("CBPL004"),
    CBPL00401("CBPL00401"),
    CBPL00402("CBPL00402"),
    CBPL00403("CBPL00403"),
    CBPL005("CBPL005"),
    CBPL006("CBPL006"),
    CBPLFPE001("CBPLFPE001"),
    CBPLFPE002("CBPLFPE002"),
    CBPLFPE003("CBPLFPE003"),
    CBPLFPC001("CBPLFPC001"),
    CBPLFPC002("CBPLFPC002"),
    CBPLFPC003("CBPLFPC003"),
    CBPLFPI001("CBPLFPI001"),
    CBPLFPI002("CBPLFPI002"),
    CBPLFPI003("CBPLFPI003"),
    CCANP001("CCANP001"),
    CCANP002("CCANP002"),
    CCANP003("CCANP003"),
    CCANP004("CCANP004"),
    CCANP00401("CCANP00401"),
    CCANP00402("CCANP00402"),
    CCANP0040201("CCANP0040201"),
    CCANP00403("CCANP00403"),
    CCANP005("CCANP005"),
    CCANP006("CCANP006"),
    CCANP007("CCANP007"),
    CCANP00701("CCANP00701"),
    CCANP00702("CCANP00702"),
    CCANP0070201("CCANP0070201"),
    CCANP00703("CCANP00703"),
    CCANP008("CCANP008"),
    CCANP009("CCANP009"),
    CRP001("CRP001"),
    CRP002("CRP002"),
    CRP00201("CRP00201"),
    CRP00202("CRP00202"),
    CRP00203("CRP00203"),
    CRP003("CRP003"),
    CRP004("CRP004");
    private String codigo;

    private Parametro(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return this.codigo;
    }
}
