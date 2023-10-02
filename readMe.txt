Параметры подключение к бд можно сменить в классе util/DatabaseConnector, после чего нужно будет пересобрать проект с помощью команды mvn package
Для запуска программы необходимо в корневой папке проекта, в терминале написать команду java -jar target/DataBaseReader--0.1.jar
далее ввести операцию (stat/search), после ввести путь для файла с тестовыми данными, и путь для файла в котором хотите получить результат
Пример:
java -jar target/DataBaseReader--0.1.jar stat src/main/resources/testStat.json src/main/resources/fileReport.json
Бэкап бд сделал в формате TAR.
