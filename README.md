# Watch2Gether API
[![Download](https://img.shields.io/github/v/release/c-eg/watch2getherapi)](https://github.com/c-eg/watch2getherapi/releases)
![GitHub License](https://img.shields.io/github/license/c-eg/watch2getherapi)

This library provides a Java-wrapper around the [JSON API](https://community.w2g.tv/t/watch2gether-api-documentation/133767) provided by
[Watch2Gether](https://w2g.tv/), which is a service to watch videos online with others.

## Setup
It's available via [Maven Central](https://central.sonatype.com/artifact/uk.co.conoregan/watch2getherapi). Just add it as dependency to your
project.

<details open>
<summary>Maven</summary>

```xml
<dependency>
    <groupId>uk.co.conoregan</groupId>
    <artifactId>watch2getherapi</artifactId>
    <version>{version}</version>
</dependency>
```
</details>

<details>
<summary>Gradle (Kotlin)</summary>

```kotlin
dependencies {
    implementation("uk.co.conoregan:watch2getherapi:{version}")
}
```
</details>

## Usage
To register for a Watch2Gether API key, click Tools / API from within your [profile page](https://w2g.tv/en/account/edit_user/).

With this you can instantiate `uk.co.conoregan.watch2getherapi.Watch2GetherApi`, which contains all the methods for making requests to the Watch2Gether API.
```java
Watch2GetherApi api = new Watch2GetherApi("<apikey>");
```

## Project Logging
This project uses [SLF4J](http://www.slf4j.org) to abstract the logging in the project. To use the logging in your own
project you should add one of the provided [adapter bindings](http://www.slf4j.org/manual.html).
