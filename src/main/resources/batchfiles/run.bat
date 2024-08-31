@echo off
setlocal

if "%~1"=="" (
    echo Specify the path to the .jar file.
    echo Usage: run "C:\path\to\your\program.jar" "C:\path\to\your\file.jfl"
    goto :eof
)

if "%~2"=="" (
    echo Specify the path to the .jfl file.
    echo Usage: run "C:\path\to\your\program.jar" "C:\path\to\your\file.jfl"
    goto :eof
)

set "jarpath=%~1"
set "jflpath=%~2"

if exist "%jarpath%" (
    java -jar "%jarpath%" "%jflpath%"
) else (
    echo The specified JAR file was not found: %jarpath%
)

endlocal
pause
