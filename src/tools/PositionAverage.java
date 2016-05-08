package tools;

import java.util.Arrays;

public class PositionAverage {
	private final Vector[] list;
	private int index_of_next_last_element = 0;

	public PositionAverage(int size) {
		list = new Vector[size];
		initialize();
	}

	public void add(Vector position) {
		list[index_of_next_last_element] = position;
		index_of_next_last_element = (index_of_next_last_element + 1) % list.length;
		// Schuif één plaats over en ga terug naar begin als einde bereikt
	}

	public double average() {
		Vector output = new Vector(0, 0);
		for (Vector element : list)
			output.optelling(element);
		output.scalair_vermenigvuldiging(1 / (double) list.length);
		// output is nu gemiddelde plaatsvector
		output.aftrekking(list[(index_of_next_last_element + list.length - 1) % list.length]);
		// output is nu gemiddelde plaatsvector - huidige plaatsvector
		return output.modulus();
	}

	public void initialize() {
		for (int i = 0; i < list.length; i++)
			list[i] = new Vector(-1, -1);
		// Bal gaat nooit geinitializeerd worden buiten het scherm
	}

	public String toString() {
		return Arrays.toString(list);
	}

}
