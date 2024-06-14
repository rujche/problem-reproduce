package rujche.problem.reproduce;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rujche.problem.reproduce.property.PropertyTwo;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProblemReproduceApplicationTests {

    @Autowired
    PropertyTwo propertyTwo;

    @Test
    void testPropertyBindingByAutowiredBean() { // Test passed
        assertEquals("property-two-value", propertyTwo.getValue());
    }

}
