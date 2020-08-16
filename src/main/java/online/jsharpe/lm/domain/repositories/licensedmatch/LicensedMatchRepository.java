package online.jsharpe.lm.domain.repositories.licensedmatch;

import online.jsharpe.lm.domain.models.Match;

import java.util.List;
import java.util.UUID;

public interface LicensedMatchRepository {

    List<Match> getMatchesForCustomer(UUID customerId);

}
