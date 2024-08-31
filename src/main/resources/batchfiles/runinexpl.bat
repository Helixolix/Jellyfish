@echo off
setlocal

set "jarpath=C:\Users\filip\IdeaProjects\Jellyfish2\target\DerlaryLenguage-1.0-SNAPSHOT.jar"

set "jflpath=%~1"

java -jar "%jarpath%" "%jflpath%"

pause
