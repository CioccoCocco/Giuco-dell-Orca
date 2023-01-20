import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Domandiere
{
    private Domanda[] mazzo;
    private int numDomande = 56;
    
    public Domandiere(int max) {
        mazzo = new Domanda[max];
    }
    
    public boolean addDomanda(Domanda d) {
	if(numDomande < mazzo.length) {
		mazzo[numDomande] = d;
		numDomande++;
		return true;
	}
	return false;
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
