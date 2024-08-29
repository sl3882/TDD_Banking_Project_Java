package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CdTest {

	Cd cd;

	@BeforeEach
	public void setUp() {

		cd = new Cd(.05, 1000);
	}

	@Test
	public void open_cd_with_1000_balance() {
		double actual = cd.getBalance();
		assertEquals(1000, actual);
	}

}
