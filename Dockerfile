FROM amuthanqa/java8-3.6.3-testdep
COPY src  home/apiframework/src
COPY index.html  home/apiframework/index.html
COPY pom.xml	home/apiframework/pom.xml
COPY testng.xml	home/apiframework/testng.xml
WORKDIR home/apiframework
ENTRYPOINT mvn clean test