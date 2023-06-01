package br.com.hub.endereco.service;


import br.com.hub.endereco.exception.NoContentException;
import br.com.hub.endereco.model.Address;
import br.com.hub.endereco.model.AddressStatus;
import br.com.hub.endereco.model.Status;
import br.com.hub.endereco.repository.AddressRepository;
import br.com.hub.endereco.repository.AddressStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class CorreiosService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressStatusRepository addressStatusRepository;

    public Status getStatus() {
        return this.addressStatusRepository.findById(AddressStatus.DEFAULT_ID)
                .orElse(AddressStatus.builder().status(Status.NEED_SETUP).build())
                .getStatus(); //ele ira buscar no banco e se não tiver ele instanciara a classe e retornara um status
    }

    public Address getAddresByZipCode(String zipcode) throws NoContentException {
        return addressRepository.findById(zipcode)
                .orElseThrow(NoContentException::new);
    }

    public void setUp(){}
}
