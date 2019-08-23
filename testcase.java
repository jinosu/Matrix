
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
public class testcase {
   static String[][] Matrix(int size,int maxnum){
        String str[][] = new String[size][size];
        Random num = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
               str[i][j] = String.valueOf(num.nextInt(maxnum));
            }
        } 
        return str;
    }
    
    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
        System.out.print("name flie: ");
        String path = "C:\\Users\\Admin\\Desktop\\"+scan.next()+".txt"; 
         System.out.print("size: ");
         int size = scan.nextInt();
         System.out.print("maxnumber: ");
         int maxnumber = scan.nextInt();
        String[][] str =  Matrix(size,maxnumber);
File file = new File(path);
FileWriter writer;
try {
writer = new FileWriter(file, true);  //True = Append to file, false = Overwrite
writer.write(String.valueOf(size)+" "+String.valueOf(maxnumber));
for (String[] stringf : str) {
    String line = "";
        for (String strings : stringf) {
           // System.out.print(strings+" ");
            line +=strings+" ";
    }
        line+="\n";
        writer.write(line);
       // System.out.println("");
    }
writer.close();
System.out.println("Write success!");
} catch (IOException e) {
e.printStackTrace();
}
 } 
}
    
    

