package game2048logic;

import game2048rendering.Side;
import static game2048logic.MatrixUtils.rotateLeft;
import static game2048logic.MatrixUtils.rotateRight;

/**
 * @author  Josh Hug
 */
public class GameLogic {
    /** Moves the given tile up as far as possible, subject to the minR constraint.
     *
     * @param board the current state of the board
     * @param r     the row number of the tile to move up
     * @param c -   the column number of the tile to move up
     * @param minR  the minimum row number that the tile can land in, e.g.
     *              if minR is 2, the moving tile should move no higher than row 2.
     * @return      if there is a merge, returns the 1 + the row number where the merge occurred.
     *              if no merge occurs, then return 0.
     */
    public static int moveTileUpAsFarAsPossible(int[][] board, int r, int c, int minR) {
        //TODO:fill this in ...
        int value = board[r][c];
        // 如果 tile 已经在 minR 或更小的行，不能移动
        if (r < minR) {
            return 0;
        }
        board[r][c] = 0;
        int targetRow = r; // 默认目标位置是原位置（如果不能移动）
        for (int i = r - 1; i >= minR; i--) {
            if (board[i][c] == 0) {
                // 空位，可以继续向上移动
                targetRow = i;
            } else if (board[i][c] == value) {
                // 遇到相同值的 tile，可以合并
                board[i][c] = value * 2; // 合并
                return 1 + i; // 返回 1 + 合并发生的行号
            } else {
                // 遇到不同值的 tile，停在它下方
                targetRow = i + 1;
                break;
            }
        }
        board[targetRow][c] = value;
        return 0; // 没有合并
    }

    /**
     * Modifies the board to simulate the process of tilting column c
     * upwards.
     *
     * @param board     the current state of the board
     * @param c         the column to tilt up.
     */
    public static void tiltColumn(int[][] board, int c) {
        int rows = board.length;
        int minR = 0;
        for (int i=0;i<rows;i+=1)
        {
            minR=moveTileUpAsFarAsPossible(board,i,c,minR);
        }
        return;

    }

    /**
     * Modifies the board to simulate tilting all columns upwards.
     *
     * @param board     the current state of the board.
     */
    public static void tiltUp(int[][] board) {
        int size = board.length;
        for(int i=0;i<size;i++)
        {
            tiltColumn(board,i);
        }
        return;
    }

    /**
     * Modifies the board to simulate tilting the entire board to
     * the given side.
     *
     * @param board the current state of the board
     * @param side  the direction to tilt
     */
    public static void tilt(int[][] board, Side side) {
        // TODO: fill this in in task 7
        if (side == Side.EAST) {
            rotateLeft(board);
            tiltUp(board);
            rotateRight(board);
            return;
        } else if (side == Side.WEST) {
            rotateRight(board);
            tiltUp(board);
            rotateLeft(board);
            return;
        } else if (side == Side.SOUTH) {
            rotateRight(board);
            rotateRight(board);
            tiltUp(board);
            rotateLeft(board);
            rotateLeft(board);
            return;
        } else {
            tiltUp(board);
            return;
        }
    }
}
