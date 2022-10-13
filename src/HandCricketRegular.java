/**Handcricket is a game that more or less follows the rules of regular cricket, with the exception being that any number can be thrown from 1-10 per delivery.
*If the batter and the bowler throw unique numbers, then the number thrown by the batter is added to their run total.
*If the batsman and bowler throw the same number, a wicket is considered to fall for the batter. The innings is considered over when all 10 wickets fall or 
30 deliveries have been bowled.

*/
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
class HandCricketRegular
{
    static Scanner sc=new Scanner(System.in);
    static Random ra=new Random();
    static int target=0,chase=0,wickets2=0;static int innings=0,shot=0,ball=0,overs=5;
    static void batting()
    {
        System.out.println("No of overs = " + overs);
        int wickets =0,totalruns=0,flag=0,ball=0;int a[]={11,12};
        for(int i=0;i<overs;i++)                                                                //loop for overs
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
		    	//ball=(((chase-totalruns)/((overs-i)*6-j)>10.0)&&(i>overs/2))?rng2(6,1):rng(11);  //CPU throws small numbers in slog overs if asking RR is very high
		    	 if(((chase-totalruns)/((overs-i)*6-j)>10.0)&&(i>overs/2))
			    	  ball=rng2(6,1);
			      else
			    	  ball=rng(11);
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
        System.out.println("No of overs = " + overs);
        int wickets =0,totalruns=0;
        for(int i=0;i<overs;i++)
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
					//shot=((int)((ball<=6)?rng2(11,((int)(target-totalruns)/((overs-i)*6-j))):rng2(11,((int)(target-totalruns)/((overs-i)*6-j)-1))));
					if(ball<=6)
						shot=rng2(11,((int)(target-totalruns)/((overs-i)*6-j)));
					else
						shot=rng2(11,((int)(target-totalruns)/((overs-i)*6-j)-1));
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
	    System.out.println("Enter number of overs for the match:");
	    overs=sc.nextInt();
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
        System.out.println("Enter number of overs for the match:");
	    overs=sc.nextInt();
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
