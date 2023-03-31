package br.com.sz.correios.model;

public enum Status {
    NEED_SETUP, // PRECISA BAIXAR O CSV DOS CORREIOS
    SETUP_RUNNING, // ESTÁ BAIXANDO O CSV DOS CORREIOS
    READY;  // SERVIÇO ESTÁ PRONTO
}
