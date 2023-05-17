package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class HospitalData {

    private final Map<LocalDate, Map<String, Integer>> data;

    public HospitalData() {
        data = new HashMap<>();
    }

    public void loadData(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            // Skip the first line which contains the column headers
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String[] dateValues = values[0].split("-");
                LocalDate date = LocalDate.parse(values[0], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                String department = values[1];
                int count = Integer.parseInt(values[2]);
                Map<String, Integer> departmentData = data.computeIfAbsent(date, k -> new HashMap<>());
                departmentData.put(department, count);
            }
        }
    }

    public void reportPerDay(LocalDate date, String filename) throws IOException {
        Map<String, Integer> departmentData = data.get(date);
        if (departmentData == null) {
            throw new IllegalArgumentException("No data for date " + date);
        }
        try (FileWriter writer = new FileWriter(filename)) {
                    writer.write("Report for " + date + "\n");
            for (Map.Entry<String, Integer> entry : departmentData.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
        }
    }

    public void reportPerMonth(int year, int month, String filename) throws IOException {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);
        Map<String, Integer> totalData = new HashMap<>();
        for (Map.Entry<LocalDate, Map<String, Integer>> dateEntry : data.entrySet()) {
            LocalDate date = dateEntry.getKey();
            if (date.isAfter(endDate)) {
                break;
            }
            if (date.isBefore(startDate)) {
                continue;
            }
            Map<String, Integer> departmentData = dateEntry.getValue();
            for (Map.Entry<String, Integer> entry : departmentData.entrySet()) {
                String department = entry.getKey();
                int count = entry.getValue();
                int totalCount = totalData.getOrDefault(department, 0);
                totalData.put(department, totalCount + count);
            }
        }
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Report for " + startDate.getMonth() + " " + startDate.getYear() + "\n");
            for (Map.Entry<String, Integer> entry : totalData.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        HospitalData hospitalData = new HospitalData();
        hospitalData.loadData("src/HospitalData.csv");
        hospitalData.reportPerDay(LocalDate.of(2021, 7, 27), "report-2021-07-27.csv");
        hospitalData.reportPerMonth(2021, 7, "report-2021-07.csv");
    }
}



