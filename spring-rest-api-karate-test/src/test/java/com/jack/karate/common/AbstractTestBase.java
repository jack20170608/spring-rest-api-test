package com.jack.karate.common;

import com.intuit.karate.junit4.Karate;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Karate.class)
public abstract class AbstractTestBase {

    private static ServerStart server;

    public static int startServer() throws Exception {
        if (server == null) { // keep spring boot side alive for all tests including package 'mock'
            server = new ServerStart();
            server.start(new String[]{"--server.port=0"}, false);
        }
        System.setProperty("karate-explorer.server.port", server.getPort() + "");
        return server.getPort();
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        startServer();
    }
}
