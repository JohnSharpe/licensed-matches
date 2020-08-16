package online.jsharpe.lm.application.resources;

import online.jsharpe.lm.domain.LicensedMatchService;
import online.jsharpe.lm.domain.representations.MatchRepresentation;

import javax.ws.rs.*;
import java.util.List;
import java.util.UUID;

public class MatchResource {

    private final LicensedMatchService licensedMatchService;

    public MatchResource(LicensedMatchService licensedMatchService) {
        this.licensedMatchService = licensedMatchService;
    }

    @Path("licensed/{customerId}")
    @GET
    public List<MatchRepresentation> getMatchesByCustomer(
            @PathParam("customerId") final UUID customerId,
            @QueryParam("summaryType") final String summaryType
    ) {
        final List<MatchRepresentation> matchRepresentations = licensedMatchService.getMatchesForCustomer(
                customerId, summaryType
        );

        if (matchRepresentations.isEmpty()) {
            throw new NotFoundException();
        } else {
            return matchRepresentations;
        }
    }

}
