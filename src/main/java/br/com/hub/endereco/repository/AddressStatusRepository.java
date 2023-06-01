package br.com.hub.endereco.repository;

import br.com.hub.endereco.model.AddressStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressStatusRepository extends JpaRepository<AddressStatus, Integer> {

}
