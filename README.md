# Problem Reproduce

## Latest information

Problem solved by upgrading spring-framework's version to **6.2.0-M4**.
Link to related issue: https://github.com/spring-projects/spring-framework/issues/28676

## Steps to reproduce this problem

Run the following comment in current project's root directory:

```shell
./mvnw clean test
```

### Expected behavior

Expect build success.

### Actual behavior

Actually, build failed with error log like this:

```text
...
[ERROR] Failures: 
[ERROR]   AutoConfigurationTests.testPropertyBindingByApplicationContextRunnerWithTwoAutoConfigurations:36->lambda$testPropertyBindingByApplicationContextRunnerWithTwoAutoConfigurations$1:39 expected: <property-two-value> but was: <null>
[INFO]
[ERROR] Tests run: 3, Failures: 1, Errors: 0, Skipped: 0
...
```

### More details

For more details, please read the source code.
