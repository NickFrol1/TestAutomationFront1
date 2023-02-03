# Разворачиваем инфраструктуру для автоматизации тестирования WEB-приложений на macOS

## Настройка
### Установка JDK
Java Development Kit (сокращенно JDK) — бесплатно распространяемый комплект разработчика приложений на языке Java, включающий в себя компилятор Java (javac), стандартные библиотеки классов Java, примеры, документацию, различные утилиты и исполнительную систему Java (JRE)

Скачать openjdk:
```
https://adoptopenjdk.net/index.html?variant=openjdk8&jvmVariant=hotspot
```
Установка по умолчанию

### Установка MAVEN
Мавен - это инструмент для сборки Java проекта: компиляции, создания jar, создания дистрибутива программы, генерации документации. Простые проекты можно собрать в командной строке. Если собирать большие проекты с командной строки, то команда для сборки будет очень длинной, поэтому её иногда записывают в bat/sh скрипт. Но такие скрипты зависят от платформы. Для того чтобы избавиться от этой зависимости и упростить написание скрипта используют инструменты для сборки проекта.
Скачать:
```
http://mirror.linux-ia64.org/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.zip
```
Переместить:
```
sudo mkdir /Library/maven
sudo mv /Users/antonpavlov/Downloads/apache-maven-3.6.3 /Library/maven
```
### Git
Git  — распределённая система управления версиями.

Скачать
```
https://git-scm.com
```
Установка по умолчанию

### Прописать jdk и maven в переменное окружение
```
nano ~/.zshrc 
```
Ввести в zshrc:
```
export JAVA_HOME=/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home
export PATH=$PATH:$JAVA_HOME/bin
export M2_HOME=/Library/maven/apache-maven-3.6.3
export PATH=$PATH:$M2_HOME/bin
```
Применить для zshrc:
```
source ~/.zshrc
```

### Проверить что jdk, maven, git успешно установлены
```
java -version
```
```
mvn -v
```
```
git --version
```

### Docker
Docker — программное обеспечение которое позволяет «упаковать» приложение со всем его окружением и зависимостями в контейнер.

Скачать:
```
https://hub.docker.com/editions/community/docker-ce-desktop-mac
```
Зарегистрироваться:
```
https://hub.docker.com/signup
```
Установка по умолчанию

### Jenkins
Jenkins — это сервер, предназначенная для обеспечения процесса непрерывной интеграции программного обеспечения

Полезные ссылки:
```
https://jenkins.io/
https://hub.docker.com/r/jenkins/jenkins/
```
Нужно создать папку:
```
sudo mkdir /Users/antonpavlov/Documents/jenkins_hom
```
Команда для установки:
```
docker run --name jenkins -p 8000:8080 -p 50000:50000 -v /Users/antonpavlov/jenkins_home:/var/jenkins_home jenkins/jenkins:lts
```
Устанавливаем по умолчанию

Jenkins доступен:
```
http://localhost:8000
```

Плагины ставяться минут 15, поэтому пока установим Selenoid:

### Selenoid
Selenoid — это легковесный сервер, запускающий изолированные браузеры в Docker контейнерах
 
Полезные ссылки:
```
https://aerokube.com/selenoid/latest/
https://aerokube.com/cm/latest/
```
Устанавливаем CM:
```
curl -s https://aerokube.com/cm/bash | bash
```
Быстрый старт:
```
./cm selenoid start --vnc --browsers 'chrome:80'
./cm selenoid-ui start
```
Старт Selenoid-UI отдельным контейнером с явным указанием ссылки на Selenoid
```
docker run -d --name selenoid-ui  --link selenoid -p 8080:8080 aerokube/selenoid-ui --selenoid-uri=http://192.168.31.48:4444
```
После исправления от Aerokube должно заработать командой:
```
./cm selenoid-ui start -force --args "-selenoid-uri http://192.168.31.48:4444"
```
Selenoid доступен:
```
http://localhost:8080
```

#### Jenkins настройка:

##### Установить плагин Allure
```
http://localhost:8000/pluginManager/available
В поиск ввести "Allure" нажать "Enter" на клавиатуре
В списке выбрать "Allure" и нажать Чекбокс напротив
Нажать кнопку "Установить без перезагрузки"
После установки перейти http://localhost:8000/configureTools/
Проскролить к "Allure"
Указать имя "allure"
Чекбокс "Install automatically"
Выбрать версию "2.13.1"
Нажать кнопку "Save"
```

##### Установить maven
```
http://localhost:8000/configureTools/
Проскролить к Maven
Указать имя "maven"
Чекбокс "Install automatically"
Выбрать версию "3.6.3"
Нажать кнопку "Save"
```

### Установка и настройка IDEA
Скачать
```
https://www.jetbrains.com/ru-ru/idea/download/
```

##### Настройка при установке
1 окно
2 окно
- отключить все не нужные плагины

##### Настройка после установки

Перейти в Preferences
```
вкладка enable 
	включить чекбокс: annotation processing
вкладка Maven
	указать путь /Library/maven/apache-maven-3.6.3
	вкладка Importing
		указать 1.8 jdk
вкладка git 
	проверить работу нажав: test
вкладка file encoding
	включить чекбокс: transparent native-to-ascii convert
```
Перейти в plugins и установить:
```
lombok
```
Перейти в Structure for New Projects
```
sdk -  выбрать 1.8
```

### Старт проекта
```
В Idea нажать New Project
Выбрать Maven - без архетипа
Название проекта:
my-first-at
Finish
```

### Работа с pom:
#### Подключение плагинов:
```xml
<!--Для работы с кириллицей-->
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>

<!--https://www.apache-maven.ru/plugins/maven-compiler-plugin.html-->
<!--Явно указывает компилятору что используем java 8-->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
            </configuration>
        </plugin>

<!--Запуск локального веб сервера для просмотра отчета allure-->
<!--https://github.com/allure-framework/allure-maven-->
<plugin>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-maven</artifactId>
    <version>2.10.0</version>
    <configuration>
        <reportVersion>2.13.0</reportVersion>
    </configuration>
</plugin>

<!--Запуск тестов -->
<!--https://www.apache-maven.ru/plugins/maven-surefire-plugin.html-->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.22.1</version>
    <configuration>
        <!--true - Статус билда не зависит от прохождения тестов-->
        <testFailureIgnore>true</testFailureIgnore>
        <includes>
            <include>**/*Test*.java</include>
        </includes>
        <!--Аргументы которые будут добавлены при запуске тестов-->
        <argLine>
            -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/1.9.1/aspectjweaver-1.9.1.jar"
        </argLine>
    </configuration>
    <!-- Aгент, который слушает события Allure и собирает информацию для отчета-->
    <dependencies>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.1</version>
        </dependency>
    </dependencies>
</plugin>
```

#### Подключение библиотек
```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <!--WEB ТЕСТИРОВАНИЕ-->
    <selenide.version>5.7.0</selenide.version>
    <!--ОТЧЕТ ALLURE-->
    <allure.version>2.13.0</allure.version>
    <!--LOMBOK-->
    <lombok.version>1.18.10</lombok.version>
    <!--ТЕСТ РАННЕР-->
    <junit-jupiter.version>5.6.0</junit-jupiter.version>
    <junit-platform.version>1.6.0</junit-platform.version>
</properties>

<dependencies>
    <!--WEB ТЕСТИРОВАНИЕ-->
    <dependency>
        <groupId>com.codeborne</groupId>
        <artifactId>selenide</artifactId>
        <version>${selenide.version}</version>
    </dependency>
    <!--ОТЧЕТ ALLURE-->
    <dependency>
        <groupId>io.qameta.allure</groupId>
        <artifactId>allure-selenide</artifactId>
        <version>${allure.version}</version>
    </dependency>
    <dependency>
        <groupId>io.qameta.allure</groupId>
        <artifactId>allure-junit5</artifactId>
        <version>${allure.version}</version>
    </dependency>
    <!--LOMBOK-->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>${lombok.version}</version>
    </dependency>
    <!--ТЕСТ РАННЕР-->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>${junit-jupiter.version}</version>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>${junit-jupiter.version}</version>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-params</artifactId>
        <version>${junit-jupiter.version}</version>
    </dependency>
    <dependency>
        <groupId>org.junit.vintage</groupId>
        <artifactId>junit-vintage-engine</artifactId>
        <version>${junit-jupiter.version}</version>
    </dependency>
    <dependency>
        <groupId>org.junit.platform</groupId>
        <artifactId>junit-platform-engine</artifactId>
        <version>${junit-platform.version}</version>
    </dependency>
    <dependency>
        <groupId>org.junit.platform</groupId>
        <artifactId>junit-platform-launcher</artifactId>
        <version>${junit-platform.version}</version>
    </dependency>
</dependencies>
```

## Написание автотеста
##### Тестируемый сайт: https://geekbrains.ru/events

#### Создание корневого пакета
Пакеты необходимы для размещения файлов по папкам, решения проблемы коллизии имен и для разделения доступа классов друг к другу. Именно благодаря пакетам мы можем через точку . получать доступ к нужным классам различных библиотек.
```
ru.geekbrains.my.first.at
```

#### Создание первого класса в test
```
FirstTest
```
#### Создание пакетов для хранения разных подходов
Создать пакеты 
```
example1 example2 example3
```
#### Создание первого класса в test
Шаги теста:
```
1.Зайти на сайт https://geekbrains.ru/courses
2.Нажать на кнопку Курсы
3.Проверить что страница Курсы открылась
4.Повторить для
     1.Курсы
     2.Вебинары
     3.Форум
     4.Блог
     5.Тесты
     6.Карьера
```

#### Как искать элемент на странице

XPath
```
Xpath - язык запросов к элементам по DOM. 

Основой HTML-документа являются теги.
В соответствии с «Document Object Model», коротко DOM, каждый HTML-тег является объектом. Вложенные теги являются «детьми» родительского элемента. Текст, который находится внутри тега, также является объектом.

```
CSS
```
CSS (Cascading Style Sheets) — язык таблиц стилей, который позволяет прикреплять стиль (например, шрифты и цвет) к структурированным документам (например, документам HTML и приложениям XML).

В CSS есть паттерны, согласно которым стили, создаваемые разработчиком, применяются к элементам страницы (DOM). Эти паттерны называются локаторы (selectors). Selenium WebDriver использует тот же принцип для нахождения элементов. И он намного быстрее, чем поиск элементов на основе XPath.
```

#### Первый тест "Простой пример"

```java
public class NavigationTest {

    @Test
    public void checkNavigation(){
        Selenide.open("https://geekbrains.ru/courses");
        Selenide.$("[class=\"gb-left-menu__nav\"] [href=\"/courses\"]").click();
        Selenide.$("[class=\"gb-header__title\"]").shouldHave(Condition.exactText("Курсы"));

        Selenide.$("[class=\"gb-left-menu__nav\"] [href=\"/events\"]").click();
        Selenide.$("[class=\"gb-header__title\"]").shouldHave(Condition.exactText("Вебинары"));

        Selenide.$("[class=\"gb-left-menu__nav\"] [href=\"/topics\"]").click();
        Selenide.$("[class=\"gb-header__title\"]").shouldHave(Condition.exactText("Форум"));

        Selenide.$("[class=\"gb-left-menu__nav\"] [href=\"/posts\"]").click();
        Selenide.$("[class=\"gb-header__title\"]").shouldHave(Condition.exactText("Блог"));

        Selenide.$("[class=\"gb-left-menu__nav\"] [href=\"/tests\"]").click();
        Selenide.$("[class=\"gb-header__title\"]").shouldHave(Condition.exactText("Тесты"));

        Selenide.$("[class=\"gb-left-menu__nav\"] [href=\"/career\"]").click();
        Selenide.$("[class=\"gb-header__title\"]").shouldHave(Condition.exactText("Карьера"));
    }
}
```

#### Разбиваем на тесты

```java
public class NavigationTest {

    @BeforeEach
    public void beforeEach(){
        Selenide.open("https://geekbrains.ru/courses");
    }

    @Test
    public void courses(){
        Selenide.$("[class=\"gb-left-menu__nav\"] [href=\"/courses\"]").click();
        Selenide.$("[class=\"gb-header__title\"]").shouldHave(Condition.exactText("Курсы"));
    }

    @Test
    public void topics(){
        Selenide.$("[class=\"gb-left-menu__nav\"] [href=\"/topics\"]").click();
        Selenide.$("[class=\"gb-header__title\"]").shouldHave(Condition.exactText("Форум"));
    }

    @Test
    public void events(){
        Selenide.$("[class=\"gb-left-menu__nav\"] [hrPaef=\"/events\"]").click();
        Selenide.$("[class=\"gb-header__title\"]").shouldHave(Condition.exactText("Вебинары"));
    }

    @Test
    public void posts(){
        Selenide.$("[class=\"gb-left-menu__nav\"] [href=\"/posts\"]").click();
        Selenide.$("[class=\"gb-header__title\"]").shouldHave(Condition.exactText("Блог"));
    }

    @Test
    public void tests(){
        Selenide.$("[class=\"gb-left-menu__nav\"] [href=\"/tests\"]").click();
        Selenide.$("[class=\"gb-header__title\"]").shouldHave(Condition.exactText("Тесты"));
    }

    @Test
    public void career(){
        Selenide.$("[class=\"gb-left-menu__nav\"] [href=\"/career\"]").click();
        Selenide.$("[class=\"gb-header__title\"]").shouldHave(Condition.exactText("Карьера"));
    }

}
```

#### Allure
allure.properties
```
allure.results.directory=target/allure-results
```

#### Распаралеливаем тесты
```
http://antkorwin.com/junit5/junit5_parallel_execution.html
```


### Запуск тестов на удаленном сервере Selenoid
```
mvn clean test -Dselenide.browser=chrome -Dselenide.remote=http://192.168.31.48:4444/wd/hub
```

### Удаленный запуск тестов
Проверим что Jenkins скачивает проект из git

Создать Jenkinsfile
```groovy
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
               echo 'This is a minimal pipeline.'
            }
        }
    }
}
```
Как выглядит нужный пример
```groovy
pipeline {
    agent any
    tools {
        maven 'maven'
        allure 'allure'
    }
    stages {
        stage('clone repository') {
            steps {
                deleteDir()
                git branch: 'master', credentialsId: 'gitlab_new', url: 'https://github.com/Antonppavlov/my-first-at.git'
            }
        }
        stage('run tests') {
            steps {
                sh "mvn test -Dselenide.browser=chrome -Dselenide.remote=http://192.168.31.48:4444/wd/hub"
            }
        }
        stage('generate allure report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }
}
```
