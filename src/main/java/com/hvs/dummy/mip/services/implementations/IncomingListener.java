package com.hvs.dummy.mip.services.implementations;

import com.hvs.dummy.mip.services.contracts.IIncomingListener;
import com.hvs.dummy.mip.services.contracts.ITransactionalService;
import lombok.extern.slf4j.Slf4j;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.jpos.space.Space;
import org.jpos.space.SpaceFactory;
import org.jpos.transaction.Context;

import java.io.IOException;

@Slf4j
public class IncomingListener implements IIncomingListener {
    private Configuration conf;
    private final ITransactionalService transactionalService;
    private final Space<String, Context> space;

    public IncomingListener() {
        this.transactionalService = new TransactionalService();
        this.space = SpaceFactory.getSpace();
    }

    @Override
    public void setConfiguration(Configuration configuration) throws ConfigurationException {
        this.conf = configuration;

    }

    @Override
    public boolean process(ISOSource isoSource, ISOMsg isoMsg) {
        log.info("incoming msg");
        try {
            if (isoMsg.isRequest()) {
                transactionalService.makeResponse(isoMsg, "00");
                isoSource.send(isoMsg);
                return false;

            }

            String key = isoMsg.getString("7").concat(isoMsg.getString("11"));
            Context cxt = new Context();
            cxt.put("msg", isoMsg);
            this.space.out(key, cxt, 5000L);


        } catch (ISOException | IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
