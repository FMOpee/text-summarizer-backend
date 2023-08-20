package com.example.textsummarizer.summarizer;

import org.apache.commons.math3.linear.*;

import java.util.ArrayList;

import static java.lang.Math.*;

public class MatrixCalculator {
    public static double[][] getAffinityMatrix(ArrayList<double[]> vectorField) {
        double[][] affinityMatrix = new double[vectorField.size()][vectorField.size()];

        double sigma = 10;
        for (int i = 0; i < vectorField.size(); i++) {
            for (int j = 0; j < vectorField.size(); j++) {
                if(i==j) affinityMatrix [i][j] = 0;
                else affinityMatrix[i][j] =
                        exp(
                                -1 * pow(
                                        distance(vectorField.get(i), vectorField.get(j)),
                                        2
                                ) / pow(sigma, 2)
                        );
            }
        }

        return affinityMatrix;
    }

    private static double distance(double[] a, double[] b) {
        double sumOfSquare = 0;
        for (int i = 0; i < a.length; i++) {
            sumOfSquare += pow(a[i] - b[i], 2);
        }
        return sqrt(sumOfSquare);
    }

    public static double[][] getDegreeMatrix(double[][] affinityMatrix) {
        double[][] degreeMatrix = new double[affinityMatrix.length][affinityMatrix[0].length];

        for (int i = 0; i < degreeMatrix[0].length; i++) {
            double value = 0;
            for (int j = 0; j < degreeMatrix.length; j++) {
                value += affinityMatrix[j][i];
            }
            degreeMatrix[i][i] = value;
        }

        return degreeMatrix;

    }

    public static double[][] subtractMatrix(double[][] A, double[][] B) throws Exception {
        double[][] result = new double[A.length][A[0].length];
        if (A.length != B.length && A[0].length != B[0].length) throw new Exception("Dimension doesn't match");

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = A[i][j] - B[i][j];
            }
        }

        return result;
    }

    public static EigenVectorAndValue getEigenValueAndEigenVectors(double[][] matrixData) {

        RealMatrix matrix = new Array2DRowRealMatrix(matrixData);

        // Perform eigen decomposition
        EigenDecomposition decomposition = new EigenDecomposition(matrix);

        // Get eigenvalues and eigenvectors
        double[] eigenvalues = decomposition.getRealEigenvalues();
//        eigenvalues = Arrays.stream(eigenvalues).sorted().toArray();
        RealMatrix eigenvectors = decomposition.getV();

//        // Print results
//        System.out.println("Eigenvalues:");
//        for (double value : eigenvalues) {
//            System.out.printf("%.2f | ",value);
//        }
//        System.out.println();
//        System.out.print("    ");
//        for (int i=1;i<eigenvalues.length;i++){
//            System.out.printf("%.2f | ", (eigenvalues[i]-eigenvalues[i-1]) );
//        }

        ArrayList<double[]> vectors = new ArrayList<>();

//        System.out.println("\nEigenvectors:");
        for (int i = 0; i < eigenvectors.getRowDimension(); i++) {
            RealVector eigenvector = eigenvectors.getRowVector(i);
            vectors.add(  eigenvector.toArray());
//            System.out.println(eigenvector);
        }


        EigenVectorAndValue ret = new EigenVectorAndValue(vectors,eigenvalues);

        return ret;
    }

    public static double[][] normalizeMatrix(double[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Matrix dimensions must be greater than 0.");
        }

        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;

        // Find the minimum and maximum values in the matrix
        for (double[] row : matrix) {
            for (double value : row) {
                min = Math.min(min, value);
                max = Math.max(max, value);
            }
        }

        // Normalize the matrix using min-max normalization
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (matrix[i][j] - min) / (max - min);
            }
        }

        return matrix;
    }


    public static double[][] lSymNormalize2(double[][] laplacianMatrix) {
        int n = laplacianMatrix.length;
        double[][] normalizedLaplacian = new double[n][n];

        // Calculate the diagonal degree matrix D
        double[] diagonalDegree = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0.0;
            for (int j = 0; j < n; j++) {
                sum += Math.abs(laplacianMatrix[i][j]);
            }
            diagonalDegree[i] = Math.sqrt(sum);
        }

        // Calculate the symmetrically normalized Laplacian matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                normalizedLaplacian[i][j] = (laplacianMatrix[i][j] / (diagonalDegree[i] * diagonalDegree[j]));
            }
        }

        return normalizedLaplacian;
    }

    public static double[][] lRwNormalizeLaplacian2(double[][] laplacianMatrix) {
        int n = laplacianMatrix.length;
        double[][] normalizedLaplacian = new double[n][n];

        // Calculate the diagonal degree matrix D
        double[] diagonalDegree = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0.0;
            for (int j = 0; j < n; j++) {
                sum += Math.abs(laplacianMatrix[i][j]);
            }
            diagonalDegree[i] = sum;
        }

        // Calculate the symmetrically normalized Laplacian matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                normalizedLaplacian[i][j] = (laplacianMatrix[i][j] / diagonalDegree[i]);
            }
        }

        return normalizedLaplacian;
    }

    public static double[][] lSymLaplacianAccordingToWikipedia(double[][] matrix){
        double[][] normalized = new double[matrix.length][matrix[0].length];
        for (int i=0;i<matrix.length;i++){
            for(int j=0; j<matrix[i].length;j++){
                if(i==j && matrix[i][j]!=0) normalized[i][j] =1;
                else if(matrix[i][j]!=0) normalized[i][j] = -1/sqrt(matrix[i][i]*matrix[j][j]);
                else normalized[i][j] =0;
            }
        }
        return normalized;
    }

    public static double[][] lRWLaplacianAccordingToWikipedia(double[][] matrix){
        double[][] normalized = new double[matrix.length][matrix[0].length];
        for (int i=0;i<matrix.length;i++){
            for(int j=0; j<matrix[i].length;j++){
                if(i==j && matrix[i][j]!=0) normalized[i][j] =1;
                else if(matrix[i][j]!=0) normalized[i][j] = -1/matrix[i][i];
                else normalized[i][j] =0;
            }
        }
        return normalized;
    }
}

