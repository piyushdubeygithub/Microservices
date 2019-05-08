package com.prosmv.services.mail;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * This class is used as service class for mail related operation such as
 * sending mail to user with file, without file etc.
 * 
 * @author piyush
 *
 */
@Service
public class MailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

	@Autowired
	private JavaMailSender javaMailSender;

	/**
	 * This mail service is used to send simple mail without any attachment.
	 * 
	 * @param from    sender email address
	 * @param to      receiver email address
	 * @param body    email content
	 * @param subject the subject regarding mail
	 */
	@Async
	public void sendMail(String from, String to, String body, String subject) {
		LOGGER.info("Sending mail to {} with subject {} from {}", to, subject, from);
		MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
				mimeMessageHelper.setFrom(from);
				mimeMessageHelper.setTo(to);
				mimeMessageHelper.setText(body, true);
				mimeMessageHelper.setSubject(subject);
			}
		};
		javaMailSender.send(mimeMessagePreparator);
	}

	/**
	 * This mail service is used to send mail with attachment.
	 * 
	 * @param from       sender email address
	 * @param to         receiver email address
	 * @param body       email content
	 * @param subject    the subject regarding mail
	 * @param attachment attachment file byte array
	 * @param fileName   name of the file
	 */
	@Async
	public void sendMail(String from, String to, String body, String subject, byte[] attachment,
			String fileName) {
		LOGGER.info("Sending mail to {} with subject {} from {} with file {}", to, subject, from,fileName);
		MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
				mimeMessageHelper.setFrom(from);
				mimeMessageHelper.setTo(to);
				mimeMessageHelper.setText(body, true);
				mimeMessageHelper.setSubject(subject);
				mimeMessageHelper.addAttachment(fileName, new ByteArrayResource(attachment));
			}
		};
		javaMailSender.send(mimeMessagePreparator);
	}

}
