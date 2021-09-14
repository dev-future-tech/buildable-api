@Library('welcome-java') _

pipeline {
    agent {
        kubernetes {
            inheritFrom "default"
        }
    }
    podTemplate(containers: [
        containerTemplate(name: 'maven', image: 'maven:3.8.1-jdk-8', command: 'sleep', args: '99d')
        ], volumes: [
        persistentVolumeClaim(mountPath: '/root/.m2/repository', claimName: 'maven-repo', readOnly: false)
        ]) {

        node(POD_LABEL) {
            stage('Build a Maven project') {
                git 'https://github.com/jenkinsci/kubernetes-plugin.git'
                container('maven') {
                    sh 'mvn -B -ntp clean package -DskipTests'
                }
            }
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
                echo "empty"
            }
        }
    }
}