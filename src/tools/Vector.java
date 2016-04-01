package tools;

public class Vector {
	public double x, y;

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double modulus() {
		return Math.sqrt(x * x + y * y);
	}

	public static Vector optelling(Vector a, Vector b) {
		return new Vector(a.x + b.x, a.y + b.y);
	}
	
	public static Vector aftrekking(Vector a, Vector b) {
		return new Vector(a.x - b.x, a.y - b.y);
	}
	
	public static Vector scalair_vermenigvuldiging(double a, Vector r) {
		return new Vector(a*r.x,a*r.y);
	}
}