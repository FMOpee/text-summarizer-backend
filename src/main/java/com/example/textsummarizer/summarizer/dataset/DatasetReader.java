package com.example.textsummarizer.summarizer.dataset;

import java.io.*;
import java.util.Scanner;

public class DatasetReader {
    public static WordVectorDataset wvd;
    public static void readFromTextFile() throws FileNotFoundException {
        wvd = new WordVectorDataset();
        Scanner sc = new Scanner(new File("cc.bn.300.vec"));
        sc.nextLine();
        int j=0;
        int percentage=0;
        String progressbar="";
        while(sc.hasNext()){
            String line = sc.nextLine();
            String[] lineSplit = line.split(" ",0);


            System.out.println(((j++)/10000)+"/146. "+lineSplit[0]);


            float[] values = new float[300];
            for (int i=1;i<301;i++){
                values[i-1] = Float.parseFloat(lineSplit[i]);
            }
            wvd.dataset.put(lineSplit[0],values);
        }
        System.out.println("hashing done");
    }

    public static void objectInput() throws IOException, ClassNotFoundException {
        ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("wordVectorData.obj"));
        wvd = (WordVectorDataset) objIn.readObject();
        objIn.close();
        System.out.println("reading done");
    }

    public static void objectOutput() throws IOException {
        ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream("wordVectorData.obj"));
        objOutput.writeObject(wvd);
        objOutput.close();
        System.out.println("saving done");
    }


}
