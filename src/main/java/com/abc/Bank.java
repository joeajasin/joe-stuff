package com.abc;

import java.util.List;
import java.util.ArrayList;

public class Bank {
    private List<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
    }

    public void addCustomer(final Customer customer) {
        customers.add(customer);
    }

    public String customerSummary() {
		StringBuilder summary = new StringBuilder("Customer Summary");
        for (Customer c : customers)
            summary.append("\n - ")
		           .append(c.getCustomerName())
				   .append(" (")
				   .append(format(c.getNumberOfAccounts(), "account"))
				   .append(")");
        return summary.toString();
    }

    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    private String format(final int number, final String word) {
		StringBuilder correctPlural = new StringBuilder();
		correctPlural.append(number)
		             .append(" ")
					 .append(number == 1 ? word : word + "s");
        return correctPlural.toString();
    }

    public double totalInterestPaid() {
        double total = 0;
        for(Customer c: customers)
            total += c.totalInterestEarned();
        return total;
    }
    
    /*
    public String getFirstCustomer() {
        try {
            customers = null;
            return customers.get(0).getCustomerName();
        } catch (Exception e){
            e.printStackTrace();
            return "Error";
        }
    }
    */
}
