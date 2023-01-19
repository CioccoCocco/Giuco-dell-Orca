public class Giocatore
{
	private String nome;
	private int point; // punteggio
	private int pos; // posizione
	
	public Giocatore(String nome, int pos)
	{
		this.nome = nome;
		this.pos = pos;
		this.point = 0;
	}

	public String getNome()
	{
		return nome;
	}
	
	public String setNome() 
	{
		return nome;
	}

	public void setpoint(int point)
	{
		this.point = point;
	}
	
	public int getpoint()
	{
		return point;
	}

	public void muoviPedina(int pos, int grandCampo)
	{
		boolean tornaInd = false;
		boolean esc = false;
		
		while(esc != true)
		{	
			if(this.pos == grandCampo || tornaInd)
			{
				this.pos = this.pos - 1;
				tornaInd = true;
			}else {
			
				this.pos = this.pos + 1;
				
			}	
			
			if(pos <= 1)
			{
				esc = true;
				
			}
			
			pos = pos - 1;
			
		}	
		
		if(this.pos < 0)
		{
			this.pos = 0;
			
		}
	}
}