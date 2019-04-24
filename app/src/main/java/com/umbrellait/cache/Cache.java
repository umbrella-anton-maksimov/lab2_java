package com.umbrellait.cache;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cache<Value> {

    private Value value;

    private final DataProvider<Value> dataProvider;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    private final long validTimeInMilliseconds;
    private long lastUpdateTimestamp;

    public Cache(long validTimeInMilliseconds, DataProvider<Value> dataProvider) {
        this.validTimeInMilliseconds = validTimeInMilliseconds;
        this.dataProvider = dataProvider;
    }

    public Value get(boolean forceInvalidate) {
        long validUntil = this.lastUpdateTimestamp + validTimeInMilliseconds;
        System.out.println("Valid until:" + sdf.format(new Date(validUntil)));
        long currentTimestamp = currentTimestamp();
        System.out.println("Current time:" + sdf.format(new Date(currentTimestamp)));
        if (currentTimestamp > validUntil || forceInvalidate) {
            Value newValue = dataProvider.provide();
            set(newValue);
            return value;
        } else {
            return value;
        }
    }

    private void set(Value value) {
        this.value = value;
        lastUpdateTimestamp = currentTimestamp();
    }

    public Value get() {
        return get(false);
    }

    public long getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    private static long currentTimestamp() {
        return new Date().getTime();
    }
}
