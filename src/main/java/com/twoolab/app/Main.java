package com.twoolab.app;

import com.twoolab.app.miners.AntMiner;
import com.twoolab.app.miners.AntMinerUtils;
import com.twoolab.app.miners.MinerConnection;
import com.twoolab.app.monitoring.MonitoringService;
import org.apache.catalina.startup.Tomcat;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
/**
 * @author yeesheng on 21/02/2018
 * @project antmonitorapp
 */
public class Main
{
    public static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    private static final Logger logger = Logger.getLogger(Main.class);
    private static final String IPADDRESS_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    public static final Optional<String> port = Optional.ofNullable(System.getenv("PORT"));
    public static final Optional<String> startIp = Optional.ofNullable(System.getenv("STARTIP"));
    public static final Optional<String> endIp = Optional.ofNullable(System.getenv("ENDIP"));

    public static void main( String[] args ) throws Exception {
        logger.info("Start scanning Ant Miner host");

        String startRange = startIp.orElse("192.168.1.180");
        String endRange = endIp.orElse("192.168.1.200");

        logger.info(String.format("Scanning range from %s to  %s", startRange, endRange));

        List<AntMiner> antminerHosts = MonitoringService.searchMiners(startRange, endRange);
        logger.info("Total antminer: " + antminerHosts.size());

//        MonitoringService.minerList.add(new AntMiner(new DummyConnection("10.10.11.1")));
//        MonitoringService.minerList.add(new AntMiner(new DummyConnection("10.10.11.2")));
//        MonitoringService.minerList.add(new AntMiner(new DummyConnection("10.10.11.3")));
//        MonitoringService.minerList.add(new AntMiner(new DummyConnection("10.10.11.4")));
//        MonitoringService.minerList.add(new AntMiner(new DummyConnection("10.10.11.5")));
//        MonitoringService.minerList.add(new AntMiner(new DummyConnection("10.10.11.6")));
//        MonitoringService.minerList.add(new AntMiner(new DummyConnection("10.10.11.7")));
//        MonitoringService.minerList.add(new AntMiner(new DummyConnection("10.10.11.8")));
//        MonitoringService.minerList.add(new AntMiner(new DummyConnection("10.10.11.9")));
//        MonitoringService.minerList.add(new AntMiner(new DummyConnection("10.10.11.10")));

//        processTask(antminerHosts);
//        Thread.sleep(30000);

        // Tomcat startup
        String contextPath = "/";
        String appBase = ".";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(Integer.valueOf(port.orElse("8080") ));
        tomcat.getHost().setAppBase(appBase);
        tomcat.addWebapp(contextPath, appBase);
        tomcat.start();
        tomcat.getServer().await();

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }

    private static List<AntMiner> scanIp(String startIp, String endIp) throws IllegalArgumentException, NumberFormatException {
        List<AntMiner> hostList = new ArrayList<>();

        Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
        int start = Integer.parseInt(startIp.substring(startIp.lastIndexOf('.') + 1));
        int end = Integer.parseInt(endIp.substring(endIp.lastIndexOf('.') + 1));

        if (!pattern.matcher(startIp).matches() || !pattern.matcher(endIp).matches()) {
            throw new IllegalArgumentException("Invalid IP address format!");
        } else if (start > end || end > 254) {
            throw new IllegalArgumentException("Invalid IP address range!");
        } else {
            int count = end - start;
            String ipPrefix = startIp.substring(0, startIp.lastIndexOf(".")+1);
            for (int i = 0; i <= count; i++) {
                String ip = ipPrefix + (start + i);
                logger.debug("Trying to open " + ip);
                try {
                    AntMinerUtils.getDebugVersion(ip);
                    hostList.add(new AntMiner(new MinerConnection(ip)));
                    logger.info("Found AntMiner at " + ip);
                } catch (Exception e) {
                    logger.debug("Unable to open socket connection on " + ip);
                }
            }
        }
        return hostList;
    }
}
