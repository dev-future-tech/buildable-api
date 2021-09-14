@Library('welcome-java') _

import org.tw.build.java.PodTemplates
buildTemplates = new PodTemplates()

buildTempaltes.javaTemplate (
    node(POD_LABEL) {
        stage('Run maven version') {
            git url: 'https://github.com/dev-future-tech/buildable-api.git', branch: 'main'
            container ('java') {
                stage('Get Maven version') {
                    sh 'pwd'
                    sh 'ls -l'
                    // sh './mvnw -version'
                }
                stage('Build project') {
                    sh './mvnw clean package'
                }
            }
            container('terraform') {
                stage('get terraform version') {
                    sh 'terraform version'
                }
            }
        }
    }
)

/*
podTemplate (
    containers: [
        containerTemplate(name: 'java', image: 'openjdk:11-oracle', command: 'sleep', args: '99d'),
        containerTemplate(name: 'terraform', image: 'hashicorp/terraform:1.0.6', command: 'sleep', args: '99d')
    ]) {
}
*/