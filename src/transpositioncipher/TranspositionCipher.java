/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transpositioncipher;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author hal-9000
 */
public class TranspositionCipher {

    private static final int[] CIPHER_ARRAY = {6, 3, 2, 4, 1, 5};

    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);
        String originalMessage;

        System.out.println("Please provide original message to be encrypted:");
        originalMessage = kb.nextLine();
        originalMessage = originalMessage.replaceAll("\\s+", "");

        char[][] messageCipher = encryptText(originalMessage);
        String finalCipherText = getFinalCipherText(messageCipher);

        System.out.println(finalCipherText);
        print2DMessageCipher(messageCipher);
        char[][] transposerMessage = print2DcipherText(finalCipherText);
        //decryptMessage(transposerMessage);

    }

    private static void showMainMenu() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Welcome to cypher machine!");
        System.out.println("--------------------------");
        System.out.println("1 - Encrypt");
        System.out.println("2 - Decrypt");
        int option = reader.nextInt();

        switch (option) {

            case 1:
                System.out.println("Enter text");
                String plainText = reader.nextLine();
                encryptText(plainText);
                break;
            case 2:
                System.out.println("Enter cipher text");
                String cipherText = reader.nextLine();
                //decryptCipherText(cipherText);
                break;
        }

    }

    private static char[][] encryptText(String planText) {
        Random random = new Random();
        int rowOfMatrix = (int) Math.ceil(planText.length() / (double) CIPHER_ARRAY.length);
        System.out.println("You need " + rowOfMatrix + " rows.");
        char[][] messageCipher = new char[rowOfMatrix][CIPHER_ARRAY.length];

        int letterCounter = 0;
        for (int i = 0; i < messageCipher.length; i++) {
            for (int j = 0; j < messageCipher[i].length; j++) {
                if (letterCounter < planText.length()) {
                    messageCipher[i][j] = planText.charAt(letterCounter);
                    letterCounter++;
                } else {
                    messageCipher[i][j] = (char) (random.nextInt(26) + 'a');
                }
            }
        }
        return messageCipher;
    }

    /**
     * @param messageCipherMatrix cipherMatrix
     * @return Returning a cipher text.
     */
    private static String getFinalCipherText(char[][] messageCipherMatrix) {
        String encryptText = "";
        for (int i = 1; i <= CIPHER_ARRAY.length; i++) {
            for (int j = 0; j < CIPHER_ARRAY.length; j++) {
                if (CIPHER_ARRAY[j] == i) {
                    for (int k = 0; k < messageCipherMatrix.length; k++) {
                        encryptText += messageCipherMatrix[k][j];
                    }
                    encryptText += " ";
                    break;
                }
            }
        }
        return encryptText;
    }

    /**
     * Reading down the rows in an order based on a textual key.
     *
     * @param plainText cipher text.
     */
    private static void print2DMessageCipher(char[][] messageCipher) {
        for (int i = 0; i < messageCipher.length; i++) {
            for (int j = 0; j < messageCipher[i].length; j++) {
                System.out.print(messageCipher[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static char[][] print2DcipherText(String cipherText) {
        int letterCounter = 0;
        int rowOfMatrix = (int) Math.ceil(cipherText.length() / (double) CIPHER_ARRAY.length);
        char[][] messageCipher = new char[rowOfMatrix][CIPHER_ARRAY.length];
        char[][] transpositionGrid = new char[rowOfMatrix][CIPHER_ARRAY.length];

        System.out.println("Given matrix :: ");

        for (int i = 0; i < CIPHER_ARRAY.length; i++) {
            for (int j = 0; j < rowOfMatrix; j++) {
                if (letterCounter < cipherText.length()) {
                    messageCipher[i][j] = cipherText.charAt(letterCounter);
                    System.out.print(messageCipher[i][j] + " ");
                    letterCounter++;
                }

            }
            System.out.println();
        }
        System.out.println("Matrix after transpose :: ");
        for (int i = 0; i < CIPHER_ARRAY.length; i++) {
            for (int j = 0; j < rowOfMatrix; j++) {
                transpositionGrid[i][j] = 0;
                for (int k = 0; k < CIPHER_ARRAY.length; k++) {
                    transpositionGrid[i][j] = messageCipher[j][i];
                }
                System.out.print(transpositionGrid[i][j] + " ");
            }
            System.out.println();
        }

        return transpositionGrid;
    }

    private static void decryptMessage(char[][] transpositionGrid) {
        int rowOfMatrix = CIPHER_ARRAY.length;
        int colunmSize = CIPHER_ARRAY.length - 1;
        char[][] resultGrid = new char[rowOfMatrix][CIPHER_ARRAY.length];
        System.out.println("Matrix after transpose :: ");
        for (int i = 1; i < CIPHER_ARRAY.length; i++) {
            for (int j = 0; j < rowOfMatrix; j++) {
                System.out.print(transpositionGrid[j][i] + " ");
            }
            System.out.println();
        }
    }

}
