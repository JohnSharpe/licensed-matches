package online.jsharpe.lm.domain.repositories;

import online.jsharpe.lm.domain.models.Match;

import java.util.List;
import java.util.UUID;

/**
 * An interface to wrap all our data-fetching behaviour.
 * Further development would likely include adding 'throws' clauses to this method as
 * data integrity management becomes more sophisticated.
 */
public interface LicensedMatchRepository {

    List<Match> getMatchesForCustomer(UUID customerId);

}
