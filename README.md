# Insecure TLS

When you need your Java code to connect to a TLS server no matter what the certificate server's using. This is a 
SSLSocketFactory that trusts every certificate offered by the server.

**Use at your own risk.**

## Build

    ./mvnw package

## Usage

Add the built jar to classpath, and configure the Java runtime with the property `java.security.properties` with the
path to `insecure.properties` file. For instance:

    java -cp insecure-tls-0.0.1-SNAPSHOT.jar;... -Djava.security.properties=insecure.properties your.Main
