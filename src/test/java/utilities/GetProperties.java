package utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GetProperties {

	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("D:\\Spring-Tool-Suit2\\Etv-Win\\src\\test\\resources\\config\\config.properties");

		Properties p = new Properties();
		p.load(fr);
		
		System.out.println(p.getProperty("browser"));
		System.out.println(p.getProperty("testurl"));
	}

}
