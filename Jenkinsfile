podTemplate (
    containers: [
        containerTemplate(name: 'java', image: 'openjdk:11-oracle', command: 'sleep', args: '99d'),
        containerTemplate(name: 'maven', image: 'maven:3.8.1-jdk-8', command: 'sleep', args: '99d'),
        containerTemplate(name: 'terraform', image: 'hashicorp/terraform:1.0.6', command: 'sleep', args: '99d')
    ]) {
    node(POD_LABEL) {
        stage('Run maven version') {
            container ('java') {
                stage('Get Maven version') {
                    sh './mvnw -version'
                }
                stage('Build project') {
                    sh './mvnw clean install'
                }
            }
        }

        stage('Run infra') {
            container('terraform') {
                stage('get terraform version') {
                    sh 'terraform version'
                }
            }
        }
    }
}