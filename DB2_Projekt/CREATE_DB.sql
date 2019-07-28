CREATE DATABASE Getraenkeladen
  ON PRIMARY
  (
   NAME = Gladen_data,
   FILENAME = 'C:\Users\Eldiar Flügel\Desktop\Datenbanken II\DB2_Projekt\Gladen.mdf',
   SIZE = 10MB,
   MAXSIZE = 100MB,
   FILEGROWTH = 20%)
  LOG ON
  (
   NAME = Gladen_log,
   FILENAME = 'C:\Users\Eldiar Flügel\Desktop\Datenbanken II\DB2_Projekt\Gladen.ldf',
   SIZE = 10MB,
   MAXSIZE = 100MB,
   FILEGROWTH = 20%)