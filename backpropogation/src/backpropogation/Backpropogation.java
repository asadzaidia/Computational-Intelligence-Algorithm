/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backpropogation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;




/**
 *
 * @author Asad_Zaidi
 */
public class Backpropogation {
public static double[][] input=new double[705][3];
public static double[] output=new double[705];
public static double[] weights=new double[8];
public static double[] biases=new double[3];
public static double[] error=new double[705];

    
    public static void main(String[] args) throws FileNotFoundException {
        
        
        
        
        double[][] rows=new double[1][3];
        readinputs();
        readoutputs();
        generateweights();
       
        generatebiases();
    
        double count=1;
        int counts=1;
   
        
        double average=0;
        
//        do{
    for(int outer=0;outer<1;outer++){
        
        double  lr =(1/(count+1));
        
        
        
        
        
        
        
            //foreach
            for(int i=0;i<705;i++){
                
            
            do{
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
          
//           System.out.println("outputk  "+outputk);
           
           
           //calculating error at output layer k
          double errorK=(output[i]-outputk)*outputk*(1-outputk);
//           System.out.println("errork  "+errorK);
//           double erroryo=Math.abs(errorK);
//            errorcheck=errorK;
           error[i]=Math.abs(errorK);//at index 1 error of output k
         
           
//           updating bias3
           double delbias3=lr*errorK;
           biases[2]=biases[2]+delbias3;
//          System.out.println("New biase 2 "+ biases[2]);

           if(error[i]<0.005){
               System.out.println(outputk);
//               System.out.println(error[i]);
               
               break;
           }
           else{
               //backpropogation
               
           //calculatin error at hidden layer h1
           double errorH1=(errorK*weights[6])*outputH1*(1-outputH1);
          
            //            System.out.println("errorH1  "+errorH1);
            
            //    System.out.println("learning rate " +lr);
            //updating bias 0
            double delbias1=lr*errorH1;
           biases[0]=biases[0]+delbias1;
            //System.out.println("New biase 0 "+ biases[0]);
            
            
            //calculatin error at hidden layer h2
           double errorH2=(errorK*weights[7])*outputH2*(1-outputH2);
         
//            System.out.println("errorH2  "+errorH2);
         
          //updating bias 1
            double delbias2=lr*errorH2;
           biases[1]=biases[1]+delbias2;
//           System.out.println("New biase 1 "+ biases[1]); 
            
            
         //now updating weights corresponding hidden layer h1
         //w7(new)=w7(current)+del w7
         //del w7=l * errork*output h1
         
         
//         System.out.println("learning rate: "+lr);
         
         double del6=lr*errorK*outputH1;
//         System.out.println("del 6 : "+del6);
            weights[6]=weights[6]+del6;
         
//         System.out.println("Weight7 new  "+weights[6]);
         
         //update w1 w3 w5
         //w1
         double del0=lr*errorH1*rows[0][0];
//         System.out.println("del 0 : "+del0);
            weights[0]=weights[0]+del0;
         
//         System.out.println("Weight1 new  "+weights[0]);
         
         //w3
         double del2=lr*errorH1*rows[0][1];
//         System.out.println("del 2 : "+del2);
            weights[2]=weights[2]+del2;
         
//         System.out.println("Weight3 new  "+weights[2]);
         
         
         //w5
         double del4=lr*errorH1*rows[0][2];
//         System.out.println("del 4 : "+del4);
         weights[4]=weights[4]+del4;
        
//         System.out.println("Weight5 new  "+weights[4]);
         
         
         
           
         //now updating weights corresponding hidden layer h1
         //w8(new)=w8(current)+del w8
         //del w8=l * errork*output h2
       
         
         double del7=lr*errorK*outputH2;
//         System.out.println("del 7 : "+del7);
         weights[7]=weights[7]+del7;
         
//         System.out.println("Weight8 new  "+weights[7]);
         
         //update w2 w4 w6
         //w2
         double del1=lr*errorH2*rows[0][0];
//         System.out.println("del 1 : "+del1);
         weights[1]=weights[1]+del1;
      
//         System.out.println("Weight2 new  "+weights[1]);
         
         //w4
         double del3=lr*errorH2*rows[0][1];
//         System.out.println("del 3 : "+del3);
         weights[3]=weights[3]+del3;
    
//         System.out.println("Weight4 new  "+weights[3]);
         
         
         //w6
         double del5=lr*errorH2*rows[0][2];
//         System.out.println("del 5 : "+del5);
         weights[5]=weights[5]+del5;
      
//         System.out.println("Weight6 new  "+weights[5]);
         
//         System.out.println("\n\n\n");
        
          
           counts++;
           }  
              
           
      
           }
            while(error[i]>0.005);
            counts++;
//            System.out.println("counts  "+counts);
//            System.out.println("Breaks because <0.005");
//             System.out.println(Arrays.toString(weights));
            

        }
            
            //for leatning rate
           count++;
           double sum=0;
           for(int i=0;i<705;i++){
           sum+=error[i];
           }
           
           average=sum/705;
           
           
           
           
    }
//    while(average>0.0001);
////        System.out.println(average);
//        System.out.println("\nlearned-Weights\n");
//        System.out.println(Arrays.toString(weights));
//        System.out.println("\n\nlearned-biases\n");
//        System.out.println(Arrays.toString(biases));
////        System.out.println(Arrays.toString(error));
        
        

    
      
        
      
        

    }
    
    public static void readinputs(){

   String inputfile="F:\\Netbean\\TOCI\\backpropogation\\input.txt";
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
   String inputfile="F:\\Netbean\\TOCI\\backpropogation\\output.txt";
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
     public static void generateweights(){

         double min=-0.1;
         double max=0.01;
         Random rn= new Random();

//          for(int i=0;i<8;i++){
////          double w= min + (max - min) * rn.nextDouble();
////          
////
////            weights[i]=w;
//
//       
//          }
         weights[0]= 0.20;
        weights[1]= -0.30;
        weights[2]= 0.4;
        weights[3]=0.10;
        weights[4]= -0.50;
        weights[5]= 0.2;
        weights[6]= -0.3;
        weights[7]= -0.2;
          
              
     }  
         
        
       
        

          
     
     
       public static void generatebiases(){
       
         double min=0;
         double max=0.99;
          Random rn= new Random();
          double b1= min + (max - min) * rn.nextDouble(); 
          double b2= min + (max - min) * rn.nextDouble();
          double b3= min + (max - min) * rn.nextDouble();
          
          biases[0]=b1;
          biases[1]=b2;
          biases[2]=b3;
            
     }
}

