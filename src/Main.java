import java.util.*;
import java.util.stream.*;


public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long youngPersonCount = persons.stream()
                .filter(ages -> ages.getAge() < 18)
                .count();
        System.out.println("Количество людей младше 18 лет - " + youngPersonCount);

        List <String> conscriptList = persons.stream()
                .filter(ages -> ages.getAge() >= 18 && ages.getAge() <= 27)
                .filter(Person -> Person.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Список потенциальных призывников" + conscriptList);

        List <Person> potentialWorkersWithHigherEducation = persons.stream()
                .filter(x -> x.getEducation() == Education.HIGHER && x.getAge() >= 18)
                .filter(x -> x.getSex() == Sex.MAN ? x.getAge() <= 65 : x.getAge() <= 60)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("Список потенциально работоспособных людей с высшим образованием" + potentialWorkersWithHigherEducation);
    }

}



