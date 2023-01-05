package com.dnt.cloud.web.exception;

import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.dnt.cloud.common.domain.result.NrcResult;
import com.dnt.cloud.common.domain.result.ResultCode;

@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public NrcResult handleValidationError(MethodArgumentNotValidException ex) {
		StringBuffer validDesc = new StringBuffer();
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		fieldErrors.forEach(error -> {
			validDesc.append(String.format("%s %s", error.getField(), error.getDefaultMessage()));
			validDesc.append("; ");
		});
		logger.info("参数校验不通过: {}", validDesc.toString());
		return new NrcResult(ResultCode.PARAM_INVALID, validDesc.toString());
	}

	@ExceptionHandler(BindException.class)
	public NrcResult handleBindException(BindException ex) {
		StringBuffer validDesc = new StringBuffer();
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		fieldErrors.forEach(error -> {
			validDesc.append(String.format("%s %s", error.getField(), error.getDefaultMessage()));
			validDesc.append("; ");
		});
		logger.info("参数校验不通过: {}", validDesc.toString());
		return new NrcResult(ResultCode.PARAM_INVALID, validDesc.toString());
	}

	@ExceptionHandler({ IllegalArgumentException.class, MissingServletRequestParameterException.class,
			MethodArgumentTypeMismatchException.class })
	public NrcResult handleIllegalParameter(Exception ex) {
		logger.info("参数校验不通过: {}", ex.getMessage());
		return new NrcResult(ResultCode.PARAM_INVALID, ex.getMessage());
	}

	@ExceptionHandler(AccessDeniedException.class)
	public NrcResult handleAccessDeniedException(AccessDeniedException e) {
		logger.info("没有访问权限: {}", e.getMessage());
		return new NrcResult(ResultCode.FORBIDDEN);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public NrcResult handleMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		String desc = String.format("%s, please try method(s) %s", e.getMessage(),
				Arrays.asList(e.getSupportedMethods()));
		logger.info("访问方法不支持: {}", desc);
		return new NrcResult(ResultCode.METHOD_NOT_SUPPORTED, desc);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	public NrcResult handleMethodNotSupportedException(HttpClientErrorException e) {
		logger.error("调用资源异常", e);
		return new NrcResult(ResultCode.FAIL, e.getMessage());
	}

	@ExceptionHandler(Throwable.class)
	public NrcResult handleThrowable(Throwable ex) {

		if ("org.springframework.dao.DuplicateKeyException".equals(ex.getClass().getName())) {
			logger.warn("违反唯一键约束: {}", ex.getMessage());
			String description = ex.getCause() == null ? null : ex.getCause().getMessage();
			return new NrcResult(ResultCode.DUPLICATE_KEY, description);
		} else {
			logger.error("未处理异常", ex);
			ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
			if (responseStatus != null) {
				logger.error("responseStatus: {}, reason: {}", responseStatus.value(), responseStatus.reason());
				return new NrcResult(ResultCode.INNER_EXCEPTION, responseStatus.reason());
			} else {
				return new NrcResult(ResultCode.INNER_EXCEPTION, ex.getMessage());
			}
		}

	}

}
