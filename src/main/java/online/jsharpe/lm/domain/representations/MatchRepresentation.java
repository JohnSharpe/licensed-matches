package online.jsharpe.lm.domain.representations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * This class represents a single Match as we want the user to see it.
 * Jackson annotations mean that it can be serialised/deserialised into/from JSON
 */
public class MatchRepresentation {

    private final UUID matchId;
    private final long startDate;
    private final String playerA;
    private final String playerB;
    private final String summary;

    @JsonCreator
    public MatchRepresentation(
            @JsonProperty("matchId") UUID matchId,
            @JsonProperty("startDate") long startDate,
            @JsonProperty("playerA") String playerA,
            @JsonProperty("playerB") String playerB,
            @JsonProperty("summary") String summary) {
        this.matchId = matchId;
        this.startDate = startDate;
        this.playerA = playerA;
        this.playerB = playerB;
        this.summary = summary;
    }

    public UUID getMatchId() {
        return matchId;
    }

    public long getStartDate() {
        return startDate;
    }

    public String getPlayerA() {
        return playerA;
    }

    public String getPlayerB() {
        return playerB;
    }

    public String getSummary() {
        return summary;
    }
}
