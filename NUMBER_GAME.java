import java.util.Random;
import java.util.Scanner;

class game{
    public int systemnumber;
    public int inputnumber;
    public int noofguess=0;

    public int getNoofguess() {
        return noofguess;
    }

    public void setNoofguess(int noofguess) {
        this.noofguess = noofguess;
    }

    public game() {
        Random rdm=new Random();
        this.systemnumber = rdm.nextInt(100);
    }

    public void takeInputnumber() {
       Scanner sc=new Scanner(System.in);
       inputnumber=sc.nextInt();
    }
    boolean a(){
        noofguess++;
        if(systemnumber==inputnumber)
        {
            System.out.printf("you guessed right :%d is the number\n you score is :%d\n",systemnumber,(100-noofguess));
            return true;
        }
        else if((inputnumber>100)  || (inputnumber<0)) {
            System.out.println("Please, enter a valid input!");
        }
        else if (systemnumber>inputnumber) {
            System.out.println("too low");
        } else if (systemnumber<inputnumber) {
            System.out.println("too high");
        }
        return false;
    }
}
public class NUMBER_GAME {
    public static void main(String[] args) {
        System.out.println("Please Enter a number in between 0 to 100: ");
        game obj=new game();
        boolean b=false;
       while (!b)
       {
           obj.takeInputnumber();
           b=obj.a();
       }
    }
}
