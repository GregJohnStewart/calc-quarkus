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


//
//
//
//pipeline {
//    agent any
//    stages {
//        stage('Checkout') {
//            steps {
//                repository checkout(
////                        [
////                                $class                           : 'GitSCM', branches: [[name: 'main']],
////                                doGenerateSubmoduleConfigurations: false,
////                                extensions                       : [[$class: 'CleanCheckout']],
////                                submoduleCfg                     : []
////                        ]
//                )
//            }
//        } stage('Build') {
//            steps {
//                sh './gradlew clean build'
//            }
//        }
//    } post {
//        success {} failure {}
//    }
//}
