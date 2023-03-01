package com.javarush.task.task37.task3709.connectors;

import com.javarush.task.task37.task3709.connectors.Connector;
import com.javarush.task.task37.task3709.connectors.SimpleConnector;
import com.javarush.task.task37.task3709.security.SecurityChecker;
import com.javarush.task.task37.task3709.security.SecurityCheckerImpl;

public class SecurityProxyConnector implements Connector {
    private final SecurityChecker securityChecker;
    private final SimpleConnector originalConnector;

    public SecurityProxyConnector(String resourceString) {
        originalConnector = new SimpleConnector(resourceString);
        securityChecker = new SecurityCheckerImpl();
    }

    @Override
    public void connect() {
        if (securityChecker.performSecurityCheck())
            originalConnector.connect();
    }
}
