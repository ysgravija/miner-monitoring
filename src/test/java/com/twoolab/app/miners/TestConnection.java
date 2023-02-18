package com.twoolab.app.miners;

import com.twoolab.app.Connection;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * @author yeesheng on 22/02/2018
 * @project antmonitorapp
 */
public class TestConnection implements Connection {

    public String FILE_PREFIX = "/sample_";

    @Override
    public String getHost() {
        return "10.10.1.11";
    }

    @Override
    public String sendAndReceive(String payload) throws IOException {
        try {
            String filename = FILE_PREFIX + payload.substring(12, payload.lastIndexOf('"')) + ".txt";
            URL url = getClass().getResource(filename);
            if (url == null) {
                throw new IOException("Invalid path");
            }
            String contents = new String(Files.readAllBytes(Paths.get(url.toURI())));
            return contents;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
