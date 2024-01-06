public class Tetromino {
    private final int[][][] tetromino;
    private final Tetris tetris;
    private final int colour;
    private int posY;
    private int rotation = 0;
    private int posYofBottomest;
    private int mostLeft;
    private int mostRight;
    private int posX;

    public Tetromino(Tetris t, int[][][] tetro, int x, int y, int c) {
        tetris = t;
        tetromino = tetro;
        posX = x;
        posY = y;
        colour = c;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getRotation() {
        return rotation;
    }

    public int getColour() {
        return colour;
    }

    public int getMostLeft() {
        return mostLeft;
    }

    public int getMostRight() {
        return mostRight;
    }

    public int[][][] getTetromino() {
        return tetromino;
    }

    public int[][] rotate(int[][] board, Tetromino currentTetromino) {
        int originalRotation = rotation;
        int originalPosX = posX;

        if (currentTetromino instanceof O_Tetromino) {
            return board;
        }

        findValues();
        // Erase itself on the board
        erase(board);

        if (posX == -2 || posX == -1 || posX == 7 && currentTetromino instanceof I_Tetromino || posX == 8) {
            if (currentTetromino instanceof I_Tetromino) {
                if (posX == -1) posX++;
                if (posX == 7) posX--;
                if (posX == -2) posX += 2;
                if (posX == 8) posX -= 2;

            } else {
                if (posX == -1) posX++;
                if (posX == 8) posX--;
            }
            if (!checkRotation(originalRotation, board)) {
                // If no rotation possible
                if (currentTetromino instanceof I_Tetromino) {
                    if (originalPosX == -1) posX--;
                    if (originalPosX == 7) posX++;
                    if (originalPosX == -2) posX -= 2;
                    if (originalPosX == 8) posX += 2;

                } else {
                    if (originalPosX == -1) {
                        posX--;
                    }
                    if (originalPosX == 8) {
                        posX++;
                    }
                }
                rotation = originalRotation;
            }
            putOnBoard(board);
            return board;
        } else {
            if (posY == 17 || posY == 18) {
                if (currentTetromino instanceof I_Tetromino) {
                    if (posY == 17) posY--;
                    if (posY == 18) posY -= 2;
                } else {
                    posY--;
                }
                if (!checkRotation(originalRotation, board)) {
                    if (posY == 17) posY++;
                    if (posY == 18) posY += 2;
                }
                putOnBoard(board);
                return board;
            } else {
                if (checkRotation(originalRotation, board)) {
                    putOnBoard(board);
                    return board;

                } else {
                    posX--; // Move Shape left and test if it can rotate
                    if (checkRotation(originalRotation, board)) {
                        putOnBoard(board);
                        return board;
                    }
                    // Still can not rotate when moved left
                    else {
                        posX += 2; // Move Shape Right and test if it can rotate
                        if (checkRotation(originalRotation, board)) {
                            putOnBoard(board);
                            return board;

                        } else {
                            posX--; // Reset x position
                            posY--; // Move Shape Up
                            if (checkRotation(originalRotation, board)) {
                                putOnBoard(board);
                                return board;
                            }
                        }
                    }
                }
            }
        }
        rotation = originalRotation;
        posY++;
        putOnBoard(board);
        return board;
    }

    // Puts the Tetromino on the integer array board
    public void putOnBoard(int[][] board) {
        for (int i = 0; i < tetromino[0].length; i++) {
            for (int j = 0; j < tetromino[0].length; j++) {
                if (board[posY + i][posX + j] == 0 && tetromino[rotation][i][j] != 0)
                    board[posY + i][posX + j] = tetromino[rotation][i][j];
            }
        }
    }

    // Checks if it can rotate by cycling through the rotations
    public boolean checkRotation(int originalRotation, int[][] board) {
        boolean canRotate;
        // Make sure it doesn't go out of bounds
        if (mostRight + posX > 9 || mostLeft + posX < 0) {
            return false;
        }
        // Cycles through the rotations
        for (int i = 0; i < 3; i++) {
            canRotate = true;
            rotation = originalRotation;
            rotation++;
            if (rotation == tetromino.length) rotation = 0;
            for (int j = 0; j < tetromino[0].length; j++) {
                for (int k = 0; k < tetromino[0].length; k++) {
                    if (tetromino[rotation][j][k] != 0 && board[posY + j][posX + k] != 0) {
                        canRotate = false;
                        break;
                    }
                }
            }
            if (canRotate) {
                return true;
            }
        }
        return false;
    }

    public void erase(int[][] board) {
        // Erase the Tetromino on the board
        // Makes sure that it is not array out of bounds
        if (posX < 0) {
            for (int i = 0; i < posYofBottomest + 1; i++) {
                for (int j = mostLeft; j < tetromino[0].length; j++) {
                    if (board[posY + i][posX + j] != 0 && tetromino[rotation][i][j] != 0) {
                        board[posY + i][posX + j] = 0;
                    }
                }
            }
        } else {
            for (int i = 0; i < posYofBottomest + 1; i++) {
                for (int j = mostLeft; j < mostRight + 1; j++) {
                    if (posY > 17) {
                        if (board[posY + i - (posY - 17)][posX + j] != 0 && tetromino[rotation][i][j] != 0) {
                            board[posY + i - (posY - 17)][posX + j] = 0;
                        }
                    } else {
                        if (board[posY + i][posX + j] != 0 && tetromino[rotation][i][j] != 0) {
                            board[posY + i][posX + j] = 0;
                        }
                    }
                }
            }
        }
    }

    public void findValues() {
        // Find bottomest block
        for (int i = 0; i < tetromino[0].length; i++) {
            for (int j = 0; j < tetromino[0].length; j++) {
                if (tetromino[rotation][i][j] != 0) {
                    posYofBottomest = i;
                    break;
                }
            }
        }
        // Find the most left block
        for (int i = tetromino[0].length - 1; i >= 0; i--) {
            for (int j = 0; j < tetromino[0].length; j++) {
                if (tetromino[rotation][j][i] != 0) {
                    mostLeft = i;
                    break;
                }
            }
        }
        // Find the most right block
        for (int i = 0; i < tetromino[0].length; i++) {
            for (int j = 0; j < tetromino[0].length; j++) {
                if (tetromino[rotation][j][i] != 0) {
                    mostRight = i;
                    break;
                }
            }
        }
    }

    // Moves the Tetromino left, right, and down
    public int[][] move(int[][] board, int moveX, int moveY) {
        findValues();

        // Erase itself on the board
        erase(board);

        // Move Down
        if (moveY == 1) posY++;
        // Move Up
        if (moveY == -1) posY--;
        // Move Right
        if (moveX == 1) posX++;
        // move Left
        if (moveX == -1) posX--;

        // Checks if the piece is past the array bottom
        // When piece hits the bottom floor
        if (posY + posYofBottomest > 19) {
            posY--; // Reset posY
            // Add itself back onto the board

            // Make sure array is not out of bounds
            if (posX < 0) {
                for (int i = 0; i < posYofBottomest + 1; i++) {
                    for (int j = mostLeft; j < tetromino[0].length; j++) {
                        if (board[posY + i][posX + j] == 0 && tetromino[rotation][i][j] != 0) {
                            board[posY + i][posX + j] = colour;
                        }
                    }
                }
            } else {
                for (int i = 0; i < posYofBottomest + 1; i++) {
                    for (int j = 0; j < mostRight + 1; j++) {
                        if (board[posY + i][posX + j] == 0 && tetromino[rotation][i][j] != 0) {
                            board[posY + i][posX + j] = colour;
                        }
                    }
                }
            }
            tetris.clearLine();
            tetris.newTetromino();
            return board;
        }

        // If piece hits
        // left wall
        if (posX + mostLeft < 0) {
            posX++; // Resets position X
            for (int i = 0; i < posYofBottomest + 1; i++) {
                for (int j = mostLeft; j < mostRight + 1; j++) {
                    if (board[posY + i][posX + j] == 0 && tetromino[rotation][i][j] != 0) {
                        board[posY + i][posX + j] = colour;
                    }
                }
            }
            return board;
        }
        // If it hits right wall
        if (posX + mostRight > 9) {
            posX--; // Resets position X
            for (int i = 0; i < posYofBottomest + 1; i++) {
                for (int j = 0; j < mostRight + 1; j++) {
                    if (board[posY + i][posX + j] == 0 && tetromino[rotation][i][j] != 0) {
                        board[posY + i][posX + j] = colour;
                    }
                }
            }

            return board;
        }

        // Collision Detection for blocks
        for (int i = 0; i < tetromino[0].length; i++) {
            for (int j = mostLeft; j < mostRight + 1; j++) {
                // Checks if moving the shape hits a block
                if (tetromino[rotation][i][j] != 0 && board[posY + i][posX + j] != 0) {
                    // Resets the board to original
                    for (int k = 0; k < posYofBottomest + 1; k++) {
                        for (int l = mostLeft; l < mostRight + 1; l++) {
                            if (moveY == 1) {
                                if (tetromino[rotation][k][l] != 0 && board[posY + k - 1][posX + l] == 0) {
                                    board[posY + k - 1][posX + l] = colour;
                                }
                            }
                            if (moveX == 1) {
                                if (tetromino[rotation][k][l] != 0 && board[posY + k][posX + l - 1] == 0) {
                                    board[posY + k][posX + l - 1] = colour;
                                }
                            }
                            if (moveX == -1) {
                                if (tetromino[rotation][k][l] != 0 && board[posY + k][posX + l + 1] == 0) {
                                    board[posY + k][posX + l + 1] = colour;
                                }
                            }

                        }
                    }
                    if (moveY == 1) {
                        // When moved down and hits a block
                        tetris.clearLine();
                        tetris.newTetromino();
                    }
                    // Resets to previous values
                    if (moveX == 1) posX--;
                    if (moveX == -1) posX++;

                    return board;
                }
            }
        }


        // Add itself on the board
        if (posYofBottomest < tetromino[0].length - 1) {
            // Makes sure that the arrays are not out of bounds
            // Copy information from tetromino to storage
            int[][] storage = new int[posYofBottomest + 1][tetromino[0].length];
            for (int i = 0; i < storage.length; i++) {
                System.arraycopy(tetromino[rotation][i], 0, storage[i], 0, storage[0].length);
            }

            // Adds storage to the correct place on board
            for (int i = 0; i < storage.length; i++) {
                for (int j = 0; j < storage[0].length; j++) {
                    if (storage[i][j] != 0 && board[posY + i][posX + j] == 0) {
                        board[posY + i][posX + j] = storage[i][j];
                    }
                }
            }
        } else if (mostLeft > 0) { // Makes sure that the arrays are not out of bounds
            int[][] storage = new int[tetromino[0].length][mostRight - mostLeft + 1];
            for (int i = 0; i < storage.length; i++) {
                System.arraycopy(tetromino[rotation][i], mostLeft, storage[i], 0, storage[0].length);
            }
            // Adds storage to the correct place on board
            for (int i = 0; i < storage.length; i++) {
                for (int j = 0; j < storage[0].length; j++) {
                    if (storage[i][j] != 0 && board[posY + i][posX + j + mostLeft] == 0) {
                        board[posY + i][posX + j + mostLeft] = storage[i][j];
                    }
                }
            }
        } else if (mostRight < tetromino[0].length - 1) { // Makes sure that the arrays are not out of bounds
            int[][] storage = new int[tetromino[0].length][mostRight + 1];
            for (int i = 0; i < storage.length; i++) {
                System.arraycopy(tetromino[rotation][i], 0, storage[i], 0, storage[0].length);
            }
            // Adds storage to the correct place on board
            for (int i = 0; i < storage.length; i++) {
                for (int j = 0; j < storage[0].length; j++) {
                    if (storage[i][j] != 0 && board[posY + i][posX + j] == 0) {
                        board[posY + i][posX + j] = storage[i][j];
                    }
                }
            }
        } else {
            for (int i = 0; i < tetromino[0].length; i++) {
                for (int j = 0; j < tetromino[0].length; j++) {
                    if (tetromino[rotation][i][j] != 0 && board[posY + i][posX + j] == 0) {
                        board[posY + i][posX + j] = tetromino[rotation][i][j];
                    }
                }
            }

        }
        return board;
    }
}
