class Casella {
    private Casella precedente;
    private Casella successivo;
    private int identificativo;
    private String titolo;

    public Casella( int identificativo, Casella precedente, Casella successivo, String titolo ){
        this.identificativo = identificativo;
        this.precedente = precedente;
        this.successivo = successivo;
        this.titolo = titolo;
    }

    public int getIdentificativo( ){
        return identificativo;
    }

    public Casella getPrecedente( ){
        return precedente;
    }

    public Casella getSuccessivo( ){
        return successivo;
    }
    
    public String getTitolo( ){
        return titolo;
    }

    public void setIdentificativo( int identificativo ){
        this.identificativo = identificativo;
    }
    
    public void setPrecedente( Casella precedente ){
        this.precedente = precedente;
    }

    public void setSuccessivo( Casella successivo ){
        this.successivo = successivo;
    }
    
    public void setTitolo( String titolo )  {
        this.titolo = titolo;
    }
    
    public boolean equals( Object obj ) {
        if( obj instanceof Casella )    {
            Casella cmp = (Casella)obj;
            return this.precedente == cmp.getPrecedente() && this.successivo == cmp.getSuccessivo() && this.identificativo == cmp.getIdentificativo()  && this.titolo.equals(cmp.getTitolo());
        }
        
        return false;
    }
}
