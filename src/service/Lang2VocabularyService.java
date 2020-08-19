package service;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Lang2VocabularyService extends VocabularyService {

    private static final String PATH = "2.txt";

    @Override
    public boolean isKeyValid(String key) {
        return key.matches("\\d{5}");
    }

    @Override
    protected Path getPath() {
        return Paths.get(PATH);
    }
}
