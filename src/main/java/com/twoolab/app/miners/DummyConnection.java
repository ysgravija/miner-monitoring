package com.twoolab.app.miners;

import com.twoolab.app.Connection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author yeesheng on 23/02/2018
 * @project antmonitorapp
 */
public class DummyConnection implements Connection {

    private String hostIp;
    public DummyConnection(String ip) {
        hostIp = ip;
    }

    @Override
    public String getHost() {
        return hostIp;
    }

    @Override
    public String sendAndReceive(String payload) throws IOException {
        if (payload != null) {
            String command = payload.substring(12, payload.lastIndexOf('"'));
            switch(command) {
                case "summary":
                    return getSummary();
                case "pools":
                    return getPool();
                case "stats":
                    return getStats();
                case "version":
                default:
                    return getVersion();
            }
        }
        return null;
    }

    private String getPool() {
        return "{\n" +
                "  \"STATUS\": [{\n" +
                "    \"Msg\": \"3 Pool(s)\",\n" +
                "    \"STATUS\": \"S\",\n" +
                "    \"When\": 1519068889,\n" +
                "    \"Description\": \"bmminer 1.0.0\",\n" +
                "    \"Code\": 7\n" +
                "  }],\n" +
                "  \"POOLS\": [\n" +
                "    {\n" +
                "      \"User\": \"okl.btc11\",\n" +
                "      \"Pool Stale%\": 0,\n" +
                "      \"Diff\": \"32.8K\",\n" +
                "      \"Stratum URL\": \"stratum.antpool.com\",\n" +
                "      \"Rejected\": 6,\n" +
                "      \"Get Failures\": 0,\n" +
                "      \"Pool Rejected%\": 0.1534,\n" +
                "      \"Proxy Type\": \"\",\n" +
                "      \"URL\": \"stratum+tcp://stratum.antpool.com:3333\",\n" +
                "      \"Last Share Time\": \"0:00:00\",\n" +
                "      \"Proxy\": \"\",\n" +
                "      \"Has GBT\": false,\n" +
                "      \"Status\": \"Alive\",\n" +
                "      \"Has Stratum\": true,\n" +
                "      \"Best Share\": 74598702,\n" +
                "      \"Priority\": 0,\n" +
                "      \"Remote Failures\": 0,\n" +
                "      \"Difficulty Accepted\": 1.06627072E8,\n" +
                "      \"Diff1 Shares\": 0,\n" +
                "      \"Discarded\": 26006,\n" +
                "      \"Long Poll\": \"N\",\n" +
                "      \"Accepted\": 4903,\n" +
                "      \"Getworks\": 990,\n" +
                "      \"Last Share Difficulty\": 32768,\n" +
                "      \"Quota\": 1,\n" +
                "      \"Difficulty Rejected\": 163840,\n" +
                "      \"POOL\": 0,\n" +
                "      \"Stale\": 0,\n" +
                "      \"Stratum Active\": true,\n" +
                "      \"Difficulty Stale\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"User\": \"okl.btc11\",\n" +
                "      \"Pool Stale%\": 0,\n" +
                "      \"Diff\": \"\",\n" +
                "      \"Stratum URL\": \"\",\n" +
                "      \"Rejected\": 0,\n" +
                "      \"Get Failures\": 0,\n" +
                "      \"Pool Rejected%\": 0,\n" +
                "      \"Proxy Type\": \"\",\n" +
                "      \"URL\": \"stratum+tcp://stratum.antpool.com:443\",\n" +
                "      \"Last Share Time\": \"0\",\n" +
                "      \"Proxy\": \"\",\n" +
                "      \"Has GBT\": false,\n" +
                "      \"Status\": \"Alive\",\n" +
                "      \"Has Stratum\": true,\n" +
                "      \"Best Share\": 0,\n" +
                "      \"Priority\": 1,\n" +
                "      \"Remote Failures\": 0,\n" +
                "      \"Difficulty Accepted\": 0,\n" +
                "      \"Diff1 Shares\": 0,\n" +
                "      \"Discarded\": 0,\n" +
                "      \"Long Poll\": \"N\",\n" +
                "      \"Accepted\": 0,\n" +
                "      \"Getworks\": 1,\n" +
                "      \"Last Share Difficulty\": 0,\n" +
                "      \"Quota\": 1,\n" +
                "      \"Difficulty Rejected\": 0,\n" +
                "      \"POOL\": 1,\n" +
                "      \"Stale\": 0,\n" +
                "      \"Stratum Active\": false,\n" +
                "      \"Difficulty Stale\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"User\": \"okl.btc11\",\n" +
                "      \"Pool Stale%\": 0,\n" +
                "      \"Diff\": \"\",\n" +
                "      \"Stratum URL\": \"\",\n" +
                "      \"Rejected\": 0,\n" +
                "      \"Get Failures\": 0,\n" +
                "      \"Pool Rejected%\": 0,\n" +
                "      \"Proxy Type\": \"\",\n" +
                "      \"URL\": \"stratum+tcp://stratum.antpool.com:25\",\n" +
                "      \"Last Share Time\": \"0\",\n" +
                "      \"Proxy\": \"\",\n" +
                "      \"Has GBT\": false,\n" +
                "      \"Status\": \"Dead\",\n" +
                "      \"Has Stratum\": true,\n" +
                "      \"Best Share\": 0,\n" +
                "      \"Priority\": 2,\n" +
                "      \"Remote Failures\": 0,\n" +
                "      \"Difficulty Accepted\": 0,\n" +
                "      \"Diff1 Shares\": 0,\n" +
                "      \"Discarded\": 0,\n" +
                "      \"Long Poll\": \"N\",\n" +
                "      \"Accepted\": 0,\n" +
                "      \"Getworks\": 0,\n" +
                "      \"Last Share Difficulty\": 0,\n" +
                "      \"Quota\": 1,\n" +
                "      \"Difficulty Rejected\": 0,\n" +
                "      \"POOL\": 2,\n" +
                "      \"Stale\": 0,\n" +
                "      \"Stratum Active\": false,\n" +
                "      \"Difficulty Stale\": 0\n" +
                "    }\n" +
                "  ],\n" +
                "  \"id\": 1\n" +
                "}";
    }
    private String getVersion() {
        return "{\n" +
                "  \"STATUS\": [{\n" +
                "    \"Msg\": \"BMMiner versions\",\n" +
                "    \"STATUS\": \"S\",\n" +
                "    \"When\": 1519069505,\n" +
                "    \"Description\": \"bmminer 1.0.0\",\n" +
                "    \"Code\": 22\n" +
                "  }],\n" +
                "  \"VERSION\": [{\n" +
                "    \"Miner\": \"16.8.1.3\",\n" +
                "    \"Type\": \"Antminer S9\",\n" +
                "    \"BMMiner\": \"2.0.0\",\n" +
                "    \"API\": \"3.1\",\n" +
                "    \"CompileTime\": \"Fri Nov 17 17:37:49 CST 2017\"\n" +
                "  }],\n" +
                "  \"id\": 1\n" +
                "}";
    }
    private String getStats() {
        return "{\n" +
                "  \"STATUS\": [{\n" +
                "    \"Msg\": \"BMMiner stats\",\n" +
                "    \"STATUS\": \"S\",\n" +
                "    \"When\": 1519068983,\n" +
                "    \"Description\": \"bmminer 1.0.0\",\n" +
                "    \"Code\": 70\n" +
                "  }],\n" +
                "  \"STATS\": [\n" +
                "    {\n" +
                "      \"Miner\": \"16.8.1.3\",\n" +
                "      \"Type\": \"Antminer S9\",\n" +
                "      \"BMMiner\": \"2.0.0\",\n" +
                "      \"CompileTime\": \"Fri Nov 17 17:37:49 CST 2017\"\n" +
                "    }{\n" +
                "      \"temp2_10\": 0,\n" +
                "      \"temp2_11\": 0,\n" +
                "      \"temp2_12\": 0,\n" +
                "      \"temp2_13\": 0,\n" +
                "      \"temp2_14\": 0,\n" +
                "      \"temp2_15\": 0,\n" +
                "      \"temp2_16\": 0,\n" +
                "      \"total_rate\": 9050.61,\n" +
                "      \"total_freqavg\": 637.91,\n" +
                "      \"chain_hw2\": 0,\n" +
                "      \"chain_hw1\": 0,\n" +
                "      \"chain_hw4\": 0,\n" +
                "      \"chain_hw3\": 0,\n" +
                "      \"miner_id\": \"8018350415408814\",\n" +
                "      \"chain_hw9\": 0,\n" +
                "      \"ID\": \"BC50\",\n" +
                "      \"chain_hw6\": 700,\n" +
                "      \"chain_hw5\": 0,\n" +
                "      \"chain_hw8\": 0,\n" +
                "      \"chain_hw7\": 3,\n" +
                "      \"chain_hw14\": 0,\n" +
                "      \"chain_hw15\": 0,\n" +
                "      \"fan_num\": 2,\n" +
                "      \"chain_hw16\": 0,\n" +
                "      \"Device Hardware%\": 7.0E-4,\n" +
                "      \"freq_avg16\": 0,\n" +
                "      \"freq_avg13\": 0,\n" +
                "      \"chain_offside_6\": \"0\",\n" +
                "      \"temp3_1\": 0,\n" +
                "      \"freq_avg12\": 0,\n" +
                "      \"temp3_2\": 0,\n" +
                "      \"freq_avg15\": 0,\n" +
                "      \"Min\": 9.9999999E7,\n" +
                "      \"temp3_3\": 0,\n" +
                "      \"freq_avg14\": 0,\n" +
                "      \"temp3_4\": 0,\n" +
                "      \"temp3_5\": 0,\n" +
                "      \"temp3_6\": 0,\n" +
                "      \"freq_avg11\": 0,\n" +
                "      \"temp3_7\": 0,\n" +
                "      \"freq_avg10\": 0,\n" +
                "      \"temp3_8\": 0,\n" +
                "      \"chain_hw10\": 0,\n" +
                "      \"temp3_9\": 0,\n" +
                "      \"chain_hw11\": 0,\n" +
                "      \"chain_hw12\": 0,\n" +
                "      \"chain_hw13\": 0,\n" +
                "      \"temp11\": 0,\n" +
                "      \"chain_rateideal15\": 0,\n" +
                "      \"temp12\": 0,\n" +
                "      \"chain_rateideal9\": 0,\n" +
                "      \"chain_rateideal14\": 0,\n" +
                "      \"temp13\": 0,\n" +
                "      \"chain_rateideal8\": 4500.75,\n" +
                "      \"chain_rateideal13\": 0,\n" +
                "      \"temp14\": 0,\n" +
                "      \"chain_rateideal7\": 4500.32,\n" +
                "      \"chain_rateideal12\": 0,\n" +
                "      \"Max\": 0,\n" +
                "      \"temp10\": 0,\n" +
                "      \"chain_rateideal16\": 0,\n" +
                "      \"miner_count\": 3,\n" +
                "      \"temp2\": 0,\n" +
                "      \"temp15\": 0,\n" +
                "      \"chain_rateideal11\": 0,\n" +
                "      \"temp_max\": 70,\n" +
                "      \"temp3\": 0,\n" +
                "      \"temp16\": 0,\n" +
                "      \"chain_rateideal10\": 0,\n" +
                "      \"temp1\": 0,\n" +
                "      \"fan2\": 0,\n" +
                "      \"fan1\": 0,\n" +
                "      \"chain_acs10\": \"\",\n" +
                "      \"chain_acs11\": \"\",\n" +
                "      \"chain_acs12\": \"\",\n" +
                "      \"chain_rate8\": \"0.00000\",\n" +
                "      \"total_rateideal\": 13501.18,\n" +
                "      \"chain_acs13\": \"\",\n" +
                "      \"chain_rate9\": \"\",\n" +
                "      \"chain_acs14\": \"\",\n" +
                "      \"chain_rate6\": \"4513.36\",\n" +
                "      \"chain_acs15\": \"\",\n" +
                "      \"chain_rate7\": \"4537.25\",\n" +
                "      \"chain_acs16\": \"\",\n" +
                "      \"chain_rate4\": \"\",\n" +
                "      \"chain_rate5\": \"\",\n" +
                "      \"chain_rate2\": \"\",\n" +
                "      \"chain_rate3\": \"\",\n" +
                "      \"chain_rate1\": \"\",\n" +
                "      \"temp3_10\": 0,\n" +
                "      \"Calls\": 0,\n" +
                "      \"GHS 5s\": \"9050.603\",\n" +
                "      \"chain_acs2\": \"\",\n" +
                "      \"chain_acs1\": \"\",\n" +
                "      \"temp6\": 70,\n" +
                "      \"temp7\": 68,\n" +
                "      \"temp4\": 0,\n" +
                "      \"temp5\": 0,\n" +
                "      \"temp8\": 0,\n" +
                "      \"temp9\": 0,\n" +
                "      \"chain_rateideal2\": 0,\n" +
                "      \"chain_rateideal1\": 0,\n" +
                "      \"chain_rateideal6\": 4500.09,\n" +
                "      \"chain_rateideal5\": 0,\n" +
                "      \"chain_rateideal4\": 0,\n" +
                "      \"chain_rateideal3\": 0,\n" +
                "      \"chain_rate16\": \"\",\n" +
                "      \"chain_rate15\": \"\",\n" +
                "      \"chain_rate14\": \"\",\n" +
                "      \"chain_rate13\": \"\",\n" +
                "      \"total_acn\": 189,\n" +
                "      \"frequency\": \"681\",\n" +
                "      \"freq_avg6\": 633.9,\n" +
                "      \"chain_acs4\": \"\",\n" +
                "      \"freq_avg5\": 0,\n" +
                "      \"chain_acs3\": \"\",\n" +
                "      \"freq_avg4\": 0,\n" +
                "      \"chain_acs6\": \" oooooooo oooooooo oooooooo oooooooo oooooooo oooooooo oooooooo ooooooo\",\n" +
                "      \"freq_avg3\": 0,\n" +
                "      \"chain_acs5\": \"\",\n" +
                "      \"chain_acs8\": \" oooooooo oooooooo oooooooo oooooooo oooooooo oooooooo oooooooo ooooooo\",\n" +
                "      \"freq_avg9\": 0,\n" +
                "      \"chain_acs7\": \" oooooooo oooooooo oooooooo oooooooo oooooooo oooooooo oooooooo ooooooo\",\n" +
                "      \"freq_avg8\": 647.49,\n" +
                "      \"freq_avg7\": 632.33,\n" +
                "      \"chain_acs9\": \"\",\n" +
                "      \"freq_avg2\": 0,\n" +
                "      \"freq_avg1\": 0,\n" +
                "      \"no_matching_work\": 703,\n" +
                "      \"temp3_11\": 0,\n" +
                "      \"temp3_12\": 0,\n" +
                "      \"temp3_13\": 0,\n" +
                "      \"temp3_14\": 0,\n" +
                "      \"temp3_15\": 0,\n" +
                "      \"temp3_16\": 0,\n" +
                "      \"fan8\": 0,\n" +
                "      \"fan7\": 0,\n" +
                "      \"fan6\": 5640,\n" +
                "      \"chain_acn16\": 0,\n" +
                "      \"fan5\": 0,\n" +
                "      \"chain_acn15\": 0,\n" +
                "      \"fan4\": 0,\n" +
                "      \"temp_num\": 3,\n" +
                "      \"chain_acn14\": 0,\n" +
                "      \"fan3\": 6000,\n" +
                "      \"chain_acn13\": 0,\n" +
                "      \"chain_acn12\": 0,\n" +
                "      \"chain_acn11\": 0,\n" +
                "      \"chain_acn10\": 0,\n" +
                "      \"Elapsed\": 49962,\n" +
                "      \"Wait\": 0,\n" +
                "      \"chain_xtime6\": \"{X42=1}\",\n" +
                "      \"STATS\": 0,\n" +
                "      \"GHS av\": 9149.97,\n" +
                "      \"chain_opencore_6\": \"1\",\n" +
                "      \"chain_opencore_7\": \"1\",\n" +
                "      \"chain_offside_8\": \"0\",\n" +
                "      \"chain_offside_7\": \"0\",\n" +
                "      \"chain_xtime8\": \"{X0=817,X1=817,X2=817,X3=817,X4=817,X5=817,X6=817,X7=817,X8=817,X9=817,X10=817,X11=817,X12=817,X13=817,X14=817,X15=817,X16=817,X17=817,X18=817,X19=817,X20=817,X21=817,X22=817,X23=817,X24=817,X25=817,X26=817,X27=817,X28=817,X29=817,X30=817,X31=817,X32=817,X33=817,X34=817,X35=817,X36=817,X37=817,X38=817,X39=817,X40=817,X41=817,X42=817,X43=817,X44=817,X45=817,X46=817,X47=817,X48=817,X49=817,X50=817,X51=817,X52=817,X53=817,X54=817,X55=817,X56=817,X57=817,X58=817,X59=817,X60=817,X61=817,X62=817}\",\n" +
                "      \"chain_xtime7\": \"{}\",\n" +
                "      \"temp2_1\": 0,\n" +
                "      \"temp2_2\": 0,\n" +
                "      \"temp2_3\": 0,\n" +
                "      \"chain_acn1\": 0,\n" +
                "      \"temp2_4\": 0,\n" +
                "      \"temp2_5\": 0,\n" +
                "      \"chain_acn3\": 0,\n" +
                "      \"chain_opencore_8\": \"0\",\n" +
                "      \"temp2_6\": 85,\n" +
                "      \"chain_acn2\": 0,\n" +
                "      \"temp2_7\": 83,\n" +
                "      \"chain_acn5\": 0,\n" +
                "      \"temp2_8\": 15,\n" +
                "      \"chain_acn4\": 0,\n" +
                "      \"temp2_9\": 0,\n" +
                "      \"chain_acn7\": 63,\n" +
                "      \"chain_rate12\": \"\",\n" +
                "      \"chain_acn6\": 63,\n" +
                "      \"chain_rate11\": \"\",\n" +
                "      \"chain_acn9\": 0,\n" +
                "      \"chain_rate10\": \"\",\n" +
                "      \"chain_acn8\": 63,\n" +
                "      \"miner_version\": \"16.8.1.3\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"id\": 1\n" +
                "}";
    }
    private String getSummary() {
        return "{\n" +
                "  \"STATUS\": [{\n" +
                "    \"Msg\": \"Summary\",\n" +
                "    \"STATUS\": \"S\",\n" +
                "    \"When\": 1519068976,\n" +
                "    \"Description\": \"bmminer 1.0.0\",\n" +
                "    \"Code\": 11\n" +
                "  }],\n" +
                "  \"SUMMARY\": [{\n" +
                "    \"Pool Stale%\": 0,\n" +
                "    \"Work Utility\": 128579.39,\n" +
                "    \"GHS av\": 9149.82,\n" +
                "    \"Rejected\": 6,\n" +
                "    \"Get Failures\": 0,\n" +
                "    \"Pool Rejected%\": 0.153,\n" +
                "    \"Hardware Errors\": 703,\n" +
                "    \"Utility\": 5.9,\n" +
                "    \"Found Blocks\": 0,\n" +
                "    \"Last getwork\": 1519068976,\n" +
                "    \"Device Rejected%\": 0.153,\n" +
                "    \"Local Work\": 1690616,\n" +
                "    \"Best Share\": 74598702,\n" +
                "    \"Remote Failures\": 0,\n" +
                "    \"Total MH\": 4.57070159161E11,\n" +
                "    \"Difficulty Accepted\": 1.06889216E8,\n" +
                "    \"GHS 5s\": \"9166.349\",\n" +
                "    \"Device Hardware%\": 7.0E-4,\n" +
                "    \"Discarded\": 26053,\n" +
                "    \"Accepted\": 4911,\n" +
                "    \"Getworks\": 993,\n" +
                "    \"Difficulty Rejected\": 163840,\n" +
                "    \"Network Blocks\": 89,\n" +
                "    \"Elapsed\": 49955,\n" +
                "    \"Stale\": 0,\n" +
                "    \"Difficulty Stale\": 0\n" +
                "  }],\n" +
                "  \"id\": 1\n" +
                "}";
    }
}
