import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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

    public static int two_steps(ArrayList<Integer> meters, ArrayList<ArrayList<Integer>> map, boolean[] solved_array, int depth)
    {   
        boolean solved = true;
        // System.out.println((int) lights[0]+" "+(int) lights[1]+" "+(int) lights[2]+" "+(int) lights[3]);
        
        for (int c:meters)
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
        for (int i=0;i<solved_array.length;i++) // for each button
        {
            /*
            Map = 
            [1,4]:6,
            [1,2]:5,
            [3]:5
            }
            */
            if (solved_array[i])
            {
                continue;
            }
            ArrayList<Integer> new_meters = new ArrayList<>();
            for (int j=0;j<meters.size();j++)
            {
                new_meters.add(meters.get(j));
            } 

            for (int j=0;j<map.size();j++)
            {
                if (map.get(j).contains(i))
                {
                    new_meters.set(j,new_meters.get(j)-1);
                }
            }
            int num = two_steps(new_meters, map, solved_array, depth-1)+1;
            if (num> 0)
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

            String[] temp_meters = line[line.length-1].substring(1,line.length).split(",");

            // System.out.println(temp_meters.length);
            ArrayList<Integer> meters = new ArrayList<>();
            
            // System.out.println(meters.size());
            for (int j = 0;j<temp_meters.length;j++)
            {
                meters.add(Integer.valueOf(temp_meters[j]));
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

            Arrays.sort(buttons, (int[] a1, int[] a2) -> a1.length-a2.length); //sorts buttons by size ?potential optimization?
            

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
            ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>(); // a list of buttons which contribute to each meter
            
            //parsing data into map
            for (int j = 0;j<meters.size();j++)
            {
                ArrayList<Integer> temp = new ArrayList<>();
                for (int k = 0;k<buttons.length;k++)
                {
                    if (Arrays.binarySearch(buttons[k], j)>=0)
                    {
                        temp.add(k);
                    }
                }
                map.add(temp);                
            }

            //simplifying
            boolean simple = false;
            while (!simple)
            {
                simple = true;
                for (int j = 0;j<map.size();j++)
                {
                    for (int k = 0;k<map.size();k++)
                    {
                        if (j == k)
                        {
                            continue;
                        }
                        if (map.get(j).equals(map.get(k)))
                        {
                            // if there are duplicates, remove one
                            map.remove(k);
                            meters.remove(k);
                            k-=1;
                            simple = false;
                        }
                        else if ((map.get(j).containsAll(map.get(k))))
                        {
                            // if j contains k, remove k from j and m[k] from m[j]
                            map.get(j).removeAll(map.get(k));
                            meters.set(j,meters.get(j)-meters.get(k));
                            simple = false;
                        }
                    }
                }
            }
            /*
            {
            [1,4]:6,
            [1,2]:5,
            [3]:5
            }
            */

            boolean[] knowns = new boolean[buttons.length];
            int[] vals = new int[buttons.length];

            for (int j = 0; j<buttons.length; j++)
            {
                if (map.contains(new ArrayList<Integer>(Arrays.asList(j))))
                {
                    knowns[j] = true;
                    vals[j] = meters.get(map.indexOf(new ArrayList<Integer>(Arrays.asList(j))));
                }
            }

            solved = false;
            int depth = 0;
            int num = Integer.MIN_VALUE;

            for (int j = 0; j<knowns.length; j++)
            {  
                if (knowns[j])
                {
                    two_total += meters.get(j);
                    meters.set(j,0);
                }

            }
            while (!solved)
            {
                solved = true;
                System.out.println(depth);
                //1 0 0 
                //0 1 0
                //0 0 1
                //2 0 0 
                //1 1 0
                //1 0 1
                //0 2 0
                //0 1 1
                //0 0 2
                num = two_steps(meters, map, knowns, depth);
                if (num <0)
                {
                    depth+=1;
                    solved = false;
                }
            }
            two_total += num;

        }
        System.out.println(total);
        System.out.println(two_total);
    }
}