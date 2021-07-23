import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
class Crazy
{
    static Scanner sc=new Scanner(System.in);
    static Random ra=new Random();
    static int target=0,chase=0,wickets2=0;static int innings=0,shot=0,ball=0;
    static void batting()
    {
        System.out.println("No of overs =5");
        int wickets =0,totalruns=0,flag=0,ball=0;
        for(int i=0;i<5;i++) //loop for overs
        {
            for(int j=0;j<6;j++) //deliveries per over
            {
                System.out.println("Delivery:"+i+"."+j);
                System.out.println("enter runs");
				do
				{
                shot=sc.nextInt();
				ball=rng(11);
				}while(shot<0 || shot>10);
                if(Math.abs(shot-ball)==1.0) //difference between runs entered and ball is 1 (out case)
                {
                    System.out.println("Out!");
                    wickets++;
                    if(wickets==10) //all out
                    {
                        break;
                    }
                }
                else if(shot==ball) // whole squared case
                {
                    totalruns+=(shot*shot);
                }
                else
                {
                    totalruns+=shot;
                }
                System.out.println("Score="+totalruns+"/"+wickets);
                if(((totalruns>target)&&(target!=0))||((totalruns>chase)&&(chase!=0))) //2nd innings end case
                {
                    break;
                }
            }
            if(wickets==10) // legit dunno why this is here
            {
                break;
            }
            if(((totalruns>target)&&(target!=0))||((totalruns>chase)&&(chase!=0)))
                {
                    break;
                }
        }
        System.out.println(" Final Score="+totalruns+"/"+wickets);
            target=totalruns; //taeget is for the user's runs
            wickets2=wickets;
    }
    static void bowling()
    {
        System.out.println("No of overs =5");
        int wickets =0,totalruns=0;
        for(int i=0;i<5;i++)
        {
            for(int j=0;j<6;j++)
            {
                System.out.println("Delivery:"+i+"."+j);
                System.out.println("enter delivery");
				do{
                ball=sc.nextInt();
				}while(ball<0 || ball>10);
                int shot=rng(11);
                if((target-totalruns)/((5-i)*6-j)>6.0) //ok what the fuck did I do here
                {
                    int runrate=(int)(target-totalruns)/((5-i)*6-j);
                    shot=rng2(11,runrate-1);
                }
                if(Math.abs(shot-ball)==1.0)
                {
                    System.out.println("Out!");
                    wickets++;
                    if(wickets==10)
                    {
                        break;
                    }
                    System.out.println("Score="+totalruns+"/"+wickets);
                }
                else if(shot==ball)
                {
                    totalruns+=(shot*shot);
                    System.out.println("Score="+totalruns+"/"+wickets);
                }
                else
                {
                    totalruns+=shot;
                    System.out.println("Score="+totalruns+"/"+wickets);
                }
                if((totalruns>target)&&(target!=0))
                {
                    break;
                }
            }
            if(wickets==10)
            {
                break;
            }
            if((totalruns>target)&&(target!=0))
                {
                    break;
                }
        }
        System.out.println(" Final Score="+totalruns+"/"+wickets);
            chase=totalruns; //chase is for the cpu's runs
            wickets2=wickets;
    }
    public static void main(String args[])
    {
        int toss=rng(2);
        if(toss==0)
        {
            System.out.println("Enter 0 for batting,1 for bowling");
             innings=sc.nextInt();
        }
        else
        {
            innings=rng(2);
            if(innings==0)
            {
                System.out.println("You lost the toss,CPU will bowl first");
            }
            else
            {
                System.out.println("You lost the toss,CPU will bat first");
            }
        }
        switch(innings)
        {
            case 0:batting();
            innings++;
            bowling();
            if(target>chase)
            {
                System.out.println("You win by "+(target-chase)+" runs");
            }
            else if(target==chase)
            {
                System.out.println("tie");
            }
            else
            {
                System.out.println("CPU wins by "+(10-wickets2)+" wickets");
            }
            break;
            case 1:bowling();
            innings--;
            batting();
            if(target<chase)
            {
                System.out.println("CPU wins by "+(chase-target)+" runs");
            }
            else if(target==chase)
            {
                System.out.println("tie");
            }
            else
            {
                System.out.println("You win by "+(10-wickets2)+" wickets");
            }
            break;
            default:System.out.println("Please re-enter");
            sc.close();
            System.exit(0);
        }
    }
    static int rng(int n)
    {
        return ra.nextInt(n);
    }
    static int rng2(int high, int low)
    {
        return (ra.nextInt(high-low)+low);
    }
}

