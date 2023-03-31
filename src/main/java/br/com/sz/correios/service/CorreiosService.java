package br.com.sz.correios.service;

import br.com.sz.correios.exception.NoContentException;
import br.com.sz.correios.model.Address;
import br.com.sz.correios.model.Status;
import br.com.sz.correios.repository.AddressRepository;
import br.com.sz.correios.repository.AddressStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CorreiosService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressStatusRepository addressStatusRepository;
    public Status getStatus(){
        return Status.READY;
    }

    public Address getAddressByZipcode(String zipcode) throws NoContentException {
        return addressRepository.findById(zipcode)
                .orElseThrow(NoContentException::new);
    }

    public void setup(){}

}
