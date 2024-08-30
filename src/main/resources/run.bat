@echo off
setlocal

if "%~1"=="" (
    echo Specify the path to the .dil file.
    echo Usage: run "C:\Users\filip\Documents\DerlaryLenguage-master\target\DerlaryLenguage-1.0-SNAPSHOT.jar" "example.dil"
    goto :eof
)

if "%~2"=="" (
    echo Specify the path to the .dil file.
    echo Usage: run "C:\Users\filip\Documents\DerlaryLenguage-master\target\DerlaryLenguage-1.0-SNAPSHOT.jar" "example.dil"
    goto :eof
)

set "jarpath=%~1"
set "dilpath=%~2"

if exist "%jarpath%" (
    if exist "%dilpath%" (
        java -jar "%jarpath%" "%dilpath%"
    ) else (
        echo The specified .dil file was not found: %dilpath%
    )
) else (
    echo The specified JAR file was not found: %jarpath%
)

endlocal
pause
