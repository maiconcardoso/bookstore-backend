package com.example.bookstore.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.bookstore.domain.Book;
import com.example.bookstore.dtos.BookDTO;
import com.example.bookstore.service.BookService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/books")
public class BookResource {
	
	@Autowired
	BookService bookService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Book> findById(@PathVariable Long id) {
		Book obj = bookService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "findAll")
	public ResponseEntity<List<BookDTO>> findAll() {
		List<Book> bookList = bookService.findAll();
		List<BookDTO> bookListDTO = bookList.stream().map(obj -> new BookDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(bookListDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<BookDTO>> findAllByCategory(@RequestParam(value = "category", defaultValue = "0") Long id_cat) {
		List<Book> bookList = bookService.findAllByCategory(id_cat);
		List<BookDTO> bookListDTO = bookList.stream().map(obj -> new BookDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(bookListDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Book> update(@Valid @PathVariable Long id, @RequestBody Book obj) {
		Book newObj = bookService.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Book> updatePatch(@Valid @PathVariable Long id, @RequestBody Book obj) {
		Book newObj = bookService.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}
	
	@PostMapping
	public ResponseEntity<Book> create(@Valid @RequestParam(value = "category", defaultValue = "0") Long id_cat,
			@RequestBody Book obj) {
		Book newObj = bookService.create(id_cat, obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/books/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		bookService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
