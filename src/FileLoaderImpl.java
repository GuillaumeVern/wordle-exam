import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileLoaderImpl implements FileLoader {

    public ArrayList<String> loadWordsFromFile(File file) {
        ArrayList<String> list = new ArrayList<>();
        try (FileReader fr = new FileReader(file)){
            int character;
            StringBuilder word = new StringBuilder();
            while ((character = fr.read()) != -1) {
                if (character != '\n') {
                    word.append((char) character);
                } else {
                    list.add(word.toString());
                    word = new StringBuilder();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
        return list;
    }
}
