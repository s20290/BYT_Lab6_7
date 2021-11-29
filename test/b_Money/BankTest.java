package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		/*
		initializng objects needed for tests
		 */
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
		//test for getter getName()
		assertEquals("DKK",DKK.getName());
	}

	@Test
	public void testGetCurrency() {
		//test for getter getCurrency()
		assertEquals(DKK,DanskeBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		//test for openAccount()
		/*
		if account "Ulrika" is opened, it should have balance 0 at creation
		 */

		assertEquals((Integer)0,SweBank.getBalance("Ulrika"));
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		//test for deposit
		/*
		initially, Ulrika should have 0 in the account
		after depositing 10000 DKK, Ulrika should have 2000 universal currency
		 */
		assertEquals((Integer)0,SweBank.getBalance("Ulrika"));
		SweBank.deposit("Ulrika",new Money(10000,DKK));

		assertTrue(SweBank.getBalance("Ulrika") == 2000);
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
//		test for withdraw
		/*
		Ulrika starts with 0 in the account
		after depositing 10000 DKK and withdrawing 10000 DKK it should remain 0
		 */
		assertEquals((Integer)0,SweBank.getBalance("Ulrika"));
		SweBank.deposit("Ulrika",new Money(10000,DKK));

		SweBank.withdraw("Ulrika",new Money(10000,DKK));
		assertEquals((Integer)0,SweBank.getBalance("Ulrika"));

	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		//test for getter getBalance()
		/*
		Ulrika starts with 0 in account
		after depositing 10000 DKK, account should have 2000 universal currency
		 */
		assertEquals((Integer)0 , SweBank.getBalance("Ulrika"));
		SweBank.deposit("Ulrika",new Money(10000,DKK));
		assertEquals((Integer)2000, SweBank.getBalance("Ulrika"));
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		//test for transfer
		/*
		Ulrika starts with 0 in the account
		after 10000 DKK deposit Ulrika has 2000 universal currency

		Ulrika transfers 10000 DKK to Bob who has 0 in his bank
		Ulrika should now have 0 in the account and bob should have 2000 universal currency

		Bob from SweBank, who has currently 2000 universal currency transfers all to
		another Bob from Nordea
		Bob from SweBank should now have 0
		Bob from Nordea should now have 2000 universal currency
		 */
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
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		//test for adding timed payment through bank
		/*
		Ulrika has no timed payment initially
		after adding a timed payment, timedPaymentExists() should return true
		 */
		SweBank.addTimedPayment("Ulrika","1",300,300,new Money(10000,DKK),Nordea,"Bob");
		Account tmp = SweBank.getAccount("Ulrika");
		assertTrue(tmp.timedPaymentExists("1"));
	}
}
