package nl.budget.service;


import org.springframework.stereotype.Service;

import javafx.collections.FXCollections;
import nl.budget.model.Transaction;
import nl.budget.repository.TransactionRepository;

@Service
public class TransactionService extends ListService<Transaction> {
	
	public TransactionService(TransactionRepository transactionRepository) {
		repository = transactionRepository;
		observableList = FXCollections.observableList(repository.findAll());
	}
	
}
