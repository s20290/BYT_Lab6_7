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
		/*
		initializng objects needed for tests
		 */
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
//		test for adding and removing timed payments
		/*
		add a new timed payment to test account
		check whether timed payment
		remove timed payment
		check to make sure there are no timed payments remaining
		*/
		testAccount.addTimedPayment("1",0,1,new Money(1000,SEK),SweBank, testAccount2.getId());
		assertTrue("Timed payment does not exists",testAccount.timedPaymentExists("1"));
		testAccount.removeTimedPayment("1");
		assertFalse("Timed payment exists",testAccount.timedPaymentExists("1"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
//		test for existence of timed payment
		/*
		add a new timed payment to test account
		check whether timed payment
		remove timed payment
		check to make sure there are no timed payments remaining
		*/
		testAccount.addTimedPayment("1",0,1,new Money(1000,SEK),SweBank, testAccount2.getId());
		assertTrue("Timed payment does not exists",testAccount.timedPaymentExists("1"));
		testAccount.removeTimedPayment("1");
		assertFalse("Timed payment exists",testAccount.timedPaymentExists("1"));
	}

	@Test
	public void testAddWithdraw() {
//		test for add and withdraw money
		/*
		test account should already have 10000000
		add 1000 more money to testaccount
		test account should now have 10001000
		withdraw 1000 from testaccount
		testaccount should now have the same amount as it started (10000000)
		*/
		Money tmp = new Money(1000,SEK);
		testAccount.deposit(tmp);
		assertEquals((Integer) 10001000,testAccount.getBalance().getAmount());
		testAccount.withdraw(tmp);
		assertEquals((Integer)10000000, testAccount.getBalance().getAmount());

	}
	
	@Test
	public void testGetBalance() {
//		test to check balance
		/*
		testaccount currently has 10000000
		check testaccount's balance to verify it indeed has 10000000
		 */
		assertEquals((Integer) 10000000,testAccount.getBalance().getAmount());
	}
}
