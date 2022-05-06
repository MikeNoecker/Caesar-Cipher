/*Mike Noecker, Period 7, 3-31-21, This is my own code MDN, This program will have the user choose
if they want to encrypt or decrypt a message. They will then enter a shift value to be used. The
program will then print the encrypted/ decrypted data to the screen*/

/*currently working on bruteforce method and incorperating it into the main and other methods, the buteforce call
is incorperated into the input method and should work 	4/14/21  11:15*/

//upper case wrap around:    if(decryptedText[index] >= 65 && decryptedText[index] <= 90)   {surplus = decryptedText[index] % 2}
import java.util.Scanner;
import java.lang.String;
public class CaesarCipher
{
	//just calls the input method because everything inside it is handled from there
    public static void main(String[] args)
    {
		System.out.println("---Caesar Cipher---");
		input();
    }





    //gets user input then envokes encryption/ decryption method-------------------------------------------------
	public static void input()
	{
		//vars, objects
		String text;
		String choice = "";
		int shiftValue;
		int strLength;
		Scanner keyboard = new Scanner(System.in);

		//get input from user
		System.out.print("Enter Message (Plaintext/ Encrypted): ");
		text = keyboard.nextLine();
		strLength = text.length();//might not need this***

		//user chooses to encrypt or decrypt, add idiot proof loop
		while(!choice.equals("encrypt") && !choice.equals("decrypt"))
		{
			System.out.print("Do you want to encrypt or decrypt: ");
			choice = keyboard.nextLine();
			choice = choice.toLowerCase();
		}

		//select the method needed
		if(choice.equals("encrypt"))
		{
			System.out.print("Enter the Shift Value: ");
			shiftValue = keyboard.nextInt();
			encrypt(text, shiftValue, strLength);
		}

		else if(choice.equals("decrypt"))
		{
			System.out.print("Is the shift key known (y/n): ");
			choice = keyboard.nextLine();
			choice = choice.toLowerCase();

			if(choice.equals("Y") || choice.equals("y"))
			{
				System.out.print("Enter the Shift Value: ");
				shiftValue = keyboard.nextInt();
				decrypt(text, shiftValue);//might cause error (strLength var was removed)***
			}
			else if(choice.equals("N") || choice.equals("n"))
				bruteforce(text);
			else
				System.out.println("Input not valid");
		}
	}




    //used to handle all encryption------------------------------------------------------------------------------
    public static void encrypt(String text, int shiftValue, int strLength)
    {
        //vars
        int ascii;
        char[] encryptedText = new char[text.length()];

        //increment through text to assign ascii and encryptedText
        for(int i = 0; i < text.length(); i++)
        {
			//filters thorugh letters and other chars
			if(text.charAt(i) >= 65 && text.charAt(i) <= 90 || text.charAt(i) >= 97 && text.charAt(i) <= 122)
			{
				encryptedText[i] = text.charAt(i);
				//ascii = (int)text[i] + shiftValue;
				ascii = (int)text.charAt(i) + shiftValue;
            	encryptedText[i] = (char)ascii;
			}

			else
				encryptedText[i] = text.charAt(i);
        }

        //change encryptedText to string, print results
        String output = String.valueOf(encryptedText);
        System.out.println("\nEncrypted Text: " + output);
    }





	//used to handle all decryption------------------------------------------------------------------------------
	public static String decrypt(String text, int shiftValue)//strLength was removed 4/14/21 8:16
	{
		//vars
		int ascii;
		char[] encryptedText = new char[100];

		//increment through text to assign ascii and encryptedText
		for(int i = 0; i < text.length(); i++)
		{
			if(text.charAt(i) >= 65 && text.charAt(i) <= 90 || text.charAt(i) >= 97 && text.charAt(i) <= 122)
			{
				encryptedText[i] = text.charAt(i);
				//ascii = (int)text[i] + shiftValue;
				ascii = (int)text.charAt(i) - shiftValue;
				encryptedText[i] = (char)ascii;
			}

			else
			{
				encryptedText[i] = text.charAt(i);
			}
		}

		//change encryptedText to string, print results
		String output = String.valueOf(encryptedText);
        System.out.println("\nDecrypted Text: " + output);

        return output;
	}





	//bruteforce mode allows the user to decipher an encrpyted text with an unknown shift value----------------------
	public static void bruteforce(String text)
	{
		//vars, objects
		boolean correct = false;
		String choice = "";
		char decryptedText[] = new char[text.length()];
		//char letter;
		Scanner keyboard = new Scanner(System.in);

		//assign decryptedText value to text
		for(int i = 0; i < text.length(); i++)
		{
			decryptedText[i] = text.charAt(i);
		}

		//ask user if the bruteforced text looks correct
		for(int i = 0; correct == false && i < 25; i++)//25 possible shift values
		{
			//print
			System.out.println();
			for(int index = 0; index < decryptedText.length; index++)
				System.out.print(decryptedText[index]);

			//check if correct
			System.out.print("\nDoes the message look correct (y/n): ");
			choice = keyboard.nextLine();

			if(choice.equals("Y") || choice.equals("y"))
				correct = true;
			else
			{
				for(int index = 0; index < text.length(); index++)
				{
					if(decryptedText[index] >= 65 && decryptedText[index] <= 90 || decryptedText[index] >= 97 && decryptedText[index] <= 122)
						decryptedText[index] -= 1;
				}
			}
		}



	}

}

/*Part 3 Results:

---Caesar Cipher---
Enter Message (Plaintext/ Encrypted): "I am Groot." - Groot, Guardians of the Galaxy
Do you want to encrypt or decrypt: encrypt
Enter the Shift Value: 3

Encrypted Text: "L dp Jurrw." - Jurrw, Jxdugldqv ri wkh Jdod{|



---Caesar Cipher---
Enter Message (Plaintext/ Encrypted): "L dp Jurrw." - Jurrw, Jxdugldqv ri wkh Jdod{|
Do you want to encrypt or decrypt: decrypt
Is the shift key known (y/n): n

"L dp Jurrw." - Jurrw, Jxdugldqv ri wkh Jdod{|
Does the message look correct (y/n): n

"K co Itqqv." - Itqqv, Iwctfkcpu qh vjg Icnc{|
Does the message look correct (y/n): n

"J bn Hsppu." - Hsppu, Hvbsejbot pg uif Hbmb{|
Does the message look correct (y/n): n

"I am Groot." - Groot, Guardians of the Gala{|
Does the message look correct (y/n): y



---Caesar Cipher---
Enter Message (Plaintext/ Encrypted): “Ny S vyyu dy lo sx k qkwsxq wyyn?” – Dryb
Do you want to encrypt or decrypt: decrypt
Is the shift key known (y/n): n

“Ny S vyyu dy lo sx k qkwsxq wyyn?” – Dryb
Does the message look correct (y/n): n

“Mx R uxxt cx kn rw j pjvrwp vxxm?” – Cqxa
Does the message look correct (y/n): n

“Lw Q twws bw jm qv i oiuqvo uwwl?” – Bpw`
Does the message look correct (y/n): n

“Kv P svvr av il pu h nhtpun tvvk?” – Aov`
Does the message look correct (y/n): n

“Ju O ruuq `u hk ot g mgsotm suuj?” – @nu`
Does the message look correct (y/n): n

“It N qttp `t gj ns f lfrnsl rtti?” – @mt`
Does the message look correct (y/n): n

“Hs M psso `s fi mr e keqmrk qssh?” – @ls`
Does the message look correct (y/n): n

“Gr L orrn `r eh lq d jdplqj prrg?” – @kr`
Does the message look correct (y/n): n

“Fq K nqqm `q dg kp c icokpi oqqf?” – @jq`
Does the message look correct (y/n): n

“Ep J mppl `p cf jo b hbnjoh nppe?” – @ip`
Does the message look correct (y/n): n

“Do I look `o be in a gaming mood?” – @ho`
Does the message look correct (y/n): y
*/