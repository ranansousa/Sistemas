package br.com.pempec.finance.utils;

public enum Action {

    CADASTRAR(1l),
    ALTERAR(2l),
    EXCLUIR(3l),
    IMPRESSAO(4l),
    OUTROS(5l);
    private Long action;

    private Action(Long action) {
        this.action = action;
    }

    public Long getAction() {
        return this.action;
    }
}
