import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LetturaDaFile  {
    //throws fa si che la funzione non tenga da conto i possibili errori che potrebbero occorrere nella lettura da file
    public static void main( String args[] ) throws IOException, FileNotFoundException  {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\cartella\\file.txt"));
        //creo un oggetto di tipo BufferedReader a cui passo un nuovo oggetto FileReader a cui passo il percorso assoluto del file di testo sotto forma di String
        String stringa = reader.readLine();
        //leggo un'intera riga e la strasformo in un'unica stringa
        
        while( stringa != null )   {
            System.out.println(stringa);
            stringa = reader.readLine();
            //continuo a leggere riga per riga finchè non finisco il file (ovvero la stringa risulta nulla)
        }
    }
}
