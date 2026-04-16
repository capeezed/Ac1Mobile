package com.facens.ac1;

public class Despesa {
    private int id;
    private String descricao;
    private String categoria;
    private int valor;
    private int data;
    private String pagamento;
    private boolean foipaga;

    public Despesa() {}

    public Despesa(String descricao,String categoria, int valor, int data, String pagamento, boolean foipaga){
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
        this.data = data;
        this.pagamento = pagamento;
        this.foipaga = foipaga;
    }

    public int getId(){
        return id;
    }

    public String getDescricao(){
        return descricao;
    }

    public String getCategoria(){
        return categoria;
    }

    public int getValor(){
        return valor;
    }

    public int getData(){
        return data;
    }

    public String getPagamento(){
        return pagamento;
    }

    public boolean isPaga(){
        return foipaga;
    }
}
