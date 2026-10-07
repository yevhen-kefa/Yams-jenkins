pipeline{
    agent any

    stages {
        stage('Dev / Test'){
            steps {
                echo 'Compilation et lancement des tests.....'
                sh 'mvn clean test'
            }
        }

        stage('Pre-prod (Analysis)'){
            steps {
                echo 'Start instrument pour analyse le code....'
                sh 'mvn test'
            }
        }

        stage('Prod'){
            steps{
                echo 'Cleen et regeneration file finale'
                sh 'mvn clean package -DskipTests'

                echo 'Instalation programe dans envirenement production'
                sh 'mkdir -p ~/yams-prod'
                sh 'cp target/*.jar ~/yams-prod'
            }
        }
    }
}