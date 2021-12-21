public class HW1 {

   public static void main(String[ ] args) {
      Automobile auto1 = new Automobile(12345678, 1024.3, 06, 13, 2020);
      Automobile auto2 = new Automobile(12345679, 1025.3, 07, 13, 2020);

      System.out.println("Auto1:");
      System.out.println("   VIN: "+auto1.getVIN( ));
      System.out.println("   mileage: "+auto1.getMileage( ));
      System.out.println("   last maintenance: "+auto1.getMaintenanceDate( )+"\n");

      System.out.println("Auto2:");
      System.out.println("   VIN: "+auto2.getVIN( ));
      System.out.println("   mileage: "+auto2.getMileage( ));
      System.out.println("   last maintenance: "+auto2.getMaintenanceDate( ));
   }
}
