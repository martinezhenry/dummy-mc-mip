package com.hvs.dummy.mip;

import org.jpos.iso.ISOComponent;
import org.jpos.iso.ISOException;
import org.jpos.iso.packager.GenericPackager;

import java.io.InputStream;

public class MIPPackager extends GenericPackager {


    public MIPPackager() throws ISOException {
    }

    public MIPPackager(String filename) throws ISOException {
        super(filename);
    }

    public MIPPackager(InputStream input) throws ISOException {
        super(input);
    }

    public int unpack(ISOComponent m, byte[] b) throws ISOException {
        return super.unpack(m, b);
    }
}
