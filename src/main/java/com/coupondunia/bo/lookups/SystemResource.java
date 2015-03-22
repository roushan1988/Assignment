package com.coupondunia.bo.lookups;

import org.apache.log4j.Logger;
import java.util.Observable;

/**
 * Created by roushan on 17/3/15.
 */
public abstract class SystemResource extends Observable {
    private static final Logger LOG = Logger.getLogger(SystemResource.class);

    abstract protected void onReload();

    private final String resourceName;
    private boolean reloaded = false;

    public SystemResource(String resourceName) {
        this.resourceName = resourceName;
    }

    public void reload() {
        reloaded = false; // Setting the flag as false until the reload is complete

        LOG.info("[Reload - " + resourceName + "] Triggering onReload ... Started");
        try {
            onReload();
            LOG.debug("[Reload - " + resourceName + "] Triggering Reload of all observers ... Started");
            this.setChanged();
            this.notifyObservers();
            LOG.debug("[Reload - " + resourceName + "] Triggering Reload of all observers ... Done");
            reloaded = true;
            LOG.info("[Reload - " + resourceName + "] Triggering onReload ... Done");
        } catch (Throwable t) {
            LOG.fatal("[Reload - " + resourceName + "] onReload ... Failed", t);
        }
    }

    /**
     * Registers an observer system resource as an observer of this instance.
     *
     * @param systemResource System resource to be registered as observer.
     */
    public void registerForReload(ObserverSystemResource systemResource) {
        if (systemResource != null) {
            this.addObserver(systemResource);
        }
    }

    public boolean isReloaded() {
        return reloaded;
    }
}