import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class Matrix {

    static int type;

    static int[][] readMatrix(String path) {  //ส่งที่อยู่ไฟล์มาเป็น string 
        int[][] num = null;                  //สร้างอาเรย์มาเพื่อรอสำหรับเก็บค่าเมตริก
        File file = new File(path);          //ใช้ในการบอกว่าเราต้องการอ่านไฟล์ และใส่ชื่อไฟล์ลงไป
        try {
            BufferedReader br = new BufferedReader(new FileReader(file)); //สร้างออฟเจคที่ใช้อ่านไฟล์
            String line;                                                 //เก็บค่าชองตัวเลขเป็นstring ของแต่ละบรรทัด
            type = Integer.valueOf(br.readLine());                           //แปลงแล้วเก็บบรรทัดแรกที่เข้ามาเป็น Integer บรรทัดแรกจะบอกถึงขนาดเมตริก
            num = new int[type][type];                                       //กำหนดขนาดของ num ด้วยตัวแปลจากบรรทัดแรก
            String[] str;                                                   //สร้าง string เป็นอาเรย์เพื่อเก็บตัวเลขในรูปแบบ string
            int i = 0;                                                       //ตำแหน่งอาเรย์ของแถว
            while ((line = br.readLine()) != null) {                       //เก็บบบรทัดที่อ่านเช็ดว่าถ้าอ่านแล้วไม่มีอะไรให้หยุด
                str = line.split(" ");                                    //ทำการตัด string โดย " " และนำไปใส่ในอาเรย์
                int j = 0;                                                //ตำแหน่งอาเรย์ของหลัก
                for (String string : str) {                               //วนรอบตามจำนวนสมาชิคของอาเรย์
                    num[i][j] = Integer.valueOf(string);                    //แปลงจาก string เป็น Integer แล้วเก็บเข้ามาในอาเรย์
                    //  System.out.print( num[i][j]+" ");                       //เช็คความถูกต้องของเมตริก
                    j++;
                }
                //   System.out.println("");
                i++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return num;                                                        //ส่งอาเรย์กลับไป
    }

    static BigInteger Minor(int[][] number, int cutrow, int cutcol) {//เมื่อเมธอตนี้ถูกเรียกเราจะทำการรับตำแหน่งของแถวและหลักที่เราจะไม่นำมาเก็บในอาเรย์ตัวใหม่ 
        int[][] newnumber = new int[number.length - 1][number.length - 1]; //สร้างอาเรย์ตัวใหม่ที่มีขนาดเล็กลง-1 จากเดิม
        for (int i = 0, r = 0; i < number.length; i++, r++) {   //ค่า i จะเป็นตำแหน่งของแถวเมตริกชุดเก่า ส่วนค่า r จะเป็นตำแหน่งของแถวเมตริกชุดใหม่        
            for (int j = 0, c = 0; j < number.length; j++) {   //ค่า j จะเป็นตำแหน่งของหลักเมตริกชุดเก่า ส่วนค่า c จะเป็นตำแหน่งของหลักเมตริกชุดใหม่    
                if (i != cutrow && j != cutcol) {                   //เมื่อค่าของแถวและหลักไม่ได้เป็นตัวเดียวกับที่เราเลือกไว้ 
                    newnumber[r][c] = number[i][j];             //ทำการเก็บค่าของเมตริกใหม่
                    c++;                            //ขยับตำแหน่งของหลักอาเรย์ใหม่หลังจากเก็บค่าเสร็จ
                }
            }
            if (i == cutrow) //เช็คค่าในรอบที่ตำแหน่งของรอบใหญ่เท่าตำแหน่งของแถวที่รับเข้ามาให้ลบเลขของตำแหน่งแถวของเมตริกใหม่คือ r--
            {
                r--;
            }
        }
        return det(newnumber);                                //ทำการส่งค่าของเมตริกใหม่ไปที่เมธอต det 
    }

    static BigInteger det(int[][] number) {               //ใช้เมธอตเป็น type BigInteger เพื่อให้มีการรีเคอซีพเป็น BigInteger เพื่อจะได้นำค่าที่ใช้ในการคำนวนเป็น BigInteger 
        int c = 1;      //ใช้ในการกำหนดเครื่องหมายโคแฟคเตอร์
        BigInteger big = new BigInteger("0");
        int count = 0; //ตัวแปรใช้สำหรับการเก็บค่าของ det        
        if (number.length == 2) { //ใช่เช็คว่าเมตริกที่รับเข้ามามีขนาดเหลือ 2x2 หรือยังซึ่งเป็นขนาดที่ใช้ในการหา det ได้
            count = number[0][0] * number[1][1] - number[1][0] * number[0][1];
            big = big.add(new BigInteger(String.valueOf(count)));               //เก็บค่าของ det ของเมตริกขนาด 2x2   
            return big;
        } //ส่งค่า det ขนาด 2x2 
        else //หากขนาดยังเหลือมากว่า 2x2 ให้ทำการหาโคเฟเตอร์
        {
            for (int i = 0; i < number.length; i++) {
                if (number[i][0] != 0) {                       //ลดขั้นตอนการคำนวนโดยการจะไม่ทำการคำนวนหาโคแฟคเตอร์มีค่าเท่ากับ 0                    
                    count = c * number[i][0];                 //ค่าของโคเฟเตอร์จะเป็นตัวแรกของหลักแล้วไล่ลำดับตามแถวไปเรื่อยจนถึงตัวสุดท้ายเช่นถ้าเป็นขนาด 3x3 จะมีโคเฟเตอร์ 3 ตัวหลังจากนั้ทำการเรียกไปที่เมธอต Minor เพื่อทำการตัดขนาดแล้วส่งค่าของ Minor กลับมาคูณกับโคเฟเตอร์ 
                    big = big.add(new BigInteger(String.valueOf(count)).multiply(Minor(number, i, 0))); //ทำการเก็บค่าของตัวเลขใน BigInteger โดยการใช้เมธอต add ในการใส่ค่าโคแฟคเตอร์ตามด้วยการคูณกับไมเนอร์โดยใช้เมธอต เราสามาร๔เรียกเมธอตต่อกันได้เพราะเมธอตที่เรียกใช้มีการ return เป็นคลาส์                 
                }
                c *= -1;
            }
        }
        return big;  //เมื่อจบการทำงานจะส่งค่า det กลับไป
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String path = scan.nextLine();  //รับที่อยู่ของไฟล์มาเป็น string 
        Matrix matrix = new Matrix();  //สร้างออฟเจคมาเพื่อใช้เรียกเมธอตต่างๆ
        int[][] number = null;         //สร้าง int อาเรย์มาเพื่อเก็บตัวเลขใน matrix  
        number = matrix.readMatrix(path); //นำที่อยู่ของไฟล์ที่เรารับเขามาส่งไปยังเมธอต readMatrix เพื่อในเมธอตนี้ส่งค่ากลับมาเป็นอาเรย์เพื่อเก็บในตัวแปล number 
        System.out.println(matrix.det(number)); //เรียกเมธอต det ทำการส่ง Matrix เพื่อนำไปคำนวนหา det    
    }
}
