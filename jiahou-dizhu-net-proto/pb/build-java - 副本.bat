@echo off&setlocal enabledelayedexpansion

set mulu=%cd%

echo 当前目录%mulu%

for /r %mulu% %%i in (./*.proto) do (

set name=%%~nxi

echo 生成!name!

protoc.exe --java_out=D:/developer/code/孙华/com-version-protobuf/src/main/java/./ !name!
protoc.exe --java_out=D:/developer/code\demo/kypj-parent/kypj-xuechan-protobuf/src/main/java/./ !name!

)

echo 完成.

pause>nul