package br.com.nespolo.teste.amil.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.nespolo.teste.amil.settings.Params;

public class DateUtils {

	public static String format(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(Params.DATE_FORMAT);
		return sdf.format(date);
	}

	public static String format(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat(Params.DATE_FORMAT);
		return sdf.format(new Date(time));
	}
	
	public static long parse(String date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(Params.DATE_FORMAT);
		try {
			Date parse = sdf.parse(date);
			return parse.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new RuntimeException("Erro");
	}
}
