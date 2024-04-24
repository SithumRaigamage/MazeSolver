//Name -Sithum Lisitha Raigamage
//IIT student No -20222473
//UOW student No -w1999685

//imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    //file method to read the pattern from the file using scanner class
    public static char[][] parseFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.isEmpty()) {
                lines.add(line);
            }
        }
        scanner.close();
        //adding to the maze
        char[][] mazePattern = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            mazePattern[i] = lines.get(i).toCharArray();
        }
        //returning the maze pattern
        return mazePattern;
    }
}
