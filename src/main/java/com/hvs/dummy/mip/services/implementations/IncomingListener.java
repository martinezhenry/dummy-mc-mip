package com.hvs.dummy.mip.services.implementations;

import com.hvs.dummy.mip.services.contracts.IIncomingListener;
import com.hvs.dummy.mip.services.contracts.ITransactionalService;
import lombok.extern.slf4j.Slf4j;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
public class IncomingListener implements IIncomingListener {
    private Configuration conf;
    private ITransactionalService transactionalService;

    public IncomingListener() {
        this.transactionalService = new TransactionalService();
    }

    @Override
    public void setConfiguration(Configuration configuration) throws ConfigurationException {
        this.conf = configuration;

    }

    @Override
    public boolean process(ISOSource isoSource, ISOMsg isoMsg) {
        log.info("incoming msg");
        try {
            if (transactionalService.isSignOn(isoMsg)) {
                transactionalService.makeResponse(isoMsg, "00");
                isoSource.send(isoMsg);
                return false;
            }

            if (transactionalService.isSignOff(isoMsg)) {
                transactionalService.makeResponse(isoMsg, "00");
                isoSource.send(isoMsg);
                return false;
            }

            if (transactionalService.isEchoMessage(isoMsg)) {
                transactionalService.makeResponse(isoMsg, "00");
                isoSource.send(isoMsg);
                return false;
            }


        } catch (ISOException | IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
