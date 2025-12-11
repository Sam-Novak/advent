
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Flow;




public class day6p2 {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("input6.txt");
        
        Scanner input = new Scanner(f);

        String[] list = new String[5];

        int i = 0;
        while (input.hasNextLine())
        {
            list[i] = input.nextLine();
            i++;
        }

        String[] s = list[4].split("(?=\\*|\\+)");

        int pos = 0;
        long subtotal = 0;
        long total = 0;
        for (String v : s)
        {
            String a = list[0].substring(pos,pos+v.length());
            String b = list[1].substring(pos,pos+v.length());
            String c = list[2].substring(pos,pos+v.length());
            String d = list[3].substring(pos,pos+v.length());

            pos+=v.length();
            if (v.strip().equals("*"))
            {
                subtotal = 1;
                for (int index = 0;index<a.length()-1;index++)
                {
                    String num = ""+a.charAt(index)+b.charAt(index)+c.charAt(index)+d.charAt(index);
                    subtotal *= Long.parseLong(num.strip());
                }
            }
            if (v.strip().equals("+"))
            {
                subtotal = 0;
                for (int index = 0;index<a.length()-1;index++)
                {
                    String num = ""+a.charAt(index)+b.charAt(index)+c.charAt(index)+d.charAt(index);
                    
                    System.out.println("n: "+num);
                    subtotal += Long.parseLong(num.strip());
                }
            }
            System.out.println(subtotal);
            total += subtotal;
        }


        System.out.println(total);
    }
}
