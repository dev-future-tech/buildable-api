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
        stage('Build and unit test') {
            steps {
                container('java') {
                    sh './mvnw clean verify'
                }
            }
        }
        stage('Package project') {
            steps {
                container('java') {
                    sh './mvnw -DskipTests=true package'
                }
            }
        }
    }
}