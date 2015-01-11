package br.com.nespolo.teste.amil.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.nespolo.teste.amil.report.FragReport;
import br.com.nespolo.teste.amil.report.MatchReport;

public class InputUtils {
	
//	13/05/1999 14:08:37 - New match 6073726979788505088 has started
//	13/05/1999 14:08:40 - Rudy murdered Glayds by M16
//	13/05/1999 14:08:46 - Toni dispatched Christiane using Shotgun
//	13/05/1999 14:13:37 - Match 6073726979788505088 has ended
	
//	([\d/]+\s[\d+:]+)
	private static final String PATTERN_TIME = "([\\d/]+\\s[\\d+:]+)";
//	([\d/]+\s[\d+:]+)[\s\w-]+(\d+)[\s\w]+started
	private static final String PATTERN_BEGIN_MATCH = PATTERN_TIME + "\\D+(\\d+)[\\s\\w]+started";
//	([\d/]+\s[\d+:]+)[\s\w-]+(\d+)[\s\w]+ended
	private static final String PATTERN_END_MATCH = PATTERN_TIME + "\\D+(\\d+)[\\s\\w]+ended";
//	([\d/]+\s[\d+:]+)[\s-]+(\w+)\s+\w+\s+(\w+)\s+\w+\s+(\w+)
	private static final String PATTERN_FRAG = PATTERN_TIME + "[\\s-]+(\\w+)\\s+\\w+\\s+(\\w+)\\s+\\w+\\s+(\\w+)";
	
	public static List<MatchReport> getMatches(List<String> lines)
	{
		List<MatchReport> matches = new ArrayList<MatchReport>();
		
		Pattern begin = Pattern.compile(PATTERN_BEGIN_MATCH);
		Pattern end = Pattern.compile(PATTERN_END_MATCH);
		Pattern frag = Pattern.compile(PATTERN_FRAG);
		Matcher m;
		
		MatchReport currentMatch = new MatchReport();
		for(String line : lines)
		{
			m = begin.matcher(line);
			if(m.matches())
			{
				parseStart(m, currentMatch);
				continue;
			}
			
			m = end.matcher(line);
			if(m.matches())
			{
				parseEnd(m, currentMatch);
				matches.add(currentMatch);
				currentMatch = new MatchReport();
				continue;
			}
			
			m = frag.matcher(line);
			if(m.matches())
			{
				parseFrag(m, currentMatch);
			}
		}
		
		return matches;
	}

	private static void parseStart(Matcher m, MatchReport currentMatch) {
		String instant = m.group(1);
		String id = m.group(2);
		
		if(currentMatch.getStart() != 0l)
		{
			// arquivo com erro
		} else {
			currentMatch.setId(Long.parseLong(id));
			currentMatch.setStart(DateUtils.parse(instant));
		}
	}
	
	private static void parseEnd(Matcher m, MatchReport currentMatch) {
		String instant = m.group(1);
		
		if(currentMatch.getStart() == 0l || currentMatch.getEnd() != 0l)
		{
			// arquivo com erro
		} else {
			currentMatch.setEnd(DateUtils.parse(instant));
		}
	}
	
	private static void parseFrag(Matcher m, MatchReport currentMatch) {
		String instant = m.group(1);
		String killer = m.group(2);
		String killed = m.group(3);
		String weapon = m.group(4);
		
		FragReport frag = new FragReport();
		frag.setInstant(DateUtils.parse(instant));
		frag.setKiller(killer);
		frag.setKilled(killed);
		frag.setWeapon(weapon);
		
		currentMatch.update(frag);
	}
}
