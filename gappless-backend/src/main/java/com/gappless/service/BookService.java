package com.gappless.service;

import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gappless.entity.Book;
import com.gappless.protobuf.BackendProto;
import com.gappless.protobuf.BackendProto.Empty;
import com.gappless.protobuf.BookServiceGrpc.BookServiceImplBase;
import com.gappless.repository.BookRepository;
import com.gappless.util.ValidationUtil;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

@GRpcService
@Service
public class BookService extends BookServiceImplBase {
	@Autowired
	private BookRepository bookRepository;
	
	private BackendProto.Book.Builder getBookBuilder(Book book) {
		return BackendProto.Book.newBuilder().setId(book.getId()).setTitle(book.getTitle()).setNrOfPage(book.getNrOfPages());
	}
	
	@Override
	public void list(BackendProto.Empty request, StreamObserver<BackendProto.Book> responseObserver) {
		this.bookRepository.findAll().stream().map(
				e -> getBookBuilder(e)
		).forEach(b -> responseObserver.onNext(b.build()));
		responseObserver.onCompleted();
	}

	@Override
	public void create(BackendProto.Book request, StreamObserver<BackendProto.Book> responseObserver) {
		long id = request.getId();
		if (this.bookRepository.exists(id)) {
			throw new StatusRuntimeException(Status.ALREADY_EXISTS.withDescription("Book entity already exists with id: "+id));
		}
		Book book = new Book(id, request.getTitle(), request.getNrOfPage());
		ValidationUtil.validateInput(book);
		book = this.bookRepository.saveAndFlush(book);
		responseObserver.onNext(getBookBuilder(book).build());
		responseObserver.onCompleted();
	}

	@Override
	public void read(BackendProto.Id request, StreamObserver<BackendProto.Book> responseObserver) {
		long id = request.getId();
		Book book = this.bookRepository.findOne(id);
		if (book == null) {
			throw new StatusRuntimeException(Status.NOT_FOUND.withDescription("Book entity not found with id: "+id));
		}
		responseObserver.onNext(getBookBuilder(book).build());
		responseObserver.onCompleted();
	}

	@Override
	public void update(BackendProto.Book request, StreamObserver<BackendProto.Book> responseObserver) {
		long id = request.getId();
		Book book = this.bookRepository.findOne(id);
		if (book == null) {
			throw new StatusRuntimeException(Status.NOT_FOUND.withDescription("Book entity not found with id: "+id));
		}
		book.setTitle(request.getTitle());
		book.setNrOfPages(request.getNrOfPage());
		ValidationUtil.validateInput(book);
		book = this.bookRepository.saveAndFlush(book);
		responseObserver.onNext(getBookBuilder(book).build());
		responseObserver.onCompleted();
	}

	@Override
	public void delete(BackendProto.Id request, StreamObserver<BackendProto.Empty> responseObserver) {
		long id = request.getId();
		if (!this.bookRepository.exists(id)) {
			throw new StatusRuntimeException(Status.NOT_FOUND.withDescription("Book entity not found with id: "+id));
		}
		this.bookRepository.delete(id);
		responseObserver.onNext(Empty.newBuilder().build());
		responseObserver.onCompleted();
	}


}
