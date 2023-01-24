import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Domandiere
{
    private Domanda[] domande;
    private int numDomande;

    public Domandiere( int max )   {
        this.domande = new Domanda[max];
        numDomande = 0;
    }
    
    
    public Domanda domandaRandom( )  {
        int i;
        i = (int)Math.random() * 3;
        return domande[i];
    }
    
    
    public void addDomanda( Domanda newDomanda )   {
        if( numDomande < domande.length )  {
            domande[numDomande] = newDomanda;
            numDomande++;
        } else {
            System.err.println("Troppe domande inserite");
        }
    }
    
    public String leggiFile(int domanda){
        String filePath = "domande.txt";
        
        return readSentenceFromFile(filePath);
    }
    
    //metodo per leggere da file
    public static String readSentenceFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
