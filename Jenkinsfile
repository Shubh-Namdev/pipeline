pipeline{
    agent any

    environment {
        DOCKER_IMAGE = "shubhamnamdev01/spring-pipeline:${env.BUILD_NUMBER}"
    }

    tools {
        gradle 'Gradle 8.14.2'
        jdk 'JDK21'
    }


    stages{
        stage("Checkout"){
            steps {
                git branch: 'main', url: 'https://github.com/Shubh-Namdev/pipeline.git'
            }
            post{
                success{
                    echo 'successfully fetched the repo'
                }
                failure{
                    echo 'failed to fetch the repository'
                }
            }
        }

        stage("Build"){
            steps {
                sh '''
                  chmod +x gradlew
                  ./gradlew clean build
                '''
            }
            post{
                success{
                    echo 'build successfull'
                }
                failure{
                    echo 'build failed'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }

        stage('Push Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-reg-cred', usernameVariable: 'REG_USER', passwordVariable: 'REG_PASS')]) {
                    sh 'echo $REG_PASS | docker login -u $REG_USER --password-stdin'
                    sh "docker push ${DOCKER_IMAGE}"
                }
            }
        }

        stage('Deploy to Minikube') {
            steps {
                script {
                    // Use local kubeconfig for Minikube
                    sh 'export KUBECONFIG=$HOME/.kube/config'
                    
                    // Pull image from Docker Hub by updating deployment
                    sh "kubectl set image deployment/springboot-demo springboot-demo=${DOCKER_IMAGE} -n demo || kubectl apply -f k8s/ -n demo"
                }
            }
        }
    }
    post{
        always{
            echo "========always========"
        }
        success{
            echo "========pipeline executed successfully ========"
        }
        failure{
            echo "========pipeline execution failed========"
        }
    }
}