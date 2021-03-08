package complaintsapi.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ComplaintsAPIExceptionHandler extends ResponseEntityExceptionHandler{
	
		//Dispara, por exemplo, se passar um parâmetro string onde deveria ser um Long.
		@ExceptionHandler({MethodArgumentTypeMismatchException.class}) 
		public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
		MethodArgumentTypeMismatchException ex, WebRequest request) 
		{ 
			ComplaintsAPIError apiError = new ComplaintsAPIError(
					HttpStatus.BAD_REQUEST, "Invalid parameters.",
					"MethodArgumentTypeMismatchException");
			
			return new ResponseEntity<Object>(
		    		apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);	 
		}
		
		//Utilizado no controller quando parâmetros não atendem validação.
		@ExceptionHandler(value = {ComplaintsAPIBadRequestException.class})
		public ResponseEntity<Object> handleComplaintsAPIBadRequestException(
				ComplaintsAPIBadRequestException ex, WebRequest request) {	
			
			ComplaintsAPIError apiError = new ComplaintsAPIError(
					HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(),
					"ComplaintsAPIBadRequestException");
		    
		    return new ResponseEntity<Object>(
		    		apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);		 
			
		}
		
		
		//Utilizado no controller quando não encontra resultados para os parâmetros informados.
		@ExceptionHandler(value = {ComplaintsAPINotFoundException.class})
		public ResponseEntity<Object> handleComplaintsAPINotFoundException(
				ComplaintsAPINotFoundException ex, WebRequest request) {	
			
			ComplaintsAPIError apiError = new ComplaintsAPIError(
					HttpStatus.NOT_FOUND, ex.getLocalizedMessage(),
					"ComplaintsAPINotFoundException");
		    
		    return new ResponseEntity<Object>(
		    		apiError, new HttpHeaders(), HttpStatus.NOT_FOUND);		 
			
		}
		
		//Utilizado no controller para erros genéricos.
		@ExceptionHandler(value = {ComplaintsAPIException.class})
		public ResponseEntity<Object> handleComplaintsAPIException(
				ComplaintsAPIException ex, WebRequest request) {	
			
			ComplaintsAPIError apiError = new ComplaintsAPIError(
					HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(),
					"ComplaintsAPIException");
		    
		    return new ResponseEntity<Object>(
		    		apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);		 
			
		}

		//Dispara quando ocorre um erro que não foi pego pelos anteriores.
		@ExceptionHandler({ Exception.class })
		public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {	
			
			ComplaintsAPIError apiError = new ComplaintsAPIError(
					HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "An error occured.");
		    
		    return new ResponseEntity<Object>(
		    		apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);		 
			
		}

}
