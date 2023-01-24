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
        int dim;
        do  {
            System.out.print("Scegliere la dimensione del campo ( 50 - 90 ): ");
            dim = Leggi.unInt();
        } while( dim != 50 && dim != 90 );
        
        campoDaGiuoco = new Tabellone(dim);
        
        giocatori = new Giocatore[numGiocatori];
        this.numGiocatori = numGiocatori;
        
        for( int i = 0; i < numGiocatori; i++ ) {
            String nome;
            char pedina;
            System.out.print("Inserire il proprio nome: ");
            nome = Leggi.unoString();
            System.out.print("Inserire un carattere da usare come pedina (un carattere da tastiera, possibilmente): ");
            pedina = Leggi.unChar();
            giocatori[i] = new Giocatore( nome, pedina, campoDaGiuoco );
        }
        
        //nel costruttore del domandiere, verranno inserite man mano le domande lette da un file (chiamando la funzione leggiFile() );
        domandiere = new Domandiere();
        //nel costruttore del mazzoImprevisti, verranno inseriti man mano gli imprevisti letti da un file (chiamando la funzione leggiFile() );
        mazzoImprevisti = new Imprevisto();
        
        cls();
        stampaRegole();
        Leggi.unChar();
        cls();
        
        veroturno = 0;
        turno = 0;
        //nel costruttore di dado, gli verranno assegnate 6 facce
        dado = new Dado(6);
    }

    public boolean svolgiTurno()    {
        perIlProf();
        
        System.out.println("TURNO " + veroturno + "\ngiocatore " + turno + ": " + giocatori[turno].getNome() + " punti: " + giocatori[turno].getPunti() + "\n\n");
                
        int movimento = giocatori[turno].lanciaDado( dado );
        giocatori[turno].muovi( movimento );
        
        System.out.println("\n\nè sucito il numero: " + movimento + "!");
        Leggi.unChar();
        cls();
        
        System.out.println( campoDaGiuoco.stampaTabellone(giocatori) );
        System.out.println("Casella " + giocatori[turno].getCasellaCorrente().getIdentificativo() + ": " + giocatori[turno].getCasellaCorrente().getTitolo() );

        //altroGiocatore() non returna più un boolean, ma un int che è l'indice del giocatore che è nella stessa casella di quello corrente
        //altroGiocatore() ha ora bisogno di sapere l'indice del vettore (ovvero turno) del giocatore che sta chiamando la funzione per capire che non deve controllare anche lui
        int altroGiocatore = giocatori[turno].altroGiocatore( giocatori, turno );

        if( altroGiocatore > -1 )  {
            sezioneSfida(1);
        } else {
            boolean corretta = sezioneDomanda();

            if( corretta )  {
                System.out.println("RISPOSTA CORRETTA!!!\nORA TI VERRANNO ASSEGNATI DEI PUNTICINI");
                giocatori[turno].addPunti( domanda.getPunti() );
                
                int casella, spostamentoCasella;
                System.out.print("Scegliere l'indice della casella da spostare: ");
                casella = Leggi.unInt();
                spostamentoCasella = giocatori[turno].lanciaDado( dado );
                
                campoDaGiuoco.spostaCella( casella, spostamentoCasella );
                System.out.println("Casella spostata");
                Leggi.unChar();
            } else {
                System.out.println("RISPOSTA ERRATA!!!\nPREPARARSI ALLA TRAGEDIA");
                sezioneImprevisto();
            }
        }
        
        //se il giocatore di turno ha vinto, return true così da finire il gioco
        if( sezioneVittoria() )
            return true;
        //altrimenti passa al turno successivo e returna false
        veroturno++;
        turno = (turno + 1) % numGiocatori;
        
        Leggi.unChar();
        cls();
        return false;
    }
    
    public void cls()  {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else  {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)   {
            //  Handle any exceptions.
            System.out.print('\u000C');// comando utilizzato su BlueJ per pulire lo schermo ( trovato su stackoverflow :> )
        }
    }
    
    public void sezioneSfida( int altroGiocatore )  {
        // vittoria è true se ha vinto il giocatore di turno, false se ha vinto l'altro giocatore
        boolean vittoria = giocatori[turno].sfida( giocatori[altroGiocatore], dado );
        if( vittoria )  {
            System.out.println("Ha vinto il giocatore " + turno);
            giocatori[turno].napoli( giocatori[altroGiocatore] );
            giocatori[turno].muovi(1);
        } else {
            System.out.println("Ha vinto il giocatore " + altroGiocatore);
            giocatori[altroGiocatore].napoli( giocatori[turno] );
            giocatori[altroGiocatore].muovi(1);
        }
        System.out.println("Il vincitore ha napoletanato i punti al perdente\nOcchio a non perdere le mani, vincitore");
        Leggi.unChar();
        cls();
    }
    
    public boolean sezioneVittoria()    {
        if( giocatori[turno].controlloVincita() == true )   {
            System.out.println("No, NO, NOOOO!!! NON E' POSSIBILE!!!");
            return true;
        }
        
        return false;
    }
    
    public void sezioneImprevisto() {
        int imprevisto = giocatori[turno].chiediImprevisto( mazzoImprevisti );
        switch( imprevisto ) {
            //il giocatore si muove di uno spazio definito in avanti (2) e ottiene un numero di punti definito (2). stessa cosa in negativo, ma sono -5 e -5
            //se fossero +5 i positivi, che razza di imprevisto sarebbe? >:)
            case 1 : {
                //1 == AUMENTAPUNTI
                System.out.println("Hai guadagnato 2 punti, che fortuna. . . ");
                giocatori[turno].addPunti( 2 );
                break;
            }
            case 2 : {
                //2 == TOGLIPUNTI
                System.out.println("Hai perso 5 punti! AHAHAHA LUCIDI!");
                giocatori[turno].addPunti( -5 );
                break;
            }
            case 3 : {
                //3 == SPOSTAAVANTI
                System.out.println("Sei andato avanti di due caselle, che fortuna. . . ");
                giocatori[turno].muoviImprevisto( 2 );
                break;
            }
            case 4 : {
                //4 == SPOSTAINDIETRO
                System.out.println("Sei andato indietro di 5 caselle! AHAHAHA LUCIDI!");
                giocatori[turno].muoviImprevisto( -5 );
                break;
            }
        }
    }
    
    public boolean sezioneDomanda()    {
        Domanda domanda = giocatori[turno].chiediDomanda( domandiere );
        System.out.println( domanda );
        int risposta;
        do  {
            System.out.println("Inserire la risposta alla domanda: ");
            risposta = Leggi.unInt();
        }while( risposta < 1 || risposta > 4 );
        return giocatori[turno].rispondi( risposta, domanda );
    }
    
    public void perIlProf()    {
        System.out.println("Si vuole forzare qualche funzione? s - sì ; altro - no");
        if( Leggi.unChar() == 's' )   {
            System.out.println("Che funzione? (Tutte le azioni verranno svolte sul giocatore 0 (e eventualmente 1)\n s - sfida\n v - vittoria\n i - imprevisto\n d - domanda\n m - muovi\n");
            char scelta = Leggi.unChar();
            switch(scelta)  {
                case 's': {
                    sezioneSfida(1);
                    break;
                }
                case 'v': {
                    sezioneVittoria();
                    break;
                }
                case 'i': {
                    sezioneImprevisto();
                    break;
                }
                case 'd': {
                    sezioneDomanda();
                    break;
                }
                case 'm': {
                    System.out.println("Scegliere l'indice lineare della casella a cui mandare il giocatore: ");
                    int indice = Leggi.unInt();
                    giocatori[0].setCasellaCorrente( campoDaGiuoco.casellaIn(indice) );
                    break;
                }
            }
        } else {
            cls();
        }
    }
    
    public void stampaRegole()  {
        System.out.println("Le regole sono semplice:\nI giocatori tireranno un dado per muoversi da una casella all'altra.");
        System.out.println("Una volta caduti su una casella, se ci saranno due giocatori su di essa, si sfideranno a un duello all'ultimo dado.");
        System.out.println("La lotta consiste in tre successivi lanci di dadi: ad ogni lancio chi fa il punteggio più alto vince. Il vincitore ruba tutti i punti al perdente.");
        System.out.println("Se invece non ci sono altri giocatori, al giocatore di turno verrà posta una domanda.");
        System.out.println("Se verrà data la risposta corretta, il giocatore guadagnerà dei punti e potrà spostare una casella del tabellone.");
        System.out.println("Altrimenti verrà sottoposto ad un terribile imprevisto.");
        System.out.println("Per vincere, bisogna arrivare ESATTAMENTE all'ultima casella con almeno un punticino nelle proprie tasche.");
        System.out.println("Piccolo appunto per il prof: ad ogni turno verrà chiesto se si vuole eseguire forzatamente una funzione e, se sì, quale");
        System.out.println("Bene, ora buona fortuna e che vinca quello con più fattore C. DIVERTITEVI! (spoiler: non succederà)\n\nPremere invio per continuare");
    }
}
