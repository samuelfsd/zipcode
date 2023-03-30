package br.com.sz.correios.controller;

import br.com.sz.correios.model.Address;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class CorreiosController {

    @GetMapping("/status")
    public String getStatus(){
        return "UP!";
    }


    @GetMapping("/zipcode/{zipcode}")
    public Address getAddressByZipcode(@PathVariable("zipcode") String zipcode){
        return Address.builder().zipcode(zipcode).build();
    }
}
