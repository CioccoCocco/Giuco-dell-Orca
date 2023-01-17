public class GiuocoDellOrca  {
    private Giocatore[] giocatori;
    private int numGiocatori;
    private Domandiere domandiere;
    private Imprevisto mazzoImprevisti;
    private Tabellone campoDaGiuoco;
    private int turno;
    private int veroturno;
    private Dado dado;

    public GiuocoDellOrca( int numGiocatori, int numCaselle ) {
        giocatori = new Giocatore[numGiocatori];
        this.numGiocatori = numGiocatori;
        for( int i = 0; i < numGiocatori; i++ ) {
            String nome;
            char pedina;
            System.out.print("Inserire il proprio nome: ");
            nome = Leggi.unoString();
            System.out.print("Inserire un carattere da usare come pedina (un carattere da tastiera, possibilmente): ");
            pedina = Leggi.unChar();
            giocatori[i] = new Giocatore( nome, pedina );
        }
        int dim;
        do  {
            System.out.print("Scegliere la dimensione del campo ( 50 - 90 ): ");
            dim = Leggi.unInt();
        } while( dim != 50 && dim != 90 );
        
        campoDaGiuoco = new Tabellone(dim);
        //nel costruttore del domandiere, verranno inserite man mano le domande lette da un file (chiamando la funzione leggiFile() );
        domandiere = new Domandiere();
        //nel costruttore del mazzoImprevisti, verranno inseriti man mano gli imprevisti letti da un file (chiamando la funzione leggiFile() );
        mazzoImprevisti = new Imprevisto();
        
        veroturno = 0;
        turno = 0;
        //nel costruttore di dado, gli verranno assegnate 6 facce
        dado = new Dado(6);
    }

    public boolean svolgiTurno()    {
        System.out.println( "TURNO: " + veroturno +"\n\n" );
        System.out.println( campoDaGiuoco.stampaTabellone(giocatori) );
        
        int movimento = giocatori[turno].lanciaDado( dado );
        giocatori[turno].muovi( movimento );

        //altroGiocatore() non returna più un boolean, ma un int che è l'indice del giocatore che è nella stessa casella di quello corrente
        //altroGiocatore() ha ora bisogno di sapere l'indice del vettore (ovvero turno) del giocatore che sta chiamando la funzione per capire che non deve controllare anche lui
        int altroGiocatore = giocatori[turno].altroGiocatore( turno, giocatori );

        if( altroGiocatore > 0 )  {
            // vittoria è true se ha vinto il giocatore di turno, false se ha vinto l'altro giocatore
            boolean vittoria = giocatori[turno].sfida( giocatori[altroGiocatore] );
            if( vittoria )  {
                giocatori[turno].napoli( giocatori[altroGiocatore] );
            } else {
                giocatori[altroGiocatore].napoli( giocatori[turno] );
            }
        } else {
            Domanda domanda = giocatori[turno].chiediDomanda( domandiere );
            System.out.println( domanda );
            int risposta;
            do  {
                System.out.println("Inserire la risposta alla domanda: ");
                risposta = Leggi.unInt();
            }while( risposta < 1 || risposta > 4 );
            boolean corretta = giocatori[turno].rispondi( risposta, domanda );

            if( corretta )  {
                giocatori[turno].addPunti( domanda.getPunti() );
                
                int casella, spostamentoCasella;
                System.out.print("Scegliere l'indice della casella da spostare: ");
                casella = Leggi.unInt();
                spostamentoCasella = giocatori[turno].lanciaDado( dado );
                
                campoDaGiuoco.spostaCella( casella, spostamentoCasella );
            } else {
                int imprevisto = giocatori[turno].chiediImprevisto( mazzoImprevisti );
                switch( imprevisto ) {
                    //il giocatore si muove di uno spazio definito (5) e ottiene un numero di punti definito (5). stessa cosa in negativo
                    case 1 : {
                        //1 == AUMENTAPUNTI
                        giocatori[turno].addPunti( 5 );
                        break;
                    }
                    case 2 : {
                        //2 == TOGLIPUNTI
                        giocatori[turno].addPunti( -5 );
                        break;
                    }
                    case 3 : {
                        //3 == SPOSTAAVANTI
                        giocatori[turno].muoviImprevisto( 5 );
                        break;
                    }
                    case 4 : {
                        //4 == SPOSTAINDIETRO
                        giocatori[turno].muoviImprevisto( -5 );
                        break;
                    }
                }
            }
        }
        
        //se il giocatore di turno ha vinto, return true così da finire il gioco
        if( giocatori[turno].controlloVincita() == true )   {
            return true;
        }
        //altrimenti passa al turno successivo e returna false
        veroturno++;
        turno = (turno + 1) % numGiocatori;
        return false;
    }
}
