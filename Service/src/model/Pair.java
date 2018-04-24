package model;

public class Pair {
	private int n;
	private int level;
	
	@Override
	public boolean equals(Object obj) {
		Pair temp = (Pair)obj;
		return n == temp.n && level == temp.level;
	}
	public Pair(int n, int level) {
		super();
		this.n = n;
		this.level = level;
	}
	public Pair() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "Pair [n=" + n + ", level=" + level + "]";
	}
	@Override
	public int hashCode() {
		return new Integer(n).hashCode()+new Integer(level).hashCode();
	}
	
	
	
	
}
