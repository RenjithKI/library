package com.kachappilly.library;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/readingList")
public class LibraryController {
	
	private static Logger logger = Logger
			.getLogger(LibraryController.class);

  private static final String reader = "craig", reader2 = "renjith";
  
	private LibraryRepository libraryRepository;

	@Autowired
	public LibraryController(LibraryRepository libraryRepository) {
		this.libraryRepository = libraryRepository;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String readersBooks(Model model) {
		
		List<Book> readingList = libraryRepository.findByReader(reader);
		if (readingList != null) {
			model.addAttribute("books", readingList);
		}
		return "readingList";
	}
	@RequestMapping(method=RequestMethod.POST)
	public String addToReadingList(Book book) {
		book.setReader(reader);
		libraryRepository.save(book);
		return "redirect:/readingList";
	}
	
	
	@RequestMapping(value="/new/readingListTwo", method=RequestMethod.GET)
	public String booksByIsbn(Model model) {
		/*List<Long> ll = new ArrayList<>();
		ll.add(3L);
		ll.add(2L);*/
		List<Book> allbookslist = libraryRepository.findByIsbn("3");
		logger.info("inside allBook method >>>>"+ allbookslist);
		if (allbookslist != null) {
			logger.info("inside allBook method size>>>>"+ allbookslist.size() );
			model.addAttribute("allbooks", allbookslist);
		}
		return "readingList2";
	}
	@RequestMapping(value="/new/readingListTwo", method=RequestMethod.POST)
	public String booksByIsbnPost(Book book) {		
		logger.info("inside post 2>>>>");
		book.setReader(reader2);
		logger.info("inside post 2>>>>");
		libraryRepository.save(book);
		logger.info("inside post 2>>>>");
		return "redirect:/new/readingListTwo";
	}
	
	
	
}
