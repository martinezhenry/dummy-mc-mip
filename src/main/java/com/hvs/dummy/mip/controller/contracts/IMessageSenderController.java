package com.hvs.dummy.mip.controller.contracts;

import com.hvs.dummy.mip.models.Request;
import org.jpos.iso.ISOException;
import org.jpos.util.NameRegistrar;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.Map;

public interface IMessageSenderController {
    void request(@RequestBody Map<String, String> request) throws ISOException, NameRegistrar.NotFoundException, IOException;
}
