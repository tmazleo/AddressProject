package br.com.hub.endereco.service;


import br.com.hub.endereco.EnderecoApplication;
import br.com.hub.endereco.exception.NoContentException;
import br.com.hub.endereco.exception.NotReadyException;
import br.com.hub.endereco.model.Address;
import br.com.hub.endereco.model.AddressStatus;
import br.com.hub.endereco.model.Status;
import br.com.hub.endereco.repository.AddressRepository;
import br.com.hub.endereco.repository.AddressStatusRepository;
import br.com.hub.endereco.repository.SetupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;


@org.springframework.stereotype.Service
public class CorreiosService {
    private static Logger logger = LoggerFactory.getLogger(CorreiosService.class);

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressStatusRepository addressStatusRepository;

    @Autowired
    private SetupRepository setupRepository;

    public Status getStatus() {
        return this.addressStatusRepository.findById(AddressStatus.DEFAULT_ID)
                .orElse(AddressStatus.builder().status(Status.NEED_SETUP).build())
                .getStatus(); //ele ira buscar no banco e se não tiver ele instanciara a classe e retornara um status
    }

    public Address getAddresByZipCode(String zipcode) throws NoContentException, NotReadyException {

        if (!this.getStatus().equals(Status.READY))
            throw new NotReadyException();

        return addressRepository.findById(zipcode)
                .orElseThrow(NoContentException::new);
    }

    private void saveStatus(Status status) {
        this.addressStatusRepository.save(AddressStatus.builder()
                .id(AddressStatus.DEFAULT_ID)
                .status(status)
                .build());
    }

    @EventListener(ApplicationStartedEvent.class)
    protected void setupOnStartUp() {
        try {
            this.setUp();
        } catch(Exception e) {
            EnderecoApplication.close(999);
            logger.error(".setupOnStartUp() - Exception", e);
        }
    }
    public void setUp() throws Exception{

        logger.info("------");
        logger.info("------");
        logger.info("------ SETUP RUNNING ");
        logger.info("------");
        logger.info("------");

        if (this.getStatus().equals(Status.NEED_SETUP)) {
            this.saveStatus(Status.SETUP_RUNNING);
            try {
                this.addressRepository.saveAll(this.setupRepository
                        .getFromOrigin());
            } catch (Exception e) {
                this.saveStatus(Status.NEED_SETUP);
                throw e;
            }
            this.saveStatus(Status.READY);

        }
        logger.info("------");
        logger.info("------");
        logger.info("------ SERVICE READY ");
        logger.info("------");
        logger.info("------");
    }
}