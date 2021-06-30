public class Airline {
    private final int SIZE = 3;
    private Airport[] airports = new Airport[SIZE];
    private final String name;
    private int quanAirPr = 0;

    public Airline( String n){
        name = n;
    }

    public String getName() {
        return name;
    }

    public int getQuanAirPr() {
        return quanAirPr;
    }

    public Airport[] getAirports() {
        return airports;
    }

    public int getSIZE() {
        return SIZE;
    }

    public void addAirport(String n){
        int pos = quanAirPr;
        Airport airport = new Airport(n);
        if (quanAirPr != 0) {
            for (int i = 0; i < quanAirPr; i++) {
                if (airports[i].getName().compareTo(n) > 0) {
                    pos = i;
                    // Сдвиг массива
                    for (int j = quanAirPr - 1; j >= i; j--) {
                        airports[j + 1] = airports[j];
                        airports[j] = null;
                    }
                    break;
                }
            }
        }
        airports[pos] = airport;
        ++quanAirPr;
    }

    public boolean deleteAirport(String n){
        boolean success = false;
        int q;
        Airport airPr;
        String nameAirCr;

        for (int i = 0; i < quanAirPr; i++) {
            if(airports[i].getName().equals(n)){
                success = true;

                airPr = airports[i];
                q = airPr.getQuanAirCr();
                for (int j = 0; j < q; j++) {
                    nameAirCr = airPr.getAircraft()[0].getBrand();
                    airPr.deleteAircraft(nameAirCr);
                }

                airports[i] = null;
                // сдвиг массива
                for (int j = i; j < quanAirPr - 1; j++) {
                    airports[j] = airports[j + 1];
                    airports[j + 1] = null;
                }
                --quanAirPr;
                break;
            }
        }
        System.gc();
        return success;
    }

    public Airport findAirport(String n){
        Airport aP = null;
        for (int i = 0; i < quanAirPr; i++) {
            if(airports[i].getName().equals(n)){
                aP = airports[i];
                break;
            }
        }
        return aP;
    }

    public void clearAirline(){
        if(quanAirPr > 0){
            Airport airP;
            String name;
            int n1 = quanAirPr; // Кол-во аэропортов
            int n2;             // Кол-во самолетов текущего аэропорта

            for (int i = 0; i < n1; i++) {
                airP = airports[0];
                n2 = airP.getQuanAirCr();
                for (int j = 0; j < n2; j++) {
                    name = airP.getAircraft()[0].getBrand();
                    airP.deleteAircraft(name);
                }
                deleteAirport(airP.getName());
            }
            System.out.println("Авиакомпания успешно очищена!");
        } else {
            System.out.println("Авиакомпания не имеет ни одного аэропорта. Операция невозможна");
        }
    }
}
