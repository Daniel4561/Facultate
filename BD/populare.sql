insert into joburi values('boss', 'Managre-General', 35000, 50000);
insert into joburi values('mng_pct', 'Manager-Proiectare', 25000, 35000);
insert into joburi values('mng_oft', 'Managre-Ofertare', 20000, 25000);
insert into joburi values('mng_cnt', 'Managre-Constructori', 18000, 22000);


insert into joburi values('pct', 'Proiectant', 20000, 23000);
insert into joburi values('oft', 'Ofertare', 15000, 19000);
insert into joburi values('zdr', 'Zidar', 2500, 5000);
insert into joburi values('zgr', 'Zugrav', 2900, 5500);
insert into joburi values('elc', 'Electrician', 4500, 7000);
insert into joburi values('trs', 'Transporport', 3200, 3700);


insert into departamente(nume_departament) values('Sediu_Firma');
insert into departamente(nume_departament) values('Dept_Proiectare');
insert into departamente(nume_departament) values('Dept_Ofertare');
insert into departamente(nume_departament) values('Dept_Constructii');



insert into locatii(oras, adresa, cod_postal, id_departament) values('Bucuresti', 'Unirii', 555432, 1);
insert into locatii(oras, adresa, cod_postal, id_departament) values('Iasi', 'Tudor Vladimirescu', 702554, 2);
insert into locatii(oras, adresa, cod_postal, id_departament) values('Botosani', 'Mihai Eminescu', 604345, 3);
insert into locatii(oras, adresa, cod_postal, id_departament) values('Vaslui', 'Elena Doamna', 345672, 4);




insert into angajat(nume, prenume, data_angajarii, salariu, nr_telefon, id_job, id_departament) 
values('Ionescu', 'Alexandru', to_date('20.12.2001', 'dd.mm.yyyy'), 45000, 0671231231, 'boss', 1);

insert into angajat(nume, prenume, data_angajarii, salariu, nr_telefon, id_manager, id_job, id_departament) 
values('Popescu', 'Razvan', to_date('20.12.2002', 'dd.mm.yyyy'), 30000, 0671231232, 1, 'mng_pct', 2);

insert into angajat(nume, prenume, data_angajarii, salariu, nr_telefon, id_manager, id_job, id_departament) 
values('Arhip', 'Andrei', to_date('10.11.2002', 'dd.mm.yyyy'), 22000, 0671231222, 1, 'mng_oft', 3);

insert into angajat(nume, prenume, data_angajarii, salariu, nr_telefon, id_manager, id_job, id_departament) 
values('Budaca', 'Marin', to_date('30.1.2003', 'dd.mm.yyyy'), 20000, 0671239222, 1, 'mng_cnt', 4);

insert into angajat(nume, prenume, data_angajarii, salariu, nr_telefon, id_manager, id_job, id_departament) 
values('Pintilie', 'Dorel', to_date('15.11.2005', 'dd.mm.yyyy'), 22000, 0671231292, 2, 'pct', 2);

insert into angajat(nume, prenume, data_angajarii, salariu, nr_telefon, id_manager, id_job, id_departament) 
values('Pintilie', 'Vasile', to_date('15.11.2005', 'dd.mm.yyyy'), 22000, 0671231295, 2, 'pct', 2);


insert into angajat(nume, prenume, data_angajarii, salariu, nr_telefon, id_manager, id_job, id_departament) 
values('Arhip', 'Vasile', to_date('12.07.2004', 'dd.mm.yyyy'), 18000, 0671431222, 3, 'oft', 3);

insert into angajat(nume, prenume, data_angajarii, salariu, nr_telefon, id_manager, id_job, id_departament) 
values('Filip', 'Ion', to_date('12.07.2005', 'dd.mm.yyyy'), 17000, 0671434222, 3, 'oft', 3);

insert into angajat(nume, prenume, data_angajarii, salariu, nr_telefon, id_manager, id_job, id_departament) 
values('Popescu', 'Dorel', to_date('15.03.2005', 'dd.mm.yyyy'), 4500, 0671433222, 4, 'zdr', 4);

insert into angajat(nume, prenume, data_angajarii, salariu, nr_telefon, id_manager, id_job, id_departament) 
values('Popescu', 'Costel', to_date('16.03.2005', 'dd.mm.yyyy'), 4400, 0671435222, 4, 'zdr', 4);

insert into angajat(nume, prenume, data_angajarii, salariu, nr_telefon, id_manager, id_job, id_departament) 
values('Ionescu', 'Vasile', to_date('15.03.2004', 'dd.mm.yyyy'), 5400, 0671433332, 4, 'zgr', 4);

insert into angajat(nume, prenume, data_angajarii, salariu, nr_telefon, id_manager, id_job, id_departament) 
values('Ionescu', 'Ion', to_date('16.03.2004', 'dd.mm.yyyy'), 5300, 0671435442, 4, 'zgr', 4);

insert into angajat(nume, prenume, data_angajarii, salariu, nr_telefon, id_manager, id_job, id_departament) 
values('Budescu', 'Andrei', to_date('16.03.2008', 'dd.mm.yyyy'), 6900, 0671435556, 4, 'elc', 4);

insert into angajat(nume, prenume, data_angajarii, salariu, nr_telefon, id_manager, id_job, id_departament) 
values('Pintilie', 'Razvan', to_date('16.06.2021', 'dd.mm.yyyy'), 3600, 0671432334, 4, 'trs', 4);



