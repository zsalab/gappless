package com.gappless.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.gappless.entity.Book;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;

@RunWith(JUnit4.class)
public class ValidationUtilTest {

	@Test
	public void validationTest_Invalid() {
		Book book = new Book(0l, "", 0);
		try {
			ValidationUtil.validateInput(book);
		} catch (StatusRuntimeException e) {
			assertEquals(e.getStatus().getCode(), Status.INVALID_ARGUMENT.getCode());
			assertTrue(e.getStatus().getDescription().contains("id must be greater than or equal to 1;"));
			assertTrue(e.getStatus().getDescription().contains("title size must be between 1 and 64;"));
			assertTrue(e.getStatus().getDescription().contains("nrOfPages must be greater than or equal to 1;"));
		}
	}

	@Test
	public void validationTest_Valid() {
		Book book = new Book(1l, "F", 1);
		try {
			ValidationUtil.validateInput(book);
		} catch (StatusRuntimeException e) {
			fail("valid data cause error");
		}
	}
}
