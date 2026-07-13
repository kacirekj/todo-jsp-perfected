#!/usr/bin/env bash

cd target
/opt/homebrew/Cellar/openjdk/26.0.1/bin/java -Xmx200m -jar todo-jsp.war
cd ..