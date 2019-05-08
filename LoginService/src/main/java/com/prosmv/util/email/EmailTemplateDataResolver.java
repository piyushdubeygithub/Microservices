package com.prosmv.util.email;

import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * This class is responsible for managing the data and procces it in thymleaf
 * template with context.
 * 
 * @author piyush
 *
 */
@Service
public class EmailTemplateDataResolver {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailTemplateDataResolver.class);

	@Autowired
	private TemplateEngine templateEngine;

	/**
	 * This method is used to get email template html body with data set in key map.
	 * 
	 * @param data   data part in key value {@link Map}
	 * @param locale {@link Locale}
	 * @return html body
	 */
	public String getHTMLTemplate(Map<String, Object> data, Locale locale) {

		final Context context = new Context(locale);

		for (Map.Entry<String, Object> entry : data.entrySet()) {

			context.setVariable(entry.getKey(), entry.getValue());

		}

		String htmlPage = templateEngine.process("CommonTemplate.html", context);

		LOGGER.info("html page after parsing {} ", htmlPage);

		return htmlPage;

	}

}
