
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;




public class day6 {
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

        for (int ind =0 ;ind<10;ind++)
        {
            list[0] = list[0].replaceAll("  ", " ");
            list[1] = list[1].replaceAll("  ", " ");
            list[2] = list[2].replaceAll("  ", " ");
            list[3] = list[3].replaceAll("  ", " ");
            list[4] = list[4].replaceAll("  ", " ");
        } 

        String[] a = list[0].split(" ");
        String[] b = list[1].split(" ");
        String[] c = list[2].split(" ");
        String[] d = list[3].split(" ");
        String[] s = list[4].split(" ");

        long total = 0;
        long totalb = 0;
        for (int ind =0 ;ind<a.length;ind++)
        {
            long a2 = Long.parseLong(a[ind]);
            long b2 = Long.parseLong(b[ind]);
            long c2 = Long.parseLong(c[ind]);
            long d2 = Long.parseLong(d[ind]);
            if (s[ind].equals("+"))
            {
                total += a2 + b2 + c2 + d2;
            }
            if (s[ind].equals("*"))
            {
                total += 
                Long.parseLong(a[ind]) *
                Long.parseLong(b[ind]) *
                Long.parseLong(c[ind]) * 
                Long.parseLong(d[ind]);
            }
        }
        System.out.println(total);
    }
}
