public class GiuocoDellOrca  {
    private Giocatore[] giocatori;
    private Domandiere domandiere;
    private Imprevisto mazzoImprevisti;
    private Tabellone campoDaGiuoco;
    private int turno;
    private Dado dado;

    public GiuocoDellOrca( int numGiocatori, int numCaselle ) {
        giocatori = new Giocatore[numGiocatori];
        for( int i = 0; i < numGiocatori; i++ ) {
            String nome;
            char pedina;
            System.out.print("Inserire il proprio nome: ");
            nome = Leggi.unoString();
            System.out.print("Inserire un carattere da usare come pedina: ");
            pedina = Leggi.unChar();
            giocatori[i] = new Giocatore( nome, pedina );
        }

        campoDaGiuoco = new Tabellone( );
        //nel costruttore del domandiere, verranno inserite man mano le domande lette da un file (chiamando la funzione leggiFile() );
        domandiere = new Domandiere();
        //nel costruttore del mazzoImprevisti, verranno inseriti man mano gli imprevisti letti da un file (chiamando la funzione leggiFile() );
        mazzoImprevisti = new Imprevisto();

        turno = 0;
        //nel costruttore di dado, gli verranno assegnate 6 facce
        dado = new Dado(6);
    }

    public boolean svolgiTurno()    {
        int movimento = giocatore[turno].lanciaDado( dado );
        giocatore[turno].muovi( movimento );

        //altroGiocatore() non returna più un boolean, ma un int che è l'indice del giocatore che è nella stessa casella di quello corrente
        //altroGiocatore() ha ora bisogno di sapere l'indice del vettore (ovvero turno) del giocatore che sta chiamando la funzione per capire che non deve controllare anche lui
        int altroGiocatore = giocatore[turno].altroGiocatore( turno, giocatori );

        if( altroGiocatore > 0 )  {
            // vittoria è true se ha vinto il giocatore di turno, false se ha vinto l'altro giocatore
            int vittoria = giocatore[turno].sfida( giocatore[altroGiocatore] );
            if( vittoria )  {
                giocatore[turno].napoli( giocatore[altroGiocatore] );
            } else {
                giocatore[altroGiocatore].napoli( giocatore[turno] );
            }
        } else {
            Domanda domanda = giocatore[turno].chiediDomanda( domandiere );
            System.out.println( domanda );
            int risposta;
            do  {
                System.out.println("Inserire la risposta alla domanda: ");
                risposta = Leggi.unInt();
            }while( risposta < 1 || risposta > 4 );
            boolean corretta = giocatore[turno].rispondi( risposta, domanda );

            if( corretta )  {
                giocatore[turno].addPunti( domanda.getPunti() );
                
                int casella, spostamentoCasella;
                System.out.print("Scegliere l'indice della casella da spostare: ");
                casella = Leggi.unInt();
                System.out.print("Inserire di quanto la si vuole spostare: ");
                spostamentoCasella = Leggi.unInt();
                
                campoDaGiuoco.spostaCella( casella, spostamentoCasella );
            } else {
                int imprevisto = giocatore[turno].chiediImprevisto( mazzoImprevisti );
                switch( imprevisto ) {
                    //il giocatore si muove di uno spazio definito (5) e ottiene un numero di punti definito (5). stessa cosa in negativo
                    case 1 : {
                        //1 == AUMENTAPUNTI
                        giocatore[turno].addPunti( 5 );
                        break;
                    }
                    case 2 : {
                        //2 == TOGLIPUNTI
                        giocatore[turno].addPunti( -5 );
                        break;
                    }
                    case 3 : {
                        //3 == SPOSTAAVANTI
                        giocatore[turno].muoviImprevisto( 5 );
                        break;
                    }
                    case 4 : {
                        //4 == SPOSTAINDIETRO
                        giocatore[turno].muoviImprevisto( -5 );
                        break;
                    }
                }
            }
        }
        
        //se il giocatore di turno ha vinto, return true così da finire il gioco
        if( giocatore[turno].controlloVincita() == true )   {
            return true;
        }
        //altrimenti passa al turno successivo e returna false
        turno++;
        return false;
    }
}
