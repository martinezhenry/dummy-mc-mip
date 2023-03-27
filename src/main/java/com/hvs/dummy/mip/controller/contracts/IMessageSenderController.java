package com.hvs.dummy.mip.controller.contracts;

import com.hvs.dummy.mip.exceptions.DataEmptyException;
import com.hvs.dummy.mip.exceptions.NotFoundConnectionsException;
import org.jpos.iso.ISOException;
import org.jpos.util.NameRegistrar;

import java.io.IOException;
import java.util.Map;

public interface IMessageSenderController {
    Map<Integer, String> request(String type, Map<String, String> request) throws ISOException, NameRegistrar.NotFoundException, IOException, NotFoundConnectionsException, DataEmptyException;
}
