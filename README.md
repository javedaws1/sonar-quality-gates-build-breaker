# SonarQube 💥 Build Breaker [![Build Status](https://travis-ci.org/daggerok/sonar-quality-gates-build-breaker.svg?branch=master)](https://travis-ci.org/daggerok/sonar-quality-gates-build-breaker) [ ![Download](https://api.bintray.com/packages/daggerok/maven/sonar-quality-gates-build-breaker/images/download.svg) ](https://bintray.com/daggerok/maven/sonar-quality-gates-build-breaker/_latestVersion)
SonarQube Build Breaker designed to fail SonarQube analysis during CI pipeline build if quality grates not passed

_Easy as 1-2-3_

## Maven plugin quick start

1) add `sonar-breaker-maven-plugin` in your project _pom.xml_ file:
   ```xml
   <project>
       <build>
           <plugins>
               <plugin>
                   <groupId>com.github.daggerok.sonar</groupId>
                   <artifactId>sonar-breaker-maven-plugin</artifactId>
                   <version>${project.version}</version>
                   <!--
                   <configuration>
                       <delay>1</delay>
                       <retry>100</retry>
                       <standalone>false</standalone>
                       <allowFailure>false</allowFailure>
                       <projectBaseDir>${maven.multiModuleProjectDirectory}</projectBaseDir>
                       <metadataFilePath>${maven.multiModuleProjectDirectory}/target/sonar/report-task.txt</metadataFilePath>
                       <metricsExcludes>new_coverage</metricsExcludes>
                   </configuration>
                   -->
               </plugin>
           </plugins>
       </build>
   </project>
   ```
 
   see [sonar-breaker-maven-plugin pom.xml] file

2) NOTE: Do not forget setup `sonar-maven-plugin` as well, `sonar-breaker` will be use analysis metadata generated by
   `sonar:sonar` goal execution

3) build project, exec sonar analysis (goal: `sonar:sonar`) and finally exec `sonar-breaker:analyze` goal:
   ```bash
   mvn clean install sonar:sonar sonar-breaker:analyze 
   ```

## Sonar Breaker standalone quick start

- build project and examin sonar analysis:
  ```bash
  mvn install sonar:sonar 
  ```

- download latest
  [sonar-breaker](http://repo1.maven.org/maven2/com/github/daggerok/sonar/sonar-breaker/0.1.5/sonar-breaker-0.1.5.jar)
  executable jar file.
  ```bash
  wget -O sb.jar http://repo1.maven.org/maven2/com/github/daggerok/sonar/sonar-breaker/0.1.5/sonar-breaker-0.1.5.jar
  java -jar sb.jar target/sonar/report-task.txt
  ```

See [examples] and enjoy your bugs free code! :+1: 

<!--

## Developer Guide (TODO somehow)

TODO: https://daggerok.github.io/sonar-quality-gates-build-breaker/

**NOTE** this project has been based on:

* [daggerok/main-starter](https://github.com/daggerok/main-starter/tree/maven-java)
* [daggerok/sonarqube-maven-poc](https://github.com/daggerok/sonarqube-maven-poc)
* [daggerok/publish-maven-project-to-jcenter](https://github.com/daggerok/publish-maven-project-to-jcenter)
* [daggerok/deventstore](https://github.com/daggerok/deventstore)

-->

[examples]: examples/
[sonar-breaker-maven-plugin pom.xml]: examples/sonar-breaker-maven-plugin-demo/pom.xml
