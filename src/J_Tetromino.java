public class J_Tetromino extends Tetromino {
    public J_Tetromino(Tetris t){
        super(t, new int[][][]{
                        {{2, 0, 0},
                        {2, 2, 2},
                        {0, 0, 0}},

                        {{0, 2, 2},
                        {0, 2, 0},
                        {0, 2, 0}},

                        {{0, 0, 0},
                        {2, 2, 2},
                        {0, 0, 2}},

                        {{0, 2, 0},
                        {0, 2, 0},
                        {2, 2, 0}}
        }, 3,0, Colour.blue);


    }
}
