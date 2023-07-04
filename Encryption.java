/*
 * Student Name: Wei Tang
 * Lab Professor: Aman Kahlon
 * Due Date: Jul 15
 * Modified: Jun 18
 * Description: Lab07 Encryption class
 */

import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Encryption {
	private static BufferedInputStream inputStream;
	private static BufferedOutputStream outputStream;
	
    public static void main(String[] args) {
    	// Prompt the user for the path of file
    	System.out.print("Input the path of file to be encrpted:");
    	Scanner input = new Scanner(System.in);
    	String filePath = input.nextLine();
    	
    	try {
    		// Encrypt the file
	    	String destinationPath = getDestinationPath(filePath, "Encrypted");
	        encrypt(filePath, destinationPath);
	        System.out.printf("Encrypted successfully. File( %s ) created.%n", destinationPath);
	
	        // Decrypt the encrypted file
	        filePath = destinationPath;
	        destinationPath = getDestinationPath(filePath, "Decrypted");
	        decrypt(filePath, destinationPath);
	        System.out.printf("Decrypted successfully. File( %s ) created.%n", destinationPath);
	        
    	} catch (FileNotFoundException e) {
    		System.out.printf("Error: the given file (%s) does not exit.%n", filePath);
    		e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	input.close();
        }
    }

    private static String getDestinationPath(String filePath, String suffix) {
    	int dotIndex = filePath.lastIndexOf('.');
        return filePath.substring(0, dotIndex) + suffix + filePath.substring(dotIndex);
    }

    private static void encrypt(String sourceFilePath, String destinationFilePath) throws IOException {
    	try  {
        	FileInputStream fileInputStream = new FileInputStream(sourceFilePath);
        	inputStream = new BufferedInputStream(fileInputStream);
        	
        	FileOutputStream fileOutputStream = new FileOutputStream(destinationFilePath);
            outputStream = new BufferedOutputStream(fileOutputStream);
            
            int byteRead;
            while ((byteRead = inputStream.read()) != -1) {
                byte encryptedByte = (byte) (byteRead + 7);
                outputStream.write(encryptedByte);
            }
        } finally {
        	if (inputStream != null) inputStream.close();
        	if (outputStream != null) outputStream.close();
        }
    }

    private static void decrypt(String sourceFilePath, String destinationFilePath) throws IOException {
        try {
        	FileInputStream fileInputStream = new FileInputStream(sourceFilePath);
        	inputStream = new BufferedInputStream(fileInputStream);
        	
        	FileOutputStream fileOutputStream = new FileOutputStream(destinationFilePath);
            outputStream = new BufferedOutputStream(fileOutputStream);
            
            int byteRead;
            while ((byteRead = inputStream.read()) != -1) {
                byte decryptedByte = (byte) (byteRead - 7);
                outputStream.write(decryptedByte);
            }
            
        } finally {
        	if (inputStream != null) inputStream.close();
        	if (outputStream != null) outputStream.close();
        }
    }
}

		
