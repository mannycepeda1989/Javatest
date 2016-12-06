package com.baeldung.mdc.log4j;

import org.apache.log4j.MDC;
import org.apache.log4j.NDC;

import com.baeldung.mdc.Transfer;

public class Log4JRunnable implements Runnable {

    private Transfer tx;
    private static Log4JTransferService log4jBusinessService = new Log4JTransferService();

    public Log4JRunnable(Transfer tx) {
        this.tx = tx;
    }

    public void run() {

        MDC.put("transaction.id", tx.getTransactionId());
        MDC.put("transaction.owner", tx.getSender());

        // Use NDC to add accountId
        NDC.push("accountId " + tx.getAccountId());

        boolean transferSuccess = log4jBusinessService.transfer(tx.getAmount(), tx.getAccountId());

        // Pop accountId
        NDC.pop();

        if (transferSuccess && tx.isInvestmentFund()) {
            // Use NDC to add investmentFundId
            NDC.push("investmentFundId " + tx.getInvestmentFundId());

            log4jBusinessService.transfer(tx.getAmount(), tx.getInvestmentFundId());

            // Pop investmentFundId
            NDC.pop();
        }

        MDC.clear();

        NDC.remove();

    }
}