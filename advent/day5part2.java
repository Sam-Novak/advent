import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.plaf.TreeUI;

public class day5part2 {
    public static void main(String[]args) throws FileNotFoundException
    {
        File f = new File("input5.txt");
        
        Scanner input = new Scanner(f);

        int range_count = 187;
        int num_count = 1000;
        
        String[] ranges = new String[range_count];
        long[][] intranges = new long[range_count][2];

        for (int i = 0;i<range_count;i++)
        {
            ranges[i] = input.nextLine();
        }
        
        ArrayList<long[]> range_array = new ArrayList<long[]>();

        int igg = 0;
        for (String range : ranges)
        {
            String[] splitted = range.split("-");
            intranges[igg][0] = Long.parseLong(splitted[0]);
            intranges[igg][1] = Long.parseLong(splitted[1]);

            igg++;
        }

        boolean condensed = false;


        for (long[] ugg:intranges)
        {
            range_array.add(ugg);
        }
        range_array.sort((x, y) -> Long.compare(x[0],y[0]));

        while (!condensed)
        {
            condensed = true;

            for (int j = 0; j<range_array.size();j++)
            {
                for (int i = 0; i<range_array.size();i++)
                {
                    if ((range_array.get(j)[1]>=range_array.get(i)[0])&&(range_array.get(j)[0]<range_array.get(i)[0]))
                    {
                        range_array.set(j,new long[]{range_array.get(j)[0],range_array.get(i)[1]});
                        range_array.remove(i);
                        condensed = false;
                        break;
                    }
                }
                if (!condensed)
                {
                    break;
                }
            }
        }
        long total = 0;

        long[] mins = new long[range_array.size()];
        long[] maxes = new long[range_array.size()];

        int index = 0;
        for (long[] rang : range_array)
        {
            mins[index] = rang[0];
            maxes[index] = rang[1];
            index ++;
            total += rang[1]-rang[0]+1;   
        }

        Arrays.sort(mins);
        Arrays.sort(maxes);

        // for (int i = 0;i<mins.length;i++)
        // {
        //     System.out.println(mins[i]+"-"+maxes[i]);
        // }

        System.out.println(total);
        //357907198933892
        //11901795034084
    }
}
