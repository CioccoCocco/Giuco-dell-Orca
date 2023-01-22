
public class Domanda
{
    private int identificativo;//
    private int rispostaCorretta;//
    private int punti;
    Difficolta difficolta;//
    private String domanda;//
    private String risposte;
    
    public Domanda(String domanda, String risposte, Difficolta difficolta, int rispostaCorretta, int identificativo)
    {
        this.domanda = domanda;
        this.difficolta = difficolta;
        this.rispostaCorretta = rispostaCorretta;
        this.identificativo = identificativo;
        this.risposte = risposte;
        
        switch(difficolta)
        {
            case FACILE:{
                punti = 5;
                break;
            }
            
            case MEDIO:{
                punti = 10;
                break;
            }
            
            case DIFFICILE:{
                punti = 15;
                break;
            }
            
        }
        
    }
    
    public int getRispostaCorretta(){
        return rispostaCorretta;
    }
    
    public int getPunti(){
        return rispostaCorretta;
    }
    
    
    
    public boolean corretta(int x)
    {
        if(x == rispostaCorretta)
        {
            return true;
        }
        
        return false;
    }
    
    public String mostraStringa() {
        return domanda + "\n" + risposte + "\n" + difficolta;
    }
    
    
}



