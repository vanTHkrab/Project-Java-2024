package Games.TicTacToe;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    // กำหนดค่าเริ่มต้นให้กระดานว่าง
    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // แสดงกระดานเกม
    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // ตรวจสอบว่ากระดานเต็มหรือยัง (กรณีเสมอ)
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    // ตรวจสอบผู้ชนะ
    public boolean checkForWin() {
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }

    // ตรวจสอบว่าแถวใดแถวหนึ่งมีผู้ชนะหรือไม่
    private boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
                return true;
            }
        }
        return false;
    }

    // ตรวจสอบว่าแนวตั้งใดแนวหนึ่งมีผู้ชนะหรือไม่
    private boolean checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
                return true;
            }
        }
        return false;
    }

    // ตรวจสอบว่าแนวทแยงใดแนวหนึ่งมีผู้ชนะหรือไม่
    private boolean checkDiagonalsForWin() {
        return ((checkRowCol(board[0][0], board[1][1], board[2][2]) == true) ||
                (checkRowCol(board[0][2], board[1][1], board[2][0]) == true));
    }

    // ตรวจสอบว่าเครื่องหมายในตำแหน่งที่กำหนดเหมือนกันทั้งหมดหรือไม่
    private boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
    }

    // เปลี่ยนผู้เล่น
    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // วางเครื่องหมาย X หรือ O ในตำแหน่งที่กำหนด
    public boolean placeMark(int row, int col) {
        if (row >= 0 && col >= 0 && row < 3 && col < 3) {
            if (board[row][col] == '-') {
                board[row][col] = currentPlayer;
                return true;
            }
        }
        return false;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }
}
