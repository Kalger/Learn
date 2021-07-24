import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.Inet4Address;

public class MainJ {
    public static void main(String[] args)  {
        System.out.printf("Hello World!%n\n new Line test");
//        readNum(new BufferedReader(new StringReader("abc")));
    }



    public static Integer readNum(BufferedReader reader)  {
        try{
            String line = reader.readLine();
            return Integer.parseInt(line);
        }catch (IOException e){
            return null;
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}