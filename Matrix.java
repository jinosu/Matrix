import java.io.BufferedReader;

import java.io.File;

import java.io.FileReader;

import java.io.IOException;
import java.util.Scanner;
public class Matrix {
   static int type;  
    static int[][] readMatrix(String path){  
        int[][] num = null;
        File file = new File(path);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line ;
         type = Integer.valueOf(br.readLine());
         num = new int[type][type];
         String[] str ;
         int i = 0;
           while ((line = br.readLine()) != null) {
                str = line.split(" ");
                int j = 0;
                for (String string : str) {
                  num[i][j] = Integer.valueOf(string);
                  System.out.print( num[i][j]+" ");
                   j++;
               }
                System.out.println("");
                i++;
            }
            br.close();
        } catch (IOException e){ 
            e.printStackTrace();
        }
        return num;        
    }
    static int Minor(int[][] number,int cutrow,int cutcol){
        int[][] newnumber = new int[number.length-1][number.length-1];
        for (int i = 0,r=0; i < number.length; i++,r++) {           
            for (int j = 0,c=0; j < number.length; j++) {
                if(i!=cutrow&&j!=cutcol){
                newnumber[r][c] = number[i][j];
                  //  System.out.print(i+","+j+"  ");
                //   System.out.print(r+","+c+" ");
                            c++;
                }               
            } 
            //System.out.println();
              if(i==cutrow)
               r--;
        }       
          /*  for (int j = 0; j < newnumber.length; j++) {
                for (int k = 0; k < newnumber.length; k++) {
                    System.out.print(newnumber[j][k]+" ");
                }
               System.out.println();
            }*/
       return det(newnumber);
   }
   static int det(int[][] number){
       int c =1;
       int count = 0;
       if(number.length==2)
       return number[0][0]*number[1][1]-number[1][0]*number[0][1];  
       else
        for (int i = 0; i < number.length; i++) {
            if(number[i][0]!=0)
          count += c*number[i][0]*Minor(number, i, 0);
          c*=-1;
       }
       return count;
    }
    public static void main(String[] args) {
     Scanner scan = new Scanner(System.in);
        String path = scan.nextLine();
        Matrix matrix = new Matrix();
        int[][] number = null; 
      number =  matrix.readMatrix(path);
        System.out.println(matrix.det(number));
        
    }
}
//C:\Users\Admin\Desktop\30.txt
//C:\Users\Admin\Desktop\10x10.txt
//C:\Users\Admin\Desktop\4x4.txt
//C:\Users\Admin\Desktop\3x3.txt
//C:\Users\Admin\Desktop\2x2.txt
//81 31 16 
//51 78 25 
//46 47 21 
