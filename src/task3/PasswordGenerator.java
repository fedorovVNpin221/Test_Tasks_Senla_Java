package task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {
        public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {

            int passwordLength; // длина пароля

            // запрос ввода пароля с циклом для проверки корректности
            while (true) { 
                System.out.print("Введите длину вашего пароля(от 8 до 12 символов): \n");
                passwordLength = input.nextInt();
    
                // проверка на допустимую длину пароля
                if (passwordLength < 8 || passwordLength > 12) {
                    System.out.println("Ошибка: длина пароля должна быть от 8 до 12 символов.");
                } else {
                    break;
                }            
            }
            System.out.print("Ваш пароль: ");
            System.out.println(generationMethod(passwordLength));
        } 
    }

    // метод для генерации пароля
    private static String generationMethod(Integer passwordLength) {

        String bigLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // строка с заглавными буквами
        String lowLetters = bigLetters.toLowerCase(); // строка со строчными буквами
        String symbols = "!#$%^&*()-+_=><"; // строка со специальными символами

        Random rand = new Random();
        int rand1 = rand.nextInt(Math.max(1, passwordLength - 3)) + 1; // случайное количество специальных символов в пароле
        int rand2 = rand.nextInt(Math.max(1, passwordLength - rand1 - 2)) + 1; // случайное количество заглавных букв в пароле
        int rand3 = rand.nextInt(Math.max(1, passwordLength - rand1 - rand2 - 2)) + 1; // случайное количество строчных букв в пароле
        int rand4 = passwordLength - rand1 - rand2 - rand3; // количество цифр в пароле

        // Проверка всех значений на неотрицательность
        if (rand4 <= 0) {
            throw new IllegalArgumentException("Ошибка: невозможно сгенерировать пароль с заданными параметрами.");
        }

        StringBuilder password = new StringBuilder(); // изменяемая строка для хранения пароля

        // Использование нового метода для добавления символов
        addRandomSymbols(password, symbols, rand1);
        addRandomSymbols(password, lowLetters, rand2);
        addRandomSymbols(password, bigLetters, rand3);
        addRandomDigits(password, rand4);

        // перемешивание символов в пароле
        String shuffledPassword = shuffleString(password.toString());
        return shuffledPassword;
    }

    // метод для добавления случайных символов
    private static void addRandomSymbols(StringBuilder password, String characters, int count) {
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            int randNum = rand.nextInt(characters.length());
            password.append(characters.charAt(randNum));
        }
    }

    // метод для добавления случайных цифр
    private static void addRandomDigits(StringBuilder password, int count) {
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            int randNum = rand.nextInt(10);
            password.append(randNum);
        }
    }

    // метод для перемешивания символов в пароле
    public static String shuffleString(String password) {
      
        // конвертация строки с паролем в список символов
        List<Character> passwordArray = new ArrayList<>();
        for (char c : password.toCharArray()) {
            passwordArray.add(c);
        }

        // перемешивание списка
        Collections.shuffle(passwordArray);

        // конвертация перемешанного списка с символами в строку с паролем
        StringBuilder shuffledPassword = new StringBuilder();
        for (char c : passwordArray) {
            shuffledPassword.append(c);
        }

        return shuffledPassword.toString();
    }
}
