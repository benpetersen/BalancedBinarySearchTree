import static org.junit.Assert.*;

import org.junit.Test;

public class Tests {

	@Test
	public void TestNullConvertToInt() {
		int num = Helpers.ConvertToInt("Null");
		assertEquals(0, num);
	}
	
	@Test
	public void TestInvalidCharsConvertToInt() {
		int num = Helpers.ConvertToInt("invalidvalue");
		assertEquals(0, num);
	}
	
	@Test
	public void TestMaxValueConvertToInt() {
		int num = Helpers.ConvertToInt("2147483647");
		assertEquals(2147483647, num);
	}
	
	@Test
	public void TestMinValueConvertToInt() {
		int num = Helpers.ConvertToInt("-2147483648");
		assertEquals(-2147483648, num);
	}
}
