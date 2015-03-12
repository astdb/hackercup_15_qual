import java.util.*;
import java.io.*;

public class CookingBooks{
	public static void main(String[] a) {
		//read input from STDIN
		Scanner sc = null;
		try{
			sc = new Scanner(new File("cooking_the_books_example_input.txt"));
		} catch(FileNotFoundException e) {
			System.out.println("Input file not found. ");
			System.exit(1);
		}

		long numlines = 0;	//T: number of testcases

		if( sc.hasNextLine() ){
			//get number of test cases
			String firstline = sc.nextLine();
			numlines = Long.parseLong(firstline);

			//System.out.println("****" + numlines + " testcases.");
			//System.exit(1);

			//check testcase number constraints
			if( numlines >= 1 && numlines <= 100 ) {
				//test case counter
				int i = 0;

				//loop through all test cases
				do {
					if( !sc.hasNextLine() ) {
						break;
					} else {
						i++;
						String thisTc = sc.nextLine();
						//System.out.println("Case #" + i + ": " + thisTc);

						int highest = 1;
						int lowest = 9;
						int highestPosition = -1;
						int lowestPosition = -1;
						boolean highestSet = false;
						boolean lowestSet = false;
						boolean fudgable = false;
						int tc_length = thisTc.length();

						if(thisTc.length() > 1){
							fudgable = true;
						}

						for(int j = 0; j < thisTc.length(); j++){
								int thisdigit = Character.getNumericValue(thisTc.charAt(j));
								if(thisdigit > highest){
									highest = thisdigit;
									highestPosition = j;
									highestSet = true;
								}

								if( (thisdigit > 0) && (thisdigit < lowest) ){
									lowest = thisdigit;
									lowestPosition = j;
									lowestSet = true;
								}
						}

						if(!highestSet){
							highest = -1;
						}

						if(!lowestSet){
							lowest = -1;
						}

						String lowest_thistc = null;
						String highest_thistc = null;

						//if lowest was set, switch that with the leading digit
						if(lowestSet){
							//build new string with switched chars
							int startDigit = Character.getNumericValue(thisTc.charAt(0));
							if(startDigit > lowest){
								if(lowestPosition + 2 >= tc_length){
									//lowest digit is the last in the testcase string
									lowest_thistc = lowest + thisTc.substring(1,lowestPosition) + startDigit;
								} else {
									//lowest digit is NOT the last in the testcase string
									lowest_thistc = lowest + thisTc.substring(1,lowestPosition) + startDigit + thisTc.substring(lowestPosition+2,tc_length-1);
								}
							}
						} else {
							lowest_thistc = thisTc;
						}

						lowestSet = false;

						//System.out.println("Case #" + i + ": " + thisTc + "(Highest = " + highest + "(" + highestPosition + ") / Lowest = " + lowest + "(" + lowestPosition + "))");
						System.out.println("Case #" + i + ": " + lowest_thistc);

					}
				} while(i < numlines);
			}
		}
	}
}
