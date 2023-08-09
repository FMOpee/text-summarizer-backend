package com.example.textsummarizer.summarizer.controledEnvironment;

import com.example.textsummarizer.summarizer.EigenPoint;
import com.example.textsummarizer.summarizer.EigenVectorAndValue;
import com.example.textsummarizer.summarizer.KMeansClustering;
import com.example.textsummarizer.summarizer.MatrixCalculator;

import java.util.ArrayList;

public class ControlledSummerizer {

    private double[][] affinityMatrix/* = new double[][]{
            {1.00, 0.85, 0.80, 0.90, 0.25, 0.20, 0.15, 0.20, 0.15, 0.10, 0.10, 0.10, 0.05, 0.05, 0.05, 0.10, 0.10, 0.10, 0.15, 0.20},
            {0.85, 1.00, 0.90, 0.80, 0.20, 0.25, 0.20, 0.15, 0.10, 0.15, 0.10, 0.10, 0.05, 0.10, 0.05, 0.10, 0.15, 0.10, 0.10, 0.15},
            {0.80, 0.90, 1.00, 0.85, 0.15, 0.20, 0.25, 0.20, 0.10, 0.10, 0.15, 0.10, 0.05, 0.05, 0.10, 0.15, 0.10, 0.15, 0.20, 0.10},
            {0.90, 0.80, 0.85, 1.00, 0.20, 0.15, 0.20, 0.25, 0.10, 0.10, 0.10, 0.15, 0.10, 0.10, 0.05, 0.10, 0.15, 0.20, 0.10, 0.15},
            {0.25, 0.20, 0.15, 0.20, 1.00, 0.85, 0.30, 0.25, 0.20, 0.15, 0.10, 0.10, 0.05, 0.05, 0.05, 0.10, 0.10, 0.10, 0.15, 0.20},
            {0.20, 0.25, 0.20, 0.15, 0.85, 1.00, 0.90, 0.30, 0.15, 0.20, 0.15, 0.10, 0.10, 0.10, 0.05, 0.10, 0.10, 0.10, 0.10, 0.15},
            {0.15, 0.20, 0.25, 0.20, 0.30, 0.90, 1.00, 0.85, 0.10, 0.15, 0.20, 0.15, 0.05, 0.10, 0.10, 0.10, 0.10, 0.10, 0.15, 0.10},
            {0.20, 0.15, 0.20, 0.25, 0.25, 0.30, 0.85, 1.00, 0.05, 0.10, 0.15, 0.20, 0.10, 0.10, 0.05, 0.10, 0.10, 0.10, 0.10, 0.15},
            {0.15, 0.10, 0.10, 0.10, 0.20, 0.15, 0.10, 0.05, 1.00, 0.85, 0.30, 0.25, 0.20, 0.15, 0.10, 0.10, 0.10, 0.15, 0.20, 0.15},
            {0.10, 0.15, 0.10, 0.10, 0.15, 0.20, 0.15, 0.10, 0.85, 1.00, 0.90, 0.30, 0.15, 0.20, 0.15, 0.10, 0.10, 0.10, 0.10, 0.15},
            {0.10, 0.10, 0.15, 0.10, 0.10, 0.15, 0.20, 0.15, 0.30, 0.90, 1.00, 0.85, 0.10, 0.15, 0.20, 0.15, 0.10, 0.15, 0.20, 0.10},
            {0.10, 0.10, 0.10, 0.15, 0.10, 0.10, 0.15, 0.20, 0.25, 0.30, 0.85, 1.00, 0.15, 0.10, 0.15, 0.20, 0.10, 0.10, 0.10, 0.15},
            {0.05, 0.05, 0.05, 0.10, 0.05, 0.10, 0.05, 0.10, 0.20, 0.15, 0.10, 0.15, 1.00, 0.85, 0.30, 0.25, 0.20, 0.15, 0.10, 0.10},
            {0.05, 0.10, 0.05, 0.10, 0.05, 0.10, 0.10, 0.05, 0.15, 0.20, 0.15, 0.10, 0.85, 1.00, 0.90, 0.30, 0.15, 0.20, 0.15, 0.10},
            {0.05, 0.05, 0.10, 0.05, 0.05, 0.05, 0.10, 0.15, 0.10, 0.15, 0.20, 0.15, 0.30, 0.90, 1.00, 0.85, 0.10, 0.15, 0.20, 0.15},
            {0.10, 0.10, 0.15, 0.10, 0.10, 0.10, 0.15, 0.20, 0.05, 0.10, 0.15, 0.20, 0.25, 0.30, 0.85, 1.00, 0.15, 0.10, 0.15, 0.20},
            {0.10, 0.15, 0.10, 0.15, 0.10, 0.15, 0.10, 0.15, 0.10, 0.10, 0.10, 0.10, 0.20, 0.15, 0.10, 0.15, 1.00, 0.85, 0.30, 0.25},
            {0.10, 0.10, 0.15, 0.20, 0.10, 0.10, 0.10, 0.10, 0.15, 0.10, 0.15, 0.10, 0.15, 0.20, 0.15, 0.10, 0.85, 1.00, 0.90, 0.30},
            {0.10, 0.10, 0.20, 0.15, 0.10, 0.10, 0.10, 0.10, 0.20, 0.15, 0.20, 0.15, 0.10, 0.15, 0.20, 0.15, 0.30, 0.90, 1.00, 0.85},
            {0.15, 0.20, 0.10, 0.15, 0.15, 0.15, 0.10, 0.15, 0.15, 0.10, 0.10, 0.15, 0.10, 0.10, 0.15, 0.20, 0.25, 0.30, 0.85, 1.00},
    }*/;

    private ArrayList<float[]> points= new ArrayList<>();
    private double[][] degreeMatrix;
    private double[][] graphLaplacian;

    private double secondEigenValue;
    private EigenVectorAndValue eigenVectorAndValue;

    private int clusterNumber;
    private ArrayList<EigenPoint> eigenPoints;
    private ArrayList<ArrayList<EigenPoint>> clusters;



    public void driver() throws Exception {

        points.add(new float[]{0,0});
        points.add(new float[]{0,1});
        points.add(new float[]{0,2});
        points.add(new float[]{1,1});

        points.add(new float[]{5,5});
        points.add(new float[]{5,4});
        points.add(new float[]{4,5});
        points.add(new float[]{4,4});

        points.add(new float[]{8,9});
        points.add(new float[]{8,8});
        points.add(new float[]{9,8});
        points.add(new float[]{9,9});

        points.add(new float[]{18,18});
        points.add(new float[]{18,19});
        points.add(new float[]{19,19});
        points.add(new float[]{19,18});

        points.add(new float[]{23,22});
        points.add(new float[]{22,23});
        points.add(new float[]{22,22});
        points.add(new float[]{23,23});

        affinityMatrix = MatrixCalculator.getAffinityMatrix(points);
        printMatrix(affinityMatrix);
        System.out.println("=====normalized a========================================================");

        System.out.println("=============================================================");
        degreeMatrix = MatrixCalculator.getDegreeMatrix(affinityMatrix);
        printMatrix(degreeMatrix);
        System.out.println("=============================================================");
        graphLaplacian = MatrixCalculator.subtractMatrix(degreeMatrix,affinityMatrix);
        printMatrix(graphLaplacian);
        System.out.println("=============================================================");
        graphLaplacian = MatrixCalculator.lSymNormalize2(graphLaplacian);
        printMatrix(graphLaplacian);
        eigenVectorAndValue = MatrixCalculator.getEigenValueAndEigenVectors(graphLaplacian);
        eigenVectorAndValue.sort();
        eigenVectorAndValue.printEV();
        clusterNumber = eigenVectorAndValue.getClusterNumber();
        System.out.println("Number of clusters: "+clusterNumber);
        eigenPoints = eigenVectorAndValue.getEigenPoints();
        KMeansClustering clusterUtil = new KMeansClustering();
        clusters = clusterUtil.kMeansClustering(eigenPoints,clusterNumber);
        for(ArrayList<EigenPoint> cluster:clusters){
            System.out.println("cluster:");
            for (EigenPoint e:cluster){
                System.out.print(e.getPos()+" ");
            }
            System.out.println("==================================");
        }
    }




    private void printMatrix(double[][] matrix){
        for (double[] row: matrix){
            for(double cell: row){
                System.out.printf("%4.3f  ",cell);
            }
            System.out.println();
        }
    }
}
