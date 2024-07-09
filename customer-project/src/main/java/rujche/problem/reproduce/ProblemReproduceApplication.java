package rujche.problem.reproduce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProblemReproduceApplication {

	// @Bean
//	SpringCloudAzureConnectionDetails connectionDetails() {
//		return new SpringCloudAzureConnectionDetails() {
//			@Override
//			public String getSubscriptionId() {
//				return "subscriptionId in SpringCloudAzureConnectionDetails";
//			}
//
//			@Override
//			public String getResourceGroup() {
//				return "resourceGroup in SpringCloudAzureConnectionDetails";
//			}
//		};
//	}

	public static void main(String[] args) {
		SpringApplication.run(ProblemReproduceApplication.class, args);
	}

}
