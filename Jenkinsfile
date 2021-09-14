@Library('welcome-java') _

pipeline {
    agent {
        kubernetes {
            inheritFrom 'default'
        }
    }
    stages {
        stage('Demo') {
            steps {
                echo 'Starting build...'
                sayHello 'Anthony'
                welcomeMessage()
                echo agentPodYaml.getWithContainers('java', 'dotnet')
            }
        }

        stage('Test Pod Template') {
            steps {
                echo 'testing pod template'
            }
        }
    }
}