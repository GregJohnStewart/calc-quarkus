pipeline {
    agent {
        label 'java-builder'
    }

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
                //sleep(300)
                sh 'java --version'
                sh 'javac --version'
                sh './gradlew clean build'
            }
        }
    }
}
