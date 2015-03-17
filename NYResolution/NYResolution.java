import java.util.*;
import java.io.*;

public class NYResolution{
  public static void main(String[] a){
    //read input from STDIN
    Scanner sc = null;
    try{
      sc = new Scanner(new File("new_years_resolution_example_input.txt"));
    } catch(FileNotFoundException e) {
      System.out.println("Input file not found. ");
      System.exit(1);
    }

    long numlines = 0;	//T: number of testcases

    if( sc.hasNextLine() ){
      //First line of input file: get number of test cases
      String firstline = sc.nextLine();
      numlines = Long.parseLong(firstline);

      //System.out.println("\nNo. of testcases: " + numlines );
      //System.exit(1);

      //check testcase number constraints
      if( numlines >= 1 && numlines <= 20 ) {
        //test case counter
        int i = 0;

        //loop through all test cases
        do {
          i++;
          //System.out.println("\n\t---------------TC " + i + "---------------------------");
          String[] pcf_want = sc.nextLine().split(" ");
          long Gp = Long.parseLong(pcf_want[0]);
          long Gc = Long.parseLong(pcf_want[1]);
          long Gf = Long.parseLong(pcf_want[2]);

          //System.out.println("\t Gp = " + Gp + ", Gc = " + Gc + ", Gf = " + Gf);

          long num_foods = Long.parseLong(sc.nextLine());
          //System.out.println("\t N = " + num_foods);
          boolean yes = false;

          if(num_foods >= 1 && num_foods <= 20){
            int j = 0;
            do{
              j++;

              //String pcf = sc.nextLine();
              //System.out.println("\t\t " + j + ". PCF: " + pcf);
              String[] pcf_available = sc.nextLine().split(" ");
              long p = Long.parseLong(pcf_available[0]);
              long c = Long.parseLong(pcf_available[1]);
              long f = Long.parseLong(pcf_available[2]);

              //System.out.println("\t P = " + p + ", C = " + c + ", F = " + f);

              if( Gp == p && Gc == c && Gf == f ){
                System.out.println("Case #" + i + ": yes");
                yes = true;
                break;
            }while(j < num_foods);

            if(!yes){
              System.out.println("Case #" + i + ": no");
              yes = false;
            }
          }
        } while(i < numlines);
      }
    }
  }
}
