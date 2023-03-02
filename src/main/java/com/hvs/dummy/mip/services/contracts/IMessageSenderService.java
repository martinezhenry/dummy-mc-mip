package com.hvs.dummy.mip.services.contracts;

import org.jpos.iso.ISOException;
import org.jpos.util.NameRegistrar;

import java.io.IOException;
import java.util.Map;

public interface IMessageSenderService {


    void request(Map<String, String> messageData) throws ISOException, IOException, NameRegistrar.NotFoundException;


}
