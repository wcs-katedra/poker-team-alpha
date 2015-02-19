package org.leanpoker.player;

import com.wcs.poker.gamestate.GameState;
import com.wcs.poker.jsonconverter.JsonConverter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    @Test
    public void testBetRequest() throws Exception {
        final String gameStateJson = "{\"key1\": \"value1\", \"key2\": \"value2\"}";
        
        JsonConverter<GameState> converter = new JsonConverter<>(GameState.class);

        assertEquals(0, Player.betRequest(converter.fromJson(gameStateJson)));

    }
}
