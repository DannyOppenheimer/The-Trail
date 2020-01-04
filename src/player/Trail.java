package player;

public class Trail {
	
	private int days;
	private int distance;
	
	public Trail() {
		days = 0;
		distance = 175;
	}
	
	public int getDays() {
		
		return days;
	}
	
	public void modifyDays(int da) {
		
		this.days += da;
	}
	
	public int getDistance() {
		
		return distance;
	}
	
	public void modifyDistance(int di) {
		
		this.distance += di;
	}
	
}
