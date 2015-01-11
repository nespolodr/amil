package br.com.nespolo.teste.amil;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import br.com.nespolo.teste.amil.report.MatchReport;
import br.com.nespolo.teste.amil.report.PlayerReport;
import br.com.nespolo.teste.amil.report.WeaponReport;
import br.com.nespolo.teste.amil.util.InputUtils;

/**
 * Lê arquivos de log em diretório e gera relatório.
 * <br/><br/>
 * Arquivos de log devem estar em:<br/>
 * 'home'/amil/match_log
 * <br/><br/>
 * Arquivos de relatório serão gerados em:<br/>
 * 'home'/amil/matches_report
 */
public class MainClass 
{
	private static final String END = "\r\n";
	
    public static void main( String[] args )
    {
		File logDir = new File(FileUtils.getUserDirectory(), "amil" + File.separator + "match_log");
		if(!logDir.exists())
			// Não existem logs
			return;
		
		List<MatchReport> matches = readLogs(logDir);
		String reportsString = genReport(matches);
		writeReport(reportsString);
    }

	private static void writeReport(String reportsString) {
		try {
			File reportDir = new File(FileUtils.getUserDirectory(), "amil" + File.separator + "matches_report");
			if(!reportDir.exists())
				FileUtils.forceMkdir(reportDir);

			long genInstant = RandomUtils.nextLong(0, Calendar.getInstance().getTimeInMillis());
			FileUtils.writeStringToFile(new File(reportDir, "REPORT_" + genInstant + ".txt"), reportsString.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static List<MatchReport> readLogs(File dir) {
		List<MatchReport> allMatches = new ArrayList<MatchReport>();
		
		for(File file : dir.listFiles())
		{
			if(file.isFile())
			{
				String name = file.getName();
				Pattern p = Pattern.compile("LOG_\\d+\\.txt");
				Matcher m = p.matcher(name);
				if(m.matches())
				{
					try {
						List<String> lines = FileUtils.readLines(file);
						List<MatchReport> matches = InputUtils.getMatches(lines);
						allMatches.addAll(matches);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					// Este arquivo não é um LOG de partidas FPS
				}
			}
		}
		
		return allMatches;
	}

	private static String genReport(List<MatchReport> matches) {
		
		StringBuffer report = new StringBuffer();
		for(MatchReport match : matches)
		{
			report.append(genMatchReport(match));
		}
		return report.toString();
	}

	private static String genMatchReport(MatchReport match) {
		
		StringBuffer report = new StringBuffer();
		report.append("MATCH[").append(match.getId()).append("]").append(END).append(END);

		report.append("PLAYERS").append(END);
		genPlayerReport(match, report);
		report.append(END);
		
		report.append("WEAPONS").append(END);
		genWeaponReport(match, report);
		
		report.append("-------------------------------------------------------------");
		report.append(END).append(END);
		return report.toString();
	}

	private static void genPlayerReport(MatchReport match, StringBuffer report) {
		
		PlayerReport mostKills = null;
		PlayerReport mostStreak = null;
		PlayerReport mostDeath = null;
		
		for(PlayerReport player : match.getPlayers())
		{
			report.append(StringUtils.rightPad("Player: " + player.getName(), 30))
					.append(StringUtils.rightPad(player.getWeapon() != null ? "Weapon: " + player.getWeapon() : "", 25))
					.append(StringUtils.rightPad("Kills: " + player.getTotalKills(), 15))
					.append(StringUtils.rightPad("Deaths: " + player.getTotalDeaths(), 15))
					.append(StringUtils.rightPad("Streak: " + player.getMaxStreak(), 15))
					.append(END);
			
			if(mostKills == null)
				mostKills = player;
			if(mostStreak == null)
				mostStreak = player;
			if(mostDeath == null)
				mostDeath = player;
			
			if(player.getTotalKills() > mostKills.getTotalKills())
				mostKills = player;
			if(player.getMaxStreak() > mostStreak.getMaxStreak())
				mostStreak = player;
			if(player.getTotalDeaths() > mostDeath.getTotalDeaths())
				mostDeath = player;
		}

		report.append(END);
		report.append("Most Kills: ").append(mostKills.getName()).append(" with ").append(mostKills.getTotalKills()).append(" kills. A crazy murder!!").append(END);
		report.append("Most Deaths: ").append(mostDeath.getName()).append(" with ").append(mostDeath.getTotalDeaths()).append(" deaths. Little noob!!").append(END);
		report.append("Most Streak: ").append(mostStreak.getName()).append(" with ").append(mostStreak.getMaxStreak()).append(" streak kills. Rambo!!").append(END);
	}

	private static void genWeaponReport(MatchReport match, StringBuffer report) {
		WeaponReport mostUsed = null;
		WeaponReport mostKill = null;
		for(WeaponReport weapon : match.getWeapons())
		{
			report.append(StringUtils.rightPad("Weapon: " + weapon.getName(), 30))
					.append(StringUtils.rightPad("Kills: " + weapon.getKills(), 15))
					.append(StringUtils.rightPad("Used by: " + weapon.getUsed(), 15))
					.append(END);
			
			if(mostUsed == null)
				mostUsed = weapon;
			if(mostKill == null)
				mostKill = weapon;
			
			if(weapon.getUsed() > mostUsed.getUsed())
				mostUsed = weapon;
			if(weapon.getKills() > mostKill.getKills())
				mostKill = weapon;
		}

		report.append(END);
		report.append("Best Weapon: ").append(mostKill.getName()).append(" - ").append(mostKill.getKills()).append(" total kills.").append(END);
		report.append("Most Used Weapon: ").append(mostUsed.getName()).append(" - ").append(mostUsed.getUsed()).append(" players used it.").append(END);
	}
}
