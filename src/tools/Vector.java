package tools;

public class Vector {
	private double x, y;

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	//Dit is zware shit

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
		return new Vector(a * r.x, a * r.y);
	}

	public void scalair_vermenigvuldiging(double a) {
		x *= a;
		y *= a;
	}

	public void optelling(Vector other) {
		this.x += other.x;
		this.y += other.y;
	}
	
	public void aftrekking(Vector other) {
		this.x -= other.x;
		this.y -= other.y;
	}

	public double scalair_product(Vector a, Vector b) {
		return a.x * b.x + a.y * b.y;
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public Vector clone() {
		return new Vector(x,y);
	}
}
