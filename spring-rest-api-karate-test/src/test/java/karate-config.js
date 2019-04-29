function fn() {
    karate.configure('connectTimeout', 5000);
    karate.configure('readTimeout', 5000);
    //include http log,  any step that starts with * instead of Given, When, Then etc. will not appear in the HTML report
    karate.configure('report', { showLog: true, showAllSteps: false });

    var port = karate.properties['karate-explorer.server.port'];
    if (!port) {
        port = karate.env == 'web' ? 8090 : 8080;
    }
    var protocol = 'http';
    if (karate.properties['demo.server.https'] == 'true') {
        protocol = 'https';
        karate.configure('ssl', true);
    }
    var config = { demoBaseUrl: protocol + '://127.0.0.1:' + port + "/karate-explorer"};
    if (karate.env == 'proxy') {
        var proxyPort = karate.properties['demo.proxy.port'];
        karate.configure('proxy', 'http://127.0.0.1:' + proxyPort);
    }
    /*
    if (karate.env != 'mock' && karate.env != 'proxy' && karate.env != 'contract') {
        // 'callSingle' is guaranteed to run only once even across all threads
        var result = karate.callSingle('classpath:demo/headers/common-noheaders.feature', config);
        // and it sets a variable called 'authInfo' used in headers-single.feature
        config.authInfo = { authTime: result.time, authToken: result.token };
    }
    */
    return config;
}