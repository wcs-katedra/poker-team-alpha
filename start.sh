#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd $DIR

mvn clean tomcat7:run 2>&1 &
echo $! > .pid
wait $!
