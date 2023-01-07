import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestTabellone  {
    public static void main( String args[] ) throws IOException, FileNotFoundException    {
        final Domandiere domandiere;
        final Tabellone campoDaGiuoco;
        final int turno;
        
        campoDaGiuoco = new Tabellone();
        
        System.out.println(campoDaGiuoco);
    }
}
