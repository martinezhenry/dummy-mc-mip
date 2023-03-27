package com.hvs.dummy.mip.controller.implementations;

import com.hvs.dummy.mip.controller.contracts.IMessageSenderController;
import com.hvs.dummy.mip.exceptions.DataEmptyException;
import com.hvs.dummy.mip.exceptions.NotFoundConnectionsException;
import com.hvs.dummy.mip.services.contracts.IMessageSenderService;
import org.jpos.iso.ISOException;
import org.jpos.util.NameRegistrar;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class MessageSenderController implements IMessageSenderController {

    private IMessageSenderService messageSenderService;

    public MessageSenderController(IMessageSenderService messageSenderService) {
        this.messageSenderService = messageSenderService;
    }

    @Override
    @PostMapping
    public Map<Integer, String> request(
            @RequestHeader(value = "type", defaultValue = "json", required = false) String type,
            @RequestBody Map<String, String> request)
            throws ISOException, NameRegistrar.NotFoundException,
            IOException, NotFoundConnectionsException,
            DataEmptyException {
        return this.messageSenderService.request(type, request);
    }
}
