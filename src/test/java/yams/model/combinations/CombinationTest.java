package yams.model.combinations;

import org.junit.jupiter.api.Test;
import yams.model.game.Board;
import yams.model.game.DiceModel;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CombinationTest {

    // Méthode utilitaire pour créer rapidement un plateau avec les dés souhaités
    private Board createBoard(int... values) {
        Board board = mock(Board.class);
        DiceModel[] dices = new DiceModel[values.length];
        
        for (int i = 0; i < values.length; i++) {
            DiceModel dice = mock(DiceModel.class);
            when(dice.value()).thenReturn(values[i]);
            dices[i] = dice;
        }
        
        when(board.dices()).thenReturn(Arrays.asList(dices));
        return board;
    }

    @Test
    void testYahtzeeValid() {
        Board board = createBoard(5, 5, 5, 5, 5);
        Yahtzee yahtzee = new Yahtzee();
        
        assertTrue(yahtzee.valid(board), "Le Yahtzee doit être valide pour 5 dés identiques");
        assertEquals(50, yahtzee.score(board), "Le Yahtzee donne toujours 50 points");
    }

    @Test
    void testYahtzeeInvalid() {
        Board board = createBoard(5, 5, 5, 5, 4);
        Yahtzee yahtzee = new Yahtzee();
        
        assertFalse(yahtzee.valid(board), "Le Yahtzee est invalide si les dés sont différents");
        assertEquals(0, yahtzee.score(board), "Un Yahtzee invalide donne 0 point");
    }

    @Test
    void testFullHouseValid() {
        Board board = createBoard(2, 2, 3, 3, 3);
        FullHouse fullHouse = new FullHouse();
        
        assertTrue(fullHouse.valid(board), "Le Full (Full House) doit être valide (brelan + paire)");
        assertEquals(25, fullHouse.score(board), "Le Full donne 25 points");
    }

    @Test
    void testLargeStraightValid() {
        Board board = createBoard(2, 3, 4, 5, 6);
        LargeStraight straight = new LargeStraight();
        
        assertTrue(straight.valid(board), "La Grande Suite (Large Straight) doit être valide pour 2-3-4-5-6");
        assertEquals(40, straight.score(board), "La Grande Suite donne 40 points");
    }
}