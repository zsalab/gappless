package com.gappless.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.gappless.BackendApp;
import com.gappless.protobuf.BackendProto.Book;
import com.gappless.protobuf.BookServiceGrpc;
import com.gappless.protobuf.BookServiceGrpc.BookServiceBlockingStub;
import com.gappless.repository.BookRepository;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.testing.GrpcServerRule;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {BackendApp.class, BookService.class, BookServiceExceptionHandler.class}, webEnvironment=WebEnvironment.NONE)
@DataJpaTest
public class BookServiceTest {

	@Rule
	public final GrpcServerRule grpcServerRule = new GrpcServerRule().directExecutor();
	
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookService bookService;
	
	@Test
	public void createTest() {
		this.grpcServerRule.getServiceRegistry().addService(this.bookService);
		BookServiceBlockingStub stub = BookServiceGrpc.newBlockingStub(this.grpcServerRule.getChannel());
		this.bookRepository.deleteAll();
		Book requestBook = Book.newBuilder().setId(1).setTitle("first").setNrOfPage(10).build();
		Book responseBook = stub.create(requestBook);
		assertEquals(responseBook.getId(), requestBook.getId());
		assertEquals(responseBook.getTitle(), requestBook.getTitle());
		assertEquals(responseBook.getNrOfPage(), requestBook.getNrOfPage());
		com.gappless.entity.Book entityBook = this.bookRepository.findOne(requestBook.getId());
		assertNotNull(entityBook);
		assertEquals(entityBook.getId().longValue(), requestBook.getId());
		assertEquals(entityBook.getTitle(), requestBook.getTitle());
		assertEquals(entityBook.getNrOfPages().intValue(), requestBook.getNrOfPage());
	}

	@Test
	public void createTest_DuplicatedId() {
		this.grpcServerRule.getServiceRegistry().addService(this.bookService);
		BookServiceBlockingStub stub = BookServiceGrpc.newBlockingStub(this.grpcServerRule.getChannel());
		this.bookRepository.deleteAll();
		this.bookRepository.saveAndFlush(new com.gappless.entity.Book(1l, "first", 10));
		Book requestBook = Book.newBuilder().setId(1).setTitle("first").setNrOfPage(10).build();
		try {
			stub.create(requestBook);
			fail("no unique id check");
		} catch (StatusRuntimeException e) {
			assertEquals(e.getStatus().getCode(), Status.ALREADY_EXISTS.getCode());
			assertEquals(this.bookRepository.count(), 1);
		}
	}

	@Test
	public void createTest_InputValidation() {
		this.grpcServerRule.getServiceRegistry().addService(this.bookService);
		BookServiceBlockingStub stub = BookServiceGrpc.newBlockingStub(this.grpcServerRule.getChannel());
		this.bookRepository.deleteAll();
		Book requestBook = Book.newBuilder().setId(0).setTitle("").setNrOfPage(0).build();
		try {
			stub.create(requestBook);
			fail("no validation");
		} catch (StatusRuntimeException e) {
			assertEquals(e.getStatus().getCode(), Status.INVALID_ARGUMENT.getCode());
			assertTrue(e.getStatus().getDescription().contains("id must be greater than or equal to 1;"));
			assertTrue(e.getStatus().getDescription().contains("title size must be between 1 and 64;"));
			assertTrue(e.getStatus().getDescription().contains("nrOfPages must be greater than or equal to 1;"));
			assertEquals(this.bookRepository.count(), 0);
		}
	}
	
	// TODO: more tests
}
