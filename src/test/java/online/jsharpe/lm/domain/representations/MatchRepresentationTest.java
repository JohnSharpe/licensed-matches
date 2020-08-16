package online.jsharpe.lm.domain.representations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.FixtureHelpers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class MatchRepresentationTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    void testDeserialisation() throws JsonProcessingException {
        final MatchRepresentation matchRepresentation = new MatchRepresentation(
                UUID.fromString("01028a8b-cb50-4a2b-932d-a653d8f9151d"),
                1531663200,
                "Novak Djokovic",
                "Kevin Anderson",
                "Novak Djokovic vs Kevin Anderson"
        );

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(FixtureHelpers.fixture("fixtures/wimbledon_final_2018.json"), MatchRepresentation.class));

        Assert.assertEquals(expected, MAPPER.writeValueAsString(matchRepresentation));
    }

}
