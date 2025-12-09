import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day5 {
    public static void main(String[]args) throws FileNotFoundException
    {
        File f = new File("input5.txt");
        
        Scanner input = new Scanner(f);

        int range_count = 187;
        int num_count = 1000;
        
        String[] ranges = new String[range_count];

        for (int i = 0;i<range_count;i++)
        {
            ranges[i] = input.nextLine();
        }
        
        int total = 0;
        input.nextLine();
        for (long i = 0;i<1000000000000000l;i++)
        {
            long n = i;
            boolean fresh = false;
            for (String range:ranges)
            {
                String[] low_max = range.split("-");
                if (Long.parseLong(low_max[0])<=n &&
                    n<= Long.parseLong(low_max[1]))
                {
                    fresh = true;
                }
            }
            if (fresh)
            {
                total+=1;
            }
        }
        System.out.println(total);
    }
}
