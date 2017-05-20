#!/bin/bash

cp ./biscuit.jar /usr/local/bin/biscuit.jar
touch /usr/local/bin/biscuit
chmod +x /usr/local/bin/biscuit
echo 'java -jar /usr/local/bin/biscuit.jar' > /usr/local/bin/biscuit

