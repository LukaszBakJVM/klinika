Budowanie projektu -> mvn clean package

org.example.config.DbUtils -> konfiguracja bazy

java -jar E:\sciezka do jar \clinic-1.0-SNAPSHOT.jar

build.bat
########################################################################################


@echo off
setlocal

echo Przejscie do folderu projektu...
cd /d E:\{sciezka}\clinic

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


###############################################################################################
 
