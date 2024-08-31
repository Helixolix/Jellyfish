@echo off
setlocal

set "jarpath="C:\Program Files (x86)\JFL\DerlaryLenguage-1.0-SNAPSHOT.jar"

set "jflpath=%~1"

java -jar "%jarpath%" "%jflpath%"

pause
