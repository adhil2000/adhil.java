public class Automobile {

    private int vin;
    private double miles;
    private int month;
    private int day;
    private int year;

    public Automobile(int VIN, double MILES, int MONTH, int DAY, int YEAR) {
        vin = VIN;
        miles = MILES;
        month = MONTH;
        day = DAY;
        year = YEAR;
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
        String str = ""+month+"/"+day+"/"+year;
        return str;
    }
}