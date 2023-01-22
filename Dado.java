import java.util.Random;

class Dado
{
    Random rand = new Random();
    
    int facce = 6;
    int roll;
    public int lanciaDado()
    {
        roll = rand.nextInt(6) + 1;
        System.out.print(roll);
        return roll;
    }
}