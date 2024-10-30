package task2;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class CurrencyConverter {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            input.useLocale(Locale.US);

            String currencyName; // наименование валюты для конвертации
            Double currencyValue; // количество валюты

            // Запрос ввода валюты с циклом для проверки корректности
            while (true) {
                System.out
                        .print("Выберите валюту, которую нужно сконвертировать (RUB, USD, EUR, GBP, KZT или BYN): \n");
                currencyName = input.nextLine().toUpperCase(); // наименование валюты для конвертации

                if (isValidCurrency(currencyName)) {
                    break; // Если валюта корректна, выходим из цикла
                } else {
                    System.out.print("Некорректное наименование валюты, попробуйте снова! \n");
                }
            }

            System.out.print("Введите сумму валюты для конвертации: \n");
            currencyValue = input.nextDouble(); // сумма для конвертации
            System.out.println(calculationMethod(currencyValue, currencyName)); // вывод результата в консоль
        }
    }

    // метод для проверки правильности ввода
    private static boolean isValidCurrency(String currencyName) {
        return currencyName.equals("RUB") || currencyName.equals("USD") ||
                currencyName.equals("EUR") || currencyName.equals("GBP") ||
                currencyName.equals("KZT") || currencyName.equals("BYN");
    }

    // Метод для конвертации валют
    private static String calculationMethod(Double currencyValue, String currencyName) {
        DecimalFormat df = new DecimalFormat("#.####");
        StringBuilder result = new StringBuilder(); // перезаписываемая строка для результатов конвертации каждой валюты
        Set<String> resultTable = new LinkedHashSet<>(); // результат конвертации всех валют
        System.out.print("Курсы валют: \n");

        // Карта для хранения данных (валюта-курс к рублю)
        LinkedHashMap<String, Double> exchangeRate = new LinkedHashMap<>();
        exchangeRate.put("USD", 96.1);
        exchangeRate.put("EUR", 105.49);
        exchangeRate.put("GBP", 125.65);
        exchangeRate.put("BYN", 29.19);
        exchangeRate.put("KZT", 0.197185);
        exchangeRate.put("RUB", 1.0);

        // цикл для конвертации валют с условием наличия введенной валюты
        for (String key : exchangeRate.keySet()) {
            if (exchangeRate.containsKey(key) && exchangeRate.containsKey(currencyName)) {
                Double currency = currencyValue * exchangeRate.get(currencyName) / exchangeRate.get(key); // конвертация
                                                                                                          // валют
                result.append(currencyValue).append(" ").append(currencyName).append(" => ").append(key).append(": ")
                        .append(df.format(currency)).append("\n");
                resultTable.add(result.toString()); // добавление строки с результатом в множество
                result.setLength(0); // Очистка перезаписываемой строки
            }
        }

        // Возвращение результата в виде множества строк
        return String.join("", resultTable);
    }
}
