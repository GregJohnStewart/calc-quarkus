package org.acme;


import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.ConfigProvider;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.NoSuchElementException;
import java.util.TreeMap;

@Singleton
@Slf4j
public class LifecycleBean {


    private ZonedDateTime startDateTime;

    private void startLogAnnounce(){
        this.startDateTime = ZonedDateTime.now();
        log.info("Web Server starting.");
        //		log.info("Base URL: {}", this.serverUrlService.getBaseServerUrl());
        //		log.debug("Version: {}", this.serverVersion);
        //		log.debug("build time: {}", this.buildTime);
        //		log.debug("Core lib version: {}", this.coreVersion);
        //		log.debug("ManagerIO lib version: {}", this.managerIOVersion);
        //		log.debug("Stats lib version: {}", this.statsVersion);
        //		log.debug("Web lib version: {}", this.webLibVersion);


        if (log.isInfoEnabled()) {
            TreeMap<String, String> configMap = new TreeMap<>();

            for(String curProp : ConfigProvider.getConfig().getPropertyNames()){
                String value;
                try {
                    value = ConfigProvider.getConfig().getValue(curProp, String.class);
                } catch(NoSuchElementException e) {
                    value = "";
                }
                configMap.put(curProp, value);
            }

            StringBuilder sb = new StringBuilder();
            for (String curProp : configMap.keySet()) {

                sb.append('\t');
                sb.append(curProp);
                sb.append('=');
                sb.append(configMap.get(curProp));
                sb.append(System.lineSeparator());
            }
            log.info("Configuration: \n{}", sb);
        }
    }

    void onStart(
            @Observes
            StartupEvent ev
    ) {
        this.startLogAnnounce();
        //ensures the unit service bean is initialized, and by extension had existing custom units read in
    }

    void onStop(
            @Observes
            ShutdownEvent ev
    ) {
        log.info("The server is stopping.");
        Duration runtime = Duration.between(this.startDateTime, ZonedDateTime.now());
        log.info("Server ran for {}", runtime);
    }
}