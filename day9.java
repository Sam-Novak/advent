import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day9 {
    public static void main(String[] args) throws FileNotFoundException 
    {
        File f = new File("input9.txt");
        
        Scanner input = new Scanner(f);

        int len  =(int) input.findAll("\n").count()+1;
        long[][] nums = new long[len][2];

        input = new Scanner(f);

        for (int i = 0; i<len; i++)
        {
            String nextlin = input.nextLine();
            nums[i][0] = Long.parseLong(nextlin.split(",")[0]);
            nums[i][1] = Long.parseLong(nextlin.split(",")[1]);
        }

        long max = 0;
        long abiding_max = 0;

        for (int x = 1; x<len-1;x++)
        {
            for (int y = x; y<len-1;y++)
            {

                long totabl = Math.abs((nums[x][0]-nums[y][0])+1)*(nums[x][1]-nums[y][1]+1);
                
                long total = (Math.abs(nums[x][0]-nums[y][0])+1)*(Math.abs(nums[x][1]-nums[y][1])+1);
                
                if (total>max)
                {
                    max = total;
                }
                
                boolean good = true;
            
                long minx = Math.min(nums[x][0],nums[y][0]);
                long miny = Math.min(nums[x][1],nums[y][1]);
                long manx = Math.max(nums[x][0],nums[y][0]);
                long many = Math.max(nums[x][1],nums[y][1]);

                // System.out.println(minx+" "+miny+" "+manx+" "+many);


                
                
                if (!((
                    ((nums[x-1][0]<nums[x][0]) && (nums[x+1][1]<nums[x][1]) && ((nums[y][0]>nums[x][0])||(nums[y][1]>nums[x][1]))) || //Right, Up.    more x or  more y
                    ((nums[x-1][0]<nums[x][0]) && (nums[x+1][0]>nums[x][0]) &&                           (nums[y][1]>nums[x][1]))  || //Right, Right.            more y
                    ((nums[x-1][0]<nums[x][0]) && (nums[x+1][1]>nums[x][1]) && ((nums[y][0]<nums[x][0])&&(nums[y][1]>nums[x][1]))) || //Right, Down.  less x and more y
                    ((nums[x-1][1]>nums[x][1]) && (nums[x+1][0]<nums[x][0]) && ((nums[y][0]>nums[x][0])||(nums[y][1]<nums[x][1]))) || //Up, Left.     more x or  less y
                    ((nums[x-1][1]>nums[x][1]) && (nums[x+1][1]<nums[x][1]) &&  (nums[y][0]>nums[x][0]))                           || //Up, Up.       more x 
                    ((nums[x-1][1]>nums[x][1]) && (nums[x+1][0]>nums[x][0]) && ((nums[y][0]>nums[x][0])&&(nums[y][1]>nums[x][1]))) || //Up, Right.    more x and more y
                    ((nums[x-1][0]>nums[x][0]) && (nums[x+1][1]>nums[x][1]) && ((nums[y][0]<nums[x][0])||(nums[y][1]<nums[x][1]))) || //Left, Down.   less x or  less y 
                    ((nums[x-1][0]>nums[x][0]) && (nums[x+1][0]<nums[x][0]) &&                           (nums[y][1]<nums[x][1]))  || //Left, Left.              less y
                    ((nums[x-1][0]>nums[x][0]) && (nums[x+1][1]<nums[x][1]) && ((nums[y][0]>nums[x][0])&&(nums[y][1]<nums[x][1]))) || //Left, Up.     more x and less y 
                    ((nums[x-1][1]<nums[x][1]) && (nums[x+1][0]>nums[x][0]) && ((nums[y][0]<nums[x][0])||(nums[y][1]>nums[x][1]))) || //Down, Right.  less x or  more y 
                    ((nums[x-1][1]<nums[x][1]) && (nums[x+1][1]>nums[x][1]) &&  (nums[y][0]<nums[x][0]))                           || //Down, Down.   less x
                    ((nums[x-1][1]<nums[x][1]) && (nums[x+1][0]<nums[x][0]) && ((nums[y][0]<nums[x][0])&&(nums[y][1]<nums[x][1]))))&& //Down, Left.   less x and less y
                    (

                    ((nums[y-1][0]<nums[y][0]) && (nums[y+1][1]<nums[y][1]) && ((nums[x][0]>nums[y][0])||(nums[x][1]>nums[y][1]))) || //Right, Up.    more x or more y
                    ((nums[y-1][0]<nums[y][0]) && (nums[y+1][0]>nums[y][0]) &&                           (nums[x][1]>nums[y][1]))  || //Right, Right. more y
                    ((nums[y-1][0]<nums[y][0]) && (nums[y+1][1]>nums[y][1]) && ((nums[x][0]<nums[y][0])&&(nums[x][1]>nums[y][1]))) || //Right, Down.  less x and more y
                    ((nums[y-1][1]>nums[y][1]) && (nums[y+1][0]<nums[y][0]) && ((nums[x][0]>nums[y][0])||(nums[x][1]<nums[y][1]))) || //Up, Left.     more x or less y
                    ((nums[y-1][1]>nums[y][1]) && (nums[y+1][1]<nums[y][1]) &&  (nums[x][0]>nums[y][0]))                           || //Up, Up.       more x 
                    ((nums[y-1][1]>nums[y][1]) && (nums[y+1][0]>nums[y][0]) && ((nums[x][0]>nums[y][0])&&(nums[x][1]>nums[y][1]))) || //Up, Right.    more x and more y
                    ((nums[y-1][0]>nums[y][0]) && (nums[y+1][1]>nums[y][1]) && ((nums[x][0]<nums[y][0])||(nums[x][1]<nums[y][1]))) || //Left, Down.   less x or less y 
                    ((nums[y-1][0]>nums[y][0]) && (nums[y+1][0]<nums[y][0]) &&                           (nums[x][1]<nums[y][1]))  || //Left, Left.   less y
                    ((nums[y-1][0]>nums[y][0]) && (nums[y+1][1]<nums[y][1]) && ((nums[x][0]>nums[y][0])&&(nums[x][1]<nums[y][1]))) || //Left, Up.     more x and less y 
                    ((nums[y-1][1]<nums[y][1]) && (nums[y+1][0]>nums[y][0]) && ((nums[x][0]<nums[y][0])||(nums[x][1]>nums[y][1]))) || //Down, Right.  less x or more y 
                    ((nums[y-1][1]<nums[y][1]) && (nums[y+1][1]>nums[y][1]) &&  (nums[x][0]<nums[y][0]))                           || //Down, Down.   less x
                    ((nums[y-1][1]<nums[y][1]) && (nums[y+1][0]<nums[y][0]) && ((nums[x][0]<nums[y][0])&&(nums[x][1]<nums[y][1])))))   //Down, Left.   less x and less y
                ) 
                {
                    good = false;
                }
                for (int z = 1; z<len-1;z++)
                {
                    

                    boolean bx = (nums[z][0] > minx && nums[z][0] < manx);
                    boolean by = (nums[z][1] > miny && nums[z][1] < many);

                    if (bx && by)
                    {
                        
                        // System.out.println(nums[x][0]+" "+nums[x][1]+" "+nums[y][0]+" "+nums[y][1]+" "+nums[z][0]+" "+nums[z][1]);
                        good = false;
                    }
                    if (bx)
                    {
                        if (
                            (nums[z][1] <= miny && nums[z-1][1] >= many) ||
                            (nums[z][1] <= miny && nums[z+1][1] >= many) ||
                            (nums[z][1] >= many && nums[z-1][1] <= miny) ||
                            (nums[z][1] >= many && nums[z+1][1] <= miny))
                        {
                            good = false;
                        }
                    }
                    if (by)
                    {
                        if (
                            (nums[z][0] <= minx && nums[z-1][0] >= manx) ||
                            (nums[z][0] <= minx && nums[z+1][0] >= manx) ||
                            (nums[z][0] >= manx && nums[z-1][0] <= minx) ||
                            (nums[z][0] >= manx && nums[z+1][0] <= minx))
                        {
                            good = false;
                        }
                    }

                }
                if (good && (total> abiding_max))
                {
                    System.out.println(nums[x][0]+" "+nums[x][1]+" "+nums[y][0]+" "+nums[y][1]+" total: "+total);
                    abiding_max= total; 

                    // System.out.println(
                    //     ((nums[x-1][0]<nums[x][0]) && (nums[x+1][1]<nums[x][1]) && ((nums[y][0]>nums[x][0])||(nums[y][1]>nums[x][1]))) +""+
                    //     ((nums[x-1][0]<nums[x][0]) && (nums[x+1][0]>nums[x][0]) && (nums[y][1]>nums[x][1]))                            +""+
                    //     ((nums[x-1][0]<nums[x][0]) && (nums[x+1][1]>nums[x][1]) && ((nums[y][0]<nums[x][0])&&(nums[y][1]>nums[x][1]))) +""+
                    //     ((nums[x-1][1]>nums[x][1]) && (nums[x+1][0]<nums[x][0]) && ((nums[y][0]>nums[x][0])||(nums[y][1]<nums[x][1]))) +""+
                    //     ((nums[x-1][1]>nums[x][1]) && (nums[x+1][1]<nums[x][1]) && (nums[y][0]>nums[x][0]))                            +""+
                    //     ((nums[x-1][1]>nums[x][1]) && (nums[x+1][0]>nums[x][0]) && ((nums[y][0]>nums[x][0])&&(nums[y][1]>nums[x][1]))) +""+
                    //     ((nums[x-1][0]>nums[x][0]) && (nums[x+1][1]>nums[x][1]) && ((nums[y][0]<nums[x][0])||(nums[y][1]<nums[x][1]))) +""+
                    //     ((nums[x-1][0]>nums[x][0]) && (nums[x+1][0]<nums[x][0]) && (nums[y][1]<nums[x][1]))                            +""+
                    //     ((nums[x-1][0]>nums[x][0]) && (nums[x+1][1]<nums[x][1]) && ((nums[y][0]>nums[x][0])&&(nums[y][1]<nums[x][1]))) +""+ 
                    //     ((nums[x-1][1]<nums[x][1]) && (nums[x+1][0]>nums[x][0]) && ((nums[y][0]<nums[x][0])||(nums[y][1]>nums[x][1]))) +""+
                    //     ((nums[x-1][1]<nums[x][1]) && (nums[x+1][1]>nums[x][1]) && (nums[y][0]<nums[x][0]))                            +""+
                    //     ((nums[x-1][1]<nums[x][1]) && (nums[x+1][0]<nums[x][0]) && ((nums[y][0]<nums[x][0])&&(nums[y][1]<nums[x][1]))) +"\n"+

                    //     ((nums[y-1][0]<nums[y][0]) && (nums[y+1][1]<nums[y][1]) && ((nums[x][0]>nums[y][0])||(nums[x][1]>nums[y][1]))) +""+
                    //     ((nums[y-1][0]<nums[y][0]) && (nums[y+1][0]>nums[y][0]) &&  (nums[x][1]>nums[y][1]))                           +""+
                    //     ((nums[y-1][0]<nums[y][0]) && (nums[y+1][1]>nums[y][1]) && ((nums[x][0]<nums[y][0])&&(nums[x][1]>nums[y][1]))) +""+
                    //     ((nums[y-1][1]>nums[y][1]) && (nums[y+1][0]<nums[y][0]) && ((nums[x][0]>nums[y][0])||(nums[x][1]<nums[y][1]))) +""+
                    //     ((nums[y-1][1]>nums[y][1]) && (nums[y+1][1]<nums[y][1]) &&  (nums[x][0]>nums[y][0]))                           +""+
                    //     ((nums[y-1][1]>nums[y][1]) && (nums[y+1][0]>nums[y][0]) && ((nums[x][0]>nums[y][0])&&(nums[x][1]>nums[y][1]))) +""+
                    //     ((nums[y-1][0]>nums[y][0]) && (nums[y+1][1]>nums[y][1]) && ((nums[x][0]<nums[y][0])||(nums[x][1]<nums[y][1]))) +""+ 
                    //     ((nums[y-1][0]>nums[y][0]) && (nums[y+1][0]<nums[y][0]) &&  (nums[x][1]<nums[y][1]))                           +""+
                    //     ((nums[y-1][0]>nums[y][0]) && (nums[y+1][1]<nums[y][1]) && ((nums[x][0]>nums[y][0])&&(nums[x][1]<nums[y][1]))) +""+ 
                    //     ((nums[y-1][1]<nums[y][1]) && (nums[y+1][0]>nums[y][0]) && ((nums[x][0]<nums[y][0])||(nums[x][1]>nums[y][1]))) +""+ 
                    //     ((nums[y-1][1]<nums[y][1]) && (nums[y+1][1]>nums[y][1]) &&  (nums[x][0]<nums[y][0]))                           +""+
                    //     ((nums[y-1][1]<nums[y][1]) && (nums[y+1][0]<nums[y][0]) && ((nums[x][0]<nums[y][0])&&(nums[x][1]<nums[y][1]))) +""
                    // );
                    // System.out.println(
                    //     (nums[x-1][0]<nums[x][0])+""+(nums[y][1]>nums[x][1])+"\n"+
                    //     (nums[x-1][0]>nums[x][0])+""+(nums[y][1]<nums[x][1])+"\n"+
                    //     (nums[x-1][1]<nums[x][1])+""+(nums[y][0]>nums[x][0])+"\n"+
                    //     (nums[x-1][1]>nums[x][1])+""+(nums[y][0]<nums[x][0])+"\n\n"+

                    //     (nums[x][0]<nums[x+1][0])+""+(nums[y][1]>nums[x][1])+"\n"+
                    //     (nums[x][0]>nums[x+1][0])+""+(nums[y][1]<nums[x][1])+"\n"+
                    //     (nums[x][1]<nums[x+1][1])+""+(nums[y][0]>nums[x][0])+"\n"+
                    //     (nums[x][1]>nums[x+1][1])+""+(nums[y][0]<nums[x][0])+"\n\n\n"+

                    //     (nums[y-1][0]<nums[y][0])+""+(nums[x][1]<nums[y][1])+"\n"+
                    //     (nums[y-1][0]>nums[y][0])+""+(nums[x][1]>nums[y][1])+"\n"+
                    //     (nums[y-1][1]<nums[y][1])+""+(nums[x][0]<nums[y][0])+"\n"+
                    //     (nums[y-1][1]>nums[y][1])+""+(nums[x][0]>nums[y][0])+"\n\n"+

                    //     (nums[y][0]<nums[y+1][0])+""+(nums[x][1]<nums[y][1])+"\n"+
                    //     (nums[y][0]>nums[y+1][0])+""+(nums[x][1]>nums[y][1])+"\n"+
                    //     (nums[y][1]<nums[y+1][1])+""+(nums[x][0]<nums[y][0])+"\n"+
                    //     (nums[y][1]>nums[y+1][1])+""+(nums[x][0]>nums[y][0])+"\n"
                    // );   
                }
            }    
        }
        // no corners on inside
        // all corners on edges must have box to the left (oriented)
        // for all that have x or y within, doesnt cross y or x within
        System.out.println(max);
        System.out.println(abiding_max);





    }
}

//5556 67344 94654 50355

// 2506,50355
// 94654,50355
// 94654,48393
// 1753,48393