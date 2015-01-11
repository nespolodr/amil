package br.com.nespolo.teste.amil.bean;

import org.apache.commons.lang3.StringUtils;

public class Player {

	private String name;
	private Weapon weapon;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	@Override
	public boolean equals(Object obj) {
		return StringUtils.equals(this.name, ((Player) obj).getName());
	}
}
