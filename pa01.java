/*
Author: Harold Saenz
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;



public class pa01 {

    public static void main(String[] args) {

       // System.out.println(args.length);   //delete later
        
        if(args.length < 2 || args.length > 2){
            System.out.println("Input two arguements only.\n");
            
        }

        //System.out.println("This is the matrix -->  " + args[0]);         //testing if it worked....delete later
        //System.out.println("This is the Plaintext -->  " + args[1]);      // same here......delete later
        
        File key = new File(args[0]);
        
        try{

            Scanner myScan = new Scanner(key);
            int num = Integer.parseInt(myScan.nextLine());

            int[][] matrixKey = new int[num][num];
            System.out.println("\nKey matrix:"); 
            while(myScan.hasNextLine()){
                for (int i = 0; i < num; i++){
                    String[] keyLine = myScan.nextLine().split("\\s+");
                    for(int j = 0; j < num; j++){
                        matrixKey[i][j] = Integer.parseInt(keyLine[j]);
                        System.out.print(matrixKey[i][j] + "\t");
                    }
                    System.out.println();
                }
            }
            myScan.close();

            //System.out.print(matrixKey[0][1]);      //testing if array was made...delete later
            
            File plain = new File(args[1]);
            Scanner plainScan = new Scanner(plain);

            StringBuilder sb = new StringBuilder();
            

            System.out.println();
            plainScan.close();


            plainScan = new Scanner(plain);
            while (plainScan.hasNextLine()){
                
                String plainLine = plainScan.nextLine().toLowerCase().replaceAll("([^a-zA-Z]|\\s)+", "");
                for (int i = 0; i< plainLine.length(); i++){
                    char letter = plainLine.charAt(i);
                    if (Character.isLetter(letter)) {
                        sb.append(letter);
                    }
                }
            }
            
            char[] plainArray = sb.toString().toCharArray();

            plainScan.close();
            
            int vert = matrixKey[0].length;
            int horiz =(int)Math.ceil((double)plainArray.length/vert);

            char[] padded = new char[horiz * vert];

            for (int i = 0; i < plainArray.length; i++ ){
                padded[i] = plainArray[i];
            }
           for (int i = plainArray.length; i < padded.length; i++){
                padded[i] = 'x';
            }

            plainArray = padded;
            System.out.println("\nPlaintext:");

            for (int i= 0; i < plainArray.length;i++){
                System.out.print(plainArray[i]);

                if ((i+1)%80 ==0){
                    System.out.println();
                }
            }

            System.out.println();
            //System.out.println(plainArray);

        



            int[] numericalPlain = new int[plainArray.length];

            for (int i= 0; i< plainArray.length; i++){
                numericalPlain[i]= plainArray[i] - 'a';
            }

           // System.out.println(Arrays.toString(numericalPlain));    // testing....delete later 


            int rows = (int) Math.ceil((double)numericalPlain.length / matrixKey[0].length);
            int [][] res = new int[rows][matrixKey[0].length];

            int index= 0;
            for (int i = 0; i < rows; i++){
                for (int j = 0; j < matrixKey[0].length; j++){
                    if(index < numericalPlain.length){
                        res[i][j] = numericalPlain[index];
                        index++;
                    }
                }
            }

            int [][] split = res;

/*
            System.out.println(split[0][1]); // testing... delete later
            for (int [] vector: split){
                System.out.println(Arrays.toString(vector));  // delete later
            }
 */           

            char [][] result = new char[split.length][matrixKey.length];
            System.out.println("\nCiphertext:");

            for (int i = 0; i< split.length;i++){
                int[] vector = split[i];
                for(int j = 0; j< matrixKey.length; j++){
                    int sum = 0;
                    for (int k =0; k < matrixKey[0].length; k++){
                        sum += matrixKey[j][k] * vector[k];
                    }
                    result[i][j] = (char) (sum %26+ 'a');
                    //System.out.print(result[i][j]);
                }
            }            
            //System.out.println("\n");

            for(int i = 0; i < result.length; i++){
                for(int j = 0; j < result[0].length;j++){
                    System.out.print(result[i][j]);

                    if ((i * result[i].length + j + 1)%80 ==0){
                        System.out.println();
                    }
                }
            }
            
            
/* 
            char[] letterResult = new char[result.length];
            for (int i = 0; i < result.length; i++){
                letterResult[i]= (char)((result[i]%26) + 'a');
            }
*/

        }catch(Exception e){
            e.printStackTrace();

        }

    }

}




