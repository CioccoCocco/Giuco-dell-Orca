public class Giocatore
{
	private String nome;
	private int soldi; 
	private int pos;
	private char carattere;
	
	public Giocatore(String name, char carattere)
	{
		this.nome = name;
		this.carattere = carattere;
		this.soldi = 0;
		this.pos = 0;
	}
	
	public int lanciaDado(Dado d)
	{
		return d.random(); // non capisco cos'ho sbagliato
	}
	
	public void muovi(int nCaselle, int grandezzaCampo)
	{
		boolean esci = false;
		
		while(!esci) {
			
			if(pos == grandezzaCampo-1) {
				pos -= 1;
			}
			else {
				pos += 1;
			}
			
			if(nCaselle <= 1)
				esci = true;
			
			nCaselle -= 1;
		}
		
		if(pos < 0)
			pos = 0;
	}
	
	public void chiediDomanda(Domandiere d)
	{
		String s = d.getDomanda(); // Andre riusciresti a mettere che returna la domanda al posto del void
	}
	
	public void sfida(Giocatore g2, Dado d)
	{
		int p1 = 0;
		int p2 = 0;
		
		for(int i = 0; i < 3; i++) {
			p1 += d.random();
			p2 += d.random();
		}
		
		if(p1 > p2) {
			setSoldi(g2.getSoldi(), true);
			g2.setSoldi(g2.getSoldi(), false);			
		}
		else {
			g2.setSoldi(getSoldi(), true);
			setSoldi(getSoldi(), false);
		}
	}
	
	public int getSoldi()
	{
		return soldi;
	}
	
	public void setSoldi(int s, boolean wl)
	{ //se wl è true aumenta i soldi altrimenti li toglie 
		if(wl) {
			soldi += s;
		}
		else
			soldi -= s;
	}
	
	public boolean controlloVincita(int nCaselle)
	{
		return pos == nCaselle ?  true :  false;
	}
	
	public void tornaAInizio()
	{
		pos = 0;
	}
}