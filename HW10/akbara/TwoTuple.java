public class TwoTuple <i1 extends Comparable, i2 extends Comparable> implements Comparable {

   private static final int LESS_THAN = -1;
   private static final int EQUAL = 0;
   private static final int GREATER_THAN = 1;

   private final i1 i1;
   private final i2 i2;

   public TwoTuple(i1 _i1, i2 _i2){
      i1 = _i1;
      i2 = _i2;
   }
   public int compareTo(Object tuple) {
      // Note use of instanceof to check that the arguemnt to this is a TwoTuple
      // and not some arbitrary object
      if (!(tuple instanceof TwoTuple)) {
         throw new ClassCastException("object "+tuple+" does not implement Comparable");
      }

      TwoTuple o2 = (TwoTuple) tuple;

      if ((this.i1.compareTo(o2.i1) == LESS_THAN) || ((this.i1.compareTo(o2.i1) == EQUAL) && (this.i2.compareTo(o2.i2) == LESS_THAN)))
      {
         return LESS_THAN;
      }
      else if((this.i1.compareTo(o2.i1) == EQUAL) && (this.i2.compareTo(o2.i2) == EQUAL))
      {
         return EQUAL;
      }
      else
      {
         return GREATER_THAN;
      }
   }

   public String toString( ) {
      return "["+i1+","+i2+"]";
   }
}
