package br.com.nespolo.teste.amil.constants;

import java.util.ArrayList;
import java.util.List;

public enum PlayerNames {

	Merlene,
	Don,
	Nila,
	Justin,
	Rudy,
	Margo,
	Josh,
	Jaunita,
	Beula,
	Yan,
	Glayds,
	Chauncey,
	Beverlee,
	Bobbi,
	Johnette,
	Victor,
	Chanell,
	Shameka,
	Pauline,
	Venita,
	Young,
	Ethan,
	Shirley,
	Kristle,
	Lisabeth,
	Kayce,
	Hoa,
	Carisa,
	Kristian,
	Oliver,
	Joannie,
	Meggan,
	Chin,
	Valda,
	Saul,
	Jerrica,
	Zulema,
	Mafalda,
	Neil,
	Jessie,
	Larissa,
	Toni,
	Nelida,
	Glenn,
	Huong,
	Jodie,
	Juli,
	Twanna,
	Emelia,
	Christiane,

	;
	
	public static List<String> asList()
	{
		List<String> allNames = new ArrayList<String>();
		for(PlayerNames name : PlayerNames.values())
			allNames.add(name.toString());
		
		return allNames;
	}
}
