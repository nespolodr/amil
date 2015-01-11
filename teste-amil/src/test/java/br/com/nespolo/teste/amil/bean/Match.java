package br.com.nespolo.teste.amil.bean;

import java.util.List;

import br.com.nespolo.teste.amil.settings.Params;

public class Match {

	private long id;
	private long start;
	private long end;
	private List<Player> players;
	private List<Frag> frags;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Frag> getFrags() {
		return frags;
	}

	public void setFrags(List<Frag> frags) {
		this.frags = frags;
	}
}
