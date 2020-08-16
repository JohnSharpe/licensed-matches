package online.jsharpe.lm.application.repositories;

import online.jsharpe.lm.application.repositories.LocalLicensedMatchRepository;
import online.jsharpe.lm.domain.models.License;
import online.jsharpe.lm.domain.models.Match;
import online.jsharpe.lm.domain.repositories.TestLicenseRepository;
import online.jsharpe.lm.domain.repositories.TestMatchRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

class LocalLicensedMatchRepositoryTest {

    @Test
    void testLocalLicensedMatchRepository() {
        // Given
        final UUID customerId = UUID.randomUUID();
        final UUID matchId = UUID.randomUUID();

        final TestLicenseRepository testLicenseRepository = new TestLicenseRepository(
                Collections.singletonList(
                        new License(
                                customerId, matchId
                        )
                )
        );

        final TestMatchRepository testMatchRepository = new TestMatchRepository(
                Collections.singletonList(
                        new Match(matchId, UUID.randomUUID(), 123, "PlayerA", "PlayerB")
                )
        );

        final LocalLicensedMatchRepository localLicensedMatchRepository = new LocalLicensedMatchRepository(
                testLicenseRepository,
                testMatchRepository
        );

        // When
        final List<Match> matches = localLicensedMatchRepository.getMatchesForCustomer(customerId);

        // Then
        Assert.assertNotNull(matches);
        Assert.assertEquals(1, matches.size());
        Assert.assertEquals(matchId, matches.get(0).getMatchId());

        Assert.assertEquals(customerId, testLicenseRepository.getLastCalledWith());
        Assert.assertEquals(Collections.singletonList(matchId), testMatchRepository.getLastCalledWith());

    }

}
