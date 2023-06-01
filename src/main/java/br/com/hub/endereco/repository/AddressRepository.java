package br.com.hub.endereco.repository;


import br.com.hub.endereco.model.Address;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {
}
