package br.com.nespolo.teste.amil.report;

import org.apache.commons.lang3.StringUtils;

public class PlayerReport {

	private String name;
	private String weapon;
	private int currentStreak;
	private int maxStreak;
	private int totalKills;
	private int totalDeaths;
	
	public PlayerReport() {
		currentStreak = 0;
		maxStreak = 0;
		totalKills = 0;
		totalDeaths = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	public int getCurrentStreak() {
		return currentStreak;
	}

	public int getMaxStreak() {
		return maxStreak;
	}

	public int getTotalKills() {
		return totalKills;
	}

	public int getTotalDeaths() {
		return totalDeaths;
	}
	
	public void kill()
	{
		currentStreak++;
		if(currentStreak > maxStreak)
			maxStreak = currentStreak;
		totalKills++;
	}
	
	public void dead()
	{
		currentStreak = 0;
		totalDeaths++;
	}
	
	@Override
	public boolean equals(Object obj) {
		return StringUtils.equals(this.name, ((PlayerReport) obj).getName());
	}

}
