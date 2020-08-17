package utils;

public class ConsoleUtils {

    public static void printMainMenu(){
        System.out.println("Выберите язык:");
        System.out.println("- введите '1' для выбора языка №1");
        System.out.println("- введите '2' для выбора языка №2");
    }

    public static void printActionSelectionMenu() {
        System.out.println("Выберите действие:");
        System.out.println("- введите '1' для просмотра содержимого словаря");
        System.out.println("- введите '2' для удаления записи");
        System.out.println("- введите '3' для поиска перевода");
        System.out.println("- введите '4' для добавления записи");
        System.out.println("- введите '5' для выхода");
    }

    public static void invalidInputMsg() {
        System.out.println("Некорректный ввод");
    }

}
