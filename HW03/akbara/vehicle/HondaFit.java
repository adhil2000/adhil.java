package akbara.vehicle;

public class HondaFit extends Vehicle {

   String color;

   public HondaFit(String _color) {
      super("HondaFit");
      color = _color;
      System.out.println(" built");
   }

   public String getModel( ) { // RINK MAKE PRIVATE
      return model;
   }

   public String getColor( ) { // RINK MAKE PRIVATE
      return color;
   }

   public String getEngine( ) {
      return "1.5L i-DSI 4 Cylinder";
   }

   public String getTransmission( ) {
      return "manual";
   }

   public int getNumPassengers( ) {
      return 5;
   }

   public String getWarranty( ) {
      return "3/36,000 + 5/60,000 Powertrain";
   }
}
