package online.jsharpe.lm.domain.repositories;

import online.jsharpe.lm.domain.models.Match;
import online.jsharpe.lm.domain.repositories.LicensedMatchRepository;

import java.util.List;
import java.util.UUID;

public class TestLicensedMatchRepository implements LicensedMatchRepository {

    private final List<Match> matches;

    private UUID lastCalledWith;

    public TestLicensedMatchRepository(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public List<Match> getMatchesForCustomer(UUID customerId) {
        this.lastCalledWith = customerId;
        return this.matches;
    }

    public UUID getLastCalledWith() {
        return lastCalledWith;
    }
}
