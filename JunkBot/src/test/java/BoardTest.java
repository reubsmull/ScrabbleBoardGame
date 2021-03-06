import org.junit.jupiter.api.Test;
import scrabbleGame.gameModel.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
    BoardTest JUnit Test Class

    Purpose: Unit-Testing the Board Class of the scrabbleGame package.
    Summary: This class attempts to thoroughly test the Board Class and its methods to ensure they
             are robust.

    Team: JunkBot
    Members: Reuben Mulligan (18733589), Evan Spendlove (18492656), Cal Nolan(18355103)

    Author: Reuben Mulligan, Cal Nolan
    Version: 1.0.0
    Since: 21-02-2020
 */

public class BoardTest
{

    private int playMove(Board b, int startX, int startY, char[] word, String direction, boolean[] isHook)
    {
        ArrayList<Tile> tiles = new ArrayList<>();
        String moveWord = "";

        for(char letter : word)
        {
            tiles.add(Tile.getInstance(letter));
            moveWord += letter;
        }

        while(tiles.size() < 7)
        {
            tiles.add(Tile.getInstance('A'));
        }

        int hooks = 0;

        for(int i = 0; i < isHook.length; i++)
        {
            if(isHook[i])
            {
                hooks++;
            }
        }

        Frame frame = new Frame(tiles);

        Player p1 = new Player("Aang", 0, frame);

        ArrayList<Placement> plays = new ArrayList<>();

        int playDirection = -1;

        if(direction.equals("across")) // Play across, increment x
        {
            for(int i = 0; i < word.length; i++)
            {
                if(!isHook[i]) // If not a hook
                {
                    plays.add(new Placement(startX, startY, word[i]));
                }

                startX++;
            }
            playDirection = 0;
        }
        else // Play vertical, increment y
        {
            for(int i = 0; i < word.length; i++)
            {
                if(!isHook[i]) // If not a hook
                {
                    plays.add(new Placement(startX, startY, word[i]));
                }

                startY++;
            }

            playDirection = 1;
        }

        return b.placeWord(new Move(plays, moveWord, playDirection), p1);
    }

    private int playFirstMove(Board b, int startX, int startY, char[] word, String direction, boolean[] isHook)
    {
        ArrayList<Tile> tiles = new ArrayList<>();
        String moveWord = "";

        for(char letter : word)
        {
            tiles.add(Tile.getInstance(letter));
            moveWord += letter;
        }

        while(tiles.size() < 7)
        {
            tiles.add(Tile.getInstance('A'));
        }

        int hooks = 0;

        for(int i = 0; i < isHook.length; i++)
        {
            if(isHook[i])
            {
                hooks++;
            }
        }

        Frame frame = new Frame(tiles);

        Player p1 = new Player("Aang", 0, frame);

        ArrayList<Placement> plays = new ArrayList<>();

        int playDirection = -1;

        if(direction.equals("across")) // Play across, increment x
        {
            for(int i = 0; i < word.length; i++)
            {
                if(!isHook[i]) // If not a hook
                {
                    plays.add(new Placement(startX, startY, word[i]));
                }

                startX++;
            }
            playDirection = 0;
        }
        else // Play vertical, increment y
        {
            for(int i = 0; i < word.length; i++)
            {
                if(!isHook[i]) // If not a hook
                {
                    plays.add(new Placement(startX, startY, word[i]));
                }

                startY++;
            }

            playDirection = 1;
        }

        return b.placeFirstWord(new Move(plays, moveWord, playDirection), p1);
    }

    @Test
    public void testFullConstructor(){
        try{
            Board test = new Board();
            assertEquals("3W _ _ 2L _ _ _ 3W _ _ _ 2L _ _ 3W \n" +
                    "_ 2W _ _ _ 3L _ _ _ 3L _ _ _ 2W _ \n" +
                    "_ _ 2W _ _ _ 2L _ 2L _ _ _ 2W _ _ \n" +
                    "2L _ _ 2W _ _ _ 2L _ _ _ 2W _ _ 2L \n" +
                    "_ _ _ _ 2W _ _ _ _ _ 2W _ _ _ _ \n" +
                    "_ 3L _ _ _ 3L _ _ _ 3L _ _ _ 3L _ \n" +
                    "_ _ 2L _ _ _ 2L _ 2L _ _ _ 2L _ _ \n" +
                    "3W _ _ 2L _ _ _ S _ _ _ 2L _ _ 3W \n" +
                    "_ _ 2L _ _ _ 2L _ 2L _ _ _ 2L _ _ \n" +
                    "_ 3L _ _ _ 3L _ _ _ 3L _ _ _ 3L _ \n" +
                    "_ _ _ _ 2W _ _ _ _ _ 2W _ _ _ _ \n" +
                    "2L _ _ 2W _ _ _ 2L _ _ _ 2W _ _ 2L \n" +
                    "_ _ 2W _ _ _ 2L _ 2L _ _ _ 2W _ _ \n" +
                    "_ 2W _ _ _ 3L _ _ _ 3L _ _ _ 2W _ \n" +
                    "3W _ _ 2L _ _ _ 3W _ _ _ 2L _ _ 3W \n", test.toString());
        }
        catch(Exception ex)
        {
            fail("Board should initialise properly and not throw any errors");
        }
    }

    @Test
    public void testFullPlay()
    {
        Board test = new Board();
        ArrayList<Tile> x = new ArrayList<>();
        for(int ew=0;ew<5;ew++)
            x.add(Tile.getInstance('Q'));
        x.add(Tile.getInstance('U'));
        x.add(Tile.getInstance('E'));
        Frame q = new Frame(x);
        Player one = new Player("ree", 0, q);

        Placement t = new Placement(7, 7, 'Q');
        Placement t1 = new Placement(8, 7, 'u');
        Placement t2 = new Placement(9, 7, 'e');
        List<Placement> y= new ArrayList<>();
        y.add(t);
        y.add(t1);
        y.add(t2);
        assertEquals(2, test.placeFirstWord(new Move(y, "QUE", 0), one));
    }

    @Test
    public void testGetBoard(){
        try{
            Board test1 = new Board();
            Board test2 = new Board();

            assertEquals(test1.toString(), test2.toString());
            assertEquals("3W _ _ 2L _ _ _ 3W _ _ _ 2L _ _ 3W \n" +
                    "_ 2W _ _ _ 3L _ _ _ 3L _ _ _ 2W _ \n" +
                    "_ _ 2W _ _ _ 2L _ 2L _ _ _ 2W _ _ \n" +
                    "2L _ _ 2W _ _ _ 2L _ _ _ 2W _ _ 2L \n" +
                    "_ _ _ _ 2W _ _ _ _ _ 2W _ _ _ _ \n" +
                    "_ 3L _ _ _ 3L _ _ _ 3L _ _ _ 3L _ \n" +
                    "_ _ 2L _ _ _ 2L _ 2L _ _ _ 2L _ _ \n" +
                    "3W _ _ 2L _ _ _ S _ _ _ 2L _ _ 3W \n" +
                    "_ _ 2L _ _ _ 2L _ 2L _ _ _ 2L _ _ \n" +
                    "_ 3L _ _ _ 3L _ _ _ 3L _ _ _ 3L _ \n" +
                    "_ _ _ _ 2W _ _ _ _ _ 2W _ _ _ _ \n" +
                    "2L _ _ 2W _ _ _ 2L _ _ _ 2W _ _ 2L \n" +
                    "_ _ 2W _ _ _ 2L _ 2L _ _ _ 2W _ _ \n" +
                    "_ 2W _ _ _ 3L _ _ _ 3L _ _ _ 2W _ \n" +
                    "3W _ _ 2L _ _ _ 3W _ _ _ 2L _ _ 3W \n", test1.toString());
        }catch (Exception ex){
            fail("No exceptions thrown");
        }
    }

    @Test
    public void testGetStatus(){
        try{
            Board test = new Board();
            Board test2 = new Board();

            assertEquals(test.getStatus(), test2.getStatus());
        }catch(Exception ex){
            fail("No exceptions should be thrown");
        }
    }

    @Test
    public void testAddandGetWordsPlayed(){
        try{
            Board test = new Board();
            ArrayList<Placement> ply;
            ArrayList<String> check;
            check = new ArrayList<>();
            check.add("DUE");
            ply = new ArrayList<>();

            Placement p1 = new Placement(8, 6, 'D');
            Placement p2 = new Placement(8, 8, 'E');

            ply.add(p1);
            ply.add(p2);

            Move m = new Move(ply, "DUE", 1);
            test.addWordPlayed(m);
            assertEquals(check, test.getWordsPlayed());

        }catch(Exception ex){
            fail("No exceptions should be thrown");
        }
    }

    @Test
    public void testResetBoard(){
        try
        {
            Board test = new Board();
            Board test2 = new Board();

            char[] word1 = {'Q', 'U', 'E'};
            boolean[] isHook1 = {false, false, false};
            assertEquals(2, playMove(test, 7, 7, word1, "across", isHook1));

            test.resetBoard();
            assertEquals(test.toString(), test2.toString());
            assertEquals(test.getStatus(), test2.getStatus());
            assertEquals(test.getWordsPlayed(),test2.getWordsPlayed());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            fail("No exceptions should be thrown");
        }
    }

    @Test
    public void testAddWord(){

        Board test = new Board();

        ArrayList<Tile> x = new ArrayList<Tile>();
        for(int ew=0;ew<7;ew++)
            x.add(Tile.getInstance('Q'));

        Frame q = new Frame(x);
        Player one = new Player("ree", 0, q);
        Placement t = new Placement(7, 7, 'Q');

        List<Placement> y= new ArrayList<>();
        y.add(t);

        Move e = new Move(y, "Q", 0);

        System.out.println(test.placeFirstWord(e, one));

        assertEquals("Q", test.getWordsPlayed().get(0));
        assertEquals('Q', test.getBoard()[7][7].getTile().character());
    }

    @Test
    public void testHooked()
    {
        Board test = new Board();
        ArrayList<Tile> x = new ArrayList<>();
        for(int ew=0;ew<5;ew++)
            x.add(Tile.getInstance('Q'));
        x.add(Tile.getInstance('U'));
        x.add(Tile.getInstance('E'));
        Frame q = new Frame(x);
        Player one = new Player("ree", 0, q);

        Placement t = new Placement(7, 7, 'Q');
        Placement t1 = new Placement(8, 7, 'u');
        Placement t2 = new Placement(9, 7, 'e');
        List<Placement> y= new ArrayList<>();
        y.add(t);
        y.add(t1);
        y.add(t2);
        assertEquals(2, test.placeFirstWord(new Move(y, "QUE", 0), one));
        assertEquals("3W _ _ 2L _ _ _ 3W _ _ _ 2L _ _ 3W \n" +
                "_ 2W _ _ _ 3L _ _ _ 3L _ _ _ 2W _ \n" +
                "_ _ 2W _ _ _ 2L _ 2L _ _ _ 2W _ _ \n" +
                "2L _ _ 2W _ _ _ 2L _ _ _ 2W _ _ 2L \n" +
                "_ _ _ _ 2W _ _ _ _ _ 2W _ _ _ _ \n" +
                "_ 3L _ _ _ 3L _ _ _ 3L _ _ _ 3L _ \n" +
                "_ _ 2L _ _ _ 2L _ 2L _ _ _ 2L _ _ \n" +
                "3W _ _ 2L _ _ _ Q U E _ 2L _ _ 3W \n" +
                "_ _ 2L _ _ _ 2L _ 2L _ _ _ 2L _ _ \n" +
                "_ 3L _ _ _ 3L _ _ _ 3L _ _ _ 3L _ \n" +
                "_ _ _ _ 2W _ _ _ _ _ 2W _ _ _ _ \n" +
                "2L _ _ 2W _ _ _ 2L _ _ _ 2W _ _ 2L \n" +
                "_ _ 2W _ _ _ 2L _ 2L _ _ _ 2W _ _ \n" +
                "_ 2W _ _ _ 3L _ _ _ 3L _ _ _ 2W _ \n" +
                "3W _ _ 2L _ _ _ 3W _ _ _ 2L _ _ 3W \n", test.toString());

        Placement o = new Placement(8, 6, 'D');
        Placement o1 = new Placement(8, 8, 'E');

        x.clear();
        for(int ew=0;ew<5;ew++)
            x.add(Tile.getInstance('Q'));
        x.add(Tile.getInstance('D'));
        x.add(Tile.getInstance('E'));
        one.setFrame(new Frame(x));

        List<Placement> z = new ArrayList<>();
        z.add(o);
        z.add(o1);

        assertEquals(2, test.placeWord(new Move(z, "DUE", 1), one));
        assertEquals("3W _ _ 2L _ _ _ 3W _ _ _ 2L _ _ 3W \n" +
                "_ 2W _ _ _ 3L _ _ _ 3L _ _ _ 2W _ \n" +
                "_ _ 2W _ _ _ 2L _ 2L _ _ _ 2W _ _ \n" +
                "2L _ _ 2W _ _ _ 2L _ _ _ 2W _ _ 2L \n" +
                "_ _ _ _ 2W _ _ _ _ _ 2W _ _ _ _ \n" +
                "_ 3L _ _ _ 3L _ _ _ 3L _ _ _ 3L _ \n" +
                "_ _ 2L _ _ _ 2L _ D _ _ _ 2L _ _ \n" +
                "3W _ _ 2L _ _ _ Q U E _ 2L _ _ 3W \n" +
                "_ _ 2L _ _ _ 2L _ E _ _ _ 2L _ _ \n" +
                "_ 3L _ _ _ 3L _ _ _ 3L _ _ _ 3L _ \n" +
                "_ _ _ _ 2W _ _ _ _ _ 2W _ _ _ _ \n" +
                "2L _ _ 2W _ _ _ 2L _ _ _ 2W _ _ 2L \n" +
                "_ _ 2W _ _ _ 2L _ 2L _ _ _ 2W _ _ \n" +
                "_ 2W _ _ _ 3L _ _ _ 3L _ _ _ 2W _ \n" +
                "3W _ _ 2L _ _ _ 3W _ _ _ 2L _ _ 3W \n", test.toString());
    }

    @Test
    public void testFirstMove()
    {
        Board test = new Board();

        char[] word1 = {'Q', 'U', 'E'};
        boolean[] isHook1 = {false, false, false};
        assertEquals(2, playFirstMove(test, 7, 7, word1, "across", isHook1));

        assertEquals("3W _ _ 2L _ _ _ 3W _ _ _ 2L _ _ 3W \n" +
                "_ 2W _ _ _ 3L _ _ _ 3L _ _ _ 2W _ \n" +
                "_ _ 2W _ _ _ 2L _ 2L _ _ _ 2W _ _ \n" +
                "2L _ _ 2W _ _ _ 2L _ _ _ 2W _ _ 2L \n" +
                "_ _ _ _ 2W _ _ _ _ _ 2W _ _ _ _ \n" +
                "_ 3L _ _ _ 3L _ _ _ 3L _ _ _ 3L _ \n" +
                "_ _ 2L _ _ _ 2L _ 2L _ _ _ 2L _ _ \n" +
                "3W _ _ 2L _ _ _ Q U E _ 2L _ _ 3W \n" +
                "_ _ 2L _ _ _ 2L _ 2L _ _ _ 2L _ _ \n" +
                "_ 3L _ _ _ 3L _ _ _ 3L _ _ _ 3L _ \n" +
                "_ _ _ _ 2W _ _ _ _ _ 2W _ _ _ _ \n" +
                "2L _ _ 2W _ _ _ 2L _ _ _ 2W _ _ 2L \n" +
                "_ _ 2W _ _ _ 2L _ 2L _ _ _ 2W _ _ \n" +
                "_ 2W _ _ _ 3L _ _ _ 3L _ _ _ 2W _ \n" +
                "3W _ _ 2L _ _ _ 3W _ _ _ 2L _ _ 3W \n", test.toString());

        test.resetBoard();

        char[] word = {'D', 'U', 'E'};
        boolean[] isHook = {false, false, false};

        assertEquals(-1, playFirstMove(test, 9, 7, word, "down", isHook));
    }

    @Test
    public void testContainsLetters()
    {
        Board test = new Board();
        ArrayList<Tile> x = new ArrayList<>();
        for(int ew=0;ew<5;ew++)
            x.add(Tile.getInstance('Q'));
        x.add(Tile.getInstance('U'));
        x.add(Tile.getInstance('E'));
        Frame q = new Frame(x);
        Player one = new Player("ree", 0, q);

        Placement t = new Placement(7, 7, 'Q');
        Placement t1 = new Placement(8, 7, 'u');
        Placement t2 = new Placement(9, 7, 'e');
        List<Placement> y= new ArrayList<>();
        y.add(t);
        y.add(t1);
        y.add(t2);
        assertEquals(2, test.placeFirstWord(new Move(y, "QUE", 0), one));

        Placement o = new Placement(8, 6, 'D');
        Placement o1 = new Placement(8, 8, 'E');

        x.clear();
        for(int ew=0;ew<5;ew++)
            x.add(Tile.getInstance('Q'));
        x.add(Tile.getInstance('D'));
        x.add(Tile.getInstance('Z'));
        one.setFrame(new Frame(x));

        List<Placement> z = new ArrayList<>();
        z.add(o);
        z.add(o1);

        assertEquals(-1, test.placeWord(new Move(z, "DUE", 1), one));
    }

    @Test
    public void testConnected()
    {
        Board test = new Board();
        ArrayList<Tile> x = new ArrayList<>();
        for(int ew=0;ew<5;ew++)
            x.add(Tile.getInstance('Q'));
        x.add(Tile.getInstance('U'));
        x.add(Tile.getInstance('E'));
        Frame q = new Frame(x);
        Player one = new Player("ree", 0, q);

        Placement t = new Placement(7, 7, 'Q');
        Placement t1 = new Placement(8, 7, 'u');
        Placement t2 = new Placement(9, 7, 'e');
        List<Placement> y= new ArrayList<>();
        y.add(t);
        y.add(t1);
        y.add(t2);
        assertEquals(2, test.placeFirstWord(new Move(y, "QUE", 0), one));

        Placement o = new Placement(8, 6, 'D');
        Placement o1 = new Placement(8, 9, 'E');

        x.clear();
        for(int ew=0;ew<5;ew++)
            x.add(Tile.getInstance('Q'));
        x.add(Tile.getInstance('D'));
        x.add(Tile.getInstance('E'));
        one.setFrame(new Frame(x));

        List<Placement> z = new ArrayList<>();
        z.add(o);
        z.add(o1);

        test.printBoard();

        int result = test.placeWord(new Move(z, "DUE", 1), one);

        test.printBoard();

        assertEquals(-1, result);
    }

    @Test
    public void testInLine(){

        Board test = new Board();
        ArrayList<Tile> x = new ArrayList<>();
        for(int ew=0;ew<5;ew++)
            x.add(Tile.getInstance('Q'));
        x.add(Tile.getInstance('U'));
        x.add(Tile.getInstance('E'));
        Frame q = new Frame(x);
        Player one = new Player("ree", 0, q);

        Placement t = new Placement(7, 7, 'Q');
        Placement t1 = new Placement(8, 8, 'u');
        Placement t2 = new Placement(9, 7, 'e');
        List<Placement> y= new ArrayList<>();
        y.add(t);
        y.add(t1);
        y.add(t2);
        assertEquals(-1, test.placeFirstWord(new Move(y, "QUE", 0), one));
    }

    @Test
    public void testDuplicateLettersInFrame()
    {
        Board test = new Board();
        ArrayList<Tile> x = new ArrayList<>();
        for(int ew=0;ew<5;ew++)
            x.add(Tile.getInstance('Q'));
        x.add(Tile.getInstance('U'));
        x.add(Tile.getInstance('E'));
        Frame q = new Frame(x);
        Player one = new Player("ree", 0, q);

        Placement t = new Placement(7, 7, 'Q');
        Placement t1 = new Placement(8, 7, 'Q');
        Placement t2 = new Placement(9, 7, 'e');
        List<Placement> y= new ArrayList<>();
        y.add(t);
        y.add(t1);
        y.add(t2);
        assertEquals(2, test.placeFirstWord(new Move(y, "QQE", 0), one));
    }

    /*
        Goal: To test that the isValidPosition catches out of bounds plays.
        Testing Method: Test the isValidPosition with an invalid and valid move.
     */
    @Test
    public void testIsValidPosition()
    {
        try
        {
            Board board = new Board();

            char[] letters = {'C', 'A', 'R', 'D'};
            ArrayList<Tile> tiles = new ArrayList<>();

            for(char letter : letters)
            {
                tiles.add(Tile.getInstance(letter));
            }

            tiles.add(Tile.T);
            tiles.add(Tile.T);
            tiles.add(Tile.T);

            Frame frame = new Frame(tiles);

            Player player = new Player("Natsu", 0, frame);

            ArrayList<Placement> plays = new ArrayList<>();
            int x = 7, y = 7;

            for(char letter : letters)
            {
                plays.add(new Placement(x++, y, letter));
            }

            Move m = new Move(plays, "CARD", 0);

            assertEquals(2, board.placeFirstWord(m, player));

            // Test that you cannot overlap words

            Frame frame2 = new Frame(tiles);

            Player player2 = new Player("Gray", 0, frame2);

            ArrayList<Placement> plays3 = new ArrayList<>();
            int x3 = 10, y3 = 7;

            for(char letter : letters)
            {
                plays3.add(new Placement(x3++, y3, letter));
            }

            Move m3 = new Move(plays, "CARD", 0);
            assertEquals(-1, board.placeWord(m3, player2));
        }
        catch(Exception ex)
        {
            fail("No exception should be thrown when placing a valid or invalid word.");
        }
    }

    @Test
    public void testContainsBlanks()
    {
        Board test = new Board();
        ArrayList<Tile> x = new ArrayList<>();
        for(int ew=0;ew<5;ew++)
            x.add(Tile.getInstance('Q'));
        x.add(Tile.getInstance('#'));
        x.add(Tile.getInstance('E'));
        Frame q = new Frame(x);
        Player one = new Player("ree", 0, q);

        Placement t = new Placement(7, 7, 'Q');
        Placement t1 = new Placement(8, 7, 'u');
        Placement t2 = new Placement(9, 7, 'e');
        List<Placement> y= new ArrayList<>();
        y.add(t);
        y.add(t1);
        y.add(t2);
        assertEquals(2, test.placeFirstWord(new Move(y, "QUE", 0), one));

        Placement o = new Placement(8, 6, 'D');
        Placement o1 = new Placement(8, 8, 'E');

        x.clear();
        for(int ew=0;ew<5;ew++)
            x.add(Tile.getInstance('Q'));
        x.add(Tile.getInstance('#'));
        x.add(Tile.getInstance('#'));
        one.setFrame(new Frame(x));

        List<Placement> z = new ArrayList<>();
        z.add(o);
        z.add(o1);

        int result = test.placeWord(new Move(z, "DUE", 1), one);

        assertEquals(2, result);
    }

    @Test
    public void testContainsHooks()
    {
        Board test = new Board();
        ArrayList<Tile> x = new ArrayList<>();
        for(int ew=0;ew<5;ew++)
            x.add(Tile.getInstance('Q'));
        x.add(Tile.getInstance('U'));
        x.add(Tile.getInstance('E'));
        Frame q = new Frame(x);
        Player one = new Player("ree", 0, q);

        Placement t = new Placement(7, 7, 'Q');
        Placement t1 = new Placement(8, 7, 'u');
        Placement t2 = new Placement(9, 7, 'e');
        List<Placement> y= new ArrayList<>();
        y.add(t);
        y.add(t1);
        y.add(t2);
        assertEquals(2, test.placeFirstWord(new Move(y, "QUE", 0), one));

        Placement o = new Placement(8, 6, 'D');
        Placement o1 = new Placement(8, 8, 'E');

        x.clear();
        for(int ew=0;ew<5;ew++)
            x.add(Tile.getInstance('Q'));
        x.add(Tile.getInstance('D'));
        x.add(Tile.getInstance('E'));
        one.setFrame(new Frame(x));

        List<Placement> z = new ArrayList<>();
        z.add(o);
        z.add(o1);

        int result = test.placeWord(new Move(z, "DUE", 1), one);

        assertEquals(2, result);
    }

    @Test
    public void testHookedHorizontal()
    {
        try
        {
            Board test = new Board();

            char[] word1 = {'Q', 'U', 'E'};
            boolean[] isHook1 = {false, false, false};
            assertEquals(2, playFirstMove(test, 7, 7, word1, "across", isHook1));

            char[] word = {'D', 'U', 'E'};
            boolean[] isHook = {false, true, false};

            assertEquals(2, playMove(test, 8, 6, word, "down", isHook));

            char[] word2 = {'D', 'U', 'D', 'E', 'S'};
            boolean[] isHook2 = {true, false, false, false, false};

            assertEquals(2, playMove(test, 8, 6, word2, "across", isHook2));

            char[] word3 = {'S', 'E', 'E'};
            boolean[] isHook3 = {false, true, false};

            assertEquals(2, playMove(test, 7, 8, word3, "across", isHook3));

            test.printBoard();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}

