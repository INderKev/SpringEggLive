create table AUTOR (
   ID      VARCHAR(36)         not null,
   NOMBRE   VARCHAR(30)         not null,
   constraint PK_ADMINISTRADOR primary key (ID)
);
create table EDITORIAL (
   ID      VARCHAR(36)         not null,
   NOMBRE   VARCHAR(30)         not null,
   constraint PK_EDITORIAL primary key (ID)
);
create table LIBRO (
   ISBN     numeric (13)         not null,
   TITULO   VARCHAR(30)         not null,
	EJEMPLARES INTEGER    not null,
	ALTA DATE	not null,
	AUTOR_ID VARCHAR(36) not null,
	EDITORIAL_ID      VARCHAR(36)     not null,
   constraint PK_LIBRO primary key (ISBN)	
);

alter table LIBRO
   add constraint FK_AUTOR foreign key (FKAUTOR)
      references AUTOR (ID)
 on delete restrict on update restrict;
	  
alter table LIBRO
   add constraint FK_EDITORIAL foreign key (FKEDITORIAL)
      references EDITORIAL (ID)
 on delete restrict on update restrict;
	 
select * from autor;
select * from editorial;
select * from LIBRO;

	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  