# Spring Boot GAE Blank

# Springboot
# Spring 4 +
# Thymeleaf
# Controller
# REST controller
# Login with Google account
# Finding possible alpha-numeric combination of phone number having 7/10 digits - Local/Domestic US phone number.



Maven archetype to create a GAE-configured maven project for Spring Boot Application

**Note that this project is not optimized to GAE.**

## How to use

with Bash

    mvn archetype:generate\
     -DarchetypeGroupId=am.ik.archetype\
     -DarchetypeArtifactId=spring-boot-gae-blank-archetype\
     -DarchetypeVersion=1.0.4

with CommandPrompt (Windows)

    mvn archetype:generate^
     -DarchetypeGroupId=am.ik.archetype^
     -DarchetypeArtifactId=spring-boot-gae-blank-archetype^
     -DarchetypeVersion=1.0.4

As a default setting, `artifactId` is used as application-id.

### Example

#### Create a project

```
$ mvn archetype:generate -B\
 -DarchetypeGroupId=am.ik.archetype\
 -DarchetypeArtifactId=spring-boot-gae-blank-archetype\
 -DarchetypeVersion=1.0.4\
 -DgroupId=com.example\
 -DartifactId=spring-boot-demo\
 -Dversion=1.0.0-SNAPSHOT
$ cd spring-boot-demo
```

#### Run on dev server

    $ mvn appengine:devserver

Go to http://localhost:8080

#### Deploy

modify application name in src/main/webapp/WEB-INF/appengine-web.xml if needed

    $ mvm clean appengine:update

http://appengdemo-1230.appspot.com/

## License

Licensed under the Apache License, Version 2.0.
