/**Crazy is a variant of handcricket, where all normal rules of handcricket are followed, with the exceptions that an absolute difference of 1 between the numbers thrown 
results in a wicket, and, if both throw the same number, the runs tally increases by the square of the same.
eg: *shot=4; ball=7;
     totalruns+=4;
	*shot=7;ball=6;
	 wickets++;
	*shot=5;ball=5;
	totalruns+=25;
*/
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
class HandCricketRegular
{
    static Scanner sc=new Scanner(System.in);
    static Random ra=new Random();
    static int target=0,chase=0,wickets2=0;static int innings=0,shot=0,ball=0;
    static void batting()
    {
        System.out.println("No of overs =5");
        int wickets =0,totalruns=0,flag=0,ball=0;int a[]={11,12};
        for(int i=0;i<5;i++)                                                                //loop for overs
        {
            for(int j=0;j<6;j++)                                                           //deliveries per over
            {
                System.out.println("Delivery:"+i+"."+j);
                System.out.println("enter runs");
		do
		{
			shot=sc.nextInt();
		}while(shot<0 || shot>10);
		    if(innings==1)
		    {
			ball=(((chase-totalruns)/((5-i)*6-j)>10.0)&&(i>=3))?rng2(6,1):rng(11);  //CPU throws small numbers in slog overs if asking RR is very high
		    }
		    else
		    {
			    ball=rng(10);
		    }
		    if(a[0]==a[1])
			{
				ball=a[0];									//check for spamming the same number
			}
                if(shot==ball)                                            //difference between runs entered and ball is 1 (out case)
                {
                    System.out.println("Out!");
                    wickets++;
                    if(wickets==10)                                                      //all out
                    {
                        break;
                    }
                }
                else
                {
                    totalruns+=shot;
                }
                System.out.println("Score="+totalruns+"/"+wickets);
                if(((totalruns>target)&&(target!=0))||((totalruns>chase)&&(chase!=0)))   //2nd innings end case
                {
                    break;
                }
		    a[(6*i+j)%2]=shot;						//storing entries in alternating positions of the array
            }
			       if(wickets==10) // break from j loop needs to fall through i loop too
            {
                break;
            }
            if((totalruns>chase)&&(chase!=0))
                {
                    break;
                }
        }
        System.out.println(" Final Score="+totalruns+"/"+wickets);
            target=totalruns;                                                           //target is for the user's runs
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
				}while(ball<0 || ball>10);                         //CPU batting logic based on NRR- CPU checks RRR and generates the throw accordingly
				if(target!=0)
				{
					shot=((int)((ball<=6)?rng2(11,((int)(target-totalruns)/((5-i)*6-j))):rng2(11,((int)(target-totalruns)/((5-i)*6-j)-1))));
				}
		    else
		    {
			    shot=rng(11);
		    }
                if(shot==ball)
                {
                    System.out.println("Out!");
                    wickets++;
                    if(wickets==10)
                    {
                        break;
                    }
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
			do
			{
            System.out.println("Enter 0 for batting,1 for bowling");
			innings=sc.nextInt();
			}while(innings!=0&&innings!=1);
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
