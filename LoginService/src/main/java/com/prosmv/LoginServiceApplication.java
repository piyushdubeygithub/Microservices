package com.prosmv;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.prosmv.services.login.BootStrapService;

@SpringBootApplication
@EnableDiscoveryClient
public class LoginServiceApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceApplication.class);

	@Value("${spring.mail.host}")
	private String host;

	@Value("${spring.mail.port}")
	private int port;

	@Value("${spring.mail.protocol}")
	private String protocol;

	@Value("${spring.mail.username}")
	private String username;

	@Value("${spring.mail.password}")
	private String password;

	@Value("${spring.mail.properties.mail.smtp.auth}")
	private boolean smtpAuth;

	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private boolean tlsRequired;

	@Autowired
	private BootStrapService bootStrapService;

	public static void main(String[] args) {

		LOGGER.info("Login Microservice client is running");

		SpringApplication.run(LoginServiceApplication.class, args);

	}

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		Properties javaMailProperties = new Properties();
		javaMailSender.setHost(host);
		javaMailSender.setPort(port);
		javaMailSender.setProtocol(protocol);
		javaMailSender.setUsername(username);
		javaMailSender.setPassword(password);
		javaMailProperties.put("mail.smtp.auth", smtpAuth);
		javaMailProperties.put("mail.smtp.starttls.enable", tlsRequired);
		javaMailSender.setJavaMailProperties(javaMailProperties);
		return javaMailSender;
	}

	@Override
	public void run(String... args) throws Exception {
		bootStrapService.saveSAUser();
	}

}
