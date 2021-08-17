package mx.gda.correo;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2   //tag to enable swagger
public class ApiCorreoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCorreoApplication.class, args);
	}

	/*  configuration bean for swagger properties */
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/ApiCorreo/**"))     //take  the methods of the path
				.apis(RequestHandlerSelectors.basePackage("mx.gda"))  //take only the models used in this package
				.build()
				.apiInfo(myApiDetails());
	}
	
	/* Overwrite apiInfo for set our information  */
	private ApiInfo myApiDetails() {
		return new ApiInfo(
				"API Correo",   //title
				"API desarrollada para el envio de correos por parte de GDA",           //description
				"1.0",				  //version
				null,//"API constructed for GDA, use internal only", //termsOfServiceUrl
				new springfox.documentation.service.Contact("Equipo de Desarrollo de TI",null, "marco.sosa@gda.mx"),   //name,url, email
				"Grupo Diagnóstico Aries",        //license
				"https://grupodiagnosticoaries.com/",  		//licenseUrl
				Collections.emptyList()						//vendorExtensions
				);
	}
}
