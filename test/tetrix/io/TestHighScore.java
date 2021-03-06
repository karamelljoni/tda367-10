package tetrix.io;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import tetrix.core.Entry;
import tetrix.core.HighScore2;

public class TestHighScore {

	@Test
	public void test() throws FileNotFoundException {
		HighScore2 h = new HighScore2();
		List<Entry> es = h.getHighScore();
		Entry first = es.get(0);
		Entry second = es.get(1);
		Entry third = es.get(2);
		Entry fourth = es.get(3);
		String name = first.getName();
		String name2 = second.getName(); 
		String name3 = third.getName();
		String name4 = fourth.getName();
		Integer points = first.getPoints();
		Integer points2 = second.getPoints();
		Integer points3 = third.getPoints();
		Integer points4 = fourth.getPoints();
		assertTrue(name.equals("Pelle"));
		assertTrue(name2.equals("Olle"));
		assertTrue(name3.equals("Johan"));
		assertTrue(name4.equals("Banan"));
		assertTrue(points.equals(26));
		assertTrue(points2.equals(23));
		assertTrue(points3.equals(12));
		assertTrue(points4.equals(15));
	}
	@Test
	public void testUpdate() throws IOException{
		HighScore2 h = new HighScore2();
		h.setHighScore("God", 14);
		List<Entry> es = h.getHighScore();
		Entry first = es.get(0);
		String name = first.getName();
		Integer points = first.getPoints();
		assertTrue(name.equals("Pelle"));
		assertTrue(points.equals(26));
		
	}

}
