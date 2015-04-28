package org.leanpoker.player;

import com.google.gson.JsonDeserializationContext;
import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import com.wcs.poker.jsonconverter.JsonConverter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;

public class PlayerTest {

    private static final String SOURCE = "gamestate.json";
    private GameState gameState;
    
    @Before
    public void before(){
        try (InputStream resource = getClass().getResourceAsStream(SOURCE)) {
            String json = IOUtils.toString(resource);
            gameState = new JsonConverter<>(GameState.class).fromJson(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBetRequest() throws Exception {
        assertEquals(0, Player.betRequest(gameState));
    }
}
