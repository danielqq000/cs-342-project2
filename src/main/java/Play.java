
import java.util.List;


public class Play{


	private NumberBase userList = new NumberBase();
	private int slots = 0;
	private int rounds = 0;

	public void setSlots(int slots) { this.slots = slots; }

	public void setRounds(int rounds) { this.rounds = rounds; }

	public int getSlots() {	return slots; }

	public int getRounds() { return rounds; }

	public boolean checkOverSize() { return userList.getSize() > slots;	}

	public void addNumber(int n) { userList.addNumber(n); }

	public void removeNumber(int n) { userList.removeNumber(n); }

	public List<Integer> getList() { return userList.getList(); }

	public int getSize() { return userList.getSize(); }

	public String getStrList() { return userList.toString(); }

	public void randomList() { userList.randomList(slots); }

	public void cleanList() { userList.cleanList(); }
}
