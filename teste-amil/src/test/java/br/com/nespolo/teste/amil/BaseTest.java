package br.com.nespolo.teste.amil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import br.com.nespolo.teste.amil.bean.Frag;
import br.com.nespolo.teste.amil.bean.Match;
import br.com.nespolo.teste.amil.bean.Player;
import br.com.nespolo.teste.amil.bean.Weapon;
import br.com.nespolo.teste.amil.constants.KillNames;
import br.com.nespolo.teste.amil.constants.PlayerNames;
import br.com.nespolo.teste.amil.constants.PrepositionNames;
import br.com.nespolo.teste.amil.constants.WeaponNames;
import br.com.nespolo.teste.amil.settings.Params;
import br.com.nespolo.teste.amil.util.DateUtils;

public class BaseTest extends TestCase {
	public BaseTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(BaseTest.class);
	}

	public void testApp() {
		genLOGFile();
	}
	
	private static final String END = "\r\n";

	private void genLOGFile() {

		long genInstant = RandomUtils.nextLong(0, Calendar.getInstance().getTimeInMillis());
		long lastInstant = genInstant;
		int matchesToLog = RandomUtils.nextInt(1, 11);
		StringBuffer log = new StringBuffer();
		for(int i=0; i< matchesToLog; i++)
		{
			Match match = genMatch(lastInstant);
			log.append(DateUtils.format(match.getStart())).append(" - New match ").append(match.getId()).append(" has started").append(END);
			
			for(Frag frag : match.getFrags())
			{
				log.append(DateUtils.format(frag.getInstant()))
					.append(" - ")
					.append(frag.getKiller().getName())
					.append(" ")
					.append(KillNames.asList().get(RandomUtils.nextInt(0, KillNames.asList().size())))
					.append(" ")
					.append(frag.getKilled().getName())
					.append(" ")
					.append(PrepositionNames.asList().get(RandomUtils.nextInt(0, PrepositionNames.asList().size())))
					.append(" ")
					.append(frag.getKiller().getWeapon().getName())
					.append(END);
			}
			
			log.append(DateUtils.format(match.getEnd())).append(" - Match ").append(match.getId()).append(" has ended").append(END);
			lastInstant = match.getEnd();
		}
		
		try {
			File dir = new File(FileUtils.getUserDirectory(), "amil" + File.separator + "match_log");
			FileUtils.forceMkdir(dir);
			FileUtils.writeStringToFile(new File(dir, "LOG_" + genInstant + ".txt"), log.toString());
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}
		
	}
	
	private Match genMatch(long after) {

		long start = RandomUtils.nextLong(after, after + (2 * 60 * 60 * 1000));
		Match match = new Match();
		match.setId(Math.abs(Long.reverse(start)));
		match.setStart(start);
		match.setEnd(start + Params.MATCH_DURATION);
		match.setPlayers(genPlayers(RandomUtils.nextInt(2, 21)));
		
		List<Frag> frags = new ArrayList<Frag>();
		match.setFrags(frags);
		
		Frag currentFrag = genFrag(match);
		while(currentFrag != null)
		{
			frags.add(currentFrag);
			currentFrag = genFrag(match);
		}
		
		return match;
	}
	
	private long periodBetweenActions(int players)
	{
		int avg = (20 * 1000) / players;
		return RandomUtils.nextLong(avg, avg + (5 * 1000));
	}

	private Frag genFrag(Match match) {

		long instant = lastInstant(match) + periodBetweenActions(match.getPlayers().size());
		if(instant > match.getEnd())
			return null;

		Random r = new Random();
		LinkedList<Player> players = new LinkedList<Player>(match.getPlayers());
		Player killer = players.remove(r.nextInt(players.size()));
		Player killed = players.remove(r.nextInt(players.size()));
		
		Frag frag = new Frag();
		frag.setInstant(instant);
		frag.setKiller(killer);
		frag.setKilled(killed);
				
		return frag;
	}
	
	private long lastInstant(Match match)
	{
		if(match.getFrags().isEmpty())
			return match.getStart();
		return match.getFrags().get(match.getFrags().size() - 1).getInstant();
	}

	private List<Player> genPlayers(int quantity) {
		List<Player> players = new ArrayList<Player>();
		List<String> allPlayers = PlayerNames.asList();
		List<String> allWeapon = WeaponNames.asList();
		
		Random r = new Random();
		
		while(players.size() < quantity)
		{
			String playerName = allPlayers.remove(r.nextInt(allPlayers.size()));
			
			Player player = new Player();
			player.setName(playerName.toString());
			player.setWeapon(new Weapon(allWeapon.get(r.nextInt(allWeapon.size())).toString()));
			
			players.add(player);
		}
		
		return players;
	}

}
