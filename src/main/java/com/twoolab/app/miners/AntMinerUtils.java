package com.twoolab.app.miners;

import com.twoolab.app.Connection;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author yeesheng on 22/02/2018
 * @project antmonitorapp
 */
public class AntMinerUtils {

    private static final Logger logger = Logger.getLogger(AntMinerUtils.class);
    private static final DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
    public static final String CMD = "command";

    public static void getDashboardDisplay(AntMiner antMiner) throws IOException {
        JSONObject versionObject = antMiner.getVersion().getJSONArray("VERSION").getJSONObject(0);
        JSONObject statsObject = antMiner.getStats().getJSONArray("STATS").getJSONObject(1);
        JSONObject summaryObject = antMiner.getSummary().getJSONArray("SUMMARY").getJSONObject(0);

        logger.info("==== VERSION ====");
        logger.info(String.format("Model: %s\n", (String) versionObject.get("Type")));

        logger.info("==== STATS ====");
        logger.info(String.format("HashRate: %s (%s)\n",
                String.valueOf(statsObject.get("total_rate")),
                String.valueOf(statsObject.get("total_rateideal"))));

        logger.info(String.format("PCB Temp: %s %s %s\n",
                String.valueOf(statsObject.get("temp6")),
                String.valueOf(statsObject.get("temp7")),
                String.valueOf(statsObject.get("temp8"))));

        logger.info(String.format("Chip Temp: %s %s %s\n",
                String.valueOf(statsObject.get("temp2_6")),
                String.valueOf(statsObject.get("temp2_7")),
                String.valueOf(statsObject.get("temp2_8"))));

        logger.info("==== SUMMARY ====");
        logger.info(String.format("Accepted: %s Rejected: %s Error: %s\n",
                String.valueOf(summaryObject.get("Accepted")),
                String.valueOf(summaryObject.get("Rejected")),
                String.valueOf(summaryObject.get("Hardware Errors"))));

        logger.info("==== END ====\n\n\n");
    }

    public static AntMinerInfo getMinerInfo(AntMiner antMiner) throws IOException {
        JSONObject versionObject = antMiner.getVersion().getJSONArray("VERSION").getJSONObject(0);
        JSONObject statsObject = antMiner.getStats().getJSONArray("STATS").getJSONObject(1);
        JSONObject summaryObject = antMiner.getSummary().getJSONArray("SUMMARY").getJSONObject(0);
        JSONObject poolObject = antMiner.getPools().getJSONArray("POOLS").getJSONObject(0);

        String model = (String) versionObject.get("Type");
        String hashRate = String.valueOf(statsObject.get("total_rate"));
        String idealHashRate = String.valueOf(statsObject.get("total_rateideal"));

        AntMinerInfo.AntMinerTemp pcbTemp = new AntMinerInfo.AntMinerTemp(
                (int) statsObject.get("temp6"),
                (int) statsObject.get("temp7"),
                (int) statsObject.get("temp8"));

        AntMinerInfo.AntMinerTemp chipTemp = new AntMinerInfo.AntMinerTemp(
                (int) statsObject.get("temp2_6"),
                (int) statsObject.get("temp2_7"),
                (int) statsObject.get("temp2_8"));

        String accepted = String.valueOf(summaryObject.get("Accepted"));
        String rejected = String.valueOf(summaryObject.get("Rejected"));
        String errors = String.valueOf(summaryObject.get("Hardware Errors"));
        String workerName = (String) poolObject.get("User");

        return new AntMinerInfo(antMiner.getHostIp(), model, hashRate, idealHashRate,
                pcbTemp, chipTemp, accepted, rejected, errors, workerName);
    }

    public static JSONObject getDebugVersion(String ip) throws IOException {
        Connection conn = new MinerConnection(ip, true);
        return new JSONObject(conn.sendAndReceive(buildCommand(AntMiner.CMD_VERSION)));
    }

    public static String buildCommand(String command) {
        logger.debug("Requested command: " + command);
        return "{\"" + CMD + "\":\"" + command +"\"}";
    }

//
//    public static Map<String, String> getHardwareStatus(JSONObject stats) {
//        JSONArray array = stats.getJSONArray("STATS");
//        JSONObject object = array.getJSONObject(1);
//        Map<String, String> mapList = new LinkedHashMap<>();
//        mapList.put("ptemp1", String.valueOf(object.get("temp6")));
//        mapList.put("ptemp2", String.valueOf(object.get("temp7")));
//        mapList.put("ptemp3", String.valueOf(object.get("temp8")));
//        mapList.put("ctemp1", String.valueOf(object.get("temp2_6")));
//        mapList.put("ctemp2", String.valueOf(object.get("temp2_7")));
//        mapList.put("ctemp3", String.valueOf(object.get("temp2_8")));
//        mapList.put("hwstatchain6", (String) object.get("chain_acs6"));
//        mapList.put("hwstatchain7", (String) object.get("chain_acs7"));
//        mapList.put("hwstatchain8", (String) object.get("chain_acs8"));
//        mapList.put("totalhashrate", String.valueOf(object.get("total_rate")));
//        mapList.put("totalidealhashrate", String.valueOf(object.get("total_rateideal")));
//
//        printDebug(mapList);
//        return mapList;
//    }
//
//    public static Map<String, String> getMinerVersion(JSONObject version) {
//        JSONObject object = version.getJSONArray("VERSION").getJSONObject(0);
//
//        Map<String, String> mapList = new LinkedHashMap<>();
//        mapList.put("version", (String) object.get("Miner"));
//        mapList.put("model", (String) object.get("Type"));
//
//        printDebug(mapList);
//        return mapList;
//    }
//
//    public static Map<String, String> getPoolConfiguration(JSONObject pools) {
//        JSONArray jsonArray = pools.getJSONArray("POOLS");
//        int length = jsonArray.length();
//        Map<String, String> mapList = new LinkedHashMap<>();
//        for (int i = 0; i < length; i++) {
//            JSONObject object = jsonArray.getJSONObject(i);
//            mapList.put("server" + String.valueOf(i), (String) object.get("URL"));
//            mapList.put("status" + String.valueOf(i), (String) object.get("Status"));
//            mapList.put("worker" + String.valueOf(i), (String) object.get("User"));
//        }
//
//        printDebug(mapList);
//        return mapList;
//    }
//
//    public static Map<String, String> getMiningStats(JSONObject summary) {
//        JSONObject object = summary.getJSONArray("SUMMARY").getJSONObject(0);
//
//        Map<String, String> mapList = new LinkedHashMap<>();
//        int elapsed = (int) object.get("Elapsed");
//        String formatted = formatter.format(new Date(elapsed));
//
//        mapList.put("elapsed", formatted);
//        mapList.put("hwerrors", String.valueOf(object.get("Hardware Errors")));
//        mapList.put("avgspeed", String.valueOf(object.get("GHS av")));
//
//        printDebug(mapList);
//        return mapList;
//    }
}