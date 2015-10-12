package com.crimsonlogic.service.web.controller.exception;

import java.util.List;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import com.crimsonlogic.service.web.dto.ErrorInfoDto;
import com.crimsonlogic.service.web.dto.ResponseDto;
import com.crimsonlogic.service.web.dto.helper.ResponseHelper;


@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
			Object body, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		ResponseDto responseDto = ResponseHelper.getErrorResponse(status.value());
		ErrorInfoDto errorInfo = new ErrorInfoDto();
		errorInfo.setErrorDesc(ex.getMessage());
		responseDto.getStatus().getErrors().add(errorInfo);
		return super.handleExceptionInternal(ex, responseDto, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoSuchRequestHandlingMethod(
			NoSuchRequestHandlingMethodException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ResponseDto responseDto = ResponseHelper.getErrorResponse(status.value());
		ErrorInfoDto errorInfo = new ErrorInfoDto();
		errorInfo.setErrorDesc(ex.getMessage());
		responseDto.getStatus().getErrors().add(errorInfo);
		return super.handleExceptionInternal(ex, responseDto, headers, status, request);
		//return super.handleNoSuchRequestHandlingMethod(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ResponseDto responseDto = ResponseHelper.getErrorResponse(status.value());
		ErrorInfoDto errorInfo = new ErrorInfoDto();
		errorInfo.setErrorDesc(ex.getMessage());
		responseDto.getStatus().getErrors().add(errorInfo);
		return super.handleExceptionInternal(ex, responseDto, headers, status, request);
		//return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
			HttpMediaTypeNotSupportedException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ResponseDto responseDto = ResponseHelper.getErrorResponse(status.value());
		ErrorInfoDto errorInfo = new ErrorInfoDto();
		errorInfo.setErrorDesc(ex.getMessage());
		responseDto.getStatus().getErrors().add(errorInfo);
		return super.handleExceptionInternal(ex, responseDto, headers, status, request);
		//return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
			HttpMediaTypeNotAcceptableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ResponseDto responseDto = ResponseHelper.getErrorResponse(status.value());
		ErrorInfoDto errorInfo = new ErrorInfoDto();
		errorInfo.setErrorDesc(ex.getMessage());
		responseDto.getStatus().getErrors().add(errorInfo);
		return super.handleExceptionInternal(ex, responseDto, headers, status, request);
		//return super.handleHttpMediaTypeNotAcceptable(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(
			MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ResponseDto responseDto = ResponseHelper.getErrorResponse(status.value());
		ErrorInfoDto errorInfo = new ErrorInfoDto();
		errorInfo.setErrorDesc(ex.getMessage());
		responseDto.getStatus().getErrors().add(errorInfo);
		return super.handleExceptionInternal(ex, responseDto, headers, status, request);
		//return super.handleMissingPathVariable(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			MissingServletRequestParameterException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ResponseDto responseDto = ResponseHelper.getErrorResponse(status.value());
		ErrorInfoDto errorInfo = new ErrorInfoDto();
		errorInfo.setErrorDesc(ex.getMessage());
		responseDto.getStatus().getErrors().add(errorInfo);
		return super.handleExceptionInternal(ex, responseDto, headers, status, request);
		//return super.handleMissingServletRequestParameter(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(
			ServletRequestBindingException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ResponseDto responseDto = ResponseHelper.getErrorResponse(status.value());
		ErrorInfoDto errorInfo = new ErrorInfoDto();
		errorInfo.setErrorDesc(ex.getMessage());
		responseDto.getStatus().getErrors().add(errorInfo);
		return super.handleExceptionInternal(ex, responseDto, headers, status, request);
		//return super.handleServletRequestBindingException(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleConversionNotSupported(
			ConversionNotSupportedException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ResponseDto responseDto = ResponseHelper.getErrorResponse(status.value());
		ErrorInfoDto errorInfo = new ErrorInfoDto();
		errorInfo.setErrorDesc(ex.getMessage());
		responseDto.getStatus().getErrors().add(errorInfo);
		return super.handleExceptionInternal(ex, responseDto, headers, status, request);
		//return super.handleConversionNotSupported(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(
			TypeMismatchException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {

		ResponseDto responseDto = ResponseHelper.getErrorResponse(status.value());
		ErrorInfoDto errorInfo = new ErrorInfoDto();
		errorInfo.setErrorDesc(ex.getMessage());
		responseDto.getStatus().getErrors().add(errorInfo);
		return super.handleExceptionInternal(ex, responseDto, headers, status, request);
		//return super.handleTypeMismatch(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ResponseDto responseDto = ResponseHelper.getErrorResponse(status.value());
		ErrorInfoDto errorInfo = new ErrorInfoDto();
		errorInfo.setErrorDesc(ex.getMessage());
		responseDto.getStatus().getErrors().add(errorInfo);
		return super.handleExceptionInternal(ex, responseDto, headers, status, request);
		//return super.handleHttpMessageNotReadable(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(
			HttpMessageNotWritableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ResponseDto responseDto = ResponseHelper.getErrorResponse(status.value());
		ErrorInfoDto errorInfo = new ErrorInfoDto();
		errorInfo.setErrorDesc(ex.getMessage());
		responseDto.getStatus().getErrors().add(errorInfo);
		return super.handleExceptionInternal(ex, responseDto, headers, status, request);
		//return super.handleHttpMessageNotWritable(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		BindingResult bindResult = ex.getBindingResult();
		List<FieldError> fieldErrors = bindResult.getFieldErrors();
		ResponseDto responseDto = ResponseHelper.getErrorResponse(status.value());
		
		for(FieldError fe: fieldErrors){
			ErrorInfoDto errorInfo = new ErrorInfoDto();
			errorInfo.setErrorCode("CLIENT_ERROR");
			errorInfo.setErrorDesc(fe.getField()+" "+fe.getDefaultMessage());
			responseDto.getStatus().getErrors().add(errorInfo);
		}
		return super.handleExceptionInternal(ex, responseDto, headers, status, request);
		//return super.handleMethodArgumentNotValid(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(
			MissingServletRequestPartException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ResponseDto responseDto = ResponseHelper.getErrorResponse(status.value());
		ErrorInfoDto errorInfo = new ErrorInfoDto();
		errorInfo.setErrorDesc(ex.getMessage());
		responseDto.getStatus().getErrors().add(errorInfo);
		return super.handleExceptionInternal(ex, responseDto, headers, status, request);
		//return super.handleMissingServletRequestPart(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ResponseDto responseDto = ResponseHelper.getErrorResponse(status.value());
		ErrorInfoDto errorInfo = new ErrorInfoDto();
		errorInfo.setErrorDesc(ex.getMessage());
		responseDto.getStatus().getErrors().add(errorInfo);
		return super.handleExceptionInternal(ex, responseDto, headers, status, request);
		//return super.handleBindException(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(
			NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {

		ResponseDto responseDto = ResponseHelper.getErrorResponse(status.value());
		ErrorInfoDto errorInfo = new ErrorInfoDto();
		errorInfo.setErrorDesc(ex.getMessage());
		responseDto.getStatus().getErrors().add(errorInfo);
		return super.handleExceptionInternal(ex, responseDto, headers, status, request);
		//return super.handleNoHandlerFoundException(ex, headers, status, request);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseDto> handleServiceException(Exception ex) {
		ResponseDto responseDto = ResponseHelper.getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value());
		ErrorInfoDto errorInfo = new ErrorInfoDto();
		errorInfo.setErrorDesc(ex.getMessage());
		responseDto.getStatus().getErrors().add(errorInfo);
		return new ResponseEntity<ResponseDto> (responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
