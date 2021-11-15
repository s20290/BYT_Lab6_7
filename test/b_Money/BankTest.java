package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("DKK",DKK.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(DKK,DanskeBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		assertEquals((Integer)0,SweBank.getBalance("Ulrika"));
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		assertEquals((Integer)0,SweBank.getBalance("Ulrika"));
		SweBank.deposit("Ulrika",new Money(10000,DKK));

		assertTrue(SweBank.getBalance("Ulrika") > 0);
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		assertEquals((Integer)0,SweBank.getBalance("Ulrika"));
		SweBank.deposit("Ulrika",new Money(10000,DKK));

		SweBank.withdraw("Ulrika",new Money(10000,DKK));
		assertEquals((Integer)0,SweBank.getBalance("Ulrika"));

	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals((Integer)0 , SweBank.getBalance("Ulrika"));
		SweBank.deposit("Ulrika",new Money(10000,DKK));
		assertEquals((Integer)2000, SweBank.getBalance("Ulrika"));
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		assertEquals((Integer)0 , SweBank.getBalance("Ulrika"));
		SweBank.deposit("Ulrika",new Money(10000,DKK));
		assertEquals((Integer)2000, SweBank.getBalance("Ulrika"));
		SweBank.transfer("Ulrika","Bob",new Money(10000,DKK));
		assertEquals((Integer)0 , SweBank.getBalance("Ulrika"));
		assertEquals((Integer)2000, SweBank.getBalance("Bob"));
		SweBank.transfer("Bob",Nordea,"Bob",new Money(10000,DKK));
		assertEquals((Integer)0 , SweBank.getBalance("Bob"));
		assertEquals((Integer)2000 , Nordea.getBalance("Bob"));
	}
	
	@Test//not done yeet
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.addTimedPayment("Ulrika","1",300,300,new Money(10000,DKK),Nordea,"Bob");
	}
}
