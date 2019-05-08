package com.prosmv.util.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.prosmv.constants.message.SubjectMessageCode;
import com.prosmv.domain.User;
import com.prosmv.services.mail.MailService;
import com.prosmv.services.message.MessageService;

/**
 * This class is used to access the email service with thymleaf templates. This
 * class will contains all the methods to send the email.
 * 
 * @author piyush
 *
 */
@Service
public class EmailTemplateService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailTemplateService.class);

	@Autowired
	private MailService mailService;

	@Value(value = "${spring.mail.username}")
	private String from;

	@Autowired
	private MessageService messageService;

	@Autowired
	private EmailTemplateDataResolver emailTemplateDataResolver;

	/**
	 * This method is used to send mail to {@link User} who forgot his password.
	 * 
	 * @param user {@link User}
	 */
	@Async
	public void sendForgotPasswordEmail(User user,String resetPasswordLink) {

		LOGGER.info("Sending mail for forgot password to user {} ", user.getEmail());

		mailService.sendMail(from, "piyushmakhija028@gmail.com", emailTemplateDataResolver.getHTMLTemplate(
				EmailTemplateData.getForgotPasswordTemplateData(resetPasswordLink), EmailTemplateData.getLocale()),
				messageService.getSubjectMessage(SubjectMessageCode.FORGOT_PASSWORD, null,
						EmailTemplateData.getLocale()));

	}

}
