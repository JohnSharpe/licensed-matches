package online.jsharpe.lm.application.repositories;

import online.jsharpe.lm.domain.models.License;
import online.jsharpe.lm.domain.models.Match;
import online.jsharpe.lm.domain.repositories.LicensedMatchRepository;
import online.jsharpe.lm.domain.repositories.LicenseRepository;
import online.jsharpe.lm.domain.repositories.MatchRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class LocalLicensedMatchRepository implements LicensedMatchRepository {

    private final LicenseRepository licenseRepository;
    private final MatchRepository matchRepository;

    public LocalLicensedMatchRepository(LicenseRepository licenseRepository, MatchRepository matchRepository) {
        this.licenseRepository = licenseRepository;
        this.matchRepository = matchRepository;
    }

    @Override
    public List<Match> getMatchesForCustomer(UUID customerId) {
        final List<UUID> matchIds = licenseRepository.getLicensesForCustomer(customerId).stream()
                .map(License::getMatchId)
                .collect(Collectors.toList());
        return matchRepository.getMatches(matchIds);
    }
}
