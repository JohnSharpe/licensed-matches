package online.jsharpe.lm.domain;

import online.jsharpe.lm.domain.repositories.LicensedMatchRepository;
import online.jsharpe.lm.domain.representations.MatchRepresentation;
import online.jsharpe.lm.domain.summary.SummaryGenerator;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * This class contains the core logic to satisfying the criteria.
 */
public class LicensedMatchService {

    private final SummaryGenerator summaryGenerator;
    private final LicensedMatchRepository licensedMatchRepository;

    public LicensedMatchService(SummaryGenerator summaryGenerator,
                                LicensedMatchRepository licensedMatchRepository) {
        this.summaryGenerator = summaryGenerator;
        this.licensedMatchRepository = licensedMatchRepository;
    }

    /**
     * The core function of the application - get the matches for a given customer in the specified
     * representation
     *
     * @param customerId  the identifier of the customer whose matches to find
     * @param summaryType a flag to indicate how the summary should be generated
     * @return a list of matches licensed by the given customer in the desired representation
     */
    public List<MatchRepresentation> getMatchesForCustomer(UUID customerId, String summaryType) {
        return this.licensedMatchRepository.getMatchesForCustomer(customerId).stream()
                .map(match -> new MatchRepresentation(
                        match.getMatchId(),
                        match.getStartDate(),
                        match.getPlayerA(),
                        match.getPlayerB(),
                        summaryGenerator.generateSummary(match, summaryType)
                )).collect(Collectors.toList());
    }

}
