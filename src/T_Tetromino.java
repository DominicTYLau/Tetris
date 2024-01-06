public class T_Tetromino extends Tetromino {
    public T_Tetromino(Tetris t){
        super(t, new int[][][]{
                {{0, 6, 0},
                {6, 6, 6},
                {0, 0, 0}},

                {{0, 6, 0},
                {0, 6, 6},
                {0, 6, 0}},

                {{0, 0, 0},
                {6, 6, 6},
                {0, 6, 0}},

                {{0, 6, 0},
                {6, 6, 0},
                {0, 6, 0}}
        }, 3,0,Colour.purple);


    }
}
