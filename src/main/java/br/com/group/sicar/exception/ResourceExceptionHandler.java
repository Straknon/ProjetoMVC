package br.com.group.sicar.exception;

import org.apache.commons.mail.EmailException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import com.fasterxml.jackson.core.JsonGenerationException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import javassist.tools.rmi.ObjectNotFoundException;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<StandardError> constraintViolation(ConstraintViolationException e, HttpServletRequest req) {

		List<String> messageList = new ArrayList<>();
		if (e instanceof ConstraintViolationException) {
			Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) e).getConstraintViolations();
			Set<String> messages = new HashSet<>(violations.size());
			messages.addAll(violations.stream().map(constraintViolation -> constraintViolation.getMessage())
					.collect(Collectors.toList()));

			for (Iterator<String> it = messages.iterator(); it.hasNext();) {
				messageList.add(it.next());
			}

		}

		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), messageList,
				req.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest req) {
		List<String> messagesList = new ArrayList<>();
		messagesList.add(e.getMessage());
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				messagesList, req.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(ValidatorException.class)
	public ResponseEntity<StandardError> constraintViolation(ValidatorException e, HttpServletRequest req) {
		List<String> messagesList = new ArrayList<>();
		messagesList.add(e.getMessage());
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), messagesList,
				req.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<StandardError> nullPointer(NullPointerException e, HttpServletRequest req) {
		List<String> messagesList = new ArrayList<>();
		messagesList.add(e.getMessage());
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				messagesList, req.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}

	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<StandardError> accessDenied(ExpiredJwtException e, HttpServletRequest req) {
		List<String> messagesList = new ArrayList<>();
		messagesList.add(e.getMessage());
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(), messagesList,
				req.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}

	@ExceptionHandler(SignatureException.class)
	public ResponseEntity<StandardError> accessDenied(SignatureException e, HttpServletRequest req) {
		List<String> messagesList = new ArrayList<>();
		messagesList.add(e.getMessage());
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(), messagesList,
				req.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}

	@ExceptionHandler(ClaimJwtException.class)
	public ResponseEntity<StandardError> accessDenied(ClaimJwtException e, HttpServletRequest req) {
		List<String> messagesList = new ArrayList<>();
		messagesList.add(e.getMessage());
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(), messagesList,
				req.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}

	@ExceptionHandler(JsonGenerationException.class)
	public ResponseEntity<StandardError> accessDenied(JsonGenerationException e, HttpServletRequest req) {
		List<String> messagesList = new ArrayList<>();
		messagesList.add(e.getMessage());
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(), messagesList,
				req.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}

	@ExceptionHandler(MalformedJwtException.class)
	public ResponseEntity<StandardError> accessDenied(MalformedJwtException e, HttpServletRequest req) {
		List<String> messagesList = new ArrayList<>();
		messagesList.add("JWT was not correctly constructed");
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(), messagesList,
				req.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}

	@ExceptionHandler(JwtException.class)
	public ResponseEntity<StandardError> accessDenied(JwtException e, HttpServletRequest req) {
		List<String> messagesList = new ArrayList<>();
		messagesList.add(e.getMessage());
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				messagesList, req.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}

	@ExceptionHandler(RollbackException.class)
	public ResponseEntity<StandardError> rollbackFaild(RollbackException e, HttpServletRequest req) {

		List<String> messagesList = new ArrayList<>();

		if (e.getCause() instanceof ConstraintViolationException) {
			ConstraintViolationException constViolation = (ConstraintViolationException) e.getCause();
			Set<ConstraintViolation<?>> violations = constViolation.getConstraintViolations();
			Set<String> messages = new HashSet<>(violations.size());
			messages.addAll(violations.stream().map(constraintViolation -> constraintViolation.getMessage())
					.collect(Collectors.toList()));

			for (Iterator<String> it = messages.iterator(); it.hasNext();) {
				messagesList.add(it.next());
			}
		} else {
			messagesList.add(e.getCause().getMessage());
		}

		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), messagesList,
				req.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(DataSourceLookupFailureException.class)
	public ResponseEntity<StandardError> databaseSourceLookupFailure(DataSourceLookupFailureException e,
			HttpServletRequest req) {
		List<String> messagesList = new ArrayList<>();
		messagesList.add(e.getMessage());
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				messagesList, req.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<StandardError> illegalArgument(IllegalArgumentException e,
			HttpServletRequest req) {
		List<String> messagesList = new ArrayList<>();
		messagesList.add(e.getMessage());
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				messagesList, req.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}

	@ExceptionHandler(UnknownHostException.class)
	public ResponseEntity<StandardError> reachHostFailure(UnknownHostException e, HttpServletRequest req) {
		List<String> messagesList = new ArrayList<>();
		messagesList.add("IP address could not be found");
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				messagesList, req.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}
	
	@ExceptionHandler(InvalidKeySpecException.class)
	public ResponseEntity<StandardError> jwtCertsFailure(InvalidKeySpecException e, HttpServletRequest req) {
		List<String> messagesList = new ArrayList<>();
		messagesList.add(e.getMessage());
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				messagesList, req.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}
	
	@ExceptionHandler(NoSuchAlgorithmException.class)
	public ResponseEntity<StandardError> noSuchAlgorithm(NoSuchAlgorithmException e, HttpServletRequest req) {
		List<String> messagesList = new ArrayList<>();
		messagesList.add(e.getMessage());
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				messagesList, req.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}
	
	@ExceptionHandler(MalformedURLException.class)
	public ResponseEntity<StandardError> urlMaldormated(MalformedURLException e, HttpServletRequest req) {
		List<String> messagesList = new ArrayList<>();
		messagesList.add(e.getMessage());
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				messagesList, req.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<StandardError> ioFailure(IOException e, HttpServletRequest req) {
		List<String> messagesList = new ArrayList<>();
		messagesList.add(e.getMessage());
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				messagesList, req.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}
	
	@ExceptionHandler(UnsupportedEncodingException.class)
	public ResponseEntity<StandardError> usupportedEncoding(UnsupportedEncodingException e, HttpServletRequest req) {
		List<String> messagesList = new ArrayList<>();
		messagesList.add(e.getMessage());
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				messagesList, req.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}
	
	@ExceptionHandler(RemoteException.class)
	public ResponseEntity<StandardError> remoteException(RemoteException e, HttpServletRequest req) {
		List<String> messagesList = new ArrayList<>();
		messagesList.add(e.getMessage());
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				messagesList, req.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}	
	
	@ExceptionHandler(EmailException.class)
	public ResponseEntity<StandardError> emailException(EmailException e, HttpServletRequest req) {
		List<String> messagesList = new ArrayList<>();
		messagesList.add(e.getMessage());
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				messagesList, req.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}
}
