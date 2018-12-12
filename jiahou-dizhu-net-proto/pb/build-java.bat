@echo off&setlocal enabledelayedexpansion

set mulu=%cd%

echo creating mulu%

for /r %mulu% %%i in (./*.proto) do (

set name=%%~nxi

echo creating !name!

protoc.exe --java_out=D:/developer/cb/jh-dizhu/jiahou-dizhu-protobuf/src/main/java/./ !name!

)

echo created.

pause>nul

