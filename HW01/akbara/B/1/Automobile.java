public class Automobile {

    private int vin;
    private double miles;
    private String date;

    public Automobile(int VIN, double MILES, int MONTH, int DAY, int YEAR) {
        vin = VIN;
        miles = MILES;
        date = ""+MONTH+"/"+DAY+"/"+YEAR;
    }

    public String getVIN(){
        String str = String.valueOf(vin);
        return str;
    }
    public String getMileage() {
        String str = String.valueOf(miles);
        return str;
    }
    public String getMaintenanceDate(){
        return date;
    }
}