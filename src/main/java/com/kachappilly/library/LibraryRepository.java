package com.kachappilly.library;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Book, Long> {
	
	List<Book> findByReader(String reader);
	
	List<Book> findByIsbn(String isbn);
	
	List<Book> findAll();

}