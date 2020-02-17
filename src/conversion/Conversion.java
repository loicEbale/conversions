package conversion;

import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.json.XML;

import convert.classes.Person;

public class Conversion {

	public static List<JSONObject> txtToJSON(String filePath) {
		Path file = Paths.get(filePath);
		String[] setOfData;
		Person person;
		List<JSONObject> persons = new ArrayList<>();
		
		try {
			List<String> lines = Files.readAllLines(file);
			for (int i = 0; i<lines.size(); i++) {
				setOfData = lines.get(i).split("\\|\\|");
				if(!lines.get(i).isEmpty()) {
					person = new Person(setOfData[0], setOfData[1], setOfData[2], setOfData[3]);
					persons.add(new JSONObject(person));
				} else if(lines.get(i).isEmpty() && !lines.get(i + 1).isEmpty()) {
					setOfData = lines.get(i + 1).split("\\|\\|");
					person = new Person(setOfData[0], setOfData[1], setOfData[2], setOfData[3]);
					persons.add(new JSONObject(person));	
				}
			}
		} catch (NoSuchFileException e) {
			System.err.printf("Fichier introuvable -- Chemin spécifié incorrect");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return persons;
	}
	
	public static JSONObject XmlToJSON(String filePath) {
		Path file = Paths.get(filePath);
		JSONObject obj = null;
		try {
			byte[] b = Files.readAllBytes(file);
			String xml = new String(b);
			
			 obj = XML.toJSONObject(xml);
			
		} catch (NoSuchFileException e) {
			System.err.printf("Fichier introuvable -- Chemin spécifié incorrect");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
}
