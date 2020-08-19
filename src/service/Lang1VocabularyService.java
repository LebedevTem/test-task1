package service;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Lang1VocabularyService extends VocabularyService {

    private static final String PATH = "1.txt";

    @Override
    public boolean isKeyValid(String key) {
        return key.matches("[a-z]{4}");
    }

    @Override
    protected Path getPath() {
        return Paths.get(PATH);
    }
}
