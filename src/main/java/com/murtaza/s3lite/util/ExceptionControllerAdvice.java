package com.murtaza.s3lite.util;

import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.murtaza.s3lite.exception.StorageException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;


@RestControllerAdvice
public class ExceptionControllerAdvice {


	    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(ExceptionControllerAdvice.class);

	    @Autowired
	    private Environment environment;

	   	@ExceptionHandler(StorageException.class)
	    public ResponseEntity<Error> meetingSchedulerExceptionHandler(StorageException exception)
	    {
		LOGGER.error(exception.getMessage(), exception);
		Error errorInfo = new Error();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setErrorMessage(environment.getProperty(exception.getMessage()));
		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<Error> generalExceptionHandler(Exception exception)
	    {
		LOGGER.error(exception.getMessage(), exception);
		Error errorInfo = new Error();
		errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorInfo.setErrorMessage(environment.getProperty("General.EXCEPTION_MESSAGE"));
		return new ResponseEntity<>(errorInfo,
					    HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    // add appropriate annotation
	    public ResponseEntity<Error> validatorExceptionHandler(Exception exception)
	    {
		LOGGER.error(exception.getMessage(), exception);
		String errorMsg;
		if (exception instanceof MethodArgumentNotValidException)
		{
		    MethodArgumentNotValidException manvException = (MethodArgumentNotValidException) exception;
		    errorMsg = manvException.getBindingResult()
					    .getAllErrors()
					    .stream()
					    .map(ObjectError::getDefaultMessage)
					    .collect(Collectors.joining(", "));

		}
		else
		{
		    ConstraintViolationException cvException = (ConstraintViolationException) exception;
		    errorMsg = cvException.getConstraintViolations()
					  .stream()
					  .map(ConstraintViolation::getMessage)
					  .collect(Collectors.joining(", "));

		}
		Error errorInfo = new Error();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setErrorMessage(errorMsg);
		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	    }
	}

