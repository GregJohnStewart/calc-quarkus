pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                // Check out code from the Git
                repository checkout([
                        $class                           : 'GitSCM', branches: [[name: 'main']],
                        // Specify your branch here
                        doGenerateSubmoduleConfigurations: false,
                        extensions                       : [[$class: 'CleanCheckout']],
                        submoduleCfg                     : [],
                        userRemoteConfigs                : [[url: 'https://github.com/yourusername/your-repo.git']]
                ])
            }
        } stage('Build') {
            steps {
                // Use the gradlew wrapper to build the Java application
                sh './gradlew clean build'
                // Adjust the Gradle tasks as needed
            }
        }
        // You can add more stages for deployment, testing, etc.
    } post {
        success {
            // Add post-build actions here, e.g., deploying to a server
        } failure {
            // Add actions to perform when the build fails
        }
    }
}
