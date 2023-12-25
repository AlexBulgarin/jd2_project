package by.sep;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "by.sep")
@Import(DataConfiguration.class)
public class ServiceConfiguration {
}
