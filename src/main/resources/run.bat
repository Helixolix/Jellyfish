@echo off
setlocal

if "%~1"=="" (
    echo Specify the path to the .jfl file.
    echo Usage: jfl "example.jfl"
    goto :eof
)

set "jflpath=%~1"

if exist "%jflpath%" (
    if /i "%~x1"==".jfl" (
        java -jar "%~dp0jfl.jar" "%jflpath%"
    ) else (
        echo The specified file is not a .jfl file: %jflpath%
    )
) else (
    echo The specified .jfl file was not found: %jflpath%
)

endlocal
pause
