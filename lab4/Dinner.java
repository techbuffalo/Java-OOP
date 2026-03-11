package lab4;

import java.util.Scanner;

/** This is a dinner simulation program to illustrate several OO concepts 
 * such as abstract classes, interfaces, static variables, static final constants, and polymorphism.
 * The Dinner class starts the program.
 * */


public class Dinner {
	
	/** main method prints the menu and takes user input in the variable 'choice'.
	 * It then invokes the getFood() method and passes the choice to it.
	 * After getFood() method returns the appropriate Food object, the main method  
	 * invokes the eatFood() method and passes that Food object to it. 
	 * It then prints how many calories have been consumed.
	 * The above is repeated in a loop till the user enters 'n' to the 
	 * question, 'Want some more?'
	 */
	public static void main(String[] args) {
		Dinner dinner = new Dinner();
		//Initially starting with the userInput as y so the while loop will run atleast once
		String userInput = "y";
		Scanner userChoice = new Scanner(System.in);


		while (userInput.equalsIgnoreCase("y")) { 
			System.out.println("What would you like to eat?");
			System.out.println("1. Pizza");
			System.out.println("2. Chips");
			System.out.println("3. Ice Cream");
		
			int choice = userChoice.nextInt();
			Food foodChoice = dinner.getFood(choice);
			
			if (foodChoice != null) {
				System.out.println("Here comes the food!");
				dinner.eatFood(foodChoice);
				System.out.println("You have consumed "+ Food.calories + " calories.");
			}

			System.out.println("Want some more? (y/n)");
			userInput = userChoice.next();

			}

			//Now if the userInput is anything other than y or Y the program will quit
			System.out.println("Good night!");
			userChoice.close();
			
		}
	

	/**getFood() takes choice and returns Pizza object, Chips object, or IceCream object
	 * for choices 1, 2, and 3 respectively. 
	 * 
	 * I implemented a switch statement for the various choices with the default
	 * being an invalid choice that will end the program 
	 */
	public Food getFood(int choice) {
		return switch (choice) {
			case 1 -> new Pizza();
			case 2 -> new Chips();
			case 3 -> new IceCream();
			default -> {
                            System.out.println("Invalid choice. Goodbye!");
                            yield null;
                }
		};

	}
	
	/**eatFood() method takes Food object as an argument. 
	 * It checks if the object is Pizza. If yes, then 
	 * it invokes its heatIt() method
	 * Then it invokes the eat() method of the Food object. 
	 * 
	 * When the food choice f is a pizza then the heatIt method will run. Otherwise the abstract eat will run
	 */
	
	public void eatFood(Food f) {

		if (f instanceof Pizza) {
			((Heatable) f).heatIt();
		}
		System.out.println(f.eat());
	}
}
