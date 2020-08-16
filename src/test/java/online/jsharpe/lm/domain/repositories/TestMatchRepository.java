package online.jsharpe.lm.domain.repositories;

import online.jsharpe.lm.domain.models.Match;

import java.util.List;
import java.util.UUID;

public class TestMatchRepository implements MatchRepository {

    private final List<Match> matches;

    private List<UUID> lastCalledWith;

    public TestMatchRepository(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public List<Match> getMatches(List<UUID> ids) {
        this.lastCalledWith = ids;
        return matches;
    }

    public List<UUID> getLastCalledWith() {
        return lastCalledWith;
    }

}
