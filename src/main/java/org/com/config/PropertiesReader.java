package org.com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
@PropertySource("classpath:build.properties")
public class PropertiesReader {
	@Value("${groupid}")
	private String groupId;

	@Value("${artifactid}")
	private String artifactId;

	@Value("${version}")
	private String version;

	@Value("${datebuild}")
	private String dateBuild;

	@Value("${springversion}")
	private String springVersion;

	@Value("${javaversion}")
	private String javaVersion;

	@Value("${formatfecha}")
	private String formatFechaMon;
}
