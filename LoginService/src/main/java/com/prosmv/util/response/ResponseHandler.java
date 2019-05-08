package com.prosmv.util.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.prosmv.config.context.SpringContext;
import com.prosmv.constants.message.ValidationMessageCode;
import com.prosmv.dto.ResponseDTO;
import com.prosmv.services.message.MessageService;

/**
 * This class is response class used to generate api responses.
 * 
 * @author piyush
 *
 */
public class ResponseHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResponseHandler.class);

	private static MessageService messageService = (MessageService) SpringContext.getBean(MessageService.class);

	/**
	 * This method is used to generate validation response for the api.
	 * 
	 * @param validationMessageCode validation message.
	 * @return {@link ResponseDTO}.
	 */
	public static ResponseEntity<ResponseDTO> generateValidationResponse(String validationMessageCode) {
		ResponseDTO responseDTO = new ResponseDTO(null, messageService.getValidationMessage(validationMessageCode),
				false, HttpStatus.BAD_REQUEST.value(), messageService.getErrorCode(validationMessageCode));
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.valueOf(responseDTO.getHttpStatus()));
	}

	/**
	 * This method will be used for generating exception response
	 * 
	 * @param exception {@link Exception}
	 * @return {@link ResponseDTO}.
	 */
	public static ResponseDTO generateExceptionResponse(Exception exception) {
		return new ResponseDTO(exception.getCause(),
				messageService.getValidationMessage(ValidationMessageCode.INTERNAL_SERVER_ERROR), false,
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				messageService.getErrorCode(ValidationMessageCode.INTERNAL_SERVER_ERROR));
	}

	/**
	 * This method will return the service message response.
	 * 
	 * @param data                      data part of the api
	 * @param serviceMessageCode        service message
	 * @param serviceMessageDynamicData service message dynamic data
	 * @param isSuccess                 is api success
	 * @param httpStatus                {@link HttpStatus}
	 * @return {@link ResponseDTO}
	 */
	public static ResponseDTO generateServiceResponse(Object data, String serviceMessageCode,
			Object[] serviceMessageDynamicData, boolean isSuccess, HttpStatus httpStatus) {
		try {
			return new ResponseDTO(data,
					messageService.getServiceMessage(serviceMessageCode, serviceMessageDynamicData), isSuccess,
					httpStatus.value(), (isSuccess) ? null : messageService.getErrorCode(serviceMessageCode));
		} catch (Exception exception) {
			return generateExceptionResponse(exception);
		}
	}

	/**
	 * This method will return the controller response.
	 * 
	 * @param responseDTO {@link ResponseDTO}
	 * @return {@link ResponseEntity} of {@link ResponseDTO}
	 */
	public static ResponseEntity<ResponseDTO> generateControllerResponse(ResponseDTO responseDTO) {
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.valueOf(responseDTO.getHttpStatus()));
	}

}
