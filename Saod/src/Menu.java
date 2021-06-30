import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private Airline airline;
    private Information inf;

    public void start() throws IOException {
        inf = new Information();
        airline = inf.pull();

        Airport airport;
        Aircraft airCr;
        int command;
        boolean b = true;

        while(b) {
            // Вывод таблицы
            System.out.println("\n             Авиакомпание \"" + airline.getName() + "\"");
            System.out.println("+-----------------+----------------------------");
            System.out.println("|                 |           Самолет          ");
            System.out.println("|     Аэропорт    +--------------+-------------");
            System.out.println("|                 |   Марка      | Год выпуска ");
            System.out.println("+-----------------+--------------+-------------");
            for (int i = 0; i < airline.getQuanAirPr(); i++) {
                airport = airline.getAirports()[i];
                System.out.printf("%s%12s%6s%s%s","|", airport.getName(), "|", "              |","\n");
                for (int j = 0; j < airport.getQuanAirCr(); j++) {
                    airCr = airport.getAircraft()[j];
                    System.out.printf("%s%11s%s%10d%s","|                 |", airCr.getBrand(),"   |", airCr.getYear(),"\n");
                }
                System.out.println("+-----------------+--------------+-------------");
            }

            // Вывод команд
            System.out.println("Список доступных команд");
            System.out.println("1. Добавить новый аэропорт");
            System.out.println("2. Удалить аэропорт");
            System.out.println("3. Добавить новый самолет");
            System.out.println("4. Удалить самолет");
            System.out.println("5. Найти аэропорт");
            System.out.println("6. Найти самолет");
            System.out.println("7. Очистить содержимое авиакомпании");
            System.out.println("8. Выйти из приложения");
            System.out.println("Введите команду: ");

            Scanner sc = new Scanner(System.in);
            command = sc.nextInt();

            switch (command){
                case 1:
                    commandAddAirport();
                    break;
                case 2:
                    commandDeleteAirport();
                    break;
                case 3:
                    commandAddAircraft();
                    break;
                case 4:
                    commandDeleteAircraft();
                    break;
                case 5:
                    commandFindAirport();
                    break;
                case 6:
                    commandFindAircraft();
                    break;
                case 7:
                    airline.clearAirline();
                    break;
                case 8:
                    b = false;
                    commandExit();
                    break;
            }
        }
    }

    private void commandAddAirport() {
        if(airline.getQuanAirPr() < airline.getSIZE()) {
            String name;
            Scanner sc = new Scanner(System.in);

            System.out.println("Введите название нового аэропорта: ");
            name = sc.nextLine();
            airline.addAirport(name);
            System.out.println("Добавление произошло успешно.");
        } else {
            System.out.println("Авиакомпания полная. Добавление невозможно");
        }
    }

    private void commandDeleteAirport(){
        if(airline.getQuanAirPr() > 0) {
            String name;
            Scanner sc = new Scanner(System.in);

            System.out.println("Введите название аэропорта: ");
            name = sc.nextLine();
            if(airline.deleteAirport(name)){
                System.out.println("Удаление произошло успешно.");
            } else {
                System.out.println("Аэропорт с таким названием не найден.");
            }

        } else {
            System.out.println("В авиакомпании нет аэропортов. Удаление невозможно.");
        }
    }

    private void commandAddAircraft() {
        if(airline.getQuanAirPr() > 0) {
            String name;
            int year;
            Scanner sc = new Scanner(System.in);

            System.out.println("В какой аэропорт добавить новый самолет? Введите его название: ");
            name = sc.nextLine();
            Airport aP = airline.findAirport(name);

            if(aP.getQuanAirCr() < aP.getSIZE()) {
                System.out.println("Введите марку нового самолета:");
                name = sc.nextLine();
                System.out.println("Введите его год выпуска:");
                year = sc.nextInt();
                aP.addAircraft(name, year);
                System.out.println("Добавление произошло успешно.");
            } else {
                System.out.println("Аэропорт полон. Добавление невозможно.");
            }
        } else {
            System.out.println("В авиакомпании нет аэропортов. Добавление невозможно.");
        }
    }

    private void commandDeleteAircraft(){
        if(airline.getQuanAirPr() > 0) {
            String name;
            Scanner sc = new Scanner(System.in);

            System.out.println("Из какого аэропорта удалить самолет? Введите его название:");
            name = sc.nextLine();
            Airport airP = airline.findAirport(name);

            if(airP.getQuanAirCr() > 0) {
                System.out.println("Введите марку самолета:");
                String brand = sc.nextLine();
                if(airP.deleteAircraft(brand)){
                    System.out.println("Удаление произошло успешно.");
                } else {
                    System.out.println("Самолет с таким названием не найден.");
                }

            } else {
                System.out.println("В аэропорту нет самолетов. Удаление невозможно.");
            }
        } else {
            System.out.println("В авиакомпании нет аэропортов. Удаление невозможно.");
        }
    }

    private void commandFindAirport(){
        System.out.println("Введите название аэропорта:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        if(airline.findAirport(name) != null){
            System.out.println("Аэропорт  найден.");
        } else {
            System.out.println("Аэропорт не найден.");
        }
    }

    private void commandFindAircraft(){
        System.out.println("Введите название аэропорта, в котором находится самолет:");
        Scanner sc = new Scanner(System.in);
        String nameAirport = sc.nextLine();
        System.out.println("Введите название марки самолета:");
        String nameAircraft = sc.nextLine();

        Airport airport = airline.findAirport(nameAirport);
        if(airport.findAircraft(nameAircraft) != null){
            System.out.println("Самолет найден.");
        } else {
            System.out.println("Самолет не найден.");
        }
    }

    private void commandExit() throws IOException {
        System.out.println("Хотите ли вы сохранить изменения в файл? д/н");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();

        if (answer.equals("д")) {
            inf.push();
        }

        System.out.println("Завершение работы...");
    }
}
