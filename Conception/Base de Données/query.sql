/* to select all for Necessiteux*/

select * from personne INNER JOIN Necessiteux WHERE personne.idPersonne = Necessiteux.idNecessiteux;

/* to select all for Doanteur*/

select * from personne INNER JOIN donateur WHERE personne.idPersonne = donateur.idDonateur;
select * from Personne inner join donateur on personne.idPersonne = Donateur.idDonateur;

/* to insert into personne*/

insert into personne(nom,prenom,sexe,numTel,dateNaiss,email) VALUES ('Chabane','youcef',0,'0795875779','1998-11-08','chabaneyoucef98@gmail.com');

/* to insert into necessiteux*/

insert into Necessiteux(idNecessiteux,sitFamiliale,paixMois) VALUES (2,'celebataire',27000);

/* to insert into necessiteux_admis */

insert into Necessiteux_Admis(idNecessiteuxAdmis, priorite, motdePasse) VALUES (2,'A','123456');


/* to select Necessiteux Profil*/
select	*	from	personne	INNER	JOIN	Necessiteux
								INNER JOIN Necessiteux_Admis
								WHERE personne.IdPersonne = Necessiteux.IdNecessiteux
                                AND Necessiteux.IdNecessiteux <> Necessiteux_Admis.IdNecessiteuxAdmis
                                AND Necessiteux.IdNecessiteux = 4;
						

/*select les donnateux qui ne sont pas des necessiteux*/

select * from personne inner join Donateur on personne.idPersonne = Donateur.idDonateur where Donateur.idDonateur NOT IN(select idNecessiteux from Necessiteux inner join Necessiteux_Admis on Necessiteux.idNecessiteux = Necessiteux_Admis.idNecessiteuxAdmis);

/* insert into table static Don*/

insert	into	Don(type,genre)	VALUES	('T-shirt','m'),('T-shirt','f'),('pantalon','m'),('pantalon','f'),
										('chaussure','m'),('chaussure','f');
 
 
/* insert into table donation*/

insert into Donation(idDonateur,idDon,idDepot,quantite,Date,etat) VALUES (1,1,1,15,'2019-07-25','att');

/* insert into Depot*/
insert into Depot(localisation) values ('constantine');

/*insert into table Chambre*/

insert into Chambre(idDepot,type,max) VALUES (1,'T-shirt',100);

/*select et afficher les donnes de donation*/
select * from donation inner join Donateur on Donation.idDonateur = Donateur.idDonateur
						inner join Personne on personne.idPersonne = Donateur.idDonateur
                        inner join Don on donation.idDon = Don.idDon
                        inner join Depot on donation.idDepot = Depot.idDepot;

 /*to show all donateur that are not Necessiteux*/
 
 select * from Personne INNER JOIN Donateur on Personne.idPersonne = Donateur.idDonateur WHERE Personne.email not in (SELECT	email FROM Personne INNER JOIN Necessiteux on Personne.idPersonne = Necessiteux.idNEcessiteux) AND Personne.email='chabaneyoucef98@gmail.com' AND Donateur.motdePass='123456';
 
 /*insert into emplacement*/
 insert into emplacement(idChambre,idDepot,idDonateur,idDon,typeA,etat,status) VALUES(1,1,1,7,'permanent','reserve',0);