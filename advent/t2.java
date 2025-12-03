import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws FileNotFoundException 
    {
        long total = 0l;
        File f = new File("input2.txt");
        Scanner s = new Scanner(f);

        String l = s.nextLine();
        String[] G = l.split(",");
        for (String S:G)
        {
            // for pair in all
            String[] Is = S.split("-");
            System.out.println(S);
            long start = Long.parseLong(Is[0]);
            long end = Long.parseLong(Is[1]);
            
            String str = "";
            for (long i = start;i<=end;i++)
            {
                // for num in range
                str = String.valueOf(i);
                int L = str.length();

                boolean T = false;
                for (int u = 2;u<=L;u++)
                {
                    // for repeating possibility
                    if (L%u==0)
                    {

                        T = true;
                        //if divisible
                        for (int J = 0;J<=u-1;J++)
                        {
                            // for each group of two divisions
                            if (!(str.substring(0,L/u).equals(str.substring((J)*L/u,(J+1)*L/u))))
                            {
                                T = false;
                            }
                        }

                        if (T)
                        {
                            break;
                        }
                    }
                }
                if (T)
                {
                    System.out.println(i);
                    total += i;
                }   
            }
                

        }
        System.out.println(total);
    }
    
}

// 6352170448277
// 24043483400