package online.jsharpe.lm.domain.summary.clock;

public class FixedClock implements Clock {

    private final long time;

    public FixedClock(long time) {
        this.time = time;
    }

    @Override
    public long getTime() {
        return time;
    }
}
