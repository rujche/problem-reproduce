## Latest information
This problem has been solved by changing **FirstCondition**'s **ConfigurationPhase** to **PARSE_CONFIGURATION**.

More information:
1. **ConfigurationPhase**'s java doc.
2. @wilkinsona 's [comment](https://github.com/wilkinsona/problem-reproduce/commit/be1ace28693f7b532167f066ba89443079d965f6#r143241687): **PARSE_CONFIGURATION is, essentially, the default phase. It happens first and you want to exclude things as early as possible. REGISTER_BEAN is only necessary when evaluation depends on the state of the bean factory**.

## Steps to reproduce this problem

1. Run the following command in current project's root directory:
    ```shell
    ./mvnw clean test
    ```
2. Update file [sub-module-one/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports](sub-module-one/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports).
Change **SecondAutoConfiguration** to **FirstAutoConfiguration**. Make it like this:
    ```text
    rujche.problem.reproduce.FirstAutoConfiguration
    ```
3. Run the following command again:
    ```shell
    ./mvnw clean test
    ```

### Expected behavior

Expect all tests passed.

### Actual behavior

Actually, build failed with error log like this:

```text
Caused by: java.io.FileNotFoundException: class path resource [com/azure/spring/data/cosmos/config/AbstractCosmosConfiguration.class] cannot be opened because it does not exist
```

### More information

1. IMU, **FirstAutoConfiguration** and **SecondAutoConfiguration** should have same behavior about **FileNotFoundException**. But it's not.
2. For more details, please read the source code.
