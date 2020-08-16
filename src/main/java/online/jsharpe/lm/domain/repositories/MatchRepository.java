package online.jsharpe.lm.domain.repositories;

import online.jsharpe.lm.domain.models.Match;

import java.util.List;
import java.util.UUID;

public interface MatchRepository {

    List<Match> getMatches(List<UUID> ids);

}
