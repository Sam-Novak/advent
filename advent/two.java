import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class two {

    public static void main(String[] args) throws FileNotFoundException 
    {
        File f = new File("input2.txt");
        Scanner s = new Scanner(f);

        String l = s.nextLine();
        
        for (String S:l.split(","))
        {
            String[] Is = S.split("-");
            int start = parseInt(Is[0]);
            int end = parseInt(Is[1]);
            int total = 0;
            String str = "";
            for (int i = start;i<=end;i++)
            {
                str = String.valueOf(i);
                int L = str.length();
                if (L%2==0)
                {
                    if (str.substring(0,L/2).equals(str.substring(L/2)))
                    {
                        total += i;
                    }
                }

            }
        }
    }
}
