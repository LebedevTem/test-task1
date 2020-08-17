import service.Lang1VocabularyService;
import service.Lang2VocabularyService;
import service.VocabularyService;
import utils.ConsoleUtils;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            ConsoleUtils.printMainMenu();
            VocabularyService vocabularyService = selectVocService(scanner);
            doAction(vocabularyService, scanner);
        }
    }

    private static VocabularyService selectVocService(Scanner scanner) {
        while (true) {
            switch (scanner.nextLine()) {
                case "1" -> {
                    return new Lang1VocabularyService();
                }
                case "2" -> {
                    return new Lang2VocabularyService();
                }
                default -> ConsoleUtils.invalidInputMsg();
            }
        }
    }

    private static void doAction(VocabularyService vocabularyService, Scanner scanner) {

        boolean exit = false;
        while (!exit) {
            ConsoleUtils.printActionSelectionMenu();
            switch (scanner.nextLine()) {
                case "1" -> vocabularyService.readVocabularyList().forEach(System.out::println);
                case "2" -> {
                    System.out.println("Введите ключ для удаления записи");
                    String input = scanner.nextLine();
                    if (vocabularyService.deleteRecord(input)) {
                        System.out.println("Запись удалена");
                    } else {
                        System.out.println("Ошибка удаления записи");
                    }
                }
                case "3" -> {
                    System.out.println("Введите слово для поиска перевода");
                    String input = scanner.nextLine();
                    String translation = vocabularyService.getTranslation(input);
                    System.out.println("Перевод: " + translation);
                }
                case "4" -> {
                    System.out.println("Введите переводимое слово");
                    String key = scanner.nextLine();
                    System.out.println("Введите перевод");
                    String value = scanner.nextLine();
                    if (vocabularyService.addRecord(key, value)) {
                        System.out.println("Запись добавлена");
                    } else {
                        ConsoleUtils.invalidInputMsg();
                    }
                }
                case "5" -> exit = true;
                default -> ConsoleUtils.invalidInputMsg();
            }
        }
    }
}
