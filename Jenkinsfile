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
                sh "docker run -dit --name testtest --network selenoid -p 8088:80 -v /var/lib/jenkins/workspace/SecondAt/src/main/resources/:/usr/local/apache2/htdocs/ httpd:2.4"
            }
        }
        stage('run tests') {
            steps {
                sh "mvn test -Dselenide.browser=chrome -Dselenide.remote=http://localhost:4444/wd/hub"
            }
        }
//         stage('stop docker'){
//             steps{
// //                 sh "docker stop testtest"
// //                 sh "docker rm testtest"
// //                 sh "docker rmi httpd:2.4"
//             }
//         }
        stage('generate allure report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }
}