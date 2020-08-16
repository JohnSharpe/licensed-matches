package online.jsharpe.lm.domain.models;

import java.util.UUID;

public class License {

    private final UUID customerId;
    private final UUID matchId;

    public License(UUID customerId, UUID matchId) {
        this.customerId = customerId;
        this.matchId = matchId;
    }

    public License(License copy) {
        this.customerId = copy.customerId;
        this.matchId = copy.matchId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public UUID getMatchId() {
        return matchId;
    }

}
