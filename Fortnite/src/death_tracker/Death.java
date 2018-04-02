package death_tracker;

public class Death {
	private death_tracker.InputFrame.distance dis;
	private death_tracker.InputFrame.weapon wea;
	private death_tracker.InputFrame.location loc;
	private death_tracker.InputFrame.rush rus;
	
	public Death(death_tracker.InputFrame.distance distance, death_tracker.InputFrame.weapon weapon, death_tracker.InputFrame.location location, death_tracker.InputFrame.rush rush) {
		dis = distance;
		wea = weapon;
		loc = location;
		rus = rush;
	}
	
	public death_tracker.InputFrame.distance getDistance() {
		return dis;
	}
	
	public death_tracker.InputFrame.weapon getWeapon() {
		return wea;
	}
	
	public death_tracker.InputFrame.location getLocation() {
		return loc;
	}
	
	public death_tracker.InputFrame.rush getRush() {
		return rus;
	}
}
