package br.com.sz.correios.controller;

import br.com.sz.correios.exception.NoContentException;
import br.com.sz.correios.model.Address;
import br.com.sz.correios.service.CorreiosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class CorreiosController {

    @Autowired
    private CorreiosService service;

    @GetMapping("/status")
    public String getStatus(){
        return "Service Status: " + this.service.getStatus();
    }


    @GetMapping("/zipcode/{zipcode}")
    public Address getAddressByZipcode(@PathVariable("zipcode") String zipcode) throws NoContentException {
        return this.service.getAddressByZipcode(zipcode);
    }
}
