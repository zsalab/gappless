package com.gappless.protobuf;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.6.1)",
    comments = "Source: backend.proto")
public final class BookServiceGrpc {

  private BookServiceGrpc() {}

  public static final String SERVICE_NAME = "BookService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.gappless.protobuf.BackendProto.Empty,
      com.gappless.protobuf.BackendProto.Book> METHOD_LIST =
      io.grpc.MethodDescriptor.<com.gappless.protobuf.BackendProto.Empty, com.gappless.protobuf.BackendProto.Book>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "BookService", "list"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.gappless.protobuf.BackendProto.Empty.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.gappless.protobuf.BackendProto.Book.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.gappless.protobuf.BackendProto.Book,
      com.gappless.protobuf.BackendProto.Book> METHOD_CREATE =
      io.grpc.MethodDescriptor.<com.gappless.protobuf.BackendProto.Book, com.gappless.protobuf.BackendProto.Book>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "BookService", "create"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.gappless.protobuf.BackendProto.Book.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.gappless.protobuf.BackendProto.Book.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.gappless.protobuf.BackendProto.Id,
      com.gappless.protobuf.BackendProto.Book> METHOD_READ =
      io.grpc.MethodDescriptor.<com.gappless.protobuf.BackendProto.Id, com.gappless.protobuf.BackendProto.Book>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "BookService", "read"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.gappless.protobuf.BackendProto.Id.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.gappless.protobuf.BackendProto.Book.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.gappless.protobuf.BackendProto.Book,
      com.gappless.protobuf.BackendProto.Book> METHOD_UPDATE =
      io.grpc.MethodDescriptor.<com.gappless.protobuf.BackendProto.Book, com.gappless.protobuf.BackendProto.Book>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "BookService", "update"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.gappless.protobuf.BackendProto.Book.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.gappless.protobuf.BackendProto.Book.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.gappless.protobuf.BackendProto.Id,
      com.gappless.protobuf.BackendProto.Empty> METHOD_DELETE =
      io.grpc.MethodDescriptor.<com.gappless.protobuf.BackendProto.Id, com.gappless.protobuf.BackendProto.Empty>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "BookService", "delete"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.gappless.protobuf.BackendProto.Id.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.gappless.protobuf.BackendProto.Empty.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BookServiceStub newStub(io.grpc.Channel channel) {
    return new BookServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BookServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new BookServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BookServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new BookServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class BookServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void list(com.gappless.protobuf.BackendProto.Empty request,
        io.grpc.stub.StreamObserver<com.gappless.protobuf.BackendProto.Book> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_LIST, responseObserver);
    }

    /**
     */
    public void create(com.gappless.protobuf.BackendProto.Book request,
        io.grpc.stub.StreamObserver<com.gappless.protobuf.BackendProto.Book> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CREATE, responseObserver);
    }

    /**
     */
    public void read(com.gappless.protobuf.BackendProto.Id request,
        io.grpc.stub.StreamObserver<com.gappless.protobuf.BackendProto.Book> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_READ, responseObserver);
    }

    /**
     */
    public void update(com.gappless.protobuf.BackendProto.Book request,
        io.grpc.stub.StreamObserver<com.gappless.protobuf.BackendProto.Book> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPDATE, responseObserver);
    }

    /**
     */
    public void delete(com.gappless.protobuf.BackendProto.Id request,
        io.grpc.stub.StreamObserver<com.gappless.protobuf.BackendProto.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DELETE, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_LIST,
            asyncServerStreamingCall(
              new MethodHandlers<
                com.gappless.protobuf.BackendProto.Empty,
                com.gappless.protobuf.BackendProto.Book>(
                  this, METHODID_LIST)))
          .addMethod(
            METHOD_CREATE,
            asyncUnaryCall(
              new MethodHandlers<
                com.gappless.protobuf.BackendProto.Book,
                com.gappless.protobuf.BackendProto.Book>(
                  this, METHODID_CREATE)))
          .addMethod(
            METHOD_READ,
            asyncUnaryCall(
              new MethodHandlers<
                com.gappless.protobuf.BackendProto.Id,
                com.gappless.protobuf.BackendProto.Book>(
                  this, METHODID_READ)))
          .addMethod(
            METHOD_UPDATE,
            asyncUnaryCall(
              new MethodHandlers<
                com.gappless.protobuf.BackendProto.Book,
                com.gappless.protobuf.BackendProto.Book>(
                  this, METHODID_UPDATE)))
          .addMethod(
            METHOD_DELETE,
            asyncUnaryCall(
              new MethodHandlers<
                com.gappless.protobuf.BackendProto.Id,
                com.gappless.protobuf.BackendProto.Empty>(
                  this, METHODID_DELETE)))
          .build();
    }
  }

  /**
   */
  public static final class BookServiceStub extends io.grpc.stub.AbstractStub<BookServiceStub> {
    private BookServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BookServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BookServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BookServiceStub(channel, callOptions);
    }

    /**
     */
    public void list(com.gappless.protobuf.BackendProto.Empty request,
        io.grpc.stub.StreamObserver<com.gappless.protobuf.BackendProto.Book> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_LIST, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void create(com.gappless.protobuf.BackendProto.Book request,
        io.grpc.stub.StreamObserver<com.gappless.protobuf.BackendProto.Book> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CREATE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void read(com.gappless.protobuf.BackendProto.Id request,
        io.grpc.stub.StreamObserver<com.gappless.protobuf.BackendProto.Book> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_READ, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void update(com.gappless.protobuf.BackendProto.Book request,
        io.grpc.stub.StreamObserver<com.gappless.protobuf.BackendProto.Book> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delete(com.gappless.protobuf.BackendProto.Id request,
        io.grpc.stub.StreamObserver<com.gappless.protobuf.BackendProto.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class BookServiceBlockingStub extends io.grpc.stub.AbstractStub<BookServiceBlockingStub> {
    private BookServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BookServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BookServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BookServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<com.gappless.protobuf.BackendProto.Book> list(
        com.gappless.protobuf.BackendProto.Empty request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_LIST, getCallOptions(), request);
    }

    /**
     */
    public com.gappless.protobuf.BackendProto.Book create(com.gappless.protobuf.BackendProto.Book request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CREATE, getCallOptions(), request);
    }

    /**
     */
    public com.gappless.protobuf.BackendProto.Book read(com.gappless.protobuf.BackendProto.Id request) {
      return blockingUnaryCall(
          getChannel(), METHOD_READ, getCallOptions(), request);
    }

    /**
     */
    public com.gappless.protobuf.BackendProto.Book update(com.gappless.protobuf.BackendProto.Book request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UPDATE, getCallOptions(), request);
    }

    /**
     */
    public com.gappless.protobuf.BackendProto.Empty delete(com.gappless.protobuf.BackendProto.Id request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DELETE, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class BookServiceFutureStub extends io.grpc.stub.AbstractStub<BookServiceFutureStub> {
    private BookServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BookServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BookServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BookServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gappless.protobuf.BackendProto.Book> create(
        com.gappless.protobuf.BackendProto.Book request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CREATE, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gappless.protobuf.BackendProto.Book> read(
        com.gappless.protobuf.BackendProto.Id request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_READ, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gappless.protobuf.BackendProto.Book> update(
        com.gappless.protobuf.BackendProto.Book request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gappless.protobuf.BackendProto.Empty> delete(
        com.gappless.protobuf.BackendProto.Id request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE, getCallOptions()), request);
    }
  }

  private static final int METHODID_LIST = 0;
  private static final int METHODID_CREATE = 1;
  private static final int METHODID_READ = 2;
  private static final int METHODID_UPDATE = 3;
  private static final int METHODID_DELETE = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final BookServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(BookServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LIST:
          serviceImpl.list((com.gappless.protobuf.BackendProto.Empty) request,
              (io.grpc.stub.StreamObserver<com.gappless.protobuf.BackendProto.Book>) responseObserver);
          break;
        case METHODID_CREATE:
          serviceImpl.create((com.gappless.protobuf.BackendProto.Book) request,
              (io.grpc.stub.StreamObserver<com.gappless.protobuf.BackendProto.Book>) responseObserver);
          break;
        case METHODID_READ:
          serviceImpl.read((com.gappless.protobuf.BackendProto.Id) request,
              (io.grpc.stub.StreamObserver<com.gappless.protobuf.BackendProto.Book>) responseObserver);
          break;
        case METHODID_UPDATE:
          serviceImpl.update((com.gappless.protobuf.BackendProto.Book) request,
              (io.grpc.stub.StreamObserver<com.gappless.protobuf.BackendProto.Book>) responseObserver);
          break;
        case METHODID_DELETE:
          serviceImpl.delete((com.gappless.protobuf.BackendProto.Id) request,
              (io.grpc.stub.StreamObserver<com.gappless.protobuf.BackendProto.Empty>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class BookServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.gappless.protobuf.BackendProto.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (BookServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BookServiceDescriptorSupplier())
              .addMethod(METHOD_LIST)
              .addMethod(METHOD_CREATE)
              .addMethod(METHOD_READ)
              .addMethod(METHOD_UPDATE)
              .addMethod(METHOD_DELETE)
              .build();
        }
      }
    }
    return result;
  }
}
