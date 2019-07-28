CREATE TABLE Adresse
(
AdressID INTEGER IDENTITY(1,1),
PLZ CHAR(6),
Ort CHAR(50),
Strasse CHAR(50),

CONSTRAINT conPK_Adresse PRIMARY KEY(AdressID)
);

CREATE TABLE Zulieferer 
(
ZuliefererID INTEGER IDENTITY(1,1),
Name CHAR(50),
Waehrung CHAR(1),
TelNR CHAR(10),
AdressID INTEGER,

CONSTRAINT conPK_Zulieferer PRIMARY KEY(ZuliefererID),
CONSTRAINT conFK_Zulieferer_AdressID FOREIGN KEY(AdressID) REFERENCES Adresse(AdressID)
);

CREATE TABLE Mitarbeiter
(
MitarbeiterID INTEGER IDENTITY(1,1),
FaName CHAR(50),
VoName CHAR(50),
AdressID INTEGER,
CONSTRAINT conPK_Mitarbeiter PRIMARY KEY(MitarbeiterID),
CONSTRAINT conFK_Mitarbeiter_AdressID FOREIGN KEY(AdressID) REFERENCES Adresse(AdressID)
);

CREATE TABLE Getraenk
(
GetraenkeID INTEGER IDENTITY(1,1),
Name CHAR(50),
Preis DECIMAL(10,2),
Einkaufspreis DECIMAL(10,2),
Getraenkeart CHAR(50),
ZuliefererID INTEGER,

CONSTRAINT conPK_Getraenk PRIMARY KEY(GetraenkeID),
CONSTRAINT conFK_Getraenk_ZuliefererID FOREIGN KEY(ZuliefererID) REFERENCES Zulieferer(ZuliefererID)

);

CREATE TABLE Verkauf
(
VerkaufsID INTEGER IDENTITY(1,1),
Kasse CHAR(50),
Datum DATE,
MitarbeiterID INTEGER,

CONSTRAINT conPK_Verkauf PRIMARY KEY(VerkaufsID),
CONSTRAINT conFK_Verkauf_MitarbeiterID FOREIGN KEY(MitarbeiterID) REFERENCES Mitarbeiter(MitarbeiterID)
);

CREATE TABLE Regal
(
RegalID INTEGER IDENTITY(1,1),
Standort CHAR(50),

CONSTRAINT conPK_Regal PRIMARY KEY(RegalID)
);

CREATE TABLE GetraenkeStandort
(
GetraenkeID INTEGER,
RegalID INTEGER,
CONSTRAINT conFK_GS_GetraenkeID FOREIGN KEY(GetraenkeID) REFERENCES Getraenk(GetraenkeID),
CONSTRAINT conFK_GS_RegalID FOREIGN KEy(RegalID) REFERENCES Regal(RegalID)
);

CREATE TABLE Bestellung
(
BestellungID INTEGER IDENTITY(1,1),
Datum DATE,
ZuliefererID INTEGER,

CONSTRAINT conPK_Bestellung PRIMARY KEY(BestellungID),
CONSTRAINT conFK_Bestellung_ZuliefererID FOREIGN KEY(ZuliefererID) REFERENCES Zulieferer(ZuliefererID)

);

CREATE TABLE BestellPosten
(
BestellungID INTEGER,
GetraenkeID INTEGER,
Menge INTEGER,

CONSTRAINT conFK_BP_BestellungID FOREIGN KEY(BestellungID) REFERENCES Bestellung(BestellungID),
CONSTRAINT conFK_BP_GetraenkeID FOREIGN KEY(GetraenkeID) REFERENCES Getraenk(GetraenkeID)
);

CREATE TABLE VerkaufsPosten
(
GetraenkeID INTEGER,
VerkaufsID INTEGER,

CONSTRAINT conFK_BestellPosten_GetraenkeID FOREIGN KEY(GetraenkeID) REFERENCES Getraenk(GetraenkeID),
CONSTRAINT conFK_VerkaufsPosten_VerkaufsID FOREIGN KEY(VerkaufsID) REFERENCES Verkauf(VerkaufsID)
);



