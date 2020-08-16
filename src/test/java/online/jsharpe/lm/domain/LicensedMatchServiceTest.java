package online.jsharpe.lm.domain;

import online.jsharpe.lm.domain.models.Match;
import online.jsharpe.lm.domain.repositories.TestLicensedMatchRepository;
import online.jsharpe.lm.domain.representations.MatchRepresentation;
import online.jsharpe.lm.domain.summary.SummaryGenerator;
import online.jsharpe.lm.domain.summary.clock.SystemClock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

class LicensedMatchServiceTest {

    @Test
    void testNoMatches() {
        // Given
        final UUID customerId = UUID.randomUUID();

        final SummaryGenerator summaryGenerator = new SummaryGenerator(new SystemClock());
        final TestLicensedMatchRepository testLicensedMatchRepository = new TestLicensedMatchRepository(Collections.emptyList());
        final LicensedMatchService licensedMatchService = new LicensedMatchService(summaryGenerator, testLicensedMatchRepository);

        // When
        final List<MatchRepresentation> matches = licensedMatchService.getMatchesForCustomer(customerId, "AvB");

        // Then
        Assertions.assertNotNull(matches);
        Assertions.assertTrue(matches.isEmpty());
        Assertions.assertEquals(customerId, testLicensedMatchRepository.getLastCalledWith());
    }

    @Test
    void testSingleMatch() {
        // Given
        final UUID customerId = UUID.randomUUID();

        final UUID matchId = UUID.randomUUID();
        final long startDate = 20231413;
        final String playerA = "Batman";
        final String playerB = "Joker";

        final SummaryGenerator summaryGenerator = new SummaryGenerator(new SystemClock());
        final TestLicensedMatchRepository testLicensedMatchRepository = new TestLicensedMatchRepository(
                Collections.singletonList(new Match(matchId, UUID.randomUUID(), startDate, playerA, playerB))
        );
        final LicensedMatchService licensedMatchService = new LicensedMatchService(summaryGenerator, testLicensedMatchRepository);

        // When
        final List<MatchRepresentation> matches = licensedMatchService.getMatchesForCustomer(customerId, "AvB");

        // Then
        Assertions.assertNotNull(matches);
        Assertions.assertEquals(1, matches.size());

        final MatchRepresentation firstRepresentation = matches.get(0);
        Assertions.assertEquals(matchId, firstRepresentation.getMatchId());
        Assertions.assertEquals(startDate, firstRepresentation.getStartDate());
        Assertions.assertEquals(playerA, firstRepresentation.getPlayerA());
        Assertions.assertEquals(playerB, firstRepresentation.getPlayerB());
        Assertions.assertEquals("Batman vs Joker", firstRepresentation.getSummary());

        Assertions.assertEquals(customerId, testLicensedMatchRepository.getLastCalledWith());
    }

    @Test
    void testMultipleMatches() {

        final UUID customerId = UUID.randomUUID();

        final Match a = new Match(UUID.randomUUID(), UUID.randomUUID(), 123, "PlayerA", "PlayerB");
        final Match b = new Match(UUID.randomUUID(), UUID.randomUUID(), 456, "PlayerC", "PlayerD");
        final Match c = new Match(UUID.randomUUID(), UUID.randomUUID(), 789, "PlayerE", "PlayerF");

        // Given
        final SummaryGenerator summaryGenerator = new SummaryGenerator(new SystemClock());
        final TestLicensedMatchRepository testLicensedMatchRepository = new TestLicensedMatchRepository(
                Arrays.asList(a, b, c)
        );
        final LicensedMatchService licensedMatchService = new LicensedMatchService(summaryGenerator, testLicensedMatchRepository);

        // When
        final List<MatchRepresentation> matches = licensedMatchService.getMatchesForCustomer(customerId, "AvB");

        // Then
        Assertions.assertNotNull(matches);
        Assertions.assertEquals(3, matches.size());

        final MatchRepresentation firstRepresentation = matches.get(0);
        Assertions.assertEquals(a.getMatchId(), firstRepresentation.getMatchId());
        Assertions.assertEquals(a.getStartDate(), firstRepresentation.getStartDate());
        Assertions.assertEquals(a.getPlayerA(), firstRepresentation.getPlayerA());
        Assertions.assertEquals(a.getPlayerB(), firstRepresentation.getPlayerB());
        Assertions.assertEquals("PlayerA vs PlayerB", firstRepresentation.getSummary());

        final MatchRepresentation secondRepresentation = matches.get(1);
        Assertions.assertEquals(b.getMatchId(), secondRepresentation.getMatchId());
        Assertions.assertEquals(b.getStartDate(), secondRepresentation.getStartDate());
        Assertions.assertEquals(b.getPlayerA(), secondRepresentation.getPlayerA());
        Assertions.assertEquals(b.getPlayerB(), secondRepresentation.getPlayerB());
        Assertions.assertEquals("PlayerC vs PlayerD", secondRepresentation.getSummary());

        final MatchRepresentation thirdRepresentation = matches.get(2);
        Assertions.assertEquals(c.getMatchId(), thirdRepresentation.getMatchId());
        Assertions.assertEquals(c.getStartDate(), thirdRepresentation.getStartDate());
        Assertions.assertEquals(c.getPlayerA(), thirdRepresentation.getPlayerA());
        Assertions.assertEquals(c.getPlayerB(), thirdRepresentation.getPlayerB());
        Assertions.assertEquals("PlayerE vs PlayerF", thirdRepresentation.getSummary());

        Assertions.assertEquals(customerId, testLicensedMatchRepository.getLastCalledWith());
    }

}
