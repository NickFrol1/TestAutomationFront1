pipeline {
    agent any
    tools {
        maven 'maven'
        allure 'allure'
    }
    stages {
        stage('clone repository') {
            steps {
//                 deleteDir()
//                 git branch: 'main', credentialsId: 'NickFrol1', url: 'https://github.com/NickFrol1/TestAutomationFront1.git'
                echo "Hello"
                sh "docker run -dit --name testtest -p 8088:80"
            }
        }
        stage('run tests') {
            steps {
                sh "mvn test -Dselenide.browser=chrome -Dselenide.remote=http://localhost:4444/wd/hub"
            }
        }
        stage('generate allure report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }
}