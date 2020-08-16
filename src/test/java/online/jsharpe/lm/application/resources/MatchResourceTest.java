package online.jsharpe.lm.application.resources;

import online.jsharpe.lm.domain.LicensedMatchService;
import online.jsharpe.lm.domain.models.Match;
import online.jsharpe.lm.domain.repositories.TestLicensedMatchRepository;
import online.jsharpe.lm.domain.representations.MatchRepresentation;
import online.jsharpe.lm.domain.summary.SummaryGenerator;
import online.jsharpe.lm.domain.summary.clock.FixedClock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.ws.rs.NotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

class MatchResourceTest {

    @Test
    void testEmptyResult() {
        // Given
        final LicensedMatchService licensedMatchService = new LicensedMatchService(
                new SummaryGenerator(new FixedClock(123)),
                new TestLicensedMatchRepository(Collections.emptyList())
        );

        final MatchResource matchResource = new MatchResource(licensedMatchService);

        // When
        Assertions.assertThrows(NotFoundException.class, () -> matchResource.getMatchesByCustomer(UUID.randomUUID(), "AvB"));
    }

    @Test
    void testNormalResult() {
        // Given
        final UUID customerId = UUID.randomUUID();
        final UUID matchId = UUID.randomUUID();

        final TestLicensedMatchRepository testLicensedMatchRepository = new TestLicensedMatchRepository(
                Collections.singletonList(
                        new Match(matchId, UUID.randomUUID(), 123L, "P1", "P2")
                )
        );

        final LicensedMatchService licensedMatchService = new LicensedMatchService(
                new SummaryGenerator(new FixedClock(123)),
                testLicensedMatchRepository
        );

        final MatchResource matchResource = new MatchResource(licensedMatchService);

        // When
        List<MatchRepresentation> matches = matchResource.getMatchesByCustomer(customerId, "AvBTime");

        // Then
        Assertions.assertNotNull(matches);
        Assertions.assertEquals(1, matches.size());
        Assertions.assertEquals(matchId, matches.get(0).getMatchId());
        Assertions.assertEquals(customerId, testLicensedMatchRepository.getLastCalledWith());
    }

}
