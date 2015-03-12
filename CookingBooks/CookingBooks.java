import java.util.*;
import java.io.*;

public class CookingBooks{
	public static void main(String[] a){
		//read input from STDIN
		Scanner sc = null;
		try{
			sc = new Scanner(new File("cooking_the_books.txt"));
		} catch(FileNotFoundException e) {
			System.out.println("Input file not found. ");
			System.exit(1);
		}

		long numlines = 0;	//T: number of testcases

		if( sc.hasNextLine() ){
			//First line of input file: get number of test cases
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
						i++;	//increment testcase counter
						String thisTc = sc.nextLine();	//read this test case
						//System.out.println("Case #" + i + ": " + thisTc);

						int highest = 1;										//placeholder for highest switchable digit- this is set to lowest switchable value of 1
						int lowest = 9;											//placeholder for lowest switchable digit- this is set to highest switchable value of 9
						int highestPosition = -1;						//Position of the highest digit (0-indexed)
						int lowestPosition = -1;						//Position of the lowest digit (0-indexed)
						boolean highestSet = false;					//Flag indicating if a highest digit was set within this testcase
						boolean lowestSet = false;					//Flag indicating if a lowest digit was set within this testcase
						boolean fudgable = false;						//Flag indicating if this testcase is a candidate for switching
						int tc_length = thisTc.length();		//Length of current testcase

						if(thisTc.length() > 1){
							fudgable = true;									//Switchable flag is set to true if testcase length is > 1 (i.e. TC is atleast a double-digit number)
						}

						//Loop to go through each digit of the current test case
						for(int j = 0; j < thisTc.length(); j++){
								int thisdigit = Character.getNumericValue(thisTc.charAt(j));		//get current (j-th) digit's numeric value

								//Compare to highest and set associated values and flags
								if(thisdigit > highest){
									highest = thisdigit;
									highestPosition = j;
									highestSet = true;
								}

								//Compare to lowest and set associated values and flags
								if( (thisdigit > 0) && (thisdigit < lowest) ){
									lowest = thisdigit;
									lowestPosition = j;
									lowestSet = true;
								}
						}

						//Need for these tests and settings is questionable but w.e. ATM
						if(!highestSet){
							highest = -1;
						}

						if(!lowestSet){
							lowest = -1;
						}

						//Lowest and highest values derived from this testcase
						String lowest_thistc = null;
						String highest_thistc = null;

						//if lowest was set, switch that with the leading digit
						if(lowestSet){
							//build new string with switched chars
							int startDigit = Character.getNumericValue(thisTc.charAt(0));
							if(startDigit > lowest){
								if(lowestPosition + 1 >= tc_length){
									//lowest digit is the last in the testcase string
									lowest_thistc = lowest + thisTc.substring(1,lowestPosition) + startDigit;
								} else {
									//lowest digit is NOT the last in the testcase string
									lowest_thistc = lowest + thisTc.substring(1,lowestPosition) + startDigit + thisTc.substring(lowestPosition+1,tc_length);
								}
							} else {
								lowest_thistc = thisTc;
							}
						} else {
							lowest_thistc = thisTc;
						}

						//reset flag
						lowestSet = false;

						//if highest was set, switch that with the leading digit
						if(highestSet){
							//build new string with switched chars
							int startDigit = Character.getNumericValue(thisTc.charAt(0));
							if(startDigit < highest){
								if(highestPosition + 1 >= tc_length){
									//highest digit is the last in the testcase string
									highest_thistc = highest + thisTc.substring(1,highestPosition) + startDigit;
								} else {
									//highest digit is NOT the last in the testcase string
									highest_thistc = highest + thisTc.substring(1,highestPosition) + startDigit + thisTc.substring(highestPosition+1,tc_length);
								}
							} else {
								highest_thistc = thisTc;
							}
						} else {
							highest_thistc = thisTc;
						}

						//reset flag
						highestSet = false;

						//System.out.println("Case #" + i + ": " + thisTc + "(Highest = " + highest + "(" + highestPosition + ") / Lowest = " + lowest + "(" + lowestPosition + "))");
						System.out.println("Case #" + i + ": " + lowest_thistc + " " + highest_thistc);

					}
				} while(i < numlines);
			}
		}
	}
}
