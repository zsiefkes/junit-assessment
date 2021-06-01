import java.util.Arrays;

public class MyMath {
	
	
	/**
	 * Add two integers and return the result.
	 * @param Integer a
	 * @param Integer b
	 * @return	The sum if adding a and b i.e. a + b
	 */
	int add(int a, int b) {
		return a + b;
	}
	
	/**
	 * Substract one integer from another and return the result.
	 * @param a	Integer a to be subtracted from.
	 * @param b	Integer b to be subtracted from a.
	 * @return	Integer result of subtracting b from a i.e. a - b. 
	 */
	int substract(int a, int b) {
		return a - b;
	}
	
	/**
	 * Multiply two integers and return the result.
	 * @param a	First integer to be multiplied.
	 * @param b Second integer to be multiplied.
	 * @return	The resulting integer product of a and b, i.e. a * b.
	 */
	int multiply(int a, int b) {
		return a * b;
	}

	/**
	 * Divide one integer by another integer and return the resulting double.
	 * @param a	Integer a to be divided
	 * @param b	Integer b to be divided into a
	 * @return	The double resulting from a divided by b.
	 * @throws Exception	Cannot divide by zero exception thrown if b = 0, i.e. if attempt is made to divide by zero.
	 */
	double divide(int a, int b) throws Exception {
		// Prevent attempt at division by zero.
		if (b == 0) {
			throw new Exception("Cannot divide by zero.");
		}
		return (double)((double)a / (double)b);
	}
	
	
	
	
	/**
	 * Compute the dot product of two integer arrays (representing vectors) and return the product. Arrays must be of the same length and only contain integers.
	 * @param vector1	an integer array representing a vector
	 * @param vector2	an integer array representing a vector
	 * @return			the resulting product as the sole element of an integer array representing a one-dimensional vector.
	 * @throws Exception	Arrays must be of the same length exception thrown if the 
	 */
	public int[] vectorProduct(int[] vector1, int[] vector2) throws Exception {
		if (vector1.length != vector2.length) {
			throw new Exception("Arrays must be of the same length.");
		}
		int[] result = new int[1];
		int product = 0;
		for (int i=0; i<vector1.length; i++) {
			product += vector1[i] * vector2[i];
		}
		result[0] = product;
		return result;
	}
	
	
	
	
	/**
	 * Compute the product of a matrix and a vector and return the resulting vector. Matrix and vector must contain only integers.
	 * @param matrix	An m x n matrix i.e. a matrix containing m rows and n columns, represented by a 2-dimensional integer array.
	 * @param vector	An n x 1 vector i.e. a vector consisting of n rows and 1 column. For consistency, this should be a 2-dimensional integer array containing three 1-dimensional vectors as rows, however for simplicity it will be represented by an integer array.
	 * @return	The resulting m x 1 vector 
	 * @throws Exception	Matrix and vector dimensions must be internally compatible to allow for multiplication.  
	 */
	public int[] matrixVectorProduct(int[][] matrix, int[] vector) throws Exception {
		
		// check number of columns of matrix matches number of elements in vector
		if (matrix[0].length != vector.length) {
			throw new Exception("Matrix and vector dimensions must be compatible to compute product.");
		}
		
		// check for consistency in matrix row lengths
		for (int i = 1; i < matrix.length; i++) {
			if (matrix[i].length != matrix[0].length) {
				throw new Exception("Matrix rows must be of consistent length.");
			}
		}
		
		// represent column vector as int[][] with vector.length rows and 1 column.
		int[][] colVector = new int[vector.length][1];
		for (int i = 0; i < vector.length; i++) {
			colVector[i][0] = vector[i];
		}
		
		// compute matrix product using matrix product method
		int[][] product = matrixProduct(matrix, colVector);
		
		// obtain length of matrix vector product
		int productSize = product.length;
		
		// convert column vector back to one-dimensional integer array to return.
		int[] vectorProduct = new int[productSize];
		for (int i = 0; i < productSize; i++) {
			vectorProduct[i] = product[i][0];
		}
		
		return vectorProduct;
	}
	
	
	
	
	
	/**
	 * Check whether a matrix represented by a two-dimensional integer array is the identity matrix and return a boolean indicating so. Non-square matrices will return false.
	 * @param matrix	A 2-d integer array to be checked.
	 * @return	A boolean indicating whether the matrix is identical to the identity matrix.
	 * @throws Exception If provided matrix is not square.
	 */
	public boolean isIdentityMatrix(int[][] matrix) throws Exception {
		
		// matrix must be square to be the identity matrix. I reckon it makes more sense to return an exception in this case. 'cause it needs to be a square matrix
		if (matrix.length != matrix[0].length) {
			throw new Exception("Matrix must be square to be an identity matrix.");
		}

		// check for consistency in matrix row lengths
		for (int i = 1; i < matrix.length; i++) {
			if (matrix[i].length != matrix[0].length) {
				throw new Exception("Matrix rows must be of consistent length.");
			}
		}
		
		for (int i = 0; i < matrix.length; i++) {
			// check for ones on the diagonal
			if (matrix[i][i] != 1) {
				return false;
			}
			// check for zeroes elsewhere
			for (int j = 0; j < matrix.length; j++) {
				if ((i != j) && (matrix[i][j] != 0)) {
					return false;
				}
			}
		}
		// if execution reaches this point, matrix is identity matrix.
		return true;
	}
	
	
	
	/**
	 * Multiply two 2-dimensional integer arrays and return the resulting 2-d integer array. The two matrices must have compatible dimensions i.e. matrix1 is m x n and matrix 2 is n x o. Note that the dimensions represent the number of rows x number of columns, i.e. the length of each column x the length of each row. Hence, matrix[1][2] represents the element in the second row, third column of the integer array matrix. Each int[] in the int[][] matrix is a row. To extract eg. the first column, you would take matrix[0][0], matrix[1][0] ... matrix [n][0];
	 * @param matrix1	A 2-d integer array representing an m x n matrix, where m is the number of rows and n is the number of columns.
	 * @param matrix2	A 2-d integer array representing an n x o matrix, where n is the number of rows (length of each column) and o is the number of columns (length of each row).
	 * @return			A 2-d integer array of dimensions m x o.
	 * @throws Exception 
	 */
	public int[][] matrixProduct(int[][] matrix1, int[][] matrix2) throws Exception {
		
		// check for consistency in matrix row lengths
		for (int i = 1; i < matrix1.length; i++) {
			if (matrix1[i].length != matrix1[0].length) {
				throw new Exception("Matrices must have consistent row lengths.");
			}
		}
		for (int i = 1; i < matrix2.length; i++) {
			if (matrix2[i].length != matrix2[0].length) {
				throw new Exception("Matrices must have consistent row lengths.");
			}
		}
				
		// throw exception if matrix dimensions are incompatible
		int rows1 = matrix1.length, cols1= matrix1[0].length;
		int rows2 = matrix2.length, cols2 = matrix2[0].length;
		if (cols1 != rows2) {
			throw new Exception("Matrix dimensions are not compatible for multiplication.");
		}
		
		// initialize product matrix
		int[][] result = new int[rows1][cols2];
		
		// iterate through rows of matrix 1 
		for (int i=0; i<rows1; i++) {
			// iterate through columns of matrix 2
			for (int j = 0; j < cols2; j++) {
				
				int product = 0;
				// compute product as vector product of ith row of matrix1 and jth column of matrix 2.
				for (int k = 0; k < cols1; k++) {
					product += matrix1[i][k] * matrix2[k][j];
				}
				result[i][j] = product;
			}
		}
		return result;
	}
	
	
	
	/**
	 * Compute and return the transpose of an integer matrix, i.e. a matrix containing the same elements with the rows cast as columns and vice versa.
	 * @param matrix	An integer matrix with m rows, n columns.
	 * @return		The transpose of the matrix with n rows and m columns. 
	 * @throws Exception 
	 */
	public int[][] transposeMatrix(int[][] matrix) throws Exception {
		
		// check for consistency in matrix row lengths
		for (int i = 1; i < matrix.length; i++) {
			if (matrix[i].length != matrix[0].length) {
				throw new Exception("Matrix rows must be of consistent length.");
			}
		}
		
		int matrixRows = matrix.length;
		int matrixCols = matrix[0].length;
		
		// initialize transposed matrix
		int[][] transpose = new int[matrixCols][matrixRows];
		
		// iterate over matrix rows
		for (int i = 0; i < matrixRows; i++) {
			// iterate over matrix columns
			for (int j = 0; j < matrixCols; j++) {
				// insert element into row j, col i of transpose
				transpose[j][i] = matrix[i][j];
			}
		}

		return transpose;
	}
}
