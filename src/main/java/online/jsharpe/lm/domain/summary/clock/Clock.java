package online.jsharpe.lm.domain.summary.clock;

public interface Clock {

    /**
     * The 'current' time could come from a number of sources.
     * @return the current time as decided by the implementation.
     */
    long getTime();

}
