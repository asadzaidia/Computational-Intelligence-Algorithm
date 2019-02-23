/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pso;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Asad_Zaidi
 */
public class PSO {

    public static double[][] particle=new double[25][5];
    public static double[][] PB=new double[25][5];
    public static double[] CB=new double[25];
    public static double[][] GB=new double[1][3];
    public static int rangeMinX=-2;
    public static int rangeMaxX=2;
    public static int rangeMinY=-1;
    public static int rangeMaxY=3;
    public static double x,y,rand;
    public static int index;
    
    public static void main(String[] args) {
     
        GenerateParticles();
        
        //setting initially Personal best into personal best
        for(int i=0;i<25;i++){
            System.arraycopy(particle[i], 0, PB[i], 0, 5);
        }
        
        double genFittest=getGlobalBest(PB);
//         System.out.println(Arrays.toString(GB[0]));
         
       
//      
            for(int gen=0;gen<50;gen++){
                for(int i=0;i<25;i++){//for particles in each generation
                    
                    //Vx
                    
                 particle[i][3]=particle[i][3]+(2*generateRandom()*(PB[i][0]-particle[i][0]))+(2*generateRandom()*(GB[0][0]-particle[i][0]));
//                  System.out.println("P"+i+"Vx: "+particle[i][3]);
                 particle[i][0]=particle[i][0]+particle[i][3];
                 //checking boundries
                    if(particle[i][0]>rangeMaxX){
                       
                        particle[i][0]=2.0;
                    }
                    if(particle[i][0]<rangeMinX){
                        
                        particle[i][0]=-2.0;
                    }
                    
//                 System.out.println("P"+i+"x: "+particle[i][0]);
                 
                 //Vy
                 particle[i][4]=particle[i][4]+(2*generateRandom()*(PB[i][1]-particle[i][1]))+(2*generateRandom()*(GB[0][1]-particle[i][1]));
//                 System.out.println("P"+i+"Vy: "+particle[i][4]);
                 particle[i][1]=particle[i][1]+particle[i][4];
//                 System.out.println("P"+i+"y: "+particle[i][1]);

                    //checking y boundries
                                if(particle[i][1]>rangeMaxY){
                                    particle[i][1]=3.0;
                                }
                    
                                if(particle[i][1]<rangeMinY){
                                    particle[i][1]=-1.0;
                                }
                 
                   double fitness=100*Math.pow(((Math.pow(particle[i][0], 2))-(particle[i][1])),2)+Math.pow((1-(particle[i][0])),2);
                            particle[i][2]=fitness;
//                   System.out.println("P"+i+"fitness: "+particle[i][2]);
                   
                   //updating Personal Best of Each
                   
                   if(particle[i][2]>PB[i][2]){ //if current fittness is greater than PB then set current row to PB row
//                       System.out.println("Update  hoga");
                       PB[i][0]=particle[i][0];
                       PB[i][1]=particle[i][1];
                       PB[i][2]=particle[i][2];
                       PB[i][3]=particle[i][3];
                       PB[i][4]=particle[i][4];
                       
                       CB[i]=particle[i][2];
                       
                   }
                   else{  //if current fittness is less than PB then set current row to PB row
//                       System.out.println("Update Nhi hoga");
                       particle[i][0]=PB[i][0];
                       particle[i][1]=PB[i][1];
                       particle[i][2]=PB[i][2];
                       particle[i][3]=PB[i][3];
                       particle[i][4]=PB[i][4];
                       
                       CB[i]=particle[i][2];
                       
                       
                   }
                   
                   //setting fitness of each particle to evaluate local best and global best
                  
                   
                  
                 
                }
                
                double getLocalsBest=getLocalBest(CB);

                if(getLocalsBest>GB[0][2]){ //checking global best 
//                    System.out.println("Global variable updates");
                    GB[0][0]=PB[index][0];
                    GB[0][1]=PB[index][1];
                    GB[0][2]=PB[index][2];
                    
                }
                
            for (int i=0;i<25;i++){
            for(int k=0;k<5;k++){
                System.out.print(" "+PB[i][k]+"  ");
             }
            System.out.println();
         }

         System.out.println("\n\nGlobal best"+Arrays.toString(GB[0]));
         
            
                
    }
            
            
    }
    
    public static double getLocalBest(double[] inputArray){ 
    double maxValue = inputArray[0];
    index=0;
    
    for(int i=1;i < inputArray.length;i++){ 
      if(inputArray[i] > maxValue){ 
         maxValue = inputArray[i];
         index=i;
         
      } 
    } 
    return maxValue; 
  }
    
    public static double getGlobalBest(double[][] inputArray){ 
    double maxValue = inputArray[0][2]; 
     GB[0][0]=inputArray[0][0];
     GB[0][1]=inputArray[0][1];
    for(int i=1;i < inputArray.length;i++){
   
        for(int j=0;j<3;j++){
            
       
        if(j==2){
        if(inputArray[i][j] > maxValue){
            
           
         maxValue = inputArray[i][j];
         GB[0][0]=inputArray[i][0];
         GB[0][1]=inputArray[i][1];
         
         
        }
         
        }
        
      
    } 
   
    
  }
     GB[0][2]=maxValue;
    return maxValue;
    
    }
    
    public static double generateRandom(){
           Random r=new Random();
     rand = 0 + (1 - 0) * r.nextDouble();
     return rand;
    }
     public static void GenerateParticles(){
     Random rn= new Random();
     for(int i=0;i<25;i++){
           for(int j=0;j<3;j++){
               if(j==0){
                 x = rangeMinX + (rangeMaxX - rangeMinX) * rn.nextDouble();
               particle[i][j]=x;
               }
               else if(j==1){
                   y = rangeMinY + (rangeMaxY - rangeMinY) * rn.nextDouble(); 
                   particle[i][j]=y;
               }
               else{
                    double fitness=100*Math.pow(((Math.pow(x, 2))-(y)),2)+Math.pow((1-(x)),2);
                   particle[i][j]=fitness;
               }
           }
        }
    }
    
}

