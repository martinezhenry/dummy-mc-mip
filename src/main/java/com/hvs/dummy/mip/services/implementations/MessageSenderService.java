package com.hvs.dummy.mip.services.implementations;

import com.hvs.dummy.mip.MIPPackager;
import com.hvs.dummy.mip.exceptions.DataEmptyException;
import com.hvs.dummy.mip.exceptions.NotFoundConnectionsException;
import com.hvs.dummy.mip.services.contracts.IMessageSenderService;
import lombok.extern.slf4j.Slf4j;
import org.jpos.iso.*;
import org.jpos.space.Space;
import org.jpos.space.SpaceFactory;
import org.jpos.transaction.Context;
import org.jpos.util.NameRegistrar;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@Service
@Slf4j
public class MessageSenderService implements IMessageSenderService {

    private ISOMsg isoMsg;
    private ISOPackager packager;
    private ISOServer isoServer;
    private final Space space;
    private final String pathSpec;

    public MessageSenderService() throws ISOException {
        this.pathSpec = "packager/iso87Mastercard.xml";
        this.packager = new MIPPackager(this.pathSpec);
        this.isoMsg = new ISOMsg();
        this.isoMsg.setPackager(packager);
        this.space = SpaceFactory.getSpace();
    }

    @Override
    public Map<Integer, String> request(String type, Map<String, String> messageData)
            throws ISOException,
            IOException,
            NameRegistrar.NotFoundException,
            NotFoundConnectionsException,
            DataEmptyException {

        if (this.isoServer == null) {
            isoServerInstance();
        }
        if ("json".equals(type)) {
            messageData.forEach((key, value) -> isoMsg.set(key, value));
        } else {
            String data = messageData.get("raw");
            if (!data.isEmpty()) {
                isoMsg.unpack(ISOUtil.hex2byte(data));
            } else {
                throw new DataEmptyException();
            }
        }

        ISOChannel channel = isoServer.getLastConnectedISOChannel();
        if (isoServer.getActiveConnections() > 0 && channel != null) {
            channel.send(isoMsg);
            String key = isoMsg.getString("7").concat(isoMsg.getString("11"));
            Context cxt = (Context) this.space.in(key, 5000L);

            ISOMsg rsp = cxt.get("msg");
            Map<Integer, String> fields = new TreeMap<>();
            for (int i = 0; i <= rsp.getMaxField(); i++) {
                if (rsp.hasField(i)) {
                    fields.put(i, rsp.getString(i));
                }
            }
            return fields;
        } else {
            log.error("There are not connections");
            throw new NotFoundConnectionsException("There are not connections");
        }


    }


    private void isoServerInstance() throws NameRegistrar.NotFoundException {
        this.isoServer = NameRegistrar.get("server.mip-server");
    }

}
