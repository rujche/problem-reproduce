package rujche.problem.reproduce;

import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Conditional;

@Conditional(FirstCondition.class)
@AutoConfiguration
public class FirstAutoConfiguration extends AbstractCosmosConfiguration {

    @Override
    protected String getDatabaseName() {
        return "";
    }
}
