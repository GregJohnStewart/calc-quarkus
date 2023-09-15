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
                sleep(300)
                sh './gradlew clean build --debug'
            }
        }
    }
}
