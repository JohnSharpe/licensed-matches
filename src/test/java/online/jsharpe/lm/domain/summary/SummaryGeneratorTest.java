package online.jsharpe.lm.domain.summary;

import online.jsharpe.lm.domain.models.Match;
import online.jsharpe.lm.domain.summary.clock.Clock;
import online.jsharpe.lm.domain.summary.clock.FixedClock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.UUID;

class SummaryGeneratorTest {

    private static final String AVB_TIME_MESSAGE_TYPE = "AvBTime";

    @ParameterizedTest
    @ValueSource(strings = {"AvB", "AVB", "Anything", "default", "AvBtime", "AVBTime"})
    @NullAndEmptySource
    void testAvB(final String summaryType) {
        // Given
        final Clock clock = new FixedClock(1597575462581L);
        final SummaryGenerator summaryGenerator = new SummaryGenerator(clock);

        final Match match = new Match(
                UUID.randomUUID(), UUID.randomUUID(), 0, "Roger Federer", "Rafael Nadal"
        );

        // When
        final String summary = summaryGenerator.generateSummary(match, summaryType);

        // Then
        Assertions.assertEquals("Roger Federer vs Rafael Nadal", summary);
    }

    // This match occurs in exactly x minutes
    @Test
    void testAvBTimeFuture() {
        // Given
        final long timeNow = 1597575462581L;
        final long timeLater = timeNow + 1740000; // 29 minutes in millis

        final Clock clock = new FixedClock(timeNow);
        final SummaryGenerator summaryGenerator = new SummaryGenerator(clock);

        final Match match = new Match(
                UUID.randomUUID(), UUID.randomUUID(), timeLater, "Roger Federer", "Rafael Nadal"
        );

        // When
        final String summary = summaryGenerator.generateSummary(match, AVB_TIME_MESSAGE_TYPE);

        // Then
        Assertions.assertEquals("Roger Federer vs Rafael Nadal, starts in 29 minutes", summary);
    }

    // This match occurs in x.y minutes.
    // Let's assume the user doesn't want a fraction of minutes to work with.
    // Let's also assume they want the earlier round (floor) so they don't miss anything.
    @Test
    void testAvBTimeFutureFuzzy() {
        // Given
        final long timeNow = 1597575462581L;
        final long timeLater = timeNow + 94532328; // 1575.5388 minutes in millis

        final Clock clock = new FixedClock(timeNow);
        final SummaryGenerator summaryGenerator = new SummaryGenerator(clock);

        final Match match = new Match(
                UUID.randomUUID(), UUID.randomUUID(), timeLater, "Roger Federer", "Rafael Nadal"
        );

        // When
        final String summary = summaryGenerator.generateSummary(match, AVB_TIME_MESSAGE_TYPE);

        // Then
        Assertions.assertEquals("Roger Federer vs Rafael Nadal, starts in 1575 minutes", summary);
    }

    // Let's assume, conversely, that if the game has already started, we want to over-esimate the beginning time
    @Test
    void testAvBTimePast() {
        // Given
        final long timeNow = 1597575462581L;
        final long timeLater = timeNow - 886356; // 14.7726 minutes in millis

        final Clock clock = new FixedClock(timeNow);
        final SummaryGenerator summaryGenerator = new SummaryGenerator(clock);

        final Match match = new Match(
                UUID.randomUUID(), UUID.randomUUID(), timeLater, "Roger Federer", "Rafael Nadal"
        );

        // When
        final String summary = summaryGenerator.generateSummary(match, AVB_TIME_MESSAGE_TYPE);

        // Then
        Assertions.assertEquals("Roger Federer vs Rafael Nadal, started 15 minutes ago", summary);
    }

}
