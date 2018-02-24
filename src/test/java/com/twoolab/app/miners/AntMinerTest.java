package com.twoolab.app.miners;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author yeesheng on 22/02/2018
 * @project antmonitorapp
 */
public class AntMinerTest {

    @Test
    public void testAntMinerAPI() throws Exception {
        AntMiner api = createAPITunnel();

        assertNotNull(api.getSummary());
        assertNotNull(api.getStats());
        assertNotNull(api.getVersion());
        assertNotNull(api.getPools());
        assertNotNull(api.getDevs());
    }

    @Test
    public void testGetHostIP() throws Exception {
        AntMiner api = createAPITunnel();

        assertEquals("10.10.1.11", api.getHostIp());
    }

    private AntMiner createAPITunnel() throws Exception {
        return new AntMiner(new TestConnection());
    }
}
