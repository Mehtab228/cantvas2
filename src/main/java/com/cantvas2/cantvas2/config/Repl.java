package com.cantvas2.cantvas2.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import clojure.java.api.Clojure;
import clojure.lang.IFn;

@Component
public final class Repl {
    
    @Autowired ApplicationContext ctx;

    private final IFn require = Clojure.var("clojure.core", "require");
    private final IFn symbol = Clojure.var("clojure.core", "symbol");
    private final IFn intern = Clojure.var("clojure.core", "intern");
    private final IFn startServer;
    private final IFn stopServer = Clojure.var("clojure.core.server", "stop-server");
    private Object userNamespace = symbol.invoke("user");

    protected Repl() {
        this.startServer = Clojure.var("clojure.core.server", "start-server");
        intern.invoke(userNamespace, symbol.invoke("app-ctx"), ctx);
    }

    @PostConstruct
    private final void start() {
        require.invoke(Clojure.read("clojure.core.server"));
        Object serverOpts = Clojure.read("{:port 4555 :accept clojure.core.server/repl :name repl :server-daemon false}");
        startServer.invoke(serverOpts);
    }

    @PreDestroy
    private final void stop() {
        stopServer.invoke();
    }
}
