package utils;

import views.MyFrame;

import javax.swing.*;
import java.util.Scanner;

/*
    Inputter: là 1 cái khuôn
    nhưng không dùng khuôn này để đúc
    những hành động nhập (số nguyên, số thực, chuỗi,...)
    chưa có ai đứng ra chịu trách nhiệm nên dùng
    Inputter này là người làm
    MỤC ĐÍCH => Lưu trữ
    
    Nên các thứ trong inputter dùng static => tĩnh
    không cần tạo object vẫn sử dụng những biến binh thường
    nếu không dùng static mỗi khi cần sử dụng thì phải tạo

    Muốn inputter truy cập dc => Static => không thì bị thuộc về các object
 */
public class Inputter {
    private static Scanner input = new Scanner(System.in);
    
    //trong này chứa các method hỗ trợ việc nhập chuẩn
    //method: ép nhập số nguyên
    public static int getAnInteger(String inpMsg, String errMsg){
        System.out.println(inpMsg);
        while (true){
            try{
                int number = Integer.parseInt(input.nextLine()); 
                return number;
            } catch(Exception e){
                System.out.println(errMsg);
            }
        }
    }
    //method: ép nhập số nguyên trong khoảng
    public static int getAnInteger(String inpMsg, String errMsg, 
                            int lowerBound, int upperBound){
        if(lowerBound > upperBound){
            int tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        
        System.out.println(inpMsg);
        while (true){
            try{
                int number = Integer.parseInt(input.nextLine()); 
                if(number < lowerBound | number > upperBound){
                    throw new Exception();
                }
                return number;
            } catch(Exception e){
                System.out.println(errMsg);
            }
        }
    }
    //method: ép nhập số thực 
    public static double getADouble(String inpMsg, String errMsg){ 
        System.out.println(inpMsg);
        while (true){
            try{
                double number = Double.parseDouble(input.nextLine()); 
                return number;
            } catch(Exception e){
                System.out.println(errMsg);
            }
        }
    }
    //method: ép nhập số thực trong khoảng
    public static double getADouble(String inpMsg, String errMsg, 
                            int lowerBound, int upperBound){
        if(lowerBound > upperBound){
            int tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        
        System.out.println(inpMsg);
        while (true){
            try{
                double number = Integer.parseInt(input.nextLine()); 
                if(number < lowerBound | number > upperBound){
                    throw new Exception();
                }
                return number;
            } catch(Exception e){
                System.out.println(errMsg);
            }
        }
    }
    //ép nhập chuỗi, cấm rỗng
    public static String getString(String inpMsg, String errMsg){
        System.out.println(inpMsg);
        while(true){
            try{
                String str = input.nextLine();
                if(str.isEmpty()){
                    throw new Exception();
                }
                return str;
            } catch(Exception e){
                System.out.println(errMsg);
            }
        }
    }
    //ép nhập chuỗi regex
    public static String getString(JTextField jTextField, String errMsg,
                                   String regex){
        while(true){
            try{
                String str = MyFrame.jTextField_Right_Middle_Username.getText();
                if(str.isEmpty() || !str.matches(regex)){
                    throw new Exception();
                }
                return str;
            } catch(Exception e){
                System.out.println(errMsg);
            }
        }
    }
}
