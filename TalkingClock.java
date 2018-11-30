import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 * @author Kieron Ho
 *
 *	@version 30/11/2018
 */
public class TalkingClock {
	private static HashMap<Integer, String> hourAndMinuteConversion;
	private static HashMap<Integer, String> specialMinuteConversion;
	private static HashMap<Integer, String> tensMinuteConversion;
	
	public static void main(String[] args) {
		//Fill in the conversion maps
		fillHourAndMinuteConversion();
		fillSpecialMinuteConversion();
		fillTensMinuteConversion();
		
		//start the program user interface
		Scanner scanner = new Scanner(System.in);
		String line;
		System.out.println("Welcome to Talking Clock Interface\nPlease input time in 00:00 format\ntype 'quit' to quit");
		while(!(line = scanner.nextLine()).equals("quit")) {
			if(!verifyFormat(line)) {
				System.out.println("incorrect time format, try again");
			}
			else System.out.println(clockToTextParser(line));
			System.out.println("\nPlease input time in 00:00 format\ntype 'quit' to quit");
		}
		System.out.println("Goodbye");
		scanner.close();
	}

	
	/**
	 * Convert numeric time to text output
	 * @param numericTime
	 * @return
	 */
	private static String clockToTextParser(String numericTime) {
		String dayHalf = " am";
		String text = "It's ";
		String hourString = numericTime.substring(0,  2);
		String minutesString = numericTime.substring(3, 5);
		int hourInt = Integer.parseInt(hourString);
		int minutesInt = Integer.parseInt(minutesString);
		
		//Set the half of day
		if(hourInt >= 12) dayHalf = " pm";
		hourInt = hourInt%12;
		
		//Set the time hour text
		text += hourAndMinuteConversion.get(hourInt);
		
		//Determine if minutes are required
		if(minutesInt == 0) return text + dayHalf;
		
		//Check if the minutes are one of the "special" values
		if(specialMinuteConversion.containsKey(minutesInt)) {
			text += " " + specialMinuteConversion.get(minutesInt);
		}
		//Convert minutes to text
		else {
			text += " " + tensMinuteConversion.get(Integer.parseInt(numericTime.substring(3, 4)));
			if(Integer.parseInt(numericTime.substring(4, 5)) != 0) {
				text += " " + hourAndMinuteConversion.get(Integer.parseInt(numericTime.substring(4, 5)));
			}
		}
		return text + dayHalf;
	}
	
	
	/**
	 * Ensure the input time format is in  acceptable 00:00 format
	 * @param numbericTime
	 * @return
	 */
	private static boolean verifyFormat(String numericTime) {
		if(numericTime.length() != 5) return false;
		if(!Character.isDigit(numericTime.charAt(0))) return false;
		if(!Character.isDigit(numericTime.charAt(1))) return false;
		if(!(numericTime.charAt(2) == ':')) return false;
		if(!Character.isDigit(numericTime.charAt(3))) return false;
		if(!Character.isDigit(numericTime.charAt(4))) return false;
		if(Integer.parseInt((numericTime.substring(0, 1))) > 24) return false;
		if(Integer.parseInt((numericTime.substring(3, 4))) > 59) return false;
		return true;
	}
	
	
	/**
	 * Set the conversion map for hours and single minutes
	 */
	private static void fillHourAndMinuteConversion() {
		hourAndMinuteConversion = new HashMap<Integer, String>(){{
		put(0, "twelve");
		put(1, "one");
		put(2, "two");
		put(3, "three");
		put(4, "four");
		put(5, "five");
		put(6, "six");
		put(7, "seven");
		put(8, "eight");
		put(9, "nine");
		put(10, "ten");
		put(11, "eleven");
		put(12, "twelve");
		}};
	}
	
	
	/**
	 * Set the conversion map for non-normal minute values
	 */
	private static void fillSpecialMinuteConversion() {
		specialMinuteConversion = new HashMap<Integer, String>(){{
			put(10, "ten");
			put(11, "eleven");
			put(12, "twelve");
			put(13, "thirteen");
			put(14, "fourteen");
			put(15, "fifteen");
			put(16, "sixteen");
			put(17, "seventeen");
			put(18, "eighteen");
			put(19, "nineteen");
		}};
	}
	
	
	/**
	 * Set the conversion map for the minutes of factors of ten
	 */
	private static void fillTensMinuteConversion() {
		tensMinuteConversion = new HashMap<Integer, String>(){{
			put(0, "oh");
			put(2, "twenty");
			put(3, "thirty");
			put(4, "fourty");
			put(5, "fifty");
			put(6, "sixty");
		}};
	}
	
}
