/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Asad_Zaidi
 */
public class ENN {
public static int r=0;
public static double[][] input=new double[705][3];
public static double[] output=new double[705];
public static double[] error=new double[705];
public static double[] structure=new double[12];
public static double[][] n=new double[50][12];
public static double[][] m=new double[40][12];
public static double[][] nm=new double[90][12];
public static double[][]  newIndividual=new double[1][12];
public static double[][]  newIndividual2=new double[1][12];
public static double hold1;
public static double hold2;
public static int count1;
public static int count2=1;
public static double[] weights=new double[8];
public static double[] biases=new double[3];

public static double[][] rows=new double[1][3];
    public static void main(String[] args) {
       Random rn= new Random();
       readinputs();
       readoutputs();
       
       
       //generating population 
       generatePopulation();

       
       //evolution
       
       do{
    
           for(int i=0;i<20;i++){
           int P1=rn.nextInt(49 - 0 + 1) + 0;
           int P2=rn.nextInt(49 - 0 + 1) + 0;

           
           //crossover
//           System.arraycopy(n[P1], 0, newIndividual[0], 0, 6);
           System.arraycopy(n[P1], 0, newIndividual[0], 0, 4);
           System.arraycopy(n[P2], 4, newIndividual[0], 4, 4);
           newIndividual[0][8]=n[P1][8];//crossover of biases
           newIndividual[0][9]=n[P2][9];
           newIndividual[0][10]=n[P2][10];
//           System.arraycopy(n[P2], 6, newIndividual[0], 6, 5);
           System.arraycopy(n[P2], 0, newIndividual2[0], 0, 4);
           System.arraycopy(n[P1], 4, newIndividual2[0], 4, 4);
           newIndividual2[0][8]=n[P2][8]; //crossover of biases
           newIndividual2[0][9]=n[P1][9];
           newIndividual2[0][10]=n[P1][10];

           
//           System.out.println("After Mutation\n\n\n");
           //mutation
            mutate1();
            mutate2();
            
            //calculating fitness
            fitness1();
            fitness2();

            
            //saving each to m array
            //Adding each new individual to m array
                 for(int v=0;v<2;v++){
                     if(v==0){
                         System.arraycopy(newIndividual[0], 0, m[r], 0, 12);
                     }
                 
                     if(v==1){
                         System.arraycopy(newIndividual2[0], 0, m[r], 0, 12);
                     }
                     
                     r++;
                 }
              }
           r=0;
           
//           System.out.println("m\n\n");
//            for(int k=0;k<40;k++){
//           for(int j=0;j<12;j++){
//               System.out.print(m[k][j]+"  ");
//           }
//           System.out.println();
//       }
           
            
          //n+m
          for(int v=0;v<50;v++){
           System.arraycopy(n[v], 0, nm[v], 0, 12);
             }
          
             for(int v=0;v<40;v++){
           System.arraycopy(m[v], 0, nm[v+50], 0, 12);
             }
          

            
            //sorting
            
            java.util.Arrays.sort(nm, (double[] a, double[] b) -> Double.compare(a[11], b[11]));
            

        //setting in n again
            
           for(int k=0;k<50;k++){
           System.arraycopy(nm[k], 0, n[k], 0, 12);
           }
  
              System.out.println(n[0][11]);
              
              
        if(count2<2){
        hold1=n[0][11];
        
        }
        if(count2>1){
            if(hold1!=0 && hold2==0){
             hold2=n[0][11];
            if(hold1==hold2){
                count1++;
                if(count1==300){
                
                    break;
                }
            }
            else{
                hold1=0;
                hold2=0;
                count1=0;
            }
            }
            if(hold1==0){
            hold1=n[0][11];
            
            }
            hold2=n[0][11];
            if(hold1==hold2){
                count1++;
                
                if(count1==300){
                
                    break;
                }
            }
            else{
                hold1=0;
                hold2=0;
                count1=0;
            }
        }
        
           
           
             
        count2++;
    }while(n[0][11]>0.0001);
       
   
    
    //copy into weights
    for(int i=0;i<1;i++){
           System.arraycopy(n[i], 0, weights, 0, 8);
    }
    
    for(int i=0;i<1;i++){
        int index=0;
    for(int k=8;k<11;k++){
        biases[index]=n[i][k];
        index++;
    }
    }
    
    System.out.println(Arrays.toString(weights));
     System.out.println(Arrays.toString(biases));
     
     for(int i=0;i<705;i++){
      System.arraycopy(input[i], 0, rows[0], 0, 3);
            
            //input to hidden layer 1 
           double inputH1=((weights[0]*rows[0][0])+(weights[2]*rows[0][1])+(weights[4]*rows[0][2])+biases[0]);
//            System.out.println("inputH1  "+inputH1);
           
           double xh1=-1*inputH1;
           //output to hidden layer 1
           double outputH1=  (1/(1+Math.exp(xh1)));
//           System.out.println("OutputH1 "+outputH1);
           
           //input to hidden layer 2 
           double inputH2=((weights[1]*rows[0][0])+(weights[3]*rows[0][1])+(weights[5]*rows[0][2])+biases[1]);
//         System.out.println("inputH2  "+inputH2);
           
           double xh2=-1*inputH2;
           //output to hidden layer 1
           double outputH2=  (1/(1+Math.exp(xh2)));
//           System.out.println("OutputH2 "+outputH2);
           //input at output layer
           
           double inputk=(weights[6]*outputH1)+(weights[7]*outputH2)+biases[2];
//           System.out.println("inputk  "+inputk);
           double xh3=-1*inputk;
           //output at output layer
           double outputk=  (1/(1+Math.exp(xh3)));
           
             System.out.println(outputk);
     
     }
     
     
//   
     
     
    
    
    }
    public static void mutate1(){
         Random rn= new Random();
                int mutateIndex=rn.nextInt(11 - 0 + 1) + 0;
                int check=rn.nextInt(100 - 0 + 1) + 0;
                double mutatevalue=0.01;
//                System.out.println(mutateIndex+"  "+check);
             if(mutateIndex<8){
              
              if(check%2==0){
              newIndividual[0][mutateIndex]+=mutatevalue;
              
                if(newIndividual[0][mutateIndex]>1){
                    newIndividual[0][mutateIndex]=0.9;
//              
              }
              }else{
               newIndividual[0][mutateIndex]-=mutatevalue;
//               if(newIndividual[0][mutateIndex]<-0.1){
//               newIndividual[0][mutateIndex]=-0.1;
//               }
              }
             }
             else{
               if(check%2==0){
              newIndividual[0][mutateIndex]+=mutatevalue;
              
                if(newIndividual[0][mutateIndex]>1){
                    newIndividual[0][mutateIndex]=0.9;
              
              }
              }else{
               newIndividual[0][mutateIndex]-=mutatevalue;
               if(newIndividual[0][mutateIndex]<0.1){
               newIndividual[0][mutateIndex]=0.1;
               }
              }  
                 
             }
             
           
    }
     public static void mutate2(){
         Random rn= new Random();
    int mutateIndex=rn.nextInt(11 - 0 + 1) + 0;
                int check=rn.nextInt(100 - 0 + 1) + 0;
                double mutatevalue=0.01;
//                System.out.println(mutateIndex+"  "+check);
             if(mutateIndex<8){
              
              if(check%2==0){
              newIndividual2[0][mutateIndex]+=mutatevalue;
              
                if(newIndividual2[0][mutateIndex]>1){
                    newIndividual2[0][mutateIndex]=0.9;
              
              }
              }else{
               newIndividual2[0][mutateIndex]-=mutatevalue;
//               if(newIndividual2[0][mutateIndex]<-0.1){
//               newIndividual2[0][mutateIndex]=-0.1;
//               }
              }
             }
             else{
               if(check%2==0){
              newIndividual2[0][mutateIndex]+=mutatevalue;
              
                if(newIndividual2[0][mutateIndex]>1){
                    newIndividual2[0][mutateIndex]=0.9;
              
              }
              }else{
               newIndividual2[0][mutateIndex]-=mutatevalue;
               
               if(newIndividual2[0][mutateIndex]<0.1){
               newIndividual2[0][mutateIndex]=0.1;
               }
              }  
                 
             }
             
           
    }
    
    public static void generatePopulation(){
     for(int i=0;i<50;i++){
           for(int j=0;j<705;j++){
                 System.arraycopy(input[j], 0, rows[0], 0, 3);
                 generatestructure();
                 generatebiases();
                 //input to hidden layer 1 
           double inputH1=((structure[0]*rows[0][0])+(structure[2]*rows[0][1])+(structure[4]*rows[0][2])+structure[8]);
//            System.out.println("inputH1  "+inputH1);
           
           double xh1=-1*inputH1;
           //output to hidden layer 1
           double outputH1=  (1/(1+Math.exp(xh1)));
//           System.out.println("OutputH1 "+outputH1);
           
           //input to hidden layer 2 
           double inputH2=((structure[1]*rows[0][0])+(structure[3]*rows[0][1])+(structure[5]*rows[0][2])+structure[9]);
//         System.out.println("inputH2  "+inputH2);
           
           double xh2=-1*inputH2;
           //output to hidden layer 1
           double outputH2=  (1/(1+Math.exp(xh2)));
//           System.out.println("OutputH2 "+outputH2);
           //input at output layer
           
           double inputk=(structure[6]*outputH1)+(structure[7]*outputH2)+structure[10];
//           System.out.println("inputk  "+inputk);
           double xh3=-1*inputk;
           //output at output layer
           double outputk=  (1/(1+Math.exp(xh3)));
          
//           System.out.println("outputk  "+outputk);
           
           
           //calculating error at output layer k
          double errorK=Math.abs((output[j]-outputk)*outputk*(1-outputk));
          error[j]=errorK;
          
          
            
                 
                 
           }
           double sum=0;
           for(int k=0;k<error.length;k++){
               sum+=error[k];
           }
           double fitness=sum/705;
           structure[11]=fitness;
           System.arraycopy(structure, 0, n[i], 0, 12);
      
       }
    }
    public static void readinputs(){

   String inputfile="F:\\Netbean\\TOCI\\ENN\\input.txt";
      File file=new File(inputfile);
        int row=0;
        int col=0;
        try{
            
            try (Scanner inputStream = new Scanner(file)) {
               while(inputStream.hasNext()){
                    String data=inputStream.next();
//                    System.out.println(data);
                   if(col>2){
                       row++;
                       col=0;
                   }
                   
                   double datas=Double.parseDouble(data);
                    datas/=20000;
                     input[row][col]=datas;
                    col++;
                 }
                    
            }
        } catch(FileNotFoundException e){
        
        }
}
    
     public static void readoutputs(){
   String inputfile="F:\\Netbean\\TOCI\\ENN\\output.txt";
      File file=new File(inputfile);
        int row=0;
        try{
            
            try (Scanner inputStream = new Scanner(file)) {
               while(inputStream.hasNext()){
                    String data=inputStream.next();
//                    System.out.println(data);
                    double datas=Double.parseDouble(data);
                    datas/=20000;
                     output[row]=datas;
                    row++;
                 }
                    
            }
        } catch(FileNotFoundException e){
        
        }
}
      public static void generatestructure(){

         double min=-0.5;
         double max=0.5;
         Random rn= new Random();

          for(int i=0;i<8;i++){
          double w= min + (max - min) * rn.nextDouble();
          

            structure[i]=w;

       
          }          
              
     }  
      
      public static void generatebiases(){
       
         double min=0;
         double max=0.99;
          Random rn= new Random();
          double b1= min + (max - min) * rn.nextDouble(); 
          double b2= min + (max - min) * rn.nextDouble();
          double b3= min + (max - min) * rn.nextDouble();
          
          structure[8]=b1;
          structure[9]=b2;
          structure[10]=b3;
            
     }
    public static void fitness1(){
        for(int j=0;j<705;j++){
                 System.arraycopy(input[j], 0, rows[0], 0, 3);
                
               //input to hidden layer 1 
           double inputH1=((newIndividual[0][0]*rows[0][0])+(newIndividual[0][2]*rows[0][1])+(newIndividual[0][4]*rows[0][2])+newIndividual[0][8]);
//            System.out.println("inputH1  "+inputH1);
           
           double xh1=-1*inputH1;
           //output to hidden layer 1
           double outputH1=  (1/(1+Math.exp(xh1)));
//           System.out.println("OutputH1 "+outputH1);
           
           //input to hidden layer 2 
           double inputH2=((newIndividual[0][1]*rows[0][0])+(newIndividual[0][3]*rows[0][1])+(newIndividual[0][5]*rows[0][2])+newIndividual[0][9]);
//         System.out.println("inputH2  "+inputH2);
           
           double xh2=-1*inputH2;
           //output to hidden layer 1
           double outputH2=  (1/(1+Math.exp(xh2)));
//           System.out.println("OutputH2 "+outputH2);
           //input at output layer
           
           double inputk=(newIndividual[0][6]*outputH1)+(newIndividual[0][7]*outputH2)+newIndividual[0][10];
//           System.out.println("inputk  "+inputk);
           double xh3=-1*inputk;
           //output at output layer
           double outputk=  (1/(1+Math.exp(xh3)));
          
//           System.out.println("outputk  "+outputk);
           
           
           //calculating error at output layer k
          double errorK=Math.abs((output[j]-outputk)*outputk*(1-outputk));
          error[j]=errorK;
            
                 
                 
           }
            double sum=0;
           for(int k=0;k<error.length;k++){
               sum+=error[k];
           }
           double fitness=sum/705;
           newIndividual[0][11]=fitness;
    }
    
    public static void fitness2(){
        for(int j=0;j<705;j++){
                 System.arraycopy(input[j], 0, rows[0], 0, 3);
                
               //input to hidden layer 1 
           double inputH1=((newIndividual2[0][0]*rows[0][0])+(newIndividual2[0][2]*rows[0][1])+(newIndividual2[0][4]*rows[0][2])+newIndividual2[0][8]);
//            System.out.println("inputH1  "+inputH1);
           
           double xh1=-1*inputH1;
           //output to hidden layer 1
           double outputH1=  (1/(1+Math.exp(xh1)));
//           System.out.println("OutputH1 "+outputH1);
           
           //input to hidden layer 2 
           double inputH2=((newIndividual2[0][1]*rows[0][0])+(newIndividual2[0][3]*rows[0][1])+(newIndividual2[0][5]*rows[0][2])+newIndividual2[0][9]);
//         System.out.println("inputH2  "+inputH2);
           
           double xh2=-1*inputH2;
           //output to hidden layer 1
           double outputH2=  (1/(1+Math.exp(xh2)));
//           System.out.println("OutputH2 "+outputH2);
           //input at output layer
           
           double inputk=(newIndividual2[0][6]*outputH1)+(newIndividual2[0][7]*outputH2)+newIndividual2[0][10];
//           System.out.println("inputk  "+inputk);
           double xh3=-1*inputk;
           //output at output layer
           double outputk=  (1/(1+Math.exp(xh3)));
          
//           System.out.println("outputk  "+outputk);
           
           
           //calculating error at output layer k
          double errorK=Math.abs((output[j]-outputk)*outputk*(1-outputk));
          error[j]=errorK;
            
                 
                 
           }
            double sum=0;
           for(int k=0;k<error.length;k++){
               sum+=error[k];
           }
           double fitness=sum/705;
           newIndividual2[0][11]=fitness;
    }
}
