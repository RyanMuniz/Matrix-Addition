// Name: Ryan Muniz
// Email: rmuniz15@student.cnm.edu
// Class: CSCI 2260: Section R01
// Assignment: Week 10: Matrix Addition
// Purpose: Handles the addition of one assigned matrix quadrant
// in a seperate thread, and stores that partial result in the
// shared result matrix.
// FileName: "ThreadOperation.java"
// Date: March 29, 2026

public class ThreadOperation extends Thread {
    // Store reference to first and second input matrix
    private int[][] A;
    private int[][] B;
    // Store reference for resulting matrix (sum)
    private int[][] C;
    // Store quadrant this thread is going to process
    private String quadrant;

    /*
    Constructor
    Creates a ThreadOperation object.
    Recieves references to two input matrices, result matrix,
    and the name of the quadrant that will be summed.
    */
    public ThreadOperation(int[][] A, int[][] B, int [][]C, String quadrant) {
        // Save references to objects instance variable
        this.A = A;
        this.B = B;
        this.C = C;
        this.quadrant = quadrant;
    }
    /*
    run method
    Contains the work this thread performs.
    Determines the row and column range for the assigned quadrant,
    then adds the elements from A and B which is then stored in C.
    */
    public void run() {
        // Find the start and end indexes for assigned quadrant
        int[] indexes = getQuadrantIndexes(A.length, A[0].length, quadrant);
        // Store starting and ending indexes for the quadrant
        int rowStart = indexes[0];
        int rowEnd = indexes[1];
        int columnStart = indexes[2];
        int columnEnd = indexes[3];
        // Loop through each row that belongs to this quadrant
        for (int row = rowStart; row <= rowEnd; row++) {
            // Loop through each column that belongs to this quadrant
            for (int column = columnStart; column <= columnEnd; column++) {
                // Add the two matching values and store the sum in C
                C[row][column] = A[row][column] + B[row][column];
            }
        }
    }
    /*
    getQuadrantIndexes method
    Calculates the row and column boundaries for a quadrant.
    Returns an array of four values in the order:
    row start, row end, column start, column end
    */
    public static int[] getQuadrantIndexes(int rows, int columns, String quadrant) {
        // Create array that will store the four values
        int[] indexes = new int[4];
        // Calculate indexes for each quadrant
        int upperRowEnd = (rows / 2) - 1;
        int lowerRowStart = rows / 2;
        int leftColumnEnd = (columns / 2) - 1;
        int rightColumnStart = columns / 2;
        // Check to see if this thread is responsible for upper-left quadrant
        if (quadrant.equalsIgnoreCase("upper left")) {
        indexes[0] = 0;
        indexes[1] = upperRowEnd;
        indexes[2] = 0;
        indexes[3] = leftColumnEnd;
        }
        // Is it responsible for the upper-right quadrant
        else if (quadrant.equalsIgnoreCase("upper right")) {
            indexes[0] = 0;
            indexes[1] = upperRowEnd;
            indexes[2] = rightColumnStart;
            indexes[3] = columns - 1;
        }
        // Is it responsible for the lower-left quadrant
        else if (quadrant.equalsIgnoreCase("lower left")) {
            indexes[0] = lowerRowStart;
            indexes[1] = rows - 1;
            indexes[2] = 0;
            indexes[3] = leftColumnEnd;
        }
        // If none of the above then, is it lower-right quadrant
        else {
            indexes[0] = lowerRowStart;
            indexes[1] = rows - 1;
            indexes[2] = rightColumnStart;
            indexes[3] = columns - 1;
        }
        // Return the completed index array
        return indexes;
    }
    
}
