package rujche.problem.reproduce;

import com.azure.spring.data.cosmos.core.CosmosTemplate;
import org.springframework.boot.autoconfigure.condition.AllNestedConditions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

public class FirstCondition extends AllNestedConditions {

    FirstCondition() {
        super(ConfigurationPhase.PARSE_CONFIGURATION);
    }

    @ConditionalOnClass(CosmosTemplate.class)
    static class ClassPathCondition {
    }
}
