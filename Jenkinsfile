pipeline {
    agent any

    stages {
        stage('Hello') {
            steps {
                echo 'Hello World'
            }
        }
        stage("Checkout") {
            steps {
                checkout scm
            }
        }
        stage("Docker Build") {
            steps {
                sh './gradlew clean build'
            }
        }
    }
}
