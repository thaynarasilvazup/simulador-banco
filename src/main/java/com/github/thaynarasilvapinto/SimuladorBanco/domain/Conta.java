package com.github.thaynarasilvapinto.SimuladorBanco.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Conta implements Serializable {
    private Integer id;
    private double saldo;
    private String dataHora;
    private ArrayList<Operacao> extrato = new ArrayList<Operacao>();
    private int contID = 0;
    public Conta() {
    }
    public Conta(String dataHora){
        saldo = 0.00;
        this.dataHora = dataHora;
    }
    public int saque(Operacao operacao){
        if(operacao.getValorOperacao() <= this.saldo) {
            saldo -= operacao.getValorOperacao();
            operacao.setIdOperacao(contID);
            contID++;
            this.extrato.add(operacao);
            return 1;
        }
        return 0;
    }
    public int deposito(Operacao operacao){
        if(operacao.getValorOperacao() > 0){
            saldo +=operacao.getValorOperacao();
            operacao.setIdOperacao(contID);
            contID++;
            this.extrato.add(operacao);
            return 1;
        }
        return 0;
    }
    public int Transferencia(Conta clienteDestino, Operacao operacao){
        if(operacao.getValorOperacao() <= this.saldo){
            efetuarTrasferencia(operacao);
            clienteDestino.recebimentoTransferencia(operacao);
            return 1;
        }
        return 0;
    }
    public void recebimentoTransferencia(Operacao operacao){
        if(operacao.getValorOperacao() > 0){
            saldo += operacao.getValorOperacao();
            Operacao op = new Operacao(operacao.getIdOrigem(),operacao.getIdDestino(),operacao.getValorOperacao(),TipoOperacao.RECEBIMENTO_TRANSFERENCIA);
            op.setIdOperacao(contID);
            contID++;
            this.extrato.add(op);
        }
    }
    public void efetuarTrasferencia(Operacao operacao){
        if(operacao.getValorOperacao() <= this.saldo) {
            saldo -= operacao.getValorOperacao();
            Operacao op = new Operacao(operacao.getIdOrigem(),operacao.getIdDestino(),operacao.getValorOperacao(),TipoOperacao.TRANSFERENCIA);
            op.setIdOperacao(contID);
            contID++;
            this.extrato.add(op);
        }
    }
    public double getSaldo() {
        return saldo;
    }

    public Integer getId() {
        return id;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public int getContID() {
        return contID;
    }

    public void setContID(int contID) {
        this.contID = contID;
    }

    public ArrayList<Operacao> getExtrato() {
        return extrato;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setExtrato(ArrayList<Operacao> extrato) {
        if (extrato == null) {
            extrato = new ArrayList<>();
        }
        this.extrato = extrato;
    }
}