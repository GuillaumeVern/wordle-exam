import java.io.File;
import java.util.ArrayList;

public interface FileLoader {
    ArrayList<String> loadWordsFromFile(File file);
}
