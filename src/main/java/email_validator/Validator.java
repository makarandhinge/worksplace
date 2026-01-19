package email_validator;

import java.util.Scanner;

import org.apache.commons.validator.routines.EmailValidator;



public class Validator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter your Email Id: ");
		Scanner sc = new Scanner(System.in);
		String email = sc.next();
		System.out.println(email);
		EmailValidator validator = EmailValidator.getInstance(true,true);
		boolean validEmail = validator.isValid(email);
		System.out.println(validEmail);

	}

}
