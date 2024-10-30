package task1;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class SlipknotGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        input.useLocale(Locale.forLanguageTag("ru"));

        System.out.print("Игра <<Виселица>> \n");
        System.out.print(Game(input)); // запуск игры и вывод загаданного слова
    }

    // основной сетод с игрой 
    @SuppressWarnings("unlikely-arg-type")
    private static String Game(Scanner input) { 
        ArrayList<String> wordList = new ArrayList<>(); // список загадываемых слов
        wordList.add("good");
        wordList.add("mobile");
        wordList.add("joke");
        wordList.add("laugh");
        wordList.add("trouble");

        Random rand = new Random();
        int guessedWordIndex = rand.nextInt(wordList.size()); // случайный выбор загадываемого слова
        String guessedWord = wordList.get(guessedWordIndex); // запись загадываемого слова в переменную
        int healPoints = 8; // количество жизней игрока
        int roundCount = 0; // счётчик сыгранных раундов
        StringBuilder newWord = new StringBuilder(); // строка для записи отгадываемых игроком букв

        // заполнение строки символами "_"
        for (int i = 0; i < guessedWord.length(); i++) {
            newWord.append("_");
        }

        // основной цикл игры, пока жизни игрока не закончатся
        while (healPoints > 0) { 

            System.out.print("\nВведите букву, которая есть в загаданном слове: \n");
            String userLetter = input.nextLine(); // буква, выбранная игроком
            
            // проверка на ввод игроком буквы
            if (userLetter.isEmpty()) {
                System.out.print("Вы не ввели букву, попробуйте снова! \n");
                continue;
            }

            char letter = userLetter.charAt(0); // запись буквы игрока в переменную
            boolean letterFound = false; // Флаг для проверки наличия буквы
        
            // цикл с проверкой выбранной буквы на правильность и ее записью в строку с отгаданными буквами
            for (int j = 0; j < guessedWord.length(); j++) { 
                if (letter == guessedWord.charAt(j)) { 
                    letterFound = true; 
                    newWord.setCharAt(j, letter);
                }
            }
        
            // проверка на отгагдку буквы и вывод результатов в консоль
            if (letterFound) { // если игрок отгадал букву
                System.out.print(hangmanPrintMethod(roundCount - 1) + "\nБуква " + userLetter + " есть в слове! \n");
                System.out.print("Загаданное слово: " + newWord + "\n");
                System.out.print("У вас осталось " + healPoints + " жизней \n");
            } else { // если игрок не отгадал букву
                System.out.print(hangmanPrintMethod(roundCount) + "\nБуквы " + userLetter + " нет в слове! \n");
                System.out.print("Загаданное слово: " + newWord + "\n");
                healPoints--; // отнятие жизней за неправильную попытку
                System.out.print("У вас осталось " + healPoints + " жизней\n");
                roundCount++; // увеличение счетчика раундов только при неправильной попытке
            }
            
            // проверка на выигрыш или проигрыш игрока
            if (newWord.toString().equals(guessedWord)) {
                System.out.print("\nВы выиграли! Загаданное слово => ");
                break;
            } else if (healPoints == 0) {
                System.out.print("\nВы проиграли! Загаданное слово => ");
            }
        }
        return guessedWord; // возвращаем загаданное слово
    }

    private static String hangmanPrintMethod(int roundCount) { 
        String[] hangmanStates = { // массив строк с состояниями виселицы
            "  +---+\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "=========",
            "  +---+\n" +
            "  |   |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "=========",
            "  +---+\n" +
            "  |   |\n" +
            "  O   |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "=========",
            "  +---+\n" +
            "  |   |\n" +
            "  O   |\n" +
            "  |   |\n" +
            "      |\n" +
            "      |\n" +
            "=========",
            "  +---+\n" +
            "  |   |\n" +
            "  O   |\n" +
            " -|   |\n" +
            "      |\n" +
            "      |\n" +
            "=========",
            "  +---+\n" +
            "  |   |\n" +
            "  O   |\n" +
            " -|-  |\n" +
            "      |\n" +
            "      |\n" +
            "=========",
            "  +---+\n" +
            "  |   |\n" +
            "  O   |\n" +
            " -|-  |\n" +
            " (    |\n" +
            "      |\n" +
            "=========",
            "  +---+\n" +
            "  |   |\n" +
            "  O   |\n" +
            " -|-  |\n" +
            " ( )  |\n" +
            "      |\n" +
            "========="
        };

        // если игрок отгадал букву в первом раунде
        if (roundCount < 0) {
            return hangmanStates[0];
        }

        return hangmanStates[roundCount]; // Возвращаем состояние виселицы по индексу
    }
}
