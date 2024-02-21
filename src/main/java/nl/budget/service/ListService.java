package nl.budget.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class ListService<T> {

	protected JpaRepository<T, Long> repository;
	protected ObservableList<T> observableList;
	protected Comparator<T> comparator;

	public ObservableList<T> getObservableList() {
		observableList.sort(comparator);
		return FXCollections.unmodifiableObservableList(observableList);
	}

	public List<T> getList() {
		List<T> list = repository.findAll();
		list.sort(comparator);
		return Collections.unmodifiableList(list);
	}

	public void addListener(ListChangeListener<T> listener) {
		observableList.addListener(listener);
	}

	public void removeChangeListener(ListChangeListener<T> listener) {
		observableList.removeListener(listener);
	}

	public List<T> saveAll(Iterable<T> collection) {
		List<T> addedList = repository.saveAll(collection);
		observableList.addAll(addedList);
		FXCollections.sort(observableList, comparator);
		return addedList;
	}

	public T save(T o) {
		T createdObject = repository.save(o);
		observableList.add(createdObject);
		FXCollections.sort(observableList, comparator);
		return createdObject;
	}

	public T update(T o) {
		T update = repository.save(o);
		observableList.set(observableList.lastIndexOf(o), update);
		FXCollections.sort(observableList, comparator);
		return update;
	}

	public void delete(T o) {
		repository.delete(o);
		observableList.remove(o);
	}
}
