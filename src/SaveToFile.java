import java.io.*;

public class SaveToFile {
    private final String[] topScores = new String[5];

    public void read() {
        try {
            FileReader fr = new FileReader("HighScore.txt");
            BufferedReader br = new BufferedReader(fr);
            String newScore = br.readLine();
            // Loop through all scores
            while (newScore != null) {
                for (int i = 0; i < 5; i++) {
                    if (topScores[i] == null) { // If it is empty
                        topScores[i] = newScore;
                        break;
                    }
                    // If it is greater than previous scores
                    if (Integer.parseInt(topScores[i].substring(topScores[i].indexOf(" ") + 1)) < Integer.parseInt(newScore.substring(newScore.indexOf(" ") + 1))) {
                        {
                            // Changes the position of scores for top 5 high scores
                            for (int j = 4; j > i; j--) {
                                topScores[j] = topScores[j - 1];
                            }
                            topScores[i] = newScore;
                            break;
                        }
                    }
                }
                newScore = br.readLine(); // Gets next line in document
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error in File reading");
        }
    }

    public void write(String nameAndScore) {
        try {
            FileWriter fw = new FileWriter("HighScore.txt", true); //make file
            PrintWriter pw = new PrintWriter(fw);
            pw.println(nameAndScore); // Add name and score to the file
            pw.close();
        } catch (IOException e) {
            System.err.println("Error in File writing");
        }
    }

    public String[] getTopScores() {
        return topScores;
    }


}
