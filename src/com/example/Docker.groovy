#!/usr/bin/env groovy

package com.example
// implements Serializable: save the execution state in case the pipeline stopped or get paused
class Docker implements Serializable{

    def script

    Docker(script) {
        this.script = script
    }
    def buildDockerImage(String imageName) {
        script.echo "Building the docker image..."
        script.withCredentials([script.usernamePassword(credentialsId: 'dockr-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            script.sh "docker build -t $imageName ."
            script.sh " echo $script.PASS | docker login -u $script.USER --password-stdin"
            script.sh "docker push $imageName"
        }
    }
}