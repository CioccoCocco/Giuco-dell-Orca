public class Imprevisto
{
    public int daiImprevisto(giocatore[] giocatori){
         double randomNumber = Math.random();
         randomNumber = randomNumber*100;
         int imprevisto = 0;
         
         if(randomNumber <= 40){
             return 2;
         }else if(randomNumber > 40 && randomNumber <= 80){
             return 4;
         }else if(randomNumber > 80 && randomNumber <= 87){
             return 1;
         }else if(randomNumber  > 87 && randomNumber <= 94){
             return 3;
         }else if(randomNumber > 94){
             return 5;
         }
    }
    
    
    public void svolgiImprevisto( int imp, Giocatore g )
    {
        
        switch( imp ) {
            //il giocatore si muove di uno spazio definito in avanti (2) e ottiene un numero di punti definito (2). stessa cosa in negativo, ma sono -5 e -5
            //se fossero +5 i positivi, che razza di imprevisto sarebbe? >:)
            case 1 : {
                //1 == AUMENTAPUNTI
                System.out.println("Hai guadagnato 2 punti, che fortuna. . . ");
                g.addPunti( 2 );
                break;
            }
            case 2 : {
                //2 == TOGLIPUNTI
                System.out.println("Hai perso 5 punti! AHAHAHA LUCIDI!");
                g.addPunti( -5 );
                break;
            }
            case 3 : {
                //3 == SPOSTAAVANTI
                System.out.println("Sei andato avanti di due caselle, che fortuna. . . ");
                g.muoviImprevisto( 2 );
                break;
            }
            case 4 : {
                //4 == SPOSTAINDIETRO
                System.out.println("Sei andato indietro di 5 caselle! AHAHAHA LUCIDI!");
                g.muoviImprevisto( -5 );
                break;
            }
            case 5:
            {
                System.out.println("Tieni un gatto");
                System.out.println("");
                System.out.println("▒▒▒▒▒▒▒█▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█");
                System.out.println("▒▒▒▒▒▒▒█░▒▒▒▒▒▒▒▓▒▒▓▒▒▒▒▒▒▒░█");
                System.out.println("▒▒▒▒▒▒▒█░▒▒▓▒▒▒▒▒▒▒▒▒▄▄▒▓▒▒░█░▄▄");
                System.out.println("▒▒▄▀▀▄▄█░▒▒▒▒▒▒▓▒▒▒▒█░░▀▄▄▄▄▄▀░░█");
                System.out.println("▒▒█░░░░█░▒▒▒▒▒▒▒▒▒▒▒█░░░░░░░░░░░█");
                System.out.println("▒▒▒▀▀▄▄█░▒▒▒▒▓▒▒▒▓▒█░░░█▒░░░░█▒░░█");
                System.out.println("▒▒▒▒▒▒▒█░▒▓▒▒▒▒▓▒▒▒█░░░░░░░▀░░░░░█");
                System.out.println("▒▒▒▒▒▄▄█░▒▒▒▓▒▒▒▒▒▒▒█░░█▄▄█▄▄█░░█");
                System.out.println("▒▒▒▒█░░░█▄▄▄▄▄▄▄▄▄▄█░█▄▄▄▄▄▄▄▄▄█");
                System.out.println("▒▒▒▒█▄▄█░░█▄▄█░░░░░░█▄▄█░░█▄▄█");
                break;
            }
        }
        
    }
}
