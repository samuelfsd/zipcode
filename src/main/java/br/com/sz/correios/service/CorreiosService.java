package br.com.sz.correios.service;

import br.com.sz.correios.CorreiosApplication;
import br.com.sz.correios.exception.NoContentException;
import br.com.sz.correios.exception.NotReadyException;
import br.com.sz.correios.model.Address;
import br.com.sz.correios.model.AddressStatus;
import br.com.sz.correios.model.Status;
import br.com.sz.correios.repository.AddressRepository;
import br.com.sz.correios.repository.AddressStatusRepository;
import br.com.sz.correios.repository.SetupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class CorreiosService {

    private static Logger log = LoggerFactory.getLogger(CorreiosService.class);
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressStatusRepository addressStatusRepository;

    @Autowired
    private SetupRepository setupRepository;
    public Status getStatus(){
        return this.addressStatusRepository.findById(AddressStatus.DEFAULT_ID)
                .orElse(AddressStatus.builder().status(Status.NEED_SETUP).build())
                .getStatus();
    }

    public Address getAddressByZipcode(String zipcode) throws NoContentException, NotReadyException {
        if(!this.getStatus().equals(Status.READY)) throw new NotReadyException();

        return addressRepository.findById(zipcode)
                .orElseThrow(NoContentException::new);
    }

    private void saveStatus(Status status){
        this.addressStatusRepository.save(AddressStatus.builder()
                        .id(AddressStatus.DEFAULT_ID)
                        .status(status)
                        .build());
    }

    @EventListener(ApplicationStartedEvent.class)
    protected void setupOnStartup(){
        try {
            this.setup();
        } catch (Exception ex) {
            CorreiosApplication.close(999);
            log.error("setupOnStartup - Ex", ex);
        }
    }

    public void setup() throws Exception {
        if(this.getStatus().equals(Status.NEED_SETUP)){
            this.saveStatus(Status.SETUP_RUNNING);

            try{
                this.addressRepository.saveAll(this.setupRepository.getFromOrigin());
            } catch (Exception ex ) {
                this.saveStatus(Status.NEED_SETUP);
                throw ex;
            }

            this.saveStatus(Status.READY);
        }
    }

}
