package br.com.hub.endereco.controller;

import br.com.hub.endereco.exception.NoContentException;
import br.com.hub.endereco.model.Address;
import br.com.hub.endereco.service.CorreiosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class CorreiosController {

    @Autowired
    private CorreiosService service;

    @GetMapping("/status")
    public String getStatus() {
        return "Service status: " + this.service.getStatus();
    }

    @GetMapping("/zipcode/{zipcode}")
    public Address getAdressByZipCode(@PathVariable("zipcode") String zipcode) throws NoContentException{

        return this.service.getAddresByZipCode(zipcode);

    }
}
