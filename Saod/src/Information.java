import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Information {
    private Airline airline;

    public Airline pull() throws IOException {
        int quanAirPr, quanAirCr, year;
        String name;
        Airport airPr;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("C:\\Java_f\\Airline.txt"), Charset.forName("UTF-8")))) {

            String nameAirline = reader.readLine();
            airline = new Airline(nameAirline);

            quanAirPr = Integer.parseInt(reader.readLine());
            for (int i = 0; i < quanAirPr; i++) {
                name = reader.readLine();
                airline.addAirport(name);
                airPr = airline.findAirport(name);
                quanAirCr = Integer.parseInt(reader.readLine());
                for (int j = 0; j < quanAirCr; j++) {
                    name = reader.readLine();
                    year = Integer.parseInt(reader.readLine());
                    airPr.addAircraft(name, year);
                }
            }
            return airline;
        } catch (IOException e) {
            System.out.println("Файл Airline.txt не найден. Список пуст.");
            System.out.println("Введите название авиакомпании:");
            Scanner sc = new Scanner(System.in);
            airline = new Airline(sc.nextLine());
            return airline;
        }
    }

    public void push() throws IOException {
        // значение false значит, что содержимое файла будет перезаписываться
        FileWriter file = new FileWriter("C:\\Java_f\\Airline.txt", false);
        file.write(airline.getName() + "\n");

        Airport airPr;
        Aircraft airCr;
        int quanAirPr = airline.getQuanAirPr();
        int quanAirCr;

        file.write(quanAirPr + "\n");
        for (int i = 0; i < quanAirPr; i++) {
            airPr = airline.getAirports()[i];
            quanAirCr = airPr.getQuanAirCr();

            file.write(airPr.getName() + "\n");
            file.write(quanAirCr + "\n");
            for (int j = 0; j < quanAirCr; j++) {
                airCr = airPr.getAircraft()[j];

                file.write(airCr.getBrand() + "\n");
                file.write(airCr.getYear() + "\n");
            }
        }

        file.flush();

        System.out.println("Изменения успешно сохранены!");
    }
}
