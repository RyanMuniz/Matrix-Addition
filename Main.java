// Name: Ryan Muniz
// Email: rmuniz15@student.cnm.edu
// Class: CSCI 2260: Section R01
// Assignment: Week 10: Matrix Addition
// Purpose: Reads to matrices from an input file, creates and
// coordinates four threads to add matrices by quadrant, and
// prints the final summed matrix.
// FileName: "Main.java"
// Date: March 29, 2026

// Import necessary utilities
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Main {
    /*
    main method
    Starting point.
    Reads the matrix file name from the command-line, opens the file, reads the
    matrix sizes and contents, creates four worker threads, waits for them to
    finish, and then prints the final summed matrix.
    */
    public static void main(String[] args) throws IOException, InterruptedException {
        // Check whether the user provided a file name on the command line
        if (args.length == 0) {
            // Print message if no file name was given
            System.out.println("Usage: java Main matrix1.txt");
            // Stop program early because there is no file to read
            return;
        }
        // Store the file name so it can be opened
        String fileName = args[0];
        // Create a File object that represents the input file on disk
        File inputFile = new File(fileName);
        // Create a Scanner that will read from the input file
        Scanner fileReader = new Scanner(inputFile);
        // Read the number of rows and columns from first line of file
        int rows = fileReader.nextInt();
        int columns = fileReader.nextInt();
        // Read the first and second matrix from the file and store it in a 2D array
        int[][] matrixA = matrixFromFile(rows, columns, fileReader);
        int[][] matrixB = matrixFromFile(rows, columns, fileReader);
        // Create the result matrix that will store the sum of matrix A and B
        int[][] matrixC = new int[rows][columns];
        // Create threads to add the corresponding quadrant of the matrices
        ThreadOperation thread1 = new ThreadOperation(matrixA, matrixB, matrixC, "upper left");
        ThreadOperation thread2 = new ThreadOperation(matrixA, matrixB, matrixC, "upper right");
        ThreadOperation thread3 = new ThreadOperation(matrixA, matrixB, matrixC, "lower left");
        ThreadOperation thread4 = new ThreadOperation(matrixA, matrixB, matrixC, "lower right");
        // Start each thread so itcan begin working on its quadrant
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        // Wait for the thread to finish before continuing
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        // Print a label for the summed matrix
        System.out.println("Summed Matrix:");
        // Print the final result matrix after all the threads have been completed
        print2dArray(matrixC);
        // Close Scanner
        fileReader.close();
    }
    /*
    matrixFromFile method
    Reads one matrix from the file.
    It uses the known row count and column count to fill a 2D array
    with integers from the Scanner.
    */
    public static int[][] matrixFromFile(int rows, int columns, Scanner fileReader) {
        // Create a new 2D array to store the matrix values being read
        int[][] matrix = new int[rows][columns];
        // Loop through each row of the matrix
        for (int row = 0; row < rows; row++) {
            // Loop through each column of the current row
            for (int column = 0; column < columns; column++) {
                // Read next integer from file and store it in current matrix position
                matrix[row][column] = fileReader.nextInt();
            }
        }
        // Return completed matrix
        return matrix;
    }
    /*
    print2dArray method
    Prints a 2D int array in an aligned format.
    */
   public static void print2dArray(int[][] matrix) {
    // Loop through each row of matrix
    for (int row = 0; row < matrix.length; row++) {
        // Loop through each column in the current row
        for (int column = 0; column < matrix[row].length; column++) {
            // Print value with format for neatness
            System.out.printf("%5d", matrix[row][column]);
        }
        // After printing one full row, move to next line
        System.out.println();
    }
   }
}
