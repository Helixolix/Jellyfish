@echo off
setlocal

if "%~1"=="" (
    echo Specify the path to the JAR file.
    echo Usage: run "C:\Users\filip\Documents\DerlaryLenguage-master\target\DerlaryLenguage-1.0-SNAPSHOT.jar"
    goto :eof
)


set "jarpath=%~1"


if exist "%jarpath%" (
    java -jar "%jarpath%" %*
) else (
    echo The specified JAR file was not found: %jarpath%
)

endlocal
pause
