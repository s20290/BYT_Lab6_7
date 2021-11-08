package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	Account testAccount2;

	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));
		testAccount2 = new Account("Alice", SEK);
		testAccount2.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		testAccount.addTimedPayment("1",0,1,new Money(1000,SEK),SweBank, testAccount2.getId());
		assertTrue("Timed payment does not exists",testAccount.timedPaymentExists("1"));
		testAccount.removeTimedPayment("1");
		assertFalse("Timed payment exists",testAccount.timedPaymentExists("1"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		testAccount.addTimedPayment("1",0,1,new Money(1000,SEK),SweBank, testAccount2.getId());
		assertTrue("Timed payment does not exists",testAccount.timedPaymentExists("1"));
		testAccount.removeTimedPayment("1");
		assertFalse("Timed payment exists",testAccount.timedPaymentExists("1"));
	}

	@Test
	public void testAddWithdraw() {
		Money tmp = new Money(1000,SEK);
		testAccount.deposit(tmp); //tmp is from bank SEK, the rate is 0.15 so after conversion it is 150
								  //testaccount has currently 1500000
		assertEquals((Integer) 1500150,testAccount.getBalance().getAmount());

	}
	
	@Test
	public void testGetBalance() {
		assertEquals((Integer) 1500000,testAccount.getBalance().getAmount());
	}
}
