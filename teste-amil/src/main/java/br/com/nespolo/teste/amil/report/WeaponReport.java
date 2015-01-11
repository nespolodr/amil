package br.com.nespolo.teste.amil.report;

import org.apache.commons.lang3.StringUtils;

public class WeaponReport {

	private String name;
	private int kills;
	private int used;

	public WeaponReport(String name) {
		this.name = name;
		kills = 0;
		used = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKills() {
		return kills;
	}

	public int getUsed() {
		return used;
	}

	public void newUser() {
		used++;
	}

	public void kill() {
		kills++;
	}

	@Override
	public boolean equals(Object obj) {
		return StringUtils.equals(this.name, ((WeaponReport) obj).getName());
	}
}
