create database Prove3D;

use Prove3D;

create table tbUsuario (
idUsuario INT primary key,
nome VARCHAR(120),
login VARCHAR(20),
senha VARCHAR(14),
email VARCHAR(100),
telefone CHAR(14),
cpf CHAR(14)
);

create table tbComputador (
idComputar INT primary key,
sistemaOperacional VARCHAR(50),
cpu VARCHAR(50),
disco VARCHAR(50),
memoria VARCHAR(50),
gpuIntegrada VARCHAR(50),
gpuCompartilhada VARCHAR(50),
fkUsuario INT,
foreign key(fkUsuario) references tbUsuario(idUsuario)  
);


create table tbMemoria(
idMemoria INT primary key,
emUso DECIMAL(2,1),
compactada INT,
confirmado DECIMAL(3,1),
cache DECIMAL(2,1),
disponivel DECIMAL(2,1),
paginada INT,
nPaginada INT,
dtHora DATETIME,
fkComputador INT,
foreign key(fkComputador) references tbComputador(idComputador)
);


create table tbCpu(
idCpu INT primary key,
ultilizacao INT,
velocidade DECIMAL(3,2),
threads INT,
tempAtividade TIME,
dtHora DATETIME,
fkComputador INT,
foreign key(fkComputador) references tbComputador(idComputador)
);

create table tbProcessos(
pid INT primary key,
processo VARCHAR(45),
tempoAtividade TIME,
prioridade INT,
dataHora DATETIME,
fkCpu INT,
foreign key(fkCpu) references tbCpu(idCpu)
);

create table tbDisco(
idDisco INT primary key,
vLeitura DECIMAL(4,2),
vGravacao DECIMAL(4,2),
tempAtividade INT,
tempResp DECIMAL(4,2),
dtHora DATETIME,
fkComputador INT,
foreign key(fkComputador) references tbComputador(idComputador)
);

create table tbGpuCompartilhada(
idCompartilhada INT primary key,
ultilizacao INT,
mGpuDedicada DECIMAL(2,1),
mGpuComp DECIMAL(2,1),
dtHora DATETIME,
fkComputador INT,
foreign key(fkComputador) references tbComputador(idComputador)
);

create table tbGpuIntegrada(
idIntegrada INT primary key,
memoria DECIMAL (2,1),
mGpuCompart DECIMAL(2,1),
ultiliza INT,
dtHora DATETIME,
fkComputador INT,
foreign key(fkComputador) references tbComputador(idComputador)
);
