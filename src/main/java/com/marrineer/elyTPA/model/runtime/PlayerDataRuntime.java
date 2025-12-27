package com.marrineer.elyTPA.model.runtime;

@SuppressWarnings("unused")
public final class PlayerDataRuntime {
    private long nextTpaAllowedAt = 0L;

    private int requestCount = 0;
    private long windowStartAt = 0L;

    public boolean isOnCooldown() {
        return System.currentTimeMillis() < nextTpaAllowedAt;
    }

    public long getRemainingCooldownMs() {
        long remaining = nextTpaAllowedAt - System.currentTimeMillis();
        return Math.max(0, remaining);
    }

    public void startCooldown(long durationMs) {
        this.nextTpaAllowedAt = System.currentTimeMillis() + durationMs;
    }

    public void clearCooldown() {
        this.nextTpaAllowedAt = 0L;
    }

    public boolean canSendRequest(long windowMs, int limit) {
        long now = System.currentTimeMillis();

        // Nếu đã qua window → reset counter
        if (now - windowStartAt > windowMs) {
            windowStartAt = now;
            requestCount = 0;
        }

        requestCount++;
        return requestCount <= limit;
    }

    public int getRequestCount() {
        return requestCount;
    }

    public void resetRequestCounter() {
        requestCount = 0;
        windowStartAt = 0L;
    }

    public void resetAll() {
        clearCooldown();
        resetRequestCounter();
    }
}
