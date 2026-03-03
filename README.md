# 🏥 Clinic – Aplikacja Java

Aplikacja do zarządzania kliniką napisana w Java.

- Branch `master` – wersja podstawowa aplikacji.
- Branch `swing` – wersja desktopowa z graficznym interfejsem użytkownika (Swing).


---

## 📌 Technologie

- Java
- Maven
- JDBC (baza danych)

---

## 📦 Budowanie projektu

Aby zbudować projekt, wykonaj w katalogu głównym:

```
mvn clean package
```

Po poprawnym zbudowaniu plik `.jar` zostanie wygenerowany w:

```
target/clinic-1.0-SNAPSHOT.jar
```

---

## ⚙️ Konfiguracja bazy danych

Konfiguracja połączenia z bazą danych znajduje się w :

```
resources/application.properties
```

W tem pliku ustaw:

- URL bazy danych -> db.url
- użytkownika -> db.user
- hasło -> db.password

- struktura tabel 
 ```
\resources\schema.sql
 ```

---

## ▶️ Uruchamianie aplikacji

### 🔹 Uruchomienie ręczne

Z folderu projektu:

```
java -jar target/clinic-1.0-SNAPSHOT.jar
```

Lub podając pełną ścieżkę:

```
java -jar E:\sciezka\do\jar\clinic-1.0-SNAPSHOT.jar
```

---

## 🚀 Automatyczne budowanie i uruchamianie (Windows)

Plik `build.bat` automatyzuje proces budowania i uruchamiania aplikacji.

### 📄 build.bat

```bat
@echo off
setlocal

echo Przejscie do folderu projektu...
cd /d E:{sciezka}\clinic

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
```

---

## 📁 Wymagania

- Java 8+
- Maven
- Skonfigurowana baza danych
