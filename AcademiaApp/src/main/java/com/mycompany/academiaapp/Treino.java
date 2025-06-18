package com.mycompany.academiaapp;

import java.sql.Date;

public class Treino {
    private int id;
    private int alunoId;
    private String tipoTreino;
    private String descricao;
    private String duracao;
    private Date dataInicio;

    // Construtores
    public Treino() {}

    public Treino(int alunoId, String tipoTreino, String descricao, String duracao, Date dataInicio) {
        this.alunoId = alunoId;
        this.tipoTreino = tipoTreino;
        this.descricao = descricao;
        this.duracao = duracao;
        this.dataInicio = dataInicio;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAlunoId() { return alunoId; }
    public void setAlunoId(int alunoId) { this.alunoId = alunoId; }

    public String getTipoTreino() { return tipoTreino; }
    public void setTipoTreino(String tipoTreino) { this.tipoTreino = tipoTreino; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getDuracao() { return duracao; }
    public void setDuracao(String duracao) { this.duracao = duracao; }

    public Date getDataInicio() { return dataInicio; }
    public void setDataInicio(Date dataInicio) { this.dataInicio = dataInicio; }
}