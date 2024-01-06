public class S_Tetromino extends Tetromino {
    public S_Tetromino(Tetris t){
        super(t, new int[][][]{
                        {{0, 5, 5},
                        {5, 5, 0},
                        {0, 0, 0}},

                        {{0, 5, 0},
                        {0, 5, 5},
                        {0, 0, 5}},

                        {{0, 0, 0},
                        {0, 5, 5},
                        {5, 5, 0}},

                        {{5, 0, 0},
                        {5, 5, 0},
                        {0, 5, 0}}

        }, 3,0,Colour.green);


    }
}
