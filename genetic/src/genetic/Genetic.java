/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;


/**
 *
 * @author Asad_Zaidi
 */
public class Genetic {
public static double x;
public static double y;
public static double p;
public static double q;
public static int r=0;
public static int s=0;
public static double[][]  n=new double[25][3];
public static double[][]  m=new double[20][3];
public static double[][]  newIndividual=new double[1][3];
public static double[][]  nm=new double[45][3];
public static int rangeMinX=-2;
public static int rangeMaxX=2;
public static int rangeMinY=-1;
public static int rangeMaxY=3;


   
    public static void main(String[] args) throws InterruptedException {
        
        
     
        Random rn=new Random();
        
             
    //parent generation 25x3 within constraint
    System.out.println("Random Parent Generation");
        RandomParent(); 
      
       //Now Looping Through Generations:
       for(int gen=0;gen<50;gen++){

       for(int i=0;i<20;i++){
           //random parent Strategy
          
           //random number between 0 and 24
           int P1=rn.nextInt(24 - 0 + 1) + 0;
           int P2=rn.nextInt(24 - 0 + 1) + 0;

    //crossover
               newIndividual[0][0]=n[P1][0];
               newIndividual[0][1]=n[P2][1];
            
                    //applying mutation
                    mutate();
              //calculating fitness after mutation
        
      double fitness_after_mutation=100*Math.pow(((Math.pow(newIndividual[0][0], 2))-(newIndividual[0][1])),2)+Math.pow((1-(newIndividual[0][0])),2);
                   newIndividual[0][2]=fitness_after_mutation;
                        
                   
             //Adding each new individual to m array
             
             for(int v=0;v<1;v++){
                 for(int j=0;j<3;j++){
                     if(j==0){
                         m[r][j]=newIndividual[0][0];
                     }
                     else if(j==1){
                      m[r][j]=newIndividual[0][1];
                     }
                     else{
                     m[r][j]=newIndividual[0][2];
                     r++;
                     }
                 }
             }
                                


    }//end of crossover and mutation
       r=0;
            //merging n+m
            for(int v=0;v<25;v++){
                 for (int k=0;k<3;k++){
                     if(k==0){
                    nm[v][k]=n[v][k];
                    } 
                     else if(k==1){
                        nm[v][k]=n[v][k]; 
                     }
                     else{
                         nm[v][k]=n[v][k];
                     }
                    }
               
             }
             
             for(int v=0;v<20;v++){
             for (int k=0;k<3;k++){
                     if(k==0){
                    nm[v+25][k]=m[v][k];
                    } 
                     else if(k==1){
                        nm[v+25][k]=m[v][k]; 
                     }
                     else{
                         nm[v+25][k]=m[v][k];
                     }
                    }
             
             }

         
         //sorting and save to n-array -25 fittest
       //sorting
            
            java.util.Arrays.sort(nm, (double[] a, double[] b) -> Double.compare(b[2], a[2]));
           
        //seting top 25 fittest n again
        
        for(int v=0;v<25;v++){
                 for (int k=0;k<3;k++){
                     if(k==0){
                    n[v][k]=nm[v][k];
                    } 
                     else if(k==1){
                        n[v][k]=nm[v][k]; 
                     }
                     else{
                         n[v][k]=nm[v][k];
                     }
                    }
               
             }
         System.out.println("Generation:"+gen+" fittest is: "+Arrays.toString(n[0]));
         
       }//generation ending
         
         
         
       
       
       
    }
    
    public static void RandomParent(){
        Random rn=new Random();
     for(int i=0;i<25;i++){
           for(int j=0;j<3;j++){
               if(j==0){
                x = rangeMinX + (rangeMaxX - rangeMinX) * rn.nextDouble();
               n[i][j]=x;
               }
               else if(j==1){
                   y = rangeMinY + (rangeMaxY - rangeMinY) * rn.nextDouble(); 
                   n[i][j]=y;
               }
               else{
                    double fitness=100*Math.pow(((Math.pow(x, 2))-(y)),2)+Math.pow((1-(x)),2);
                   n[i][j]=fitness;
               }
           }
        
        }
    
    }
    public static void mutate(){
        Random rn=new Random();
    float if_mutation=rn.nextFloat();//between 0 and q random generation
        if(if_mutation>0.4){

            int check=rn.nextInt(100 - 1 + 1) + 1;//even or odd

            if(check%2==0){ //if even then

                double xmutate=newIndividual[0][0];
                int check2=rn.nextInt(100 - 1 + 1) + 1;//+ or minus
                if(check2%2==0){ //if even we will add (mutate)
                  xmutate+=0.75;
                    if(xmutate>rangeMaxX){//Exceeding maximum of X
                        xmutate=2.0;
                    
                    }
                }
                else{ //if odd Subtractung because odd
                xmutate-=0.75;
                if(xmutate<rangeMinX){//Exceeding minimum of X: "+xmutate
                        xmutate=-2.0;
                    
                    }
                }
                
                newIndividual[0][0]=xmutate;
                
                
            }
            else{//Mutating y because odd
            double ymutate=newIndividual[0][1];
          
                int check2=rn.nextInt(100 - 1 + 1) + 1;//+ or minus
                if(check2%2==0){ //if even we will add (mutate)
                    ymutate+=0.75;
                    if(ymutate>rangeMaxY){//Exceeding maximum of Y
                        ymutate=3.0;
                    
                    }
                }
                else{ //if odd Subtractung because odd 
                ymutate-=0.75;
                if(ymutate<rangeMinY){//Exceeding minumum of Y
                        ymutate=-1.0;
                    
                    }
                
                }
                 newIndividual[0][1]=ymutate;
            
            }
        }else{
        
//            System.out.println("No mutation because random value <0.4  "+if_mutation);
        }
    
    }
    
 
    
}

