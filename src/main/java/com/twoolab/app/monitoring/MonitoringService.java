package com.twoolab.app.monitoring;

import com.twoolab.app.miners.AntMiner;
import com.twoolab.app.miners.AntMinerInfo;
import com.twoolab.app.miners.AntMinerUtils;
import com.twoolab.app.miners.DummyConnection;
import com.twoolab.app.miners.MinerConnection;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author yeesheng on 23/02/2018
 * @project antmonitorapp
 */
public class MonitoringService {

    private static final Logger logger = Logger.getLogger(MonitoringService.class);
    private static final String IP_ADDRESS_REGEX =
                    "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    public static final List<AntMiner> minerList = new ArrayList<>();

    public static List<AntMiner> searchMiners(String rangeStart, String rangeEnd) throws IllegalArgumentException {
        Pattern pattern = Pattern.compile(IP_ADDRESS_REGEX);
        int start = Integer.parseInt(rangeStart.substring(rangeStart.lastIndexOf('.') + 1));
        int end = Integer.parseInt(rangeEnd.substring(rangeEnd.lastIndexOf('.') + 1));

        if (!pattern.matcher(rangeStart).matches() || !pattern.matcher(rangeEnd).matches()) {
            throw new IllegalArgumentException("Invalid IP address format!");
        } else if (start > end || end > 254) {
            throw new IllegalArgumentException("Invalid IP address range!");
        } else {
            int count = end - start;
            String ipPrefix = rangeStart.substring(0, rangeStart.lastIndexOf(".")+1);
            for (int i = 0; i <= count; i++) {
                String ip = ipPrefix + (start + i);
                logger.debug("Trying to open " + ip);
                try {
                    AntMinerUtils.getDebugVersion(ip);
                    minerList.add(new AntMiner(new MinerConnection(ip)));
                    logger.info("Found AntMiner at " + ip);
                } catch (Exception e) {
                    logger.debug("Unable to open socket connection on " + ip);
                }
            }
        }
        return minerList;
    }

    public static List<AntMinerInfo> getMinersInfo(List<AntMiner> miners) {
        List<AntMinerInfo> list = new ArrayList<>();
        for (AntMiner m : miners) {
            list.add(AntMinerUtils.getMinerInfo(m));
        }
        return list;
    }

}
