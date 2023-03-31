package br.com.sz.correios.repository;

import br.com.sz.correios.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, String> {}
