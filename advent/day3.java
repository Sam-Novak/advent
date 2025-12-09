import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day3
{
    public static void main(String[]args) throws FileNotFoundException
    {
        long total=0;
        File f = new File("input3.txt");
        Scanner s = new Scanner(f);
        while(s.hasNextLine())
        {
            long subtotal = 0;
            String line = s.nextLine();
            String[]nums = line.split("");
            int start_index = 0;
            for(int i = 0; i < 12; i++)
            {
                long max_num = 0;
                int max_index = 0;
                for(int j = start_index; j < nums.length - 11 + i; j++)
                {
                    if(Long.parseLong(nums[j]) > max_num)
                    {
                        max_num = Long.parseLong(nums[j]);
                        max_index = j;
                    }
                }
                subtotal = subtotal*10 + max_num;
                start_index = max_index+1;
            }
        total += subtotal;
        }
        System.out.println(total);
        s.close();
    }
}