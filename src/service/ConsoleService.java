package service;

import java.util.Scanner;

public class ConsoleService {

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            printMainMenu();
            VocabularyService vocabularyService = selectVocService(scanner);
            doAction(vocabularyService, scanner);
        }
    }

    private VocabularyService selectVocService(Scanner scanner) {
        while (true) {
            switch (scanner.nextLine()) {
                case "1" -> {
                    return new Lang1VocabularyService();
                }
                case "2" -> {
                    return new Lang2VocabularyService();
                }
                default -> invalidInputMsg();
            }
        }
    }

    private void doAction(VocabularyService vocabularyService, Scanner scanner) {

        boolean exit = false;
        while (!exit) {
            printActionSelectionMenu();
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
                        invalidInputMsg();
                    }
                }
                case "5" -> exit = true;
                default -> invalidInputMsg();
            }
        }
    }

    private void printMainMenu(){
        System.out.println("Выберите язык:");
        System.out.println("- введите '1' для выбора языка №1");
        System.out.println("- введите '2' для выбора языка №2");
    }

    private void printActionSelectionMenu() {
        System.out.println("Выберите действие:");
        System.out.println("- введите '1' для просмотра содержимого словаря");
        System.out.println("- введите '2' для удаления записи");
        System.out.println("- введите '3' для поиска перевода");
        System.out.println("- введите '4' для добавления записи");
        System.out.println("- введите '5' для выхода");
    }

    private void invalidInputMsg() {
        System.out.println("Некорректный ввод");
    }
}
