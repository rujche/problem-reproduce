package rujche.problem.reproduce;

import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.core.CosmosTemplate;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

@ConditionalOnClass(CosmosTemplate.class)
@AutoConfiguration
public class SecondAutoConfiguration extends AbstractCosmosConfiguration {

    @Override
    protected String getDatabaseName() {
        return "";
    }
}
