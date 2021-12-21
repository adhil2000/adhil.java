public class IntArray extends ComparableArray {
   // create constructors as necessary
   public IntArray(int x, int y){
      super(new int[]{x, y});
   }
   public IntArray(int[] data) {
      super(data);
   }
   public IntArray(ComparableArray source) {
      super(source);
   }
   /*
    * haschCode( ) should return an int based on the values of the object fields
    * equals( ) should return true if two objects are equal based on the values of the
    * object fields.  Given IntArray objects i1, i2 and i3, it must also be the case 
    * that 
    * 1. if i1.equals(i2) == i2.equals(i1).
    * 2. i1.equals(i1) is true, for any i1
    * 3. if i1.equals(i2) is true, and i2.equals(i3) is true, then it must be that
    *    i1.equals(i3) is true.
    * 3. if i1.equals(i2) is true, then i1.hashCode( ) == i2.hashCode( )
    *
    * Note that if i1.equals(i2) is false, then i1.hashCode( ) can be equal or not
    *      equal to i2.hashCode( ).
    *
    * As long as these rules are followed, you can implement hashCode( ) and equals( )
    * any way you want.
    */
   public int hashCode( ){
      return ary.hashCode();
   }
   public boolean equals(IntArray a){
      boolean bool = (compareTo(a) == 0);
      return (bool);
   }
}
