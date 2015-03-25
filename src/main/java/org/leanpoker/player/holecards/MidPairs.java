/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player.holecards;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.Player;
import org.leanpoker.player.PreFlopContoller;

import java.util.List;

/**
 *
 * @author SAVUAAP.PTE
 */
public class MidPairs {

    private String pattern = "[J9]|10";

    private PreFlopContoller preFlopController;
    private List<Player> players;
    private String myPositionCat;
    private Card card1;
    private Card card2;
    private Integer minimum_raise;
    private String whatHappenedBeforeMe;
    private Integer call;

    public MidPairs() {
    }

    public MidPairs(String myPositionCat, String whatHappenedBeforeMe, PreFlopContoller preFlopController) {
        this.preFlopController = preFlopController;
        this.players = players;
        this.myPositionCat = myPositionCat;
        this.whatHappenedBeforeMe = whatHappenedBeforeMe;
    }

    public Integer start() {
        card1 = preFlopController.getCurrentHoleCards().get(0);
        card2 = preFlopController.getCurrentHoleCards().get(1);
        minimum_raise = preFlopController.getMinimum_raise();
        call=preFlopController.getCall();
        return betRequest();
    }

    public Integer betRequest() {
        if (preFlopController.isPair(card1, card2)
                && card1.getRank().matches(pattern)) {
            switch (myPositionCat) {
                case "Blinds": {
                    switch (whatHappenedBeforeMe) {
                        case "Everybody folded":return minimum_raise;
                        case "Somebody called":return minimum_raise;
                        case "Somebody raised":return call;
                    }
                };
                case "Early":{
                    switch (whatHappenedBeforeMe) {
                        case "Everybody folded":return 0;
                        case "Somebody called":return minimum_raise;
                        case "Somebody raised":return call;
                    }
                };
                case "Mid":{
                    switch (whatHappenedBeforeMe) {
                        case "Everybody folded":return minimum_raise;
                        case "Somebody called":return minimum_raise;
                        case "Somebody raised":return call;
                    }
                };
                case "Late":{
                    switch (whatHappenedBeforeMe) {
                        case "Everybody folded":return minimum_raise;
                        case "Somebody called":return minimum_raise;
                        case "Somebody raised":return call;
                    }
                };
                default:
                    return 0;
            }
        } else {
            return 0;
        }
    }

    //getters

    public String getWhatHappenedBeforeMe() {
        return whatHappenedBeforeMe;
    }

    public Integer getCall() {
        return call;
    }
    
    
    public PreFlopContoller getPreFlopController() {
        return preFlopController;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getMyPositionCat() {
        return myPositionCat;
    }

    public Card getCard1() {
        return card1;
    }

    public Card getCard2() {
        return card2;
    }

    public Integer getMinimum_raise() {
        return minimum_raise;
    }

    //setters
    public void setPreFlopController(PreFlopContoller preFlopController) {
        this.preFlopController = preFlopController;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setMyPositionCat(String myPositionCat) {
        this.myPositionCat = myPositionCat;
    }

    public void setCard1(Card card1) {
        this.card1 = card1;
    }

    public void setCard2(Card card2) {
        this.card2 = card2;
    }

    public void setMinimum_raise(Integer minimum_raise) {
        this.minimum_raise = minimum_raise;
    }

    public void setWhatHappenedBeforeMe(String whatHappenedBeforeMe) {
        this.whatHappenedBeforeMe = whatHappenedBeforeMe;
    }

    public void setCall(Integer call) {
        this.call = call;
    }

    
}
