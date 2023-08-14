#!/usr/bin/env groovy

def call (){
    echo "Building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'dockr-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t taqiyeddinedj/my-repo:jma-2.0 .'
        sh " echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push taqiyeddinedj/my-repo:jma-2.0'
    }
}
