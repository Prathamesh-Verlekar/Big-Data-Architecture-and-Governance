create database Business_Metadata;

use Business_Metadata;


drop table if exists Domain;
create table Domain (
DomainId INT PRIMARY KEY IDENTITY(1,1),
Description varchar(255) NOT NULL DEFAULT ''
);


drop table if exists BusinessTerms;
create table BusinessTerms (
BusTermId INT PRIMARY KEY IDENTITY(100,1),
Business_Term varchar(255) NOT NULL DEFAULT '',
Term_Description varchar(255) NOT NULL DEFAULT ''
);

drop table if exists DomainTermsRelationShip;
create table DomainTermsRelationShip (
DomainId INT NOT NULL,
BusTermId INT NOT NULL,
PRIMARY KEY(DomainId, BusTermId)
);



select * from dbo.Domain;
select * from DomainTermsRelationShip;
select * from BusinessTerms;