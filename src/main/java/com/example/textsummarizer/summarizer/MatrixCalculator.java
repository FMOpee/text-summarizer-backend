package com.example.textsummarizer.summarizer;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import java.util.ArrayList;

import static java.lang.Math.*;

public class MatrixCalculator {
    public static double[][] getAffinityMatrix(ArrayList<float[]> vectorField){
        double[][] affinityMatrix = new double[vectorField.size()][vectorField.size()];

        double sigma = 10;
        for (int i=0; i<vectorField.size();i++){
            for(int j=0;j<vectorField.size();j++){
                affinityMatrix[i][j] =
                        exp(
                                -1 * pow(
                                        distance(vectorField.get(i), vectorField.get(j)),
                                        2
                                ) / sigma
                        );
            }
        }

        return affinityMatrix;
    }

    private static double distance(float[] a, float[] b){
        double sumOfSquare = 0;
        for (int i=0; i<a.length;i++){
            sumOfSquare += pow(a[i]-b[i],2);
        }
        return sqrt(sumOfSquare);
    }

    public static double[][] getDegreeMatrix(double[][] affinityMatrix){
        double[][] degreeMatrix = new double[affinityMatrix.length][affinityMatrix[0].length];

        for(int i=0; i<degreeMatrix[0].length;i++){
            double value = 0;
            for (int j=0; j< degreeMatrix.length;j++){
                value += affinityMatrix[j][i];
            }
            degreeMatrix[i][i] = value;
        }

        return degreeMatrix;

    }

    public static double[][] subtractMatrix(double[][] A, double[][] B) throws Exception {
        double[][] result = new double[A.length][A[0].length];
        if(A.length != B.length && A[0].length != B[0].length) throw new Exception("Dimension doesn't match");

        for (int i=0; i<result.length;i++){
            for (int j=0;j<result[0].length;j++){
                result[i][j] = A[i][j]-B[i][j];
            }
        }

        return result;
    }

    public static void getEigenValueAndEigenVector(double[][] matrixData){

        RealMatrix matrix = new Array2DRowRealMatrix(matrixData);

        // Perform eigen decomposition
        EigenDecomposition decomposition = new EigenDecomposition(matrix);

        // Get eigenvalues and eigenvectors
        double[] eigenvalues = decomposition.getRealEigenvalues();
        RealMatrix eigenvectors = decomposition.getV();

        // Print results
        System.out.println("Eigenvalues:");
        for (double value : eigenvalues) {
            System.out.print(value + "\t");
        }
        System.out.println();

        System.out.println("\nEigenvectors:");
        for (int i = 0; i < eigenvectors.getRowDimension(); i++) {
            RealVector eigenvector = eigenvectors.getRowVector(i);
            System.out.println(eigenvector);
        }
    }
}
