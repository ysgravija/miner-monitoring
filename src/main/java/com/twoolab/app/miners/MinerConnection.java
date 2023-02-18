package com.twoolab.app.miners;

import com.twoolab.app.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
/**
 * @author yeesheng on 21/02/2018
 * @project antmonitorapp
 */
public class MinerConnection implements Connection {

    private static final Logger logger = LoggerFactory.getLogger(MinerConnection.class);
    private String host = null;
    private boolean debugMode = false;

    public MinerConnection(String ip) {
        host = ip;
        debugMode = false;
    }

    public MinerConnection(String ip, boolean debug) throws IOException {
        host = ip;
        debugMode = debug;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String sendAndReceive(String payload) throws IOException {
        Socket socket = null;
        DataInputStream dis = null;
        DataOutputStream dos = null;

        try {
            if (debugMode) {
                socket = new Socket();
                socket.connect(new InetSocketAddress(host, 4028), 1000);
            } else {
                socket = new Socket(InetAddress.getByName(host), 4028);
            }

            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());

            PrintWriter pw = new PrintWriter(dos, true);
            pw.println(payload);
            pw.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(dis));

            long t = System.currentTimeMillis();
            long end = t + 1000;

            StringBuilder sb = new StringBuilder();
            while (System.currentTimeMillis() < end) {
                String response;
                while ((response = in.readLine()) != null) {
                    sb.append(response);
                }
                return sb.toString();
            }
        } finally {
            if (dis != null) {
                dis.close();
            }

            if (dos != null) {
                dos.close();
            }
            if (socket != null) {
                socket.close();
            }
        }

        return null;
    }
}
