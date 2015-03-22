package com.coupondunia.bo.lookups;

import org.apache.log4j.Logger;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by roushan on 17/3/15.
 */
public abstract class ObserverSystemResource extends SystemResource implements Observer {
    private static final Logger LOG = Logger.getLogger(ObserverSystemResource.class);

    public ObserverSystemResource(String resourceName) {
        super(resourceName);
    }

    @Override
    public void update(Observable o, Object arg) {
        reload(); // The Observable this instance was registered with has chagned. So cascade the trigger call to all its obeservers
    }
}
