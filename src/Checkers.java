import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Checkers
{
    final static int SIZE = 8;
    static char[][] checker_board;
    static int white;
    static int black;
    static char getMove;

//    private static boolean isKing;


    public static void main(String args[]) throws IOException
    {
        System.out.println("***************************************************************");
        System.out.println("\t\tWELCOME TO THE GAME OF CHECKERS!");
        System.out.println("***************************************************************");


        Checkers game = new Checkers();

        game.printBoard();


        while (!gameOver()) {

            getNextMove();
            game.printBoard();
        }

        System.out.println("*******************************************************");
        System.out.println("THE WINNER IS: " + winnerIs() + "!");
        System.out.println("*******************************************************");

    }

    private Checkers()
    {

        checker_board = new char[SIZE][SIZE];
        white = 12;
        black = 12;
        getMove = 'W';


        int i;
        int j;

        for (i=0;i<SIZE;i++)
            for (j=0;j<SIZE;j++)
                checker_board[i][j] = ' ';

        for (i=1;i<SIZE;i+=2) {
            checker_board[i][1] = 'W';
            checker_board[i][5] = 'B';
            checker_board[i][7] = 'B';
        }
        for (i=0;i<SIZE;i+=2) {
            checker_board[i][0] = 'W';
            checker_board[i][2] = 'W';
            checker_board[i][6] = 'B';
        }
    }


    private void printBoard() {
        int i,j;
        System.out.println("  1 2 3 4 5 6 7 8 x");
        System.out.println("  -----------------");
        for (i=0;i<SIZE;i++) {
            System.out.print((i+1) + " ");
            System.out.print("|" );
            for (j=0;j<SIZE;j++) {
                System.out.print(checker_board[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println("y");
    }


    private static void getNextMove()
    {
        try
        {
            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);

            if (getMove=='W')
                System.out.println("White's TURN");
            else
                System.out.println("BLACK's TURN");

            boolean moved = false;

            while (!moved) {

                System.out.println("Enter as a 2-digit number to locate a piece to move. First X, then Y.");

                int movefrom = Integer.parseInt(br.readLine());

                System.out.print("Move to?");

                int moveto = Integer.parseInt(br.readLine());

                // Checks to see if move is valid, if so, executes it.
                if (validMove(movefrom,moveto)) {
                    executeMove(movefrom,moveto);
                    moved = true;
                }
                else
                    System.out.println("That was an invalid move, try again.");
            }

            if (getMove == 'W')
                getMove = 'B';
            else
                getMove = 'W';
        }
        catch(Exception e)
        {
            System.out.println("ERROR! " +e.getMessage());
        }


    }

    private static boolean validMove(int moveFrom, int moveTo) {


        int xfrom = moveFrom/10 - 1;
        int yfrom = moveFrom%10 - 1;
        int xto = moveTo/10 - 1;
        int yto = moveTo%10 - 1;


        if (xfrom < 0 || xfrom > 7 || yfrom < 0 || yfrom > 7 ||
                xto < 0 || xto > 7 || yto < 0 || yto > 7)
            return false;


        else if (checker_board[xfrom][yfrom]==getMove && checker_board[xto][yto]==' ') {


            if (Math.abs(xfrom-xto)==1) {
                if ((getMove == 'W') && (yto - yfrom == 1))
                    return true;
                else if ((getMove == 'B') && (yto - yfrom == -1))
                    return true;
            }



            else if (Math.abs(xfrom-xto)==2) {
                if (getMove == 'W' && (yto - yfrom == 2) &&
                        checker_board[(xfrom+xto)/2][(yfrom+yto)/2] == 'B')
                    return true;
                else if (getMove == 'B' && (yto - yfrom == -2) &&
                        checker_board[(xfrom+xto)/2][(yfrom+yto)/2] == 'W')
                    return true;
            }
        }

        return false;
    }


    private static void executeMove(int moveFrom, int moveTo) {

        int xfrom = moveFrom/10 - 1;
        int yfrom = moveFrom%10 - 1;
        int xto = moveTo/10 - 1;
        int yto = moveTo%10 - 1;


        checker_board[xfrom][yfrom] = ' ';
        checker_board[xto][yto] = getMove;


//        if (xto == 8 && getMove == 'W')
//            checker_board[xto][yto] = 'K';
//        else if (xto == 1 && getMove == 'B')
//            checker_board[xto][yto] = 'k';


        if (Math.abs(xto - xfrom) == 2) {
            checker_board[(xfrom+xto)/2][(yfrom+yto)/2] = ' ';
            if (getMove == 'W')
                white--;
            else
                black--;
        }

    }


    private static boolean gameOver() {
        return (white == 0 || black == 0);
    }


    private static String winnerIs() {
        if (black == 0)
            return "white";
        else
            return "black";
    }
}
