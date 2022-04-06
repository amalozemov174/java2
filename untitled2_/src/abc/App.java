package abc;

//1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
// Посчитать сколько раз встречается каждое слово.
//2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
// В этот телефонный справочник с помощью метода add() можно добавлять записи. С помощью метода get() искать номер телефона по фамилии  o(1) или o(logn).
// Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.
//Желательно как можно меньше добавлять своего, чего нет в задании (т.е. не надо в телефонную запись добавлять еще дополнительные поля (имя, отчество, адрес),
// делать взаимодействие с пользователем через консоль и т.д.). Консоль желательно не использовать (в том числе Scanner), тестировать просто из метода main() прописывая add() и get().

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.util.*;

public class App {
    public static void main(String[] args) {

        //////////////Задание 1
        List<String> wordLists = new ArrayList<String>();
        wordLists.add("Слово1");
        wordLists.add("Слово2");
        wordLists.add("Слово3");
        wordLists.add("Слово4");
        wordLists.add("Слово5");
        wordLists.add("Слово6");
        wordLists.add("Слово1");
        wordLists.add("Слово2");
        wordLists.add("Слово3");
        wordLists.add("Слово4");
        wordLists.add("Слово5");
        wordLists.add("Слово6");
        wordLists.add("Слово7");
        wordLists.add("Слово8");
        wordLists.add("Слово1");
        wordLists.add("Слово2");
        wordLists.add("Слово3");
        wordLists.add("Слово4");
        wordLists.add("Слово5");
        wordLists.add("Слово6");
        Set<String> unikLitWords = new LinkedHashSet<String>(wordLists);
        System.out.println(unikLitWords);
        printCountOfElements(wordLists);

        //////////////Задание 2
        Phonebook phonebook = new Phonebook();
        phonebook.setPhoneNumber("Иванов", "1111");
        phonebook.setPhoneNumber("Петров", "8888");
        phonebook.setPhoneNumber("Сидров", "9999");
        phonebook.setPhoneNumber("Петров", "7777");
        phonebook.setPhoneNumber("Иванов", "6666");

        phonebook.getPhoneBySurname("Иванов");
        phonebook.getPhoneBySurname("Петров");
        phonebook.getPhoneBySurname("Сидров");

    }

    private static void printCountOfElements(List<String> wordLists){
        Map<String, Integer> countOfElements = new HashMap<String, Integer>();
        for (String s: wordLists){
            if(countOfElements.containsKey(s)){
                Integer i = countOfElements.get(s);
                countOfElements.put(s, countOfElements.get(s) + 1);
            }
            else {
                countOfElements.put(s, 1);
            }
        }

        System.out.println(countOfElements);
    }
}
