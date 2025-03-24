import java.io.*;
import java.util.ArrayList;

public class WordsDictionaryImpl implements WordsDictionary {
    private ArrayList<String> listLa;
    private ArrayList<String> listTa;
    private FileLoader fileLoader;

    public WordsDictionaryImpl() {
        this(new FileLoaderImpl());
    }

    public WordsDictionaryImpl(FileLoader fileLoader) {
        File listLaFile = new File("dictionaries/wordle-La.txt");
        File listTaFile = new File("dictionaries/wordle-Ta.txt");
        listLa = fileLoader.loadWordsFromFile(listLaFile);
        listTa = fileLoader.loadWordsFromFile(listTaFile);
    }

    public String getWordOfTheDay() {
        return "hello";
    }

    public boolean checkUserInputWordIsValid(String word) {
        return true;
    }
}
