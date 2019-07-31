# password_validation

A RESTful service for validating text.

Basic rules:
  - Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each.
  - Must be between 5 and 12 characters in length.
  - Must not contain any sequence of characters immediately followed by the same sequence.

## Usage
1. Build
    this project uses maven, the build command:
    ```
    mvn package
    ```
2. Run application
    2.1 Run jar file:
    ```
    java -jar -Dspring.profiles.active=${env} target/passwordvalidation-0.0.1-SNAPSHOT.jar
    ```
    where `${env}` could be `dev` or `prod`

    2.2 Using maven(spring-boot-maven-plugin):
    ```
    mvn spring-boot:run -Dspring-boot.run.profiles=${env}
    ```
    where `${env}` could be `dev` or `prod`

3. Use service
    api endpoint:
    `http://localhost:8080/validate`

    request body:
    ```
    {
        "text": "qwerty123456"
    }
    ```

    cURL example:
    ```
    curl -X POST \
        http://localhost:8080/validate \
        -H 'Cache-Control: no-cache' \
        -H 'Content-Type: application/json' \
        -d '{
            "text": "qwerty123456"
        }'
    ```

    response body:
    ```
    {
        "data": {
            "text": "qwe1231111111",
            "valid": false
        },
        "timestamp": 1564587730374
    }
    ```
    `data['valid']` indicates the input text is valid or not.

4. Trouble shoot
    If there is something wrong, the response body would be (`debug mode: false`, default setting of env `PROD`):
    ```
    {
        "data": "server error",
        "timestamp": 1564591862220
    }
    ```
    or (`debug mode: true`, default setting of env `DEV`):
    ```
    {
        "data": "server error",
        "stacktrace": ...,
        "message": error message,
        "timestamp": 1564591862220
    }
    ```
    setting ref: `application.yml` `application: debug`

5. Development - Add new rule:
    5.1 Implement `practice.service.rule.ValidationRule`
    5.2 Define rule bean in `practice.config.ValidationConfig`
    5.3 Append bean name in setting: `application.yml` `application: rules: applied-rules`
