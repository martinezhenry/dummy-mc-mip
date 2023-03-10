package com.hvs.dummy.mip.services.contracts;

import com.hvs.dummy.mip.exceptions.NotFoundConnectionsException;
import org.jpos.iso.ISOException;
import org.jpos.util.NameRegistrar;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public interface IMessageSenderService {


    Map<Integer, String> request(Map<String, String> messageData) throws ISOException, IOException, NameRegistrar.NotFoundException, NotFoundConnectionsException;


}
