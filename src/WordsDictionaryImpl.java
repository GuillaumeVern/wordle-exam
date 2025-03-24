import java.io.*;
import java.util.ArrayList;
import java.util.Random;

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
        Random random = new Random();
        int randomNumber = random.nextInt(listLa.size());
        return listLa.get(randomNumber);
    }

    public boolean checkUserInputWordIsValid(String word) {
        boolean valid = false;
        for (String s : listLa) {
            if (s.equals(word)) {
                valid = true;
                break;
            }
        }
        if (!valid) {
            for (String s : listTa) {
                if (s.equals(word)) {
                    valid = true;
                    break;
                }
            }
        }
        return valid;
    }
}
