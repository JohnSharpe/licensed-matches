package online.jsharpe.lm.domain;

import online.jsharpe.lm.domain.repositories.LicensedMatchRepository;
import online.jsharpe.lm.domain.representations.MatchRepresentation;
import online.jsharpe.lm.domain.summary.SummaryGenerator;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class LicensedMatchService {

    private final SummaryGenerator summaryGenerator;
    private final LicensedMatchRepository licensedMatchRepository;

    public LicensedMatchService(SummaryGenerator summaryGenerator,
                                LicensedMatchRepository licensedMatchRepository) {
        this.summaryGenerator = summaryGenerator;
        this.licensedMatchRepository = licensedMatchRepository;
    }

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
