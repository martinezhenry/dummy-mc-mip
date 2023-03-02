package com.hvs.dummy.mip.services.implementations;

import com.hvs.dummy.mip.MIPPackager;
import com.hvs.dummy.mip.services.contracts.IMessageSenderService;
import lombok.extern.slf4j.Slf4j;
import org.jpos.iso.*;
import org.jpos.util.NameRegistrar;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@Slf4j
public class MessageSenderService implements IMessageSenderService {

    private ISOMsg isoMsg;
    private ISOPackager packager;
    private ISOServer isoServer;

    public MessageSenderService() throws ISOException {
        this.packager = new MIPPackager("packager/iso87Mastercard.xml");
        this.isoMsg = new ISOMsg();
        this.isoMsg.setPackager(packager);
    }

    @Override
    public void request(Map<String, String> messageData) throws ISOException, IOException, NameRegistrar.NotFoundException {

        if (this.isoServer == null) {
            isoServerInstance();
        }
        messageData.forEach((key, value) -> isoMsg.set(key, value));

        ISOChannel channel = isoServer.getLastConnectedISOChannel();
        if (isoServer.getActiveConnections() > 0 && channel != null) {
            channel.send(isoMsg);
        } else {
            log.info("There are not connections");
        }


    }


    private void isoServerInstance() throws NameRegistrar.NotFoundException {
        this.isoServer = NameRegistrar.get("server.mip-server");
    }

}
