public class Z_Tetromino extends Tetromino {
    public Z_Tetromino(Tetris t){
        super(t, new int[][][]{
                        {{7, 7, 0},
                        {0, 7, 7},
                        {0, 0, 0}},

                        {{0, 0, 7},
                        {0, 7, 7},
                        {0, 7, 0}},

                        {{0, 0, 0},
                        {7, 7, 0},
                        {0, 7, 7}},

                        {{0, 7, 0},
                        {7, 7, 0},
                        {7, 0, 0}}

        }, 3,0,Colour.red);


    }
}
