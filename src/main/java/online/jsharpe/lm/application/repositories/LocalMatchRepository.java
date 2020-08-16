package online.jsharpe.lm.application.repositories;

import online.jsharpe.lm.domain.models.Match;
import online.jsharpe.lm.domain.repositories.MatchRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class LocalMatchRepository implements MatchRepository {

    private final List<Match> matches = new ArrayList<>();

    /**
     * Get all matches from a local store that have an id in the provided list.
     * This is potentially a very slow operation, especially if there are many
     * Matches involved.
     * As with Licenses, copies are returned to prevent leaking the original pointers.
     *
     * @param ids A collection of ids to search for.
     * @return A list of matches with ids contained in the given list
     */
    @Override
    public List<Match> getMatches(List<UUID> ids) {
        return matches.stream()
                .filter(match -> ids.contains(match.getMatchId()))
                .map(Match::new)
                .collect(Collectors.toList());
    }

}
