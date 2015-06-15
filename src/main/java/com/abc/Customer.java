package com.abc;

import java.util.List;
import java.util.ArrayList;

import java.text.NumberFormat;
import java.util.Locale;

import static java.lang.Math.abs;

public class Customer {
    private String customerName;
    private List<Account> accounts;

    public Customer(final String customerName) {
        this.customerName = customerName;
        this.accounts = new ArrayList<Account>();
    }

    public String getCustomerName(){
        return customerName;
    }

    public Customer openAccount(final Account account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
        StringBuilder statement = new StringBuilder("Statement for ").append(customerName);
																	 .append("\n");
        double total = 0.0;
        for (Account a : accounts) {
            statement.append("\n")
					 .append(statementForAccount(a))
					 .append("\n");
            total += a.sumTransactions();
        }
        statement.append("\nTotal In All Accounts ")
				 .append(numberFormatter(total));
		statement.append("\nNumber Of Accounts for ")
				 .append(customerName)
				 .append(": ")
				 .append( getNumberOfAccounts());
		statement.append("\nTotal Interest Earned by ")
				 .append(customerName)
				 .append(": ")
				 .append(numberFormatter(totalInterestEarned()));
        return statement.toString();
    }
	
	private String getAccountName(final Account a) {
        String accountName = "";

        switch(a.getAccountType()){
            case Constant.CHECKING:
                accountName = "Checking Account\n";
                break;
            case Constant.SAVINGS:
                accountName = "Savings Account\n";
                break;
            case Constant.MAXI_SAVINGS: 
                accountName = "Maxi Savings Account\n";
                break;
        }
		return accountName;
	}

    private String statementForAccount(final Account a) {
		StringBuilder accountStatement = new StringBuilder(getAccountName(a));
		
        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.transactions) {
			accountStatement.append("  ")
							.append((t.amount < 0 ? "withdrawal" : "deposit"))
							.append(" ")
							.append(numberFormatter(t.amount))
							.append("\n");
            total += t.amount;
        }
		accountStatement.append("Total ")
						.append(numberFormatter(total));
        return accountStatement.toString();
    }
	
	// Made this a class method because it only operates on the argument provided to it
	public static String numberFormatter(final double number) {
		String formatterNumber = "";
		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		formatterNumber = defaultFormat.format(number);
		return formatterNumber;
	}
}
