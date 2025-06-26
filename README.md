# NHS Jobs Search Automation Framework

This automation project validates the search functionality of the NHS Jobs website using Selenium, Cucumber, and JUnit.

## Prerequisite
- Java 21 is installed and configured
- Maven installed and configured
- Chrome/Firefox browsers installed

## Scenaiors Covered
- Search with job title, location, distance, jobreference, employer, and payRange with different set of inputs
- Clear button validation
- Message validation
- Invalid input validation – e.g., special characters
- Works in both **Chrome** and **Firefox**

## Run Test from Command Line

To run the tests in Chrome:

```bash
mvn test -Dbrowser=chrome
```

To run the tests in Firefox:

```bash
mvn test -Dbrowser=firefox
```

## Run Tests from Eclipse

1. Open the project in Eclipse.
2. Update `config.properties` to set browser:

   ```
   browser=chrome
   ```
   or
   ```
   browser=firefox
   ```

3. Run tests via `TestRunner.java`:
   - Right-click on `TestRunner.java` → Run As → JUnit Test
   - Or use the JUnit test panel

4. Test report is generated after execution.

## Test Report Location

HTML report will be generated at:

```
target/cucumber-report.html
```

To view:
1. Navigate to the `target` folder in your project directory
2. Open `cucumber-report.html` in any browser

## Tech Stack
- Java
- Selenium WebDriver
- Cucumber BDD
- JUnit
- WebDriverManager (no machine-based drivers used)
- Maven
- Git

## Notes
- WebDriverManager is used — no need for local `.exe` drivers.
