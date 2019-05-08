package com.prosmv.services.message;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 * This class is service class is used for message handling with there message
 * source and properties file and can also be used for internationalization.
 * 
 * @author piyush
 *
 */
@Service
public class MessageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

	@Autowired
	@Qualifier("validationMessageSource")
	private MessageSource validationMessageSource;

	@Autowired
	@Qualifier("errorCodeMessageSource")
	private MessageSource errorCodeMessageSource;

	@Autowired
	@Qualifier("serviceMessageSource")
	private MessageSource serviceMessageSource;

	@Autowired
	@Qualifier("subjectMessageSource")
	private MessageSource subjectMessageSource;

	@Autowired
	@Qualifier("emailMessageSource")
	private MessageSource emailMessageSource;

	/**
	 * This method will return the validation message.
	 * 
	 * @param validationMessageCode validationMessageCode
	 * @return validation message from validationmessage_en.properties
	 */
	public String getValidationMessage(String validationMessageCode) {

		return validationMessageSource.getMessage(validationMessageCode, null, new Locale("en"));

	}

	/**
	 * This method will return the error code.
	 * 
	 * @param errorCode error code key
	 * @return error code from errorcode_en.properties
	 */
	public String getErrorCode(String errorCode) {

		return errorCodeMessageSource.getMessage(errorCode, null, new Locale("en"));

	}

	/**
	 * This method will return the service message.
	 * 
	 * @param serviceMessageCode        service message code
	 * @param serviceMessageDynamicData service message dynamic data
	 * @return service message from servicemessage_en.properties
	 */
	public String getServiceMessage(String serviceMessageCode, Object[] serviceMessageDynamicData) {

		return serviceMessageSource.getMessage(serviceMessageCode, serviceMessageDynamicData, new Locale("en"));

	}

	/**
	 * This method will return the subject message.
	 * 
	 * @param subjectMessageCode        email subject code
	 * @param subjectMessageDynamicData email subject dynamic data
	 * @param locale                    {@link Locale}
	 * @return subject message from subjectmessage_en.properties
	 */
	public String getSubjectMessage(String subjectMessageCode, Object[] subjectMessageDynamicData, Locale locale) {

		return subjectMessageSource.getMessage(subjectMessageCode, subjectMessageDynamicData, locale);

	}

	/**
	 * This method will return the email message.
	 * 
	 * @param emailMessageCode        email message code
	 * @param emailMessageDynamicData email message dynamic data
	 * @param locale                  {@link Locale}
	 * @return email message from emailmessage_en.properties
	 */
	public String getEmailMessage(String emailMessageCode, Object[] emailMessageDynamicData, Locale locale) {

		return emailMessageSource.getMessage(emailMessageCode, emailMessageDynamicData, locale);

	}

}
