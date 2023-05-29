package br.com.hub.endereco.model;

public enum Status {
    NEED_SETUP, //precisa baixar o csv dos correios
    SETUP_RUNNING, //esta baixando/salvando no banco
    READY; //serviço pronto para ser consumido
}
