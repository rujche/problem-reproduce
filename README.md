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
