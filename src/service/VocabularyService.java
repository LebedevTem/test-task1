package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class VocabularyService {
    public static final String SEPARATOR = ",";

//    public Map<String, String> readVocabularyMap(String path) {
//        try (Stream<String> stream = Files.lines(Paths.get(path))) {
//            return stream
//                    .map(s -> s.split(SEPARATOR))
//                    .collect(Collectors.toMap(strings -> strings[0], strings -> strings[1]));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return new HashMap<>();
//    }

    public List<String> readVocabularyList() {
        createIfNotExists(getPath());
        try {
            return Files.readAllLines(getPath());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public String getTranslation(String key) {
        createIfNotExists(getPath());
        try (Stream<String> stream = Files.lines(getPath())) {
            return stream
                    .filter(s -> s.startsWith(key))
                    .map(s -> s.split(SEPARATOR)[1])
                    .findFirst()
                    .orElse("");
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public boolean deleteRecord(String key) {
        Path filePath = getPath();
        createIfNotExists(filePath);

        try (Stream<String> stream = Files.lines(filePath)) {
            List<String> updatedVoc = stream
                    .filter(s -> !s.startsWith(key))
                    .collect(Collectors.toList());
            Files.delete(filePath);
            Files.write(filePath, updatedVoc, StandardOpenOption.CREATE);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addRecord(String key, String value) {
        createIfNotExists(getPath());
        if (containsRecord(key)) {
            deleteRecord(key);
        }
        try {
            Files.writeString(getPath(),
                    key + SEPARATOR + value + System.lineSeparator(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract boolean isKeyValid(String key);

    protected abstract Path getPath();

    private boolean containsRecord(String key) {
        try (Stream<String> stream = Files.lines(getPath())) {
            return stream.anyMatch(s -> s.startsWith(key));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void createIfNotExists(Path path) {
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
