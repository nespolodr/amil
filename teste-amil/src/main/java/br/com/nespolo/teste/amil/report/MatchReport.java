package br.com.nespolo.teste.amil.report;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class MatchReport {

	private long id;
	private long start;
	private long end;

	private List<PlayerReport> players;
	private List<WeaponReport> weapons;

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

	public List<PlayerReport> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerReport> players) {
		this.players = players;
	}

	public List<WeaponReport> getWeapons() {
		return weapons;
	}

	public void setWeapons(ArrayList<WeaponReport> weapons) {
		this.weapons = weapons;
	}

	public void update(FragReport frag) {
		PlayerReport killer = getPlayer(frag.getKiller(), frag.getWeapon());
		killer.kill();
		PlayerReport killed = getPlayer(frag.getKilled(), null);
		killed.dead();
		WeaponReport weapon = getWeapon(frag.getWeapon());
		weapon.kill();
	}

	private PlayerReport getPlayer(String name, String weapon) {

		PlayerReport player;
		if (players == null)
			players = new ArrayList<PlayerReport>();

		for (PlayerReport current : players) {
			if (StringUtils.equals(current.getName(), name))
			{
				// Precisa atualizar a arma
				if(current.getWeapon() == null && weapon != null)
				{
					current.setWeapon(weapon);
					WeaponReport weaponReport = getWeapon(weapon);
					weaponReport.newUser();
				}
				
				return current;
			}
		}

		player = new PlayerReport();
		player.setName(name);
		player.setWeapon(weapon);
		players.add(player);

		if(weapon != null)
		{
			WeaponReport weaponReport = getWeapon(weapon);
			weaponReport.newUser();
		}
		return player;
	}

	private WeaponReport getWeapon(String name) {

		WeaponReport weapon;
		if (weapons == null)
			weapons = new ArrayList<WeaponReport>();

		for (WeaponReport current : weapons) {
			if (StringUtils.equals(current.getName(), name))
				return current;
		}

		weapon = new WeaponReport(name);
		weapons.add(weapon);

		return weapon;
	}
}
