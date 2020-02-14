package conversion;

import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONObject;

import convert.classes.Person;

public class Conversion {

	public static List<JSONObject> txtToJSON(String filePath) {
		Path file = Paths.get(filePath);
		String[] setOfData;
		Person person;
		List<JSONObject> persons = new ArrayList<>();
		
		try {
			List<String> lines = Files.readAllLines(file);
			for (String line : lines) {
				setOfData = line.split("\\|\\|");
				person = new Person(setOfData[0], setOfData[1], setOfData[2], setOfData[3]);
				persons.add(new JSONObject(person));
			}
		} catch (NoSuchFileException e) {
			System.err.printf("Le fichier %s n'existe pas", file.getFileName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return persons;
	}
	
	public static void XmlToJSON() {}
}
