package online.jsharpe.lm.domain.summary;

import online.jsharpe.lm.domain.models.Match;
import online.jsharpe.lm.domain.summary.clock.Clock;

public class SummaryGenerator {

    private static final String AVB_TIME_TYPE = "AvBTime";
    private static final double MILLIS_IN_MINUTE = 60 * 1000;

    private static final String AVB_TEMPLATE = "%s vs %s";
    private static final String AVB_TIME_FUTURE_TEMPLATE = "%s vs %s, starts in %d minutes";
    private static final String AVB_TIME_PAST_TEMPLATE = "%s vs %s, started %d minutes ago";

    private final Clock clock;

    public SummaryGenerator(Clock clock) {
        this.clock = clock;
    }

    public String generateSummary(Match match, String summaryType) {
        if (AVB_TIME_TYPE.equals(summaryType)) {

            long millisUntilMatch = match.getStartDate() - clock.getTime();

            if (millisUntilMatch < 0) {
                // The match has already started
                return String.format(
                        AVB_TIME_PAST_TEMPLATE,
                        match.getPlayerA(),
                        match.getPlayerB(),
                        (long) Math.ceil(Math.abs(millisUntilMatch) / MILLIS_IN_MINUTE)
                );
            } else {
                return String.format(
                        AVB_TIME_FUTURE_TEMPLATE,
                        match.getPlayerA(),
                        match.getPlayerB(),
                        (long) Math.floor(millisUntilMatch / MILLIS_IN_MINUTE)
                );
            }
        } else {
            // default to AvB
            return String.format(AVB_TEMPLATE, match.getPlayerA(), match.getPlayerB());
        }
    }

}
