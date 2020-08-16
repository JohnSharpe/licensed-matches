package online.jsharpe.lm.domain.summary.clock;

public class SystemClock implements Clock {

    @Override
    public long getTime() {
        return System.currentTimeMillis();
    }

}
