package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ClassesChallenge {
    public static void main (String[] args) {
        String csvFile = "src/DataEmployees.csv";
        String currentLine;
        String delimitComma = ",";
        PersonsRestrictedModel classPerson = null;
        PersonsRestrictedModel classPerson2 = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            boolean isFirstLine = true;
            while ((currentLine = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] data = currentLine.split(delimitComma);
                String name = data[0];
                int age = Integer.parseInt(data[1]);

                String email = data[2];

                classPerson = new PersonsRestrictedModel(name, age, email);

                if (classPerson.getName().equals("Dupont David")) {
                    System.out.println("Email: " + classPerson.getEmail());
                }

                classPerson2 = new PersonsRestrictedModel(name, age, email);
                int age2 = classPerson2.getAge();
                if (age2 > 30 && age2 < 40) {
                    System.out.println("Age: " + classPerson2.getAge());
                }

                classPerson2.setAge(50); // Here we change (data) the age of the person
                classPerson2.setName("Prude Paul");
                classPerson2.setEmail("touchard@jean.com");
            }

            assert classPerson != null; // Here we check if the person is not null

            System.out.println("Age: " + classPerson2.getAge());
            System.out.println("Name: " + classPerson2.getName());
            System.out.println("Email: " + classPerson2.getEmail());
            System.out.println("Email: " + classPerson.getEmail());

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

class PersonsRestrictedModel {
    private String name;
    private int age;
    private String email;

    public PersonsRestrictedModel(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
