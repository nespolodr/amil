package br.com.nespolo.teste.amil.constants;

import java.util.ArrayList;
import java.util.List;

public enum KillNames {

	killed,
	assassinated,
	executed,
	murdered,
	slayed,
	dispatched,
	exterminated,
	neutralized,
	erased,
	annihilated,
	
	;

	public static List<String> asList() {
		List<String> allNames = new ArrayList<String>();
		for (KillNames name : KillNames.values())
			allNames.add(name.toString());

		return allNames;
	}
}
