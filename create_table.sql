CREATE TABLE Usuario (
	Id serial unique,
	Email varchar(200) not null,
	Senha varchar(max) not null,
	DataCadastro date not null
);

CREATE TABLE Consumidor (
	Id serial unique,
	Nome varchar(100) not null,
	IdUsuario int not null references Usuario(Id)
);

CREATE TABLE Negocio (
	Id serial unique,
	Nome varchar(100) not null,
	Descricao varchar(300) not null,
	Latitude double precision not null,
	Longitude double precision not null,
	IdUsuario int not null references Usuario(Id)
);

CREATE TABLE Avaliacao (
	Id serial unique,
	Comentario varchar(300) not null,
	Nota int not null,
	IdUsuario int not null references Usuario(Id),
	IdVendedor int not null references Vendedor(Id)
);