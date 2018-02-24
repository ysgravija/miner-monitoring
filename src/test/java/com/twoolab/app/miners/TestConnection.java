package com.twoolab.app.miners;

import com.twoolab.app.Connection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author yeesheng on 22/02/2018
 * @project antmonitorapp
 */
public class TestConnection implements Connection {

    @Override
    public String getHost() {
        return "10.10.1.11";
    }

    @Override
    public String sendAndReceive(String payload) throws IOException {
        try {
            String filename = "/sample_" + payload.substring(12, payload.lastIndexOf('"')) + ".txt";
            String contents = new String(Files.readAllBytes(Paths.get(getClass().getResource(filename).toURI())));
            return contents;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException(e.getMessage(), e);
        }
    }

}
