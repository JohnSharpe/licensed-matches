package online.jsharpe.lm.domain.summary.clock;

/**
 * A simple implemntation of the Clock interface, relating to the assumption that system time is 'good enough'
 * for our needs.
 */
public class SystemClock implements Clock {

    @Override
    public long getTime() {
        return System.currentTimeMillis();
    }

}
