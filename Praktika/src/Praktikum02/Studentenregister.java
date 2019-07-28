package Praktikum02;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Studentenregister {
	HashMap<String, List<Student>> students = new HashMap<>();

	public Studentenregister() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("resources/sample.txt"));
			String line;
			Student newstudent;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
				String[] strings = line.split(";");
				boolean boolean1 = Boolean.parseBoolean(strings[4]);
				newstudent = new Student(Integer.parseInt(strings[0]), strings[1], strings[2], strings[3], boolean1);
				immatrikulieren(newstudent);
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void immatrikulieren(Student neuer) {
		List<Student> newlist = new ArrayList<>();
		if (students.containsKey(neuer.getNachname())) {
			if (!students.get(neuer.getNachname()).contains(neuer)) {
				newlist = students.get(neuer.getNachname());
				newlist.add(neuer);
				students.put(neuer.getNachname(), newlist);
			} else {
				System.out.println("Fehler bei der Immatrikulation: Student schon vorhanden");
			}
		} else {
			newlist = new ArrayList<>();
			newlist.add(neuer);
			students.put(neuer.getNachname(), newlist);
		}
	}

	public Student finde(String nachname, String vorname, String gebDatum) {
		Student student = null;
		if (students.containsKey(nachname)) {
			List<Student> studentlist = students.get(nachname);
			for (Student st : studentlist) {
				if (st.getVorname().equals(vorname) && st.getGebdatum().equals(gebDatum)) {
					student = st;
				}

			}
		} else {
			System.out.println("Studenten mit dem Nachnamen " + nachname + " gibt es nicht");
		}
		return student;
	}

	public List<Student> findeAlle(String nachname) {
		return students.get(nachname);
	}

	public void exmatrikulieren(Student stu) {
		List<Student> studenten = new ArrayList<>();
		if (students.containsKey(stu.getNachname())) {
			studenten = students.get(stu.getNachname());
			for (Student s : studenten) {
				if (s.equals(stu)) {
					studenten.remove(s);
				}
			}
			students.put(stu.getNachname(), studenten);
		} else {
			System.out.println("Studenten mit dem Nachnamen " + stu.getNachname() + " gibt es nicht");
		}
	}
}
