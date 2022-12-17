package com.nhnacademy.springboot.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "DepartmentIds does not exist.")
public class DepartmentIdsNotFoundException extends RuntimeException {
}