public class Airport {
    private final int SIZE = 5;
    private int quanAirCr = 0;
    private Aircraft[] aircraft = new Aircraft[SIZE];
    private final String name;

    public Airport(String n){
        name = n;
    }

    public String getName(){
        return name;
    }

    public int getQuanAirCr(){
        return quanAirCr;
    }

    public Aircraft[] getAircraft(){
        return aircraft;
    }

    public int getSIZE() {
        return SIZE;
    }

    public void addAircraft(String brand, int year) {
        int pos = quanAirCr;
        Aircraft airCr = new Aircraft(brand, year);
        if (quanAirCr != 0) {
            for (int i = 0; i < quanAirCr; i++) {
                if (aircraft[i].getBrand().compareTo(brand) > 0) {
                    pos = i;
                    // Сдвиг массива
                    for (int j = quanAirCr - 1; j >= i; j--) {
                        aircraft[j + 1] = aircraft[j];
                        aircraft[j] = null;
                    }
                    break;
                }
            }
        }
        aircraft[pos] = airCr;
        ++quanAirCr;
    }

    public boolean deleteAircraft(String brand){
        boolean success = false;

        for (int i = 0; i < quanAirCr; i++) {
            if(aircraft[i].getBrand().equals(brand)){
                success = true;
                aircraft[i] = null;
                // сдвиг массива
                for (int j = i; j < quanAirCr - 1; j++) {
                    aircraft[j] = aircraft[j + 1];
                    aircraft[j + 1] = null;
                }
                --quanAirCr;
                break;
            }
        }
        System.gc();
        return success;
    }

    public Aircraft findAircraft(String n){
        Aircraft aC = null;
        for (int i = 0; i < quanAirCr; i++) {
            if(aircraft[i].getBrand().equals(n)){
                aC = aircraft[i];
                break;
            }
        }
        return aC;
    }
}
