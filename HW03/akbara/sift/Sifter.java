package akbara.sift;
import akbara.vehicle.FordF350;
import akbara.vehicle.HondaFit;
import akbara.vehicle.Vehicle;

public class Sifter {

   FordF350[ ] getF350s(Vehicle[ ] candidates) {
      FordF350 fordF350s[ ] = new FordF350[candidates.length];
      int F350Count = 0;
      for (int i = 0; i < candidates.length; i++) {
         if (candidates[i] instanceof FordF350) {
            fordF350s[F350Count++] = (FordF350) candidates[i];
         }
      }
 
      for (int i = F350Count; i < fordF350s.length; i++) {
         fordF350s[i] = null;
      }

      return fordF350s;
   }

   public HondaFit[ ] getFits(Vehicle[] candidates) {
      HondaFit hondaFits[ ] = new HondaFit[candidates.length];
      int F350Count = 0;
      for (int i = 0; i < candidates.length; i++) {
         if (candidates[i] instanceof HondaFit) {
            hondaFits[F350Count++] = (HondaFit) candidates[i];
         }
      }
 
      for (int i = F350Count; i < hondaFits.length; i++) {
         hondaFits[i] = null;
      }

      return hondaFits;
   }
}
