package src;

import java.time.LocalDate;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProgramToGenerateCSVFiles {
    public record Person(String firstName, String lastName) {
    }

    public static class Visit {
        private final Person person;
        private final String reason;
        private String department;
        private final LocalDate date;

        private static final String[] REASONS = {"Appointment", "Visit"};
        private static final String[] DEPARTMENTS = {"Cardiology", "Radiology", "Pediatrics", "Geriatrics", "Pulmonology"};
        private static final int NUM_FIRSTNAMES = 30;
        private static final int NUM_LASTNAMES = 40;
        private static final Random random = new Random();

        public Visit(LocalDate date) {
            this.person = new Person(getRandomFirstName(), getRandomLastName());
            this.reason = getRandomReason();
            this.date = date;
            if (this.reason.equals("Appointment")) {
                this.department = getRandomDepartment();
            }
        }

        public Person getPerson() {
            return person;
        }

        public String getReason() {
            return reason;
        }

        public String getDepartment() {
            return department;
        }

        public LocalDate getDate() {
            return date;
        }

        private String getRandomFirstName() {
            return "First" + (random.nextInt(NUM_FIRSTNAMES) + 1);
        }

        private String getRandomLastName() {
            return "Last" + (random.nextInt(NUM_LASTNAMES) + 1);
        }

        private String getRandomReason() {
            return REASONS[random.nextInt(REASONS.length)];
        }

        private String getRandomDepartment() {
            return DEPARTMENTS[random.nextInt(DEPARTMENTS.length)];
        }
    }

    public static class VisitGenerator {
        private static final String[] CSV_HEADERS = {"First Name", "Last Name", "Reason", "Department", "Date"};

        public static void generateCSVFile(String filePath, int numVisits) throws IOException {
            List<Visit> visits = generateVisits(numVisits);
            FileWriter csvWriter = new FileWriter(filePath);
            csvWriter.append(String.join(",", CSV_HEADERS)).append("\n");
            for (Visit visit : visits) {
                csvWriter.append(visit.getPerson().firstName()).append(",");
                csvWriter.append(visit.getPerson().lastName()).append(",");
                csvWriter.append(visit.getReason()).append(",");
                csvWriter.append(visit.getDepartment() != null ? visit.getDepartment() : "").append(",");
                csvWriter.append(visit.getDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))).append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
        }

        private static List<Visit> generateVisits(int numVisits) {
            List<Visit> visits = new ArrayList<>();
            LocalDate date = LocalDate.of(2023, Month.MARCH, 1);
            for (int i = 0; i < numVisits; i++) {
                visits.add(new Visit(date));
            }
            return visits;
        }
    }
        public static void main(String[] args) {
            String filePath = "visits.csv";
            int numVisits = 10;
            try {
                VisitGenerator.generateCSVFile(filePath, numVisits);
                System.out.println("CSV file generated successfully!");
            } catch (IOException e) {
                System.err.println("Error generating CSV file: " + e.getMessage());
            }
        }
    }
