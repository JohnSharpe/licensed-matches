package online.jsharpe.lm.domain.models;

import java.util.UUID;

public class Match {

    private final UUID matchId;
    private final UUID tournamentId;

    private final long startDate;

    // In a more sophisticated implementation, these could be pointers to player entities.
    private final String playerA;
    private final String playerB;

    public Match(UUID matchId, UUID tournamentId, long startDate, String playerA, String playerB) {
        this.matchId = matchId;
        this.tournamentId = tournamentId;
        this.startDate = startDate;
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public Match(Match copy) {
        this.matchId = copy.matchId;
        this.tournamentId = copy.tournamentId;
        this.startDate = copy.startDate;
        this.playerA = copy.playerA;
        this.playerB = copy.playerB;
    }

    public UUID getMatchId() {
        return matchId;
    }

    public UUID getTournamentId() {
        return tournamentId;
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
}
