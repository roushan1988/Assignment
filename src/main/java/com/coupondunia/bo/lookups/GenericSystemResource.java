package com.coupondunia.bo.lookups;

/**
 * Created by roushan on 17/3/15.
 */
public class GenericSystemResource extends ObserverSystemResource {

    public GenericSystemResource(String resourceName) {
        super(resourceName);
    }

    @Override
    protected void onReload() {
        // Nothing to do here as this is generic implementation.
    }
}
