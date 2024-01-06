public class L_Tetromino extends Tetromino {
    public L_Tetromino(Tetris t){
        super(t, new int[][][]{
                        {{0, 0, 3},
                        {3, 3, 3},
                        {0, 0, 0}},

                        {{0, 3, 0},
                        {0, 3, 0},
                        {0, 3, 3}},

                        {{0, 0, 0},
                        {3, 3, 3},
                        {3, 0, 0}},

                        {{3, 3, 0},
                        {0, 3, 0},
                        {0, 3, 0}}

        }, 3,0, Colour.orange);


    }
}
