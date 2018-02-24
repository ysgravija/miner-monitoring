package com.twoolab.app.miners;

import com.twoolab.app.Connection;
import org.json.JSONObject;

import java.io.IOException;
/**
 * @author yeesheng on 22/02/2018
 * @project antmonitorapp
 */
public class AntMiner {
    public static final String CMD_SUMMARY = "summary";
    public static final String CMD_STATS = "stats";
    public static final String CMD_POOL = "pools";
    public static final String CMD_VERSION = "version";
    public static final String CMD_DEVS = "devs";

    private Connection connection = null;

    public AntMiner(Connection conn) {
        connection = conn;
    }

    public String getHostIp() {
        return connection.getHost();
    }

    public JSONObject getSummary() throws IOException {
        return sendAndReceiveCommand(CMD_SUMMARY);
    }

    public JSONObject getStats() throws IOException {
        return sendAndReceiveCommand(CMD_STATS);
    }

    public JSONObject getPools() throws IOException {
        return sendAndReceiveCommand(CMD_POOL);
    }

    public JSONObject getVersion() throws IOException {
        return sendAndReceiveCommand(CMD_VERSION);
    }

    public JSONObject getDevs() throws IOException {
        return sendAndReceiveCommand(CMD_DEVS);
    }

    private JSONObject sendAndReceiveCommand(String command) throws IOException {
        String retResponse = connection.sendAndReceive(AntMinerUtils.buildCommand(command));
        if (CMD_STATS.equals(command)) {
            retResponse = retResponse.replace("}{", "},{");
        }
        return new JSONObject(retResponse);
    }
}
