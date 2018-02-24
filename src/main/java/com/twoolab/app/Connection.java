package com.twoolab.app;

import java.io.IOException;

/**
 * @author yeesheng on 22/02/2018
 * @project antmonitorapp
 */
public interface Connection {

    String getHost();

    String sendAndReceive(String payload) throws IOException;
}
