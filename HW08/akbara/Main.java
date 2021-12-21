public class Main {

   public static void main(String[ ] args) {
      RunNumberofThreads(1); // Implementation with 1 Thread
      RunNumberofThreads(2); // Implementation with 2 Threads
      RunNumberofThreads(4); // Implementation with 4 Threads
      RunNumberofThreads(8); // Implementation with 8 Threads
   }

   public static void RunNumberofThreads(int numThreads){
      System.out.println("\nFor " + numThreads + " Threads:");
      //int numThreads = 2; // Number of Threads
      int N = 50; // Array Size
      int sortSize = 32000; // quickSort Size
      Thread[] threads = new Thread[numThreads];
      WorkQueue workqueue = new WorkQueue();
      //Add object references
      for(int j = 0; j < 10; j++){
         workqueue.put(new QuickSort(sortSize));
         workqueue.put(new DotProduct(N));
         // Double Problem Size
         sortSize *= 2;
         N *= 2;
      }
      // Start and Run on Each Thread
      long startTime = System.nanoTime( );
      for(int i = 0; i < numThreads; i++){
         threads[i] = new Thread(workqueue);
         threads[i].start();
      }
      // waits until all threads have finished executing
      for (int t = 0; t < numThreads; t++) {
         try{
            threads[t].join();
         }
         catch(Exception e){
            e.printStackTrace();
         }
      }
      long endTime = System.nanoTime( );
      System.out.println("execution time with "  + numThreads +  " threads is " + (endTime-startTime)/100000. +" milliseconds");
   }
}

