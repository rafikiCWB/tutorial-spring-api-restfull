package org.brisa.defasioitau.service;

import org.brisa.defasioitau.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class TransactionService {
    private final Queue<Transaction> transactions = new ConcurrentLinkedQueue<>();

    // adicionar transação
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    // limpar transação
    public void cleanTransaction() {
        transactions.clear();
    }

    // estatística da transação
    public DoubleSummaryStatistics getStatistics() {
        OffsetDateTime now = OffsetDateTime.now();
        return transactions.stream()
//                .filter(t -> t.getDataHora().isAfter(now.minusSeconds(60)))
                .mapToDouble(Transaction::getValor)
                .summaryStatistics();
    }
}
