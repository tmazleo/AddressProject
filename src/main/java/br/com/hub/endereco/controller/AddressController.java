package br.com.hub.endereco.controller;

import br.com.hub.endereco.model.Address;
import br.com.hub.endereco.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private Service service;

    @GetMapping("/status")
    public String getStatus() {
        return "Service status: " + service.getStatus();
    }

    @GetMapping("zipcode/{zipCode}")
    public Address getAdressByZipCode(@PathVariable("zipCode") String zipCode) {
        return this.service.getAddresByZipCode(zipCode);

    }
}
