package rujche.problem.reproduce;

import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import org.springframework.context.annotation.Conditional;

@Conditional(SubModuleOneAllNestedCondition.class)
public class SubModuleOneAllNestedConditionConfiguration extends AbstractCosmosConfiguration {

    @Override
    protected String getDatabaseName() {
        return "";
    }
}
