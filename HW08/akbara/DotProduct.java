import java.util.Random;

public class DotProduct implements Command{

    private int size;
    private int array1[ ];
    private int array2[ ];
    private int sum = 0;

    public DotProduct(int _size) {
        size = _size;
        array1 = new int[size];
        array2 = new int[size];

        // Generate Array1 of Random Ints
        Random random1 = new Random();
        for (int i = 0; i < size; i++)
        {
            array1[i] = (random1.nextInt(1000));
        }

        // Generate Array2 of Random Ints
        Random random2 = new Random();
        for (int i = 0; i < size; i++)
        {
            array2[i] = (random2.nextInt(1000));
        }

    }

    public String identify( ){
        return ("inner product on arrays of length " + size + ",the result is " + sum + "\n");
    }

    public void execute( ) {
        for (int i = 0; i < size; i++)
        {
            sum += array1[i] * array2[i];
        }
        System.out.println(identify());
    }
}
