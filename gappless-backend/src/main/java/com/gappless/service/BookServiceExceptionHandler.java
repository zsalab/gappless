package com.gappless.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

@Aspect
@Component
public class BookServiceExceptionHandler {

	@Around("execution(public * com.gappless.service.BookService.*(*,io.grpc.stub.StreamObserver))")
	public void handleException(ProceedingJoinPoint pjp) {
		try {
			pjp.proceed();
		} catch (Throwable th) {
			StatusRuntimeException sre = th instanceof StatusRuntimeException
					? (StatusRuntimeException)th
					: new StatusRuntimeException(Status.INTERNAL.withDescription(th.getMessage()));
			((StreamObserver<?>)pjp.getArgs()[1]).onError(sre);
		}
	}

}
