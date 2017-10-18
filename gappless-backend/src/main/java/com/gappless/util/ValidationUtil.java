package com.gappless.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;

public final class ValidationUtil {
	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	private ValidationUtil() {
		super();
	}

	public static <T> void validateInput(T object) {
		Set<ConstraintViolation<T>> result = validator.validate(object);
		if (result != null && !result.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (ConstraintViolation<T> cv: result) {
				sb.append(cv.getPropertyPath()).append(' ').append(cv.getMessage()).append("; ");
			}
			throw new StatusRuntimeException(Status.INVALID_ARGUMENT.withDescription(sb.toString()));
		}
	}
	
}
