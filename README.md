#Introduction
All of the code examples in this book will include maven files for building docker images of the services being built.  As we progress through the book we will be leveraging more and more infrastructure as we build our services out.   These additional pieces of infrastructure will also be docker containers.  


#Software Needed
All of the codes instances have been built and compiled on a Mac running OS X.  We leverage Kitematic to build docker images.  Kitematics will install docker, docker-machine and docker-compose.  Docker is the core-runtime for docker containers.  Docker-machine providers a virtual-machine instance that the docker containers will run, while docker-compose providers orchestration capabilities for  starting and stopping groups of docker-machines.  Download [Kitematic](https://kitematic.com/) and follow the instructions for installing the software.

We are going to build and start all of our machines from the command-line.  So once you start kitematic you should can open a command line window by pressing the command-line CLI button on left hand of the screen.
