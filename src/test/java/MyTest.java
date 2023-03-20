import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MyTest {

	@Test
	void test() {
		System.out.println("***TESTING***");
	}

	@Test
	public void GameMenuTest() {
		GameMenu gm = new GameMenu();
		assertEquals("Display rules", gm.rulesItem.getText());
		assertEquals("Display odds", gm.oddsItem.getText());
		assertEquals("Exit Game", gm.exitItem.getText());
	}

	@Test
	public void NumberBaseTest() {
		NumberBase empty = new NumberBase();
		List<Integer> expected = new ArrayList<>();
		assertEquals(expected, empty.getList());
		assertEquals(0, empty.getSize());

		List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
		NumberBase load = new NumberBase(nums);
		assertEquals(nums, load.getList());
		assertEquals(5, load.getSize());

		assertEquals("1 2 3 4 5 ", load.toString());

		load.addNumber(5);
		nums.add(5);
		assertEquals(nums, load.getList());
		assertNotEquals(5, load.getSize()); // addNumber don't add duplicate

		load.addNumber(6);
		nums.add(6);
		assertEquals(nums, load.getList());
		assertNotEquals(6, load.getSize()); 

		load.removeNumber(6);
		nums.remove(6);
		assertEquals(nums, load.getList());
		assertNotEquals(5, load.getSize());

		load.removeNumber(8);
		nums.remove(3);
		assertEquals(nums, load.getList());
		assertNotEquals(6, load.getSize());


		NumberBase random = new NumberBase();
		random.randomList(5);
		assertEquals(5, random.getSize());

		load.cleanList();
		assertEquals(expected, load.getList());
		assertEquals(0, load.getSize());
	}

	@Test
	public void PlayTest() {
		Play p = new Play();
		p.setSlots(4);
		p.setRounds(3);
		assertEquals(4, p.getSlots());
		assertEquals(3, p.getRounds());
		p.addNumber(5);
		p.addNumber(7);
		assertEquals(2, p.getSize());
		List<Integer> pNums = new ArrayList<>(Arrays.asList(5, 7));
		assertEquals(pNums, p.getList());
		String arrayStr = "5 7 ";
		assertEquals(arrayStr, p.getStrList());
		assertEquals(false, p.checkOverSize());
		p.removeNumber(7);
		pNums.remove(1);
		assertEquals(pNums, p.getList());
		assertEquals(1, p.getSize());
		p.randomList();
		assertEquals(4, p.getSize());
		p.cleanList();
		assertEquals(0, p.getSize());
	}

}
