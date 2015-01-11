package br.com.nespolo.teste.amil.constants;

import java.util.ArrayList;
import java.util.List;

public enum PrepositionNames {

	with,
	by,
	using,
	
	;
	
	public static List<String> asList() {
		List<String> allNames = new ArrayList<String>();
		for (PrepositionNames name : PrepositionNames.values())
			allNames.add(name.toString());

		return allNames;
	}
}
