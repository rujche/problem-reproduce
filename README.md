# Problem Reproduce

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
