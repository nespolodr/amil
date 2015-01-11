package br.com.nespolo.teste.amil.bean;


public class Frag {

	private long instant;
	private Player killer;
	private Player killed;

	public long getInstant() {
		return instant;
	}

	public void setInstant(long instant) {
		this.instant = instant;
	}

	public Player getKiller() {
		return killer;
	}

	public void setKiller(Player killer) {
		this.killer = killer;
	}

	public Player getKilled() {
		return killed;
	}

	public void setKilled(Player killed) {
		this.killed = killed;
	}

}
