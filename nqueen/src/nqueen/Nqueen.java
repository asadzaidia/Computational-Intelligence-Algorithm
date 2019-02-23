/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nqueen;


import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Asad_Zaidi
 */
public class Nqueen {
    
public static int s=44;
public static int count=0;
    public static void main(String[] args) {
        
        // TODO code application logic here
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the size of Matrix");
        int size=input.nextInt();
      
        Random rn=new Random();
        
        int n[][]=new int[25][size+1];
        int m[][]=new int[20][size+1];
        int matrix[][]=new int[size][size];
        int nm[][]=new int[45][size+1];
        int newIndividual[]=new int[size+1];
        int new1[]=new int[size+1];
        int new11[]=new int[size+1];
        int new22[]=new int[size+1];
        
        //generating 25 rows with every row with radom and unique column of each row
        for(int k=0;k<25;k++){
         for (int i = 0; i < size; i++) {
                     n[k][i] = (int)(Math.random()*size);

                       for (int j = 0;j<i;j++) {
                          
                        if (n[k][i] == n[k][j]) {
                           i--; //if a[i] is a duplicate of a[j], then run the outer loop on i again
                        break;
                    }
                     
                } 
            }
        
        }

        
        //calculating fitness of population
        for(int i=0;i<25;i++){
          for(int j=0;j<size;j++){
            int col=n[i][j];
            int row=j;
           matrix[row][col]=1; 
           
        }
          for(int k=0;k<size;k++){
              for(int l=0;l<size;l++){
                  System.out.print(matrix[k][l]+" ");
              
              }
          System.out.println();
          }
          
           int fitness=0;
          for(int x=0;x<size;x++){
              for(int y=0;y<size;y++){
              if(matrix[x][y]==1){
                 
                  int col=y;
                  int row=x;
//                  System.out.println("for"+row+" "+col);
                  int check_row=row+1;
                      
                      //diagonal right
                      int flag=0;
//                      System.out.println("Checking right-diagonal");
                      for(int diagonal=col;diagonal<size;diagonal++){
                          if((diagonal+1)==size ){
                              break;
                          }
                          
                          if((check_row)==size){
                              
                          break;
                          }
                          
                          else if(matrix[check_row][diagonal+1]==1 ){
//                              System.out.println("Found at"+ check_row+"  "+(diagonal+1));
                              flag=1;
                              break;
                          }
                          
                          else if(matrix[check_row][diagonal+1]!=1){
                              check_row++;
                          }
                          
                          
                      
                  }//checking in diagonal
                      check_row=row+1;
                      if(flag==0){
//                       System.out.println("Checking left-diagonal");
                       for(int diagonal=col;diagonal>=0;diagonal--){
                          if((diagonal-1)<0){
                              break;
                          }
                          
                          if((check_row)==matrix.length){
                              
                          break;
                          }
                          
                          else if(matrix[check_row][diagonal-1]==1 ){
//                              System.out.println("Found at"+ check_row+"  "+(diagonal-1));
                              flag=1;
                              break;
                          }
                          
                          else if(matrix[check_row][diagonal-1]!=1){
                              check_row++;
                          }
                          
                          
                      
                  }
                       if(flag==0){
//                   System.out.println("Fitness increases");
                              fitness++;    
                      
                      }
                       
                    
              }
              }
              }
          
          }
//          System.out.println(fitness);
          n[i][size]=fitness;

          //making empty matrix
          for(int b=0;b<size;b++){
                for(int c=0;c<size;c++){
                   matrix[b][c]=0;
              }
          }
      }
        
        //printing
//         for(int i=0;i<25;i++){
//             System.out.print("P"+i+":");
//            for(int j=0;j<size+1;j++){
////            "P"+i+":"+
//            System.out.print(n[i][j]+" ");
//            }
//            System.out.println();
//            
//         }

        do{
        for(int i=0;i<20;i++){
           
           int P1=rn.nextInt(24 - 0 + 1) + 0;
           int P2=rn.nextInt(24 - 0 + 1) + 0;

          if(P1==P2){
              for(int j=0;j<=size;j++){
              new1[j]=n[P1][j];
              }
          
               System.arraycopy(new1, 0, newIndividual, 0, new1.length);
        }
          
          else{
           //crossover
           int onepointcross=size/2;
//           System.out.println("cut "+onepointcross);
            for(int j=0;j<=size;j++){
              new11[j]=n[P1][j];
              }
           for(int j=0;j<=size;j++){
              new22[j]=n[P1][j];
              }
               //starting crossover
        System.arraycopy(new11, 0, newIndividual, 0, onepointcross);
        newIndividual[size]=404;
        
//        System.out.println("Before Crossover "+Arrays.toString(newIndividual));
        int num=onepointcross;
        for(int j=onepointcross-1;j<size;j++){
          
            int flag=0;
            int check=new22[j+1];
            for(int k=0;k<num;k++){
                 
                if(newIndividual[k]==check){
                 flag=1;
                break;
                }
               
            }
            num++;
                    if(flag==0){
                    newIndividual[j+1]=check;
                    }
                else{
                newIndividual[j+1]=510;
                
                }
          }
         for(int k=0;k<onepointcross;k++){
            int check2=new22[k];
            int flag=0;
            for(int j=0;j<size;j++){
               if(newIndividual[j]==check2){
                flag=1;
               break;
               }
              
            }
            if(flag==0){
                for(int l=onepointcross-1;l<size;l++){
                    if(newIndividual[l]==510){
                      
                    newIndividual[l]=check2;
                    break;
                    }
                }
            }
          
        }
//        System.out.println("newIndividual individual :  "+Arrays.toString(newIndividual));
    }
          
          
          //mutation
          int r1=rn.nextInt((size-1) - 0 + 1) + 0;
          int r2=rn.nextInt((size-1) - 0 + 1) + 0;
//          System.out.println(r1+"   "+r2);
          int c1= newIndividual[r2];
          int c2= newIndividual[r1];
          newIndividual[r1]=c1;
          newIndividual[r2]=c2;
          
//          System.out.println("After Mutation:  "+Arrays.toString(newIndividual));
          
          
          //calculating fitness
            //setting rows column into matrix
          for(int j=0;j<size;j++){
            int col=newIndividual[j];
            int row=j;
           matrix[row][col]=1; 
           
        }

          
           int fitness=0;
          for(int x=0;x<size;x++){
              for(int y=0;y<size;y++){
              if(matrix[x][y]==1){
                 
                  int col=y;
                  int row=x;
//                  System.out.println("for"+row+" "+col);
                   int check_row=row+1;
                      
                      //diagonal right
                      int flag=0;
//                      System.out.println("Checking right-diagonal");
                      for(int diagonal=col;diagonal<size;diagonal++){
                          if((diagonal+1)==size ){
                              break;
                          }
                          
                          if((check_row)==size){
                              
                          break;
                          }
                          
                          else if(matrix[check_row][diagonal+1]==1 ){
//                              System.out.println("Found at"+ check_row+"  "+(diagonal+1));
                              flag=1;
                              break;
                          }
                          
                          else if(matrix[check_row][diagonal+1]!=1){
                              check_row++;
                          }
                     }//checking in diagonal
                      check_row=row+1;
                      if(flag==0){
//                       System.out.println("Checking left-diagonal");
                       for(int diagonal=col;diagonal>=0;diagonal--){
                          if((diagonal-1)<0){
                              break;
                          }
                          if((check_row)==matrix.length){
                          break;
                          }
                          else if(matrix[check_row][diagonal-1]==1 ){
//                              System.out.println("Found at"+ check_row+"  "+(diagonal-1));
                              flag=1;
                              break;
                          }
                          else if(matrix[check_row][diagonal-1]!=1){
                              check_row++;
                          }
                   }
                       if(flag==0){
//                   System.out.println("Fitness increases");
                              fitness++;    
                      
                      }
                       
                    
              }
              }
              }
          
          }

          newIndividual[size]=fitness;
          //making empty matrix
          for(int b=0;b<size;b++){
                for(int c=0;c<size;c++){
                   matrix[b][c]=0;
              }
          }

               
//          System.out.println("After fitness:  "+Arrays.toString(newIndividual));
          for(int insert=0;insert<=size;insert++){
              
              m[i][insert]=newIndividual[insert];
              
          }
        
           
           
        }
         
         //merging n+m
         
         for(int v=0;v<25;v++){
                 for (int k=0;k<=size;k++){
                   nm[v][k]=n[v][k];
                    }
               
             }
             
             for(int v=0;v<20;v++){
             for (int k=0;k<=size;k++){
                     nm[v+25][k]=m[v][k];
                    }
             
             }
//               //printing n+m
//               System.out.println("N+M");
//         for(int i=0;i<45;i++){
//             System.out.print("nm"+i+":");
//            for(int j=0;j<size+1;j++){
////            "P"+i+":"+
//            System.out.print(nm[i][j]+" ");
//            }
//            System.out.println();
//            
//         }
//         
         //sorting
                  
           java.util.Arrays.sort(nm, new java.util.Comparator<int[]>() {
            @Override
             public int compare(int[] a, int[] b) {
               return  Double.compare(a[size], b[size]);
                     }
});
           

           
        //seting top 25 fittest n again
        
        for(int v=0;v<25;v++){
            
                 for (int k=0;k<=size;k++){
                    n[v][k]=nm[s][k];
                    }
                 s--;
               
             }
        s=44;
        count++;
     System.out.println("Generation: "+(count));
        }while(n[0][size]!=size);
        
        
        
        
        
        
          for(int j=0;j<size;j++){
            int col=n[0][j];
            int row=j;
           matrix[row][col]=1; 
           
             }
                  for(int k=0;k<size;k++){
                     for(int l=0;l<size;l++){
                  System.out.print(matrix[k][l]+" ");
              
              }
          System.out.println();
          }

    }
    
}
