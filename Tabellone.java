class Tabellone {
    private Casella inizio; // riferimento al primo nodo della lista
    private Casella fine; // riferimento all'ultimo nodo della lista
    private int dimensione; // numero di elementi inseriti nella lista
    
    public Tabellone() {
        inizio = null;
        fine = null;
        dimensione = 0;
    }
    
    public Tabellone( int numCaselle ) {
        dimensione = 0;
        for( int i = 0; i < numCaselle; i++ )   {
            inserisciUltimo( i );
        }
    }
    // verifica se la lista e' vuota
    public boolean vuota(){
        return dimensione == 0;
    }

    public int getDimensione(){
        return dimensione;
    }

    public int getPrimoIdentificativo(){
        return inizio.getIdentificativo();
    }

    public int getUltimoIdentificativo(){
        return fine.getIdentificativo();
    }
    // Inserisce un nuovo elemento nella lista al primo posto
    public void inserisciPrimo(int identificativo){
        inizio = new Casella(identificativo,null,null,null);
        if (vuota())
            fine = inizio;
        dimensione++;
    }
    // Inserisce un nuovo elemento nella lista in ultima posizione
    public void inserisciUltimo(int identificativo){
        if (vuota())
            inserisciPrimo(identificativo);
        else {
            fine.setSuccessivo(new Casella(identificativo,fine,null,null));
            fine = fine.getSuccessivo();
            dimensione++;
        }
    }
    /*
    public int identificativoIn( int i )  {
        Casella questo = inizio;
        int cnt = 0;
        
        do {
            cnt++;
            questo = questo.getSuccessivo();
        }while( questo != null && cnt < i );
        
        return questo.getIdentificativo();
    }*/
    
    public int identificativoIn( int i )  {
        return identificativoIn( i, inizio, 0 );
    }
    
    public int identificativoIn( int i, Casella questo, int cnt )  {
        if( cnt == i )  {
            return questo.getIdentificativo();
        } else {
            return identificativoIn( i, questo.getSuccessivo(), ++cnt );
        }
    }
    
    public Casella casellaIn( int i )  {
        return casellaIn( i, inizio, 0 );
    }
    
    public Casella casellaIn( int i, Casella questo, int cnt )  {
        if( cnt == i )  {
            return questo;
        } else {
            return casellaIn( i, questo.getSuccessivo(), ++cnt );
        }
    }
    
    public void spostaCella( int casella, int spostamento )   {
        Casella daSpostare = casellaIn( casella );
        /* dovrò conoscere altre 4 caselle : 
         * la casella prima di quella da spostare
         * la casella dopo di quella da spostare
         * la casella prima di quella che ho appena spostato dopo lo spoastamento
         * la casella dopo di quella che ho appena spostato dopo lo spoastamento
         * così facendo potrò collegare tutte le caselle nel modo giusto
         */
        Casella primaUno = daSpostare.getPrecedente();
        Casella dopoUno = daSpostare.getSuccessivo();
        Casella primaDue = casellaIn( casella+spostamento );
        Casella dopoDue = casellaIn( casella+spostamento + 1 );
        
        primaUno.setSuccessivo( dopoUno );
        dopoUno.setPrecedente( primaUno );
        
        // primaDue     daSpostare      dopoDue
        primaDue.setSuccessivo( daSpostare );
        daSpostare.setPrecedente( primaDue );
        daSpostare.setSuccessivo( dopoDue );
        dopoDue.setPrecedente( daSpostare );
    }
    
    public String toString()    {
        String s = "";
        
        Casella questo = inizio;
        
        while( questo != null ) {
            s += questo.getIdentificativo() + ", ";
            questo = questo.getSuccessivo();
        }
        
        s = s.substring(0, s.length()-2);
        
        return s;
    }
}
