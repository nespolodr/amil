package br.com.nespolo.teste.amil.constants;

import java.util.ArrayList;
import java.util.List;

public enum WeaponNames {

	M4A1,
	Beretta,
	Shotgun,
	SniperRifle,
	Spear,
	M16,
	Crossbow,
	AK47,
	Fireball,
	Glock,
	Lightning,
	Catalyst,
	Dagger,
	DesertEagle,
	Shuriken,
	Minigun,
	ThrowingAxes,
	SchmidtScout,
	Rock,
	Cannon,
	Lasergun,
	USP,
	MP5Navy,
	RocketLaucher,
	Goldengun,
	RPG,
	
	;
	

	public static List<String> asList()
	{
		List<String> allNames = new ArrayList<String>();
		for(WeaponNames name : WeaponNames.values())
			allNames.add(name.toString());
		
		return allNames;
	}
}
