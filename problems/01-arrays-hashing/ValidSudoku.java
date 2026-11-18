/**
 * Valid Sudoku 
 * Pattern: hashing - Contains Duplicate applied to 27 groups (9 rows, 9 cols, 9 boxes)
 * Approach: one HashSet per row, column and box; for each filled cell,
 *           !set.add(val) on any of its three sets means a duplicate -> false
 * Box index: (row / 3) * 3 + (col / 3)
 *            row / 3 = which band of rows (0-2); each band holds 3 boxes, so * 3;
 *            col / 3 = which box within that band. Same idea as row * width + col.
 * Time: O(1) for a fixed 9x9 board (O(n^2) for an n x n board)
 * Space: O(1) for 9x9 (O(n^2) for n x n)
 * Alternatives: three separate passes (rows, then cols, then boxes) - same complexity, more code;
 *               boolean[9][9] arrays instead of sets - faster in practice
 * Note: creating Set[] makes null slots; fill each with new HashSet<>() first.
 *       || short-circuits: later add() calls don't run once one fails (fine here, we return).
 */
import java.util.HashSet;
import java.util.Set;

class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        Set<Character>[] rows = new HashSet[9];
        Set<Character>[] cols = new HashSet[9];
        Set<Character>[] boxes = new HashSet[9];

        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                char val = board[row][col];
                if (val == '.')
                    continue;
                int box = (row / 3) * 3 + (col / 3);

                if (!(rows[row].add(val)) || !(cols[col].add(val)) || !(boxes[box].add(val))) {
                    return false;
                }
            }
        }

        return true;
    }
}
