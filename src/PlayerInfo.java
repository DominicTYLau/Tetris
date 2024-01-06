public class PlayerInfo {
    private int score;
    private int lines;
    private int level;
    private final Tetris tetris;

    public PlayerInfo(Tetris t) {
        score = 0;
        lines = 0;
        level = 0;
        tetris = t;
    }

    public int getLines() {
        return lines;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public void oneLine() {
        // When one row is completed simultaneously
        score += (level + 1) * 40;
        lines++;
        levelUp();
    }

    public void twoLine() {
        // When two rows are completed simultaneously
        score += (level + 1) * 100;
        lines += 2;
        levelUp();
    }

    public void threeLine() {
        // When three rows are completed simultaneously
        score += (level + 1) * 300;
        lines += 3;
        levelUp();
    }

    public void fourLine() {
        // When four rows are completed simultaneously
        score += (level + 1) * 1200;
        lines += 4;
        levelUp();
    }

    public void levelUp() {
        // Checks if reaches next level
        if (lines >= level * 10 + 10) addLevel();
    }

    public void addLevel() {
        // Add a level and changes the drop delay of the tetris class
        level++;
        tetris.subDropDelay();
    }


}
