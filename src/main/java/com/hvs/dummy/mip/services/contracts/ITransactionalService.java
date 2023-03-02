package com.hvs.dummy.mip.services.contracts;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

public interface ITransactionalService {

    default boolean isSignOn(ISOMsg isoMsg) throws ISOException {
        return isNetworkMessage(isoMsg) && "001".equals(isoMsg.getString("70"));
    }

    default boolean isSignOff(ISOMsg isoMsg) throws ISOException {
        return isNetworkMessage(isoMsg) && "002".equals(isoMsg.getString("70"));
    }

    default boolean isEchoMessage(ISOMsg isoMsg) throws ISOException {
        return isNetworkMessage(isoMsg) && "270".equals(isoMsg.getString("70"));
    }

    default boolean isNetworkMessage(ISOMsg isoMsg) throws ISOException {
        return "0800".equals(isoMsg.getMTI());
    }

    default ISOMsg makeResponse(ISOMsg isoMsg, String rc) throws ISOException {
        isoMsg.setResponseMTI();
        isoMsg.set("39", rc);
        return isoMsg;
    }

}
