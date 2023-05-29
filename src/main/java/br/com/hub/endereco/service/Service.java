package br.com.hub.endereco.service;

import br.com.hub.endereco.model.Address;
import br.com.hub.endereco.model.Status;

@org.springframework.stereotype.Service
public class Service {

    public Status getStatus() {
        return Status.READY;
    }

    public Address getAddresByZipCode(String zipCode) {
        return null;
    }

    public void setUp(){}
}
