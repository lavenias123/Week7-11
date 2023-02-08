package projects;

//import pkgs classes
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import projects.exception.DbException;
import projects.service.ProjectService;
import java.math.*;
import projects.entity.Project;


public class ProjectsApp {
	
	private ProjectService projectService = new ProjectService();
	
	// creating scanner obj
	private Scanner scanner = new Scanner(System.in);
			// prevent Eclipse from reformatting list
	// @formatter:off
	
	private List<String> operations = List.of(
			"1) Add a project"); {
	// @formatter:on
	}
	

		// ********** creating user's menu selection *********
						
			// @param args Unused
	public static void main(String[] args) {
				
		// purpose: it will process the menu
		new ProjectsApp().processUserSelections();
		
	} // main
		
	
//	*** displays the menu selection ***
	private void processUserSelections() {
		// set the loop state
		boolean done = false;
		
		// loop until variable 'done' is true user makes correct selection #
		while(!done) {
			// purpose: validate user's selection
			try {
				// 
				int selection = getUserSelection();
				switch(selection) {
				case -1: 
					done = exitMenu();
					break;
				case 1:
					createProject();
					break;
					
				default:
					System.out.println("\n" + selection + " isn't a valid selection.");
					break;
				} // switch
				
			} // try
			// purpose: handle any errors without breaking App
			catch (Exception e){
				System.out.println("\nError: " + e + " Try Again!");
			} // catch
		} // while
	} // processUserSelection
	
	private void createProject() {
	// this will gather project details
		
		
		String projectName = getStringInput(" Enter the project name: ");
		BigDecimal estimatedHours = getDecimalInput(" Enter the estimated hours");
		BigDecimal actualHours = getDecimalInput(" Enter the actual hours");
		Integer difficulty = getIntInput(" Enter the project difficulty (1-5)");
		String notes = getStringInput(" Enter the project notes");
			
	
		Project project = new Project();
		
		project.setProjectName(projectName);
		project.setEstimatedHours(estimatedHours);
		project.setActualHours(actualHours);
		project.setDifficulty(difficulty);
		project.setNotes(notes);
		
		Project dbProject = projectService.addProject(project);
		System.out.println("You have successfullyy created project: " + dbProject);
} // createProject


	private BigDecimal getDecimalInput(String prompt) {
		// This content is from getIntInput method
		
		// declare var & type
				String input = getStringInput(prompt);
				
				
				// the obj is the user's input check if it's null
				if (Objects.isNull(input)) {
					return null;
				} // if
				try {
					// try to convert input from str to Integer
					return new BigDecimal(input).setScale(2);
				} // try -- red need a 'finally block'
				
				catch(NumberFormatException e) { // err can't be resolved to a type
					// imported dbEx to eliminate red line
					throw new DbException(input + " isn't a valid decimal number. Try Again."); 
				}// catch
	}

	// when user wants to exit from entering more projects
	private boolean exitMenu() {
	// exit app
		System.out.println(" Exiting the menu!!!");
		return true; // why?

}

	// purpose: to print the operations & accept user's int input
	private int getUserSelection() {
		// user's menu choice to be returned by the setter?
		
		// method calls
		printOperations();
		
		// this will return the user's menu choice
		Integer input = getIntInput("Enter a menu selection: ");
		
		return Objects.isNull(input) ? -1 : input;
		
	} // getUserSelection 
	
	private void printOperations() {
		// purpose: to displays each menu selection on a new line
		System.out.println("\n\tThese are the available selections. Press the enter key to quit.");
	
	} // printOperations
		
	
	private Integer getIntInput(String prompt) {
		// declare var & type
		String input = getStringInput(prompt);
		
		
		// the obj is the user's input check if it's null
		if (Objects.isNull(input)) {
			return null;
		} // if
		try {
			// try to convert input from str to Integer
			return Integer.valueOf(input);
		} // try -- red need a 'finally block'
		
		catch(NumberFormatException e) { // err can't be resolved to a type
			// imported dbEx to eliminate red line
			throw new DbException(input + " isn't a valid number. Try Again."); 
		}// catch
		
		/** The NumberFormatException occurs when an attempt is made to convert a string with improper format into a numeric value. That means, when it is not possible to convert a string in any numeric type (float, int, etc), this exception is thrown.
		
		 */
	} // getIntInput
	

		private String getStringInput(String prompt) {
		// allow user to enter their menu choice
			
			System.out.print(prompt + ": "); 
//			Readable input;
			String input = scanner.nextLine();
			
			// test input value with if or try
			// neither use a boolean statement
			return input.isBlank() ? null : input.trim();
//			
		}// getStringInput
		
} // class ProjectsApp
















