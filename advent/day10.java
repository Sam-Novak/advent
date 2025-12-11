import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class day10 {

    public static int steps(char[] lights, int[][] buttons, int depth)
    {   
        boolean solved = true;
        // System.out.println((int) lights[0]+" "+(int) lights[1]+" "+(int) lights[2]+" "+(int) lights[3]);
        for (char c:lights)
        {
            if (c != 0)
            {
                solved = false;
            }
        }
        if (solved)
        {
            return 0;
        }
        if (depth == 0)
        {
            return Integer.MIN_VALUE;
        }

        for (int[] button : buttons)
        {
            char[] new_lights = lights.clone();
            for (int part: button)
            {
                new_lights[part] = (char) (new_lights[part]^1);
            }
            int num = steps(new_lights, buttons, depth-1)+1;
            if (num>0)
            {
                return num;
            }
        }


        return Integer.MIN_VALUE;
    }


    public static void main(String[]args) throws FileNotFoundException
    {
        File f = new File("input10.txt");
        
        Scanner input = new Scanner(f);

        int len  =(int) input.findAll("\n").count()+1;

        input.close();

        input = new Scanner(f);

        int total = 0;
        int two_total = 0;
        for (int i =0;i<len;i++)
        {
            String[] line = input.nextLine().split(" ");

            char[] lights = line[0].substring(1,line[0].length()-1).toCharArray();

            String[] temp_meters = line[line.length-1].substring(1,line.length-1).split(",");

            int[] meters = new int[temp_meters.length];
            for (int j = 0;j<temp_meters.length;j++)
            {
                meters[j] = Integer.parseInt(temp_meters[j]);
            }
            int[][] buttons = new int[line.length-2][];
            for (int j = 1;j<line.length-1;j++)
            {
                String[] b = line[j].substring(1, line[j].length()-1).split(",");
                int[] button = new int[b.length];

                for (int k = 0;k<b.length;k++)
                {

                    // System.out.println(b[k]);
                    button[k] = Integer.parseInt(b[k]);
                }
                buttons[j-1] = button;
            }

            for (int j = 0;j<lights.length;j++)
            {
                if (lights[j]=='.')
                {
                    lights[j] = 0;
                }
                if (lights[j]=='#')
                {
                    lights[j] = 1;
                }
            }

            Arrays.sort(buttons, new Comparator<int[]>() //sorts buttons by size ?potential optimization?
            {
                @Override
                public int compare(int[] a1, int[] a2) {return a1.length-a2.length;}
            });

            int num_steps = 0;
            boolean solved = false;
            while (!solved)
            {
                // System.out.println(num_steps);
                int score = steps(lights, buttons, num_steps);
                if (score<0)
                {
                    num_steps+=1;
                    continue;
                }
                total += score;
                solved = true;
            }

            /*
            
            create a system of equations for each meter
            where buttons corresponding to the meter are a variable  
            

            {
            [1,2,3]:10,
            [1,3,4]:11,
            [1,3,4]:11,
            [1,2]:5,
            [1,2,3]:10,
            [3]:5
            }

            substitute in any possible:
            {
            [1,4]:6,
            [1,2]:5,
            [3]:5
            }

            remove any singles and add to total
            {
            [1,4]:6,
            [1,2]:5,
            }
            t=5

            bfs in order of num occurences:

            1=5
            4=1
            2=0
            */
        }
        System.out.println(total);
    }
}