package com.prosmv.util.email;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosmv.config.context.SpringContext;
import com.prosmv.constants.message.EmailMessageCode;
import com.prosmv.constants.template.EmailTemplateConstants;
import com.prosmv.services.message.MessageService;

/**
 * This class will contains all the data method for the emails.
 * 
 * @author piyush
 *
 */
public class EmailTemplateData {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailTemplateData.class);

	private static MessageService messageService = (MessageService) SpringContext.getBean(MessageService.class);

	/**
	 * This method is used to get data for forgot password mail.
	 * 
	 * @param resetPasswordLink link for reset password.
	 * @return email Template data
	 */
	public static Map<String, Object> getForgotPasswordTemplateData(String resetPasswordLink) {

		Map<String, Object> emailTemplateData = new HashMap<>();

		emailTemplateData.put(EmailTemplateConstants.EMAIL_HEADER,
				messageService.getEmailMessage(EmailMessageCode.FORGOT_PASSWORD_HEADER, null, getLocale()));

		emailTemplateData.put(EmailTemplateConstants.EMAIL_CONTENT, messageService.getEmailMessage(
				EmailMessageCode.FORGOT_PASSWORD_CONTENT, new Object[] { resetPasswordLink }, getLocale()));

		emailTemplateData.put(EmailTemplateConstants.EMAIL_FOOTER,
				messageService.getEmailMessage(EmailMessageCode.FOOTER, null, getLocale()));

		return emailTemplateData;

	}

	/**
	 * This method is used to get {@link Locale}.
	 * 
	 * @return {@link Locale}
	 */
	public static Locale getLocale() {
		return new Locale("en");
	}

}
