import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class WordsDictionaryImpl implements WordsDictionary {
    private final ArrayList<String> listLa;
    private final ArrayList<String> listTa;
    private FileLoader fileLoader;

    public WordsDictionaryImpl() {
        this(new FileLoaderImpl());
    }

    public WordsDictionaryImpl(FileLoader fileLoader) {
        File listLaFile = new File("resources/wordle-La.txt");
        File listTaFile = new File("resources/wordle-Ta.txt");
        listLa = fileLoader.loadWordsFromFile(listLaFile);
        listTa = fileLoader.loadWordsFromFile(listTaFile);
    }

    public String getWordOfTheDay() {
        Random random = new Random();
        int randomNumber = random.nextInt(listLa.size());
        return listLa.get(randomNumber);
    }

    public boolean checkUserInputWordIsValid(String word) {
        return listLa.contains(word) || listTa.contains(word);
    }
}
