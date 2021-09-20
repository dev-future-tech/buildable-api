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
            }
        }
        stage('Deploy application') {
            steps {
                container('terraform') {
                    sh 'terraform init -backend-config="access_key=minio" -backend-config="secret_key=miniostorage" -backend-config="bucket=terraform"'
                    sh 'terraform plan'
                    sh 'terraform apply'
                }
                container('java') {
                    sh 'java -version'
                }
            }
        }
    }
}