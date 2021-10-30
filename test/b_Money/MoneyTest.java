package b_Money;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}
	public void assertEquals(int a, Integer b){
		Assert.assertEquals((Integer)a, b);
	}
	@Test
	public void testGetAmount() {
		assertEquals(10000,this.SEK100.getAmount());
		assertEquals(1000,this.EUR10.getAmount());
		assertEquals(20000,this.SEK200.getAmount());
		assertEquals(2000,this.EUR20.getAmount());
		assertEquals(0,this.SEK0.getAmount());
		assertEquals(-10000,this.SEKn100.getAmount());
	}

	@Test
	public void testGetCurrency() {
		Assert.assertEquals(SEK,this.SEK100.getCurrency());
		Assert.assertEquals(EUR,this.EUR10.getCurrency());
		Assert.assertEquals(SEK,this.SEK200.getCurrency());
		Assert.assertEquals(EUR,this.EUR20.getCurrency());
		Assert.assertEquals(SEK,this.SEK0.getCurrency());
		Assert.assertEquals(SEK,this.SEKn100.getCurrency());
	}

	@Test
	public void testToString() {
		Assert.assertEquals("(10000) (SEK)",this.SEK100.toString());
		Assert.assertEquals("(1000) (EUR)",this.EUR10.toString());
		Assert.assertEquals("(20000) (SEK)",this.SEK200.toString());
		Assert.assertEquals("(2000) (EUR)",this.EUR20.toString());
		Assert.assertEquals("(0) (SEK)",this.SEK0.toString());
		Assert.assertEquals("(-10000) (SEK)",this.SEKn100.toString());
	}

	@Test
	public void testGlobalValue() {
		assertEquals(1500,this.SEK100.universalValue());
		assertEquals(1500,this.EUR10.universalValue());
		assertEquals(3000,this.SEK200.universalValue());
		assertEquals(3000,this.EUR20.universalValue());
		assertEquals(0,this.SEK0.universalValue());
		assertEquals(-1500,this.SEKn100.universalValue());
	}

	@Test
	public void testEqualsMoney() {
		Assert.assertTrue(this.SEK100 instanceof Money);
		Assert.assertTrue(this.EUR10 instanceof Money);
		Assert.assertTrue(this.SEK200 instanceof Money);
		Assert.assertTrue(this.EUR20 instanceof Money);
		Assert.assertTrue(this.SEK0 instanceof Money);
		Assert.assertTrue(this.SEKn100 instanceof Money);
	}

	@Test
	public void testAdd() {
		assertEquals(0,SEK100.add(SEKn100).getAmount());
	}

	@Test
	public void testSub() {
		assertEquals(3000,SEK100.sub(SEKn100).getAmount());
	}

	@Test
	public void testIsZero() {
		assertTrue(SEK0.isZero());
	}

	@Test
	public void testNegate() {
		assertEquals(-10000,SEK100.negate().getAmount());
	}

	@Test
	public void testCompareTo() {
		assertEquals(1,SEK100.compareTo(SEKn100));
	}
}
