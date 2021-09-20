@Library('welcome-java') _

pipeline {
    agent {
        kubernetes {
            yaml '''
        apiVersion: v1
        kind: Pod
        metadata:
          labels:
            some-label: some-label-value
        spec:
          containers:
          - name: java
            image: openjdk:11-oracle
            command: ['sleep', '99d']
            tty: true
          - name: terraform
            image: hashicorp/terraform:1.0.6
            command: ['sleep', '99d']
            tty: true
        '''
        }
    }
    stages {
        stage('Build project') {
            steps {
                container('java') {
                    sh './mvnw clean package'
                }
                container('terraform') {
                    sh 'terraform -backend-config="access_key=jenkinsterraform" -backend-config="secret_key=buildinfra" -backend-config="bucket=terraform" init'
                    sh 'terraform plan'
                    sh 'terraform apply'
                }
            }
        }
        stage('Deploy application') {
            steps {
                container('java') {
                    sh 'java -version'
                }
            }
        }
    }
}