package tools;

import java.util.Arrays;

public class MovingAverage {
	private Vector[] list;
	private int index_of_next_last_element = 0;

	public MovingAverage(int size) {
		list = new Vector[size];
		initialize();
	}

	public void add(Vector element) {
		list[index_of_next_last_element] = element;
		index_of_next_last_element = (index_of_next_last_element + 1) % list.length;
	}

	public double average() {
		Vector output = new Vector(0, 0);
		for (Vector element : list)
			output.optelling(element);
		output.scalair_vermenigvuldiging(1 / (double) list.length);
		output.aftrekking(list[index_of_next_last_element]);
		return output.modulus();
	}

	public void initialize() {
		for (int i = 0; i < list.length; i++)
			list[i] = new Vector(0,0); // Vermijden dat het gemiddelde 0 is in het begin
	}

	public String toString() {
		return Arrays.toString(list);
	}

}
