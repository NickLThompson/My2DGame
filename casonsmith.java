import java.util.Random;
import java.util.Scanner;


public class Casonsmith {

	public static void main(String[] args) {
		// Scanner + beginning of scanner
		Scanner scanner = new Scanner(System.in);
		String userInput = "";
		System.out.println("What is your name?");
		userInput = scanner.nextLine();
		System.out.println("Hello "+ userInput);
		
		
		// Random sentence generator arrays
		Random random = new Random();
		String cnf;
		String cf;
		
		String CockFlattened[] = {"Apologize to Michaeleyyla","Avoid typing and playing dnd",
		"Tell michaeleyyla I am no longer attracted to her","Cheat","Type With Very Proper Grammar To Let Others Know I am Upset (lack of taco bell)",
		"'Do homework' but instead go out with people","Buy useless camera-related equipment",};
		
		String CockNotFlattened[] = {"Argue about monkey game","Show off hickeys","play random games I haven't touched in months",
		"Still do not play dnd, but tell the boys I will",};
		
		cnf = CockNotFlattened [random.nextInt(CockNotFlattened.length)];
		cf = CockFlattened [random.nextInt(CockFlattened.length)];
		
		
		// if else statements for Yes and No
		if (userInput.equalsIgnoreCase("Cason smith"))
				System.out.println("Has your cock been flattened?");
					userInput = scanner.nextLine();
				if (userInput.equalsIgnoreCase("No"))
					System.out.println(cnf);
				else if (userInput.equalsIgnoreCase("Yes"))
					System.out.println(cf);
				
		
				
		}
	}

