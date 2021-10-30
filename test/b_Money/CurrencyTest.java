package b_Money;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		Assert.assertEquals("SEK", SEK.getName());
	}
	
	@Test
	public void testGetRate() {
		Assert.assertEquals((Double)0.15,SEK.getRate());
	}
	
	@Test
	public void testSetRate() {
		SEK.setRate(0.99);
		Assert.assertEquals((Double) 0.99,SEK.getRate());
	}
	
	@Test
	public void testGlobalValue() {
		Assert.assertEquals((Integer) 150,SEK.universalValue(1000));
	}
	
	@Test
	public void testValueInThisCurrency() {
		Assert.assertEquals((Integer) 225,SEK.valueInThisCurrency(1000,EUR));
	}

}
