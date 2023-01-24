public class Giocatore
{
    private String nome;
    private int punti; 
    private Casella casellaCorrente;
    private char pedina;
    private int[] domandeSvolte;
    private int numDomandeSvolte;

    public Giocatore( String nome, char pedina, Tabellone tab )    {
        this.nome = nome;
        this.pedina = pedina;
        this.punti = 0;
        this.casellaCorrente = tab.casellaIn(0);
        this.domandeSvolte = new int[90];
        numDomandeSvolte = 0;
    }

    public String getNome( )
    {
        return nome;
    }

    public int getPunti( )
    {
        return punti;
    }

    public Casella getCasellaCorrente( )
    {
        return casellaCorrente;
    }

    public char getPedina( )
    {
        return pedina;
    }

    public int[] getDomadeSvolte( )
    {
        return domandeSvolte;
    }

    public void setNome( String nome )
    {
        this.nome = nome;
    }

    public void setPunti( int punti )
    {
        this.punti = punti;
    }

    public void setCasellaCorrente( Casella casellaCorrente )
    {
        this.casellaCorrente = casellaCorrente;
    }

    public void setPedina( char pedina )
    {
        this.pedina = pedina;
    }

    public int lanciaDado(Dado d)
    {
        return d.lanciaDado();
    }
    
    public void addPunti( int plus )    {
        punti += plus;
    }

    public void muovi(int spostamento)
    {
        boolean indietro = false;

        for(int i = 0; i < spostamento; i++)
        {
            if(!indietro)
            {
                if(casellaCorrente.getSuccessivo() == null)
                {
                    indietro = true;
                }
                else
                {
                    casellaCorrente = casellaCorrente.getSuccessivo();
                }
            }
            if(indietro)
            {
                casellaCorrente = casellaCorrente.getPrecedente();
            }
        }
    }

    public void muoviImprevisto( int spostamento )
    {
        if(spostamento >= 1)
        {
            muovi(spostamento);
        }
        else
        {
            spostamento *= -1;
            for(int i = 0; i < spostamento; i++)
            {
                if(casellaCorrente.getPrecedente() == null)
                {
                    return;
                }
                else
                {
                    casellaCorrente = casellaCorrente.getPrecedente();
                }
            }
        }
    }
    
    public boolean DomandeSvolte ( int k )
    {
        for(int i = 0; i < numDomandeSvolte; i++)
        {
            if( domandeSvolte[i] == k )
            {
                return true;
            }
        }
        return false;
    }

    public Domanda chiediDomanda(Domandiere domandiere)
    {
        do
        {
            Domanda domanda = domandiere.getDomanda();
        }
        while( DomandeSvolte(domanda.getIdentificativo()) );
        return domandiere.getDomanda();
    }

    public boolean rispondi(int risposta, Domanda domanda)
    {
        domandeSvolte[numDomandeSvolte] = domanda.getIdentificativo();
        numDomandeSvolte++;
        return domanda.corretta( risposta );
    }

    public int altroGiocatore(Giocatore[] giocatori, int indice)
    {
        for(int i = 0; i < giocatori.length; i++)
        {
            if(i != indice)
            {
                if(casellaCorrente.equals(giocatori[i].getCasellaCorrente()))
                {
                    return i;
                }
            }
        }
    }

    public boolean sfida( Giocatore g2, Dado d )
    {
        int p1 = 0, p2 = 0;
        int t1, t2;

        for(int i = 0; i < 3; i++)
        {
            t1 = d.lanciaDado();
            t2 = d.lanciaDado();

            if( t1 > t2 )
            {
                p1++;
            }
            else
            {
                p2++;
            }
        }

        if(p1 > p2)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void napoli( Giocatore g2 )
    {
        punti += g2.getPunti();
        g2.setPunti(0);
    }

    public boolean controlloVincita( Tabellone tab )
    {
        if(casellaCorrente.getSuccessivo() == null)
        {
            if(punti >= 1)
            {
                return true;
            }
            else
            {
                tornaAInizio(tab);
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public void tornaAInizio( Tabellone tab )
    {
        casellaCorrente = tab.getInizio();
    }
}
