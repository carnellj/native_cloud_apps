#Introduction
All of the code examples in this book will include maven files for building docker images of the services being built.  As we progress through the book we will be leveraging more and more infrastructure as we build our services out.   These additional pieces of infrastructure will also be docker containers.  


#Software Needed
All of the codes instances have been built and compiled on a Mac running OS X.  We leverage Kitematic to build docker images.  Kitematics will install docker, docker-machine and docker-compose.  Docker is the core-runtime for docker containers.  Docker-machine providers a virtual-machine instance that the docker containers will run, while docker-compose providers orchestration capabilities for  starting and stopping groups of docker-machines.  Download [Kitematic](https://kitematic.com/) and follow the instructions for installing the software.

We are going to build and start all of our machines from the command-line.  So once you start kitematic you should can open a command line window by pressing the command-line CLI button on left hand of the screen.

#Structure of the Chapters
Each chapter in the book is broken down into its own github repository and is designed to be completely indepenent of each other.  As we progress through the individual chapters you will
find some of the same projects being defined over and over again.  This was intential to ensure that the code in each chapter could stand on its.  Here are the links to the individual
github repos.

[Chapter 1 Introduction](https://github.com/carnellj/spmia-chapter1)

[Chapter 2 Introduction to Microservices](https://github.com/carnellj/spmia-chapter2)

[Chapter 3 Spring Cloud Configuration](https://github.com/carnellj/spmia-chapter3)

[Chapter 4 Spring Service Discovery](https://github.com/carnellj/spmia-chapter4)

[Chapter 5 Spring Cloud Client Side Resiliency](https://github.com/carnellj/spmia-chapter5)

[Chapter 6 Spring Cloud Routing](https://github.com/carnellj/spmia-chapter6)

[Chapter 7 Securing your microservices](https://github.com/carnellj/spmia-chapter7)

[Chapter 8 Event-driven architecture with Spring Cloud Stream](https://github.com/carnellj/spmia-chapter8)

[Chapter 9 Distributed tracing with Spring Cloud Sleuth and Zipkin](https://github.com/carnellj/spmia-chapter9)

[Chapter 10 Deployment pipelines](https://github.com/carnellj/spmia-chapter10)
