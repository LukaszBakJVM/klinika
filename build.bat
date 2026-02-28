@echo off
setlocal

echo Przejscie do folderu projektu...
cd /d E:\projekty\clinic

echo Budowanie projektu...
call mvn clean package

if errorlevel 1 (
    echo BLAD BUDOWANIA
    pause
    exit /b 1
)

echo.
echo Uruchamianie aplikacji...
java -jar target\clinic-1.0-SNAPSHOT.jar

pause
endlocal