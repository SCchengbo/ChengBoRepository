@echo off&setlocal enabledelayedexpansion

set mulu=%cd%

echo ��ǰĿ¼%mulu%

for /r %mulu% %%i in (./*.proto) do (

set name=%%~nxi

echo ����!name!

protoc.exe --java_out=D:/developer/code/�ﻪ/com-version-protobuf/src/main/java/./ !name!
protoc.exe --java_out=D:/developer/code\demo/kypj-parent/kypj-xuechan-protobuf/src/main/java/./ !name!

)

echo ���.

pause>nul