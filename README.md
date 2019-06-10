YAHOO FINANCE UI TEST

Please, download a [relevant version of ChromeDriver](http://chromedriver.chromium.org/downloads) and put it to ${PROJECT_DIR}/driver folder 

Define driver in ua.barnacle.webdriver.WebDriveFactory class

Run test
```
$  ./gradlew clean test

$  ./gradlew clean test -Psuite=regression.xml -Dbrowser=firefox
```

Open allure report
```
$ allure serve build/allure-results
```