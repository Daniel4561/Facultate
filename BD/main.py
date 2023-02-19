import cx_Oracle
from tkinter import *
from tkinter import ttk
from tkinter import font

# initialize the connection object
conn = None

try:
    # create a connection object
    conn = cx_Oracle.connect("bd019", "bd019", "bd-dc.cs.tuiasi.ro:1539/orcl")
    print(conn.version)


except Exception as err:
    print('Error while connecting to db')
    print(err)

# Create an instance of Tk
window = Tk()
window.title("Aplicatie")
window.geometry("870x700")

# Create a tab control that manages multiple tabs
tabsystem = ttk.Notebook(window)

# Create new tabs using Frame widget
tabAngajat = Frame(tabsystem)
tabDepartamente = Frame(tabsystem)
tabJoburi = Frame(tabsystem)
tabLocatii = Frame(tabsystem)
tabCommit = Frame(tabsystem)

tabsystem.add(tabAngajat, text="Angajati")
tabsystem.add(tabDepartamente, text="Departamente")
tabsystem.add(tabJoburi, text="Joburi")
tabsystem.add(tabLocatii, text="Locatii")
tabsystem.add(tabCommit, text='Commit')

tabsystem.pack(expand=1, fill="both")

# Angajati
labelAngajati = Label(tabAngajat, text="Tabel Angajati")
labelAngajati.grid(column=1,
                   row=1,
                   padx=40,
                   pady=20)

angajati = ttk.Treeview(labelAngajati)
angajati.pack(padx=0, pady=5)

angajati['column'] = (
    'id_angajat', 'Nume', 'Prenume', 'Data_Angajarii', 'Salariu', 'Nr_telefon', 'id_manager', 'id_job',
    'id_departament')

angajati.column("#0", width=0, stretch=NO)
angajati.column("id_angajat", anchor=CENTER, width=80)
angajati.column("Nume", anchor=CENTER, width=90)
angajati.column("Prenume", anchor=CENTER, width=100)
angajati.column("Data_Angajarii", anchor=CENTER, width=100)
angajati.column("Salariu", anchor=CENTER, width=80)
angajati.column("Nr_telefon", anchor=CENTER, width=80)
angajati.column("id_manager", anchor=CENTER, width=80)
angajati.column("id_job", anchor=CENTER, width=80)
angajati.column("id_departament", anchor=CENTER, width=80)

angajati.heading("#0", text="", anchor=CENTER)
angajati.heading("id_angajat", text="id", anchor=CENTER)
angajati.heading("Nume", text="Nume", anchor=CENTER)
angajati.heading("Prenume", text="Prenume", anchor=CENTER)
angajati.heading("Data_Angajarii", text="Data_Angajarii", anchor=CENTER)
angajati.heading("Salariu", text="Salariu", anchor=CENTER)
angajati.heading("Nr_telefon", text="Nr_telefon", anchor=CENTER)
angajati.heading("id_manager", text="id_manager", anchor=CENTER)
angajati.heading("id_job", text="id_job", anchor=CENTER)
angajati.heading("id_departament", text="id_departament", anchor=CENTER)

labelSelectAngajat = Label(tabAngajat, text='SELECT * FROM ANGAJAT')
labelSelectAngajat.place(x=100, y=260)

entryCommandAng = Text(tabAngajat, bd=3, height=8, width=70)
entryCommandAng.place(x=100, y=277)


def selFromAng(text):
    for item in angajati.get_children():
        angajati.delete(item)

    cur = conn.cursor()
    command = 'select * from angajat ' + text + ''
    try:
        cur.execute(command)
    except Exception as error:
        errAngajat.delete(1.0, END)
        errAngajat.insert(1.0, str(error))
    for result in cur:
        angajati.insert(parent='', index='end', text='',
                        values=(result[0], result[1], result[2], result[3].date(),
                                result[4], '0' + result[5], result[6], result[7], result[8]))
    cur.close()


buttSelFromAng = Button(tabAngajat, text='Select',
                        command=lambda: [selFromAng(entryCommandAng.get(1.0, END)), entryCommandAng.delete(1.0, END)])
buttSelFromAng.place(x=700, y=330)

labelNume = Label(tabAngajat, text='Nume:')
labelNume.place(x=20, y=440)
entryNume = Entry(tabAngajat, bd=3, width=15)
entryNume.place(x=20, y=460)

labelPrenume = Label(tabAngajat, text='Prenume:')
labelPrenume.place(x=135, y=440)
entryPrenume = Entry(tabAngajat, bd=3, width=15)
entryPrenume.place(x=135, y=460)

labelSalariu = Label(tabAngajat, text='Salariu:')
labelSalariu.place(x=250, y=440)
entrySalariu = Entry(tabAngajat, bd=3, width=10)
entrySalariu.place(x=250, y=460)

labelNrTel = Label(tabAngajat, text='Nr_Telefon:')
labelNrTel.place(x=335, y=440)
entryNrTel = Entry(tabAngajat, bd=3, width=12)
entryNrTel.place(x=335, y=460)

cur = conn.cursor()
command = 'select id_angajat from angajat ' \
          'where id_job = \'boss\' ' \
          'or id_job like \'mng%\''
cur.execute(command)
idList = [x[0] for x in cur]
variable3 = StringVar(tabAngajat, idList[0])
labelIdManager = Label(tabAngajat, text='Id_Manager:')
labelIdManager.place(x=435, y=440)
entryIdManager = OptionMenu(tabAngajat, variable3, idList[0], *(idList[1:]))
entryIdManager.place(x=435, y=460)

cur = conn.cursor()
command = 'select id_job from joburi'
cur.execute(command)
idList = [x[0] for x in cur]
variable1 = StringVar(tabAngajat, idList[0])
labelIdJob = Label(tabAngajat, text='Id_Job:')
labelIdJob.place(x=530, y=440)
entryIdJob = OptionMenu(tabAngajat, variable1, idList[0], *(idList[1:]))
entryIdJob.place(x=530, y=455)

cur = conn.cursor()
command = 'select id_departament from departamente'
cur.execute(command)
idList = [x[0] for x in cur]
variable2 = StringVar(tabAngajat, idList[0])
labelIdDept = Label(tabAngajat, text='Id_Dept')
labelIdDept.place(x=625, y=440)
entryIdDept = OptionMenu(tabAngajat, variable2, idList[0], *(idList[1:]))
entryIdDept.place(x=625, y=455)


def insertAngajat(nume, prenume, salariu, nrTel, idM, idJ, idD):
    for item in angajati.get_children():
        angajati.delete(item)

    cur = conn.cursor()
    command = 'insert into angajat(' \
              'nume, prenume, data_angajarii, salariu, nr_telefon, id_manager, id_job, id_departament) ' \
              'values(\'' + nume + '\',\'' + prenume + '\', sysdate, ' + salariu + ',' + nrTel + ',' + idM + ',\'' \
              + idJ + '\',' + idD + ')'
    try:
        cur.execute(command)
    except Exception as error:
        errAngajat.delete(1.0, END)
        errAngajat.insert(1.0, str(error))
    command = 'select * from angajat order by id_angajat'
    cur.execute(command)
    for result in cur:
        angajati.insert(parent='', index='end', text='',
                        values=(result[0], result[1], result[2], result[3].date(),
                                result[4], '0' + result[5], result[6], result[7], result[8]))
    cur.close()


buttInsertAngajat = Button(tabAngajat, text="Insereaza",
                           command=lambda: [insertAngajat(entryNume.get(), entryPrenume.get(), entrySalariu.get(),
                                                          entryNrTel.get(), variable3.get(), variable1.get(),
                                                          variable2.get()),
                                            entryNume.delete(0, END),
                                            entryPrenume.delete(0, END),
                                            entrySalariu.delete(0, END),
                                            entryNrTel.delete(0, END)])
buttInsertAngajat.place(x=740, y=456)
buttInsertAngajat.config(background='#23ffff')

# update Angajat
labelUpId = Label(tabAngajat, text='Id_Angajat')
labelUpId.place(x=20, y=490)
entryUpId = Entry(tabAngajat, bd=3, width=10)
entryUpId.place(x=20, y=510)

labelUpSal = Label(tabAngajat, text='Salariu nou:')
labelUpSal.place(x=150, y=490)
entryUpSal = Entry(tabAngajat, bd=3, width=12)
entryUpSal.place(x=150, y=510)

labelUpTel = Label(tabAngajat, text="Nr_Telenfon nou:")
labelUpTel.place(x=300, y=490)
entryUpTel = Entry(tabAngajat, bd=3, width=15)
entryUpTel.place(x=300, y=510)


def modify_Salary(sal, id):
    if sal != '':
        entryUpId.delete(0, END)
        entryUpSal.delete(0, END)
        for item in angajati.get_children():
            angajati.delete(item)
        cur = conn.cursor()
        command = 'update angajat ' \
                  'set salariu = ' + sal + ' where id_angajat = ' + id + ''
        try:
            cur.execute(command)
        except Exception as error:
            errAngajat.delete(1.0, END)
            errAngajat.insert(1.0, str(error))
        command = 'select * from angajat order by id_angajat'
        cur.execute(command)
        for result in cur:
            angajati.insert(parent='', index='end', text='',
                            values=(result[0], result[1], result[2], result[3].date(),
                                    result[4], '0' + result[5], result[6], result[7], result[8]))
        cur.close()


def modify_NrTel(nrTel, id):
    if nrTel != '':
        entryUpId.delete(0, END)
        entryUpTel.delete(0, END)
        for item in angajati.get_children():
            angajati.delete(item)
        cur = conn.cursor()
        command = 'update angajat ' \
                  'set nr_telefon = ' + nrTel + ' where id_angajat = ' + id + ''
        try:
            cur.execute(command)
        except Exception as error:
            errAngajat.delete(1.0, END)
            errAngajat.insert(1.0, str(error))
        command = 'select * from angajat order by id_angajat'
        cur.execute(command)
        for result in cur:
            angajati.insert(parent='', index='end', text='',
                            values=(result[0], result[1], result[2], result[3].date(),
                                    result[4], '0' + result[5], result[6], result[7], result[8]))
        cur.close()


buttUpAngajat = Button(tabAngajat, text='Modifica',
                       command=lambda: [modify_Salary(entryUpSal.get(), entryUpId.get()),
                                        modify_NrTel(entryUpTel.get(), entryUpId.get())])
buttUpAngajat.place(x=500, y=506)
buttUpAngajat.config(background='#23ffff')

# Delete
labelDelId = Label(tabAngajat, text='Id_Angajat')
labelDelId.place(x=20, y=540)
entryDelId = Entry(tabAngajat, bd=3, width=10)
entryDelId.place(x=20, y=560)


def deleteById(Id):
    if Id != '':
        entryDelId.delete(0, END)
        for item in angajati.get_children():
            angajati.delete(item)
        cur = conn.cursor()
        command = 'delete from angajat where id_angajat = ' + Id + ''
        try:
            cur.execute(command)
        except Exception as error:
            errAngajat.delete(1.0, END)
            errAngajat.insert(1.0, str(error))
        command = 'select * from angajat order by id_angajat'
        cur.execute(command)
        for result in cur:
            angajati.insert(parent='', index='end', text='',
                            values=(result[0], result[1], result[2], result[3].date(),
                                    result[4], '0' + result[5], result[6], result[7], result[8]))
        cur.close()


buttDelById = Button(tabAngajat, text='Sterge dupa ID', background='#23ffff',
                     command=lambda: [deleteById(entryDelId.get())])
buttDelById.place(x=120, y=556)

labelDelNume = Label(tabAngajat, text='Nume:')
labelDelNume.place(x=270, y=540)
entryDelNume = Entry(tabAngajat, bd=3, width=15)
entryDelNume.place(x=270, y=560)

labelDelPrenume = Label(tabAngajat, text='Prenume:')
labelDelPrenume.place(x=400, y=540)
entryDelPrenume = Entry(tabAngajat, bd=3, width=15)
entryDelPrenume.place(x=400, y=560)


def deleteByNP(nume, prenume):
    if nume != '' and prenume != '':
        entryDelId.delete(0, END)
        for item in angajati.get_children():
            angajati.delete(item)
        cur = conn.cursor()
        command = 'delete from angajat where nume like \'' + nume + '\' and prenume like \'' + prenume + '\''
        print(command)
        try:
            cur.execute(command)
        except Exception as error:
            errAngajat.delete(1.0, END)
            errAngajat.insert(1.0, str(error))
        command = 'select * from angajat order by id_angajat'
        cur.execute(command)
        for result in cur:
            angajati.insert(parent='', index='end', text='',
                            values=(result[0], result[1], result[2], result[3].date(),
                                    result[4], '0' + result[5], result[6], result[7], result[8]))
        cur.close()


buttDelByNP = Button(tabAngajat, text='Sterge dupa NP', background='#23ffff',
                     command=lambda: [deleteByNP(entryDelNume.get(), entryDelPrenume.get())])
buttDelByNP.place(x=530, y=556)

errAngajat = Text(tabAngajat, bd=4, width=70, height=4)
errAngajat.place(x=100, y=590)

# Departamente
labelDepartamente = Label(tabDepartamente, text="Tabel Departamente")
labelDepartamente.grid(column=1,
                       row=1,
                       padx=40,
                       pady=15)
departamente = ttk.Treeview(labelDepartamente)
departamente.pack(padx=230, pady=5)

departamente['column'] = ('id_departament', 'nume_departamente')

departamente.column("#0", width=0, stretch=NO)
departamente.column("id_departament", anchor=CENTER, width=100)
departamente.column("nume_departamente", anchor=CENTER, width=150)

departamente.heading("#0", text="", anchor=CENTER)
departamente.heading("id_departament", text="Id_Departament", anchor=CENTER)
departamente.heading("nume_departamente", text="Nume_Departament", anchor=CENTER)

labelSelectDepartament = Label(tabDepartamente, text='SELECT * FROM DEPARTAMENTE')
labelSelectDepartament.place(x=100, y=260)

entryCommandDept = Text(tabDepartamente, bd=3, height=8, width=70)
entryCommandDept.place(x=100, y=277)

errDept = Text(tabDepartamente, bd=3, width=70, height=4)
errDept.place(x=100, y=590)


def selFromDept(text):
    for item in departamente.get_children():
        departamente.delete(item)

    cur = conn.cursor()
    command = 'select * from departamente ' + text + ''
    try:
        cur.execute(command)
    except Exception as error:
        errDept.delete(1.0, END)
        errDept.insert(1.0, str(error))

    for result in cur:
        departamente.insert(parent='', index='end', text='',
                            values=(result[0], result[1]))
    cur.close()


buttSelFromDept = Button(tabDepartamente, text='Select',
                         command=lambda: [selFromDept(entryCommandDept.get(1.0, END)),
                                          entryCommandDept.delete(1.0, END)])
buttSelFromDept.place(x=700, y=330)

# Insert
lIdDept = Label(tabDepartamente, text='Nume_Departament:')
lIdDept.place(x=325, y=420)
entryDept = Entry(tabDepartamente, bd=3, width=25)
entryDept.place(x=300, y=440)


def insertDept(nume):
    if nume != '':
        entryDept.delete(0, END)
        for item in departamente.get_children():
            departamente.delete(item)

        cur = conn.cursor()
        command = 'insert into departamente(nume_departament)' \
                  ' values(\'' + nume + '\')'
        try:
            cur.execute(command)
        except Exception as error:
            errDept.delete(1.0, END)
            errDept.insert(1.0, str(error))
        selFromDept('')
        cur.execute('commit')
        cur.close()


buttInsertDept = Button(tabDepartamente, text='Insert', background='#23ffff',
                        command=lambda: [insertDept(entryDept.get())])
buttInsertDept.place(x=550, y=435)

# modify
labelUpIdD = Label(tabDepartamente, text='Id_Departament')
labelUpIdD.place(x=170, y=470)
entryUpIdD = Entry(tabDepartamente, bd=3, width=10)
entryUpIdD.place(x=170, y=490)

labelUpNume = Label(tabDepartamente, text='Nume nou')
labelUpNume.place(x=350, y=470)
entryUpNume = Entry(tabDepartamente, bd=3, width=15)
entryUpNume.place(x=350, y=490)


def modifyDept(Id, nume):
    if nume != '':
        entryDept.delete(0, END)
        for item in departamente.get_children():
            departamente.delete(item)

        cur = conn.cursor()
        command = 'update departamente' \
                  ' set nume_departament = \'' + nume + '\' where id_departament = ' + Id + ''
        try:
            cur.execute(command)
        except Exception as error:
            errDept.delete(1.0, END)
            errDept.insert(1.0, str(error))
        selFromDept('')
        cur.execute('commit')
        cur.close()


buttUpDept = Button(tabDepartamente, text='Modifica', background='#23ffff',
                    command=lambda: [modifyDept(entryUpIdD.get(), entryUpNume.get())])
buttUpDept.place(x=540, y=475)

# delete
cur = conn.cursor()
command = 'select * from departamente'
cur.execute(command)
idList = [str(x[0]) + ' ' + x[1] for x in cur]
variableDept = StringVar(tabDepartamente, idList[0])
labelDept = Label(tabDepartamente, text='Departamente:')
labelDept.place(x=350, y=520)
optMDept = OptionMenu(tabDepartamente, variableDept, idList[0], *(idList[1:]))
optMDept.place(x=335, y=540)


def deleteDept(param):
    if param != '':
        entryDept.delete(0, END)
        for item in departamente.get_children():
            departamente.delete(item)

        cur = conn.cursor()
        command = 'delete from departamente' \
                  ' where id_departament = ' + param[0] + ''
        try:
            cur.execute(command)
        except Exception as error:
            errDept.delete(1.0, END)
            errDept.insert(1.0, str(error))
        selFromDept('')
        cur.execute('commit')
        cur.close()


buttDelDept = Button(tabDepartamente, text='Sterge Departament', background='#23ffff',
                     command=lambda: [deleteDept(variableDept.get())])
buttDelDept.place(x=525, y=540)

# joburi
labelJoburi = Label(tabJoburi, text="Text Joburi")
labelJoburi.grid(column=1,
                 row=1,
                 padx=40,
                 pady=15)
joburi = ttk.Treeview(labelJoburi)
joburi.pack(padx=130, pady=5)

joburi['column'] = ('id_job', 'nume_job', 'min_salariu', 'max_salariu')

joburi.column("#0", width=0, stretch=NO)
joburi.column("id_job", anchor=CENTER, width=100)
joburi.column("nume_job", anchor=CENTER, width=100)
joburi.column("min_salariu", anchor=CENTER, width=100)
joburi.column("max_salariu", anchor=CENTER, width=100)

joburi.heading("#0", text="", anchor=CENTER)
joburi.heading("id_job", text="Id_Job", anchor=CENTER)
joburi.heading("nume_job", text="Nume_Job", anchor=CENTER)
joburi.heading("min_salariu", text="Min_Salariu", anchor=CENTER)
joburi.heading("max_salariu", text="Max_Salariu", anchor=CENTER)

errJob = Text(tabJoburi, bd=4, width=70, height=4)
errJob.place(x=100, y=590)


def selFromJob(text):
    for item in joburi.get_children():
        joburi.delete(item)

    cur = conn.cursor()
    command = 'select * from joburi ' + text + ''
    try:
        cur.execute(command)
    except Exception as error:
        errJob.delete(1.0, END)
        errJob.insert(1.0, str(error))
    for result in cur:
        joburi.insert(parent='', index='end', text='',
                      values=(result[0], result[1], result[2], result[3]))
    cur.close()


labelSelectJoburi = Label(tabJoburi, text='SELECT * FROM JOBURI')
labelSelectJoburi.place(x=100, y=260)

entryCommandJob = Text(tabJoburi, bd=3, height=8, width=70)
entryCommandJob.place(x=100, y=277)

buttSelFromJob = Button(tabJoburi, text='Select',
                        command=lambda: [selFromJob(entryCommandJob.get(1.0, END)), entryCommandJob.delete(1.0, END)])
buttSelFromJob.place(x=700, y=330)

# Insert
labIdjob = Label(tabJoburi, text='Id_job:')
labIdjob.place(x=80, y=420)
entryIdJ = Entry(tabJoburi, bd=3, width=10)
entryIdJ.place(x=80, y=440)

labelNumeJob = Label(tabJoburi, text='Nume_job')
labelNumeJob.place(x=200, y=420)
entryNumeJob = Entry(tabJoburi, bd=3, width=15)
entryNumeJob.place(x=200, y=440)

labelMinSal = Label(tabJoburi, text='Min_Salariu:')
labelMinSal.place(x=350, y=420)
entryMinSal = Entry(tabJoburi, bd=3, width=10)
entryMinSal.place(x=350, y=440)

labelMaxSal = Label(tabJoburi, text='Max_Salariu:')
labelMaxSal.place(x=480, y=420)
entryMaxSal = Entry(tabJoburi, bd=3, width=10)
entryMaxSal.place(x=480, y=440)


def insertJob(Id, nume, min, max):
    if Id != '' and nume != '' and min != '' and max != '':
        errJob.delete(1.0, END)
        for item in joburi.get_children():
            joburi.delete(item)

        cur = conn.cursor()
        command = 'insert into joburi values(' \
                  '\'' + Id + '\',\'' + nume + '\', ' + min + ',' + max + ')'
        try:
            cur.execute(command)
        except Exception as error:
            errJob.delete(1.0, END)
            errJob.insert(1.0, str(error))
        selFromJob('')
        cur.close()


buttInsertJob = Button(tabJoburi, text='Insert', background='#23ffff',
                       command=lambda: [
                           insertJob(entryIdJ.get(), entryNumeJob.get(), entryMinSal.get(), entryMaxSal.get())])

buttInsertJob.place(x=600, y=435)

# modify
cur = conn.cursor()
command = 'select id_job from joburi'
cur.execute(command)
idList = [x[0] for x in cur]
variableJob = StringVar(tabJoburi, idList[0])
labelJob = Label(tabJoburi, text='Joburi')
labelJob.place(x=200, y=500)
optMJob = OptionMenu(tabJoburi, variableJob, idList[0], *(idList[1:]))
optMJob.place(x=185, y=520)

labelminSalNew = Label(tabJoburi, text='Min_Salariu nou:')
labelminSalNew.place(x=350, y=500)
entryMinSalNew = Entry(tabJoburi, bd=3, width=10)
entryMinSalNew.place(x=350, y=520)

labelMaxSalNew = Label(tabJoburi, text='Max_Salariu nou:')
labelMaxSalNew.place(x=480, y=500)
entryMaxSalNew = Entry(tabJoburi, bd=3, width=10)
entryMaxSalNew.place(x=480, y=520)


def updateJob(job, min, max):
    if job != '' and min != '' and max != '':
        errJob.delete(1.0, END)
        for item in joburi.get_children():
            joburi.delete(item)

        cur = conn.cursor()
        command = 'update joburi ' \
                  'set min_salariu = '+min+' , max_salariu = '+max+' ' \
                  ' where id_job = \''+job+'\''

        try:
            cur.execute(command)
        except Exception as error:
            errJob.delete(1.0, END)
            errJob.insert(1.0, str(error))
        selFromJob('')
        cur.close()


buttModifySal = Button(tabJoburi, text='Modifica', background='#23ffff',
                       command=lambda: [updateJob(variableJob.get(), entryMinSalNew.get(), entryMaxSalNew.get())])

buttModifySal.place(x=600, y=515)

# locatii
labelLocatii = Label(tabLocatii, text="Text Joburi")
labelLocatii.grid(column=1,
                  row=1,
                  padx=40,
                  pady=15)
locatii = ttk.Treeview(labelLocatii)
locatii.pack(padx=80, pady=5)

locatii['column'] = ('location_id', 'oras', 'adresa', 'cod_postal', 'id_departament')

locatii.column("#0", width=0, stretch=NO)
locatii.column("location_id", anchor=CENTER, width=100)
locatii.column("oras", anchor=CENTER, width=100)
locatii.column("adresa", anchor=CENTER, width=100)
locatii.column("cod_postal", anchor=CENTER, width=100)
locatii.column("id_departament", anchor=CENTER, width=100)

locatii.heading("#0", text="", anchor=CENTER)
locatii.heading("location_id", text="Id_Locatie", anchor=CENTER)
locatii.heading("oras", text="Oras", anchor=CENTER)
locatii.heading("adresa", text="Adresa", anchor=CENTER)
locatii.heading("cod_postal", text="Cod_Postal", anchor=CENTER)
locatii.heading("id_departament", text="Id_Departament", anchor=CENTER)


def selFromLoc(text):
    for item in locatii.get_children():
        locatii.delete(item)

    cur = conn.cursor()
    command = 'select * from locatii ' + text + ''

    try:
        cur.execute(command)
    except Exception as error:
        errLoc.delete(1.0, END)
        errLoc.insert(1.0, str(error))
    for result in cur:
        locatii.insert(parent='', index='end', text='',
                       values=(result[0], result[1], result[2], result[3], result[4]))
    cur.close()


labelSelectLocatii = Label(tabLocatii, text='SELECT * FROM JOBURI')
labelSelectLocatii.place(x=100, y=260)

entryCommandLoc = Text(tabLocatii, bd=3, height=8, width=70)
entryCommandLoc.place(x=100, y=277)

buttSelFromLoc = Button(tabLocatii, text='Select',
                        command=lambda: [selFromLoc(entryCommandLoc.get(1.0, END)), entryCommandLoc.delete(1.0, END)])
buttSelFromLoc.place(x=700, y=330)

errLoc = Text(tabLocatii, bd=4, width=70, height=4)
errLoc.place(x=100, y=590)

cur = conn.cursor()
command = 'select location_id from locatii'
cur.execute(command)
idList = [x[0] for x in cur]
variableLoc = StringVar(tabLocatii, idList[0])
labelLoc = Label(tabLocatii, text='Locatii')
labelLoc.place(x=70, y=450)
optMLoc = OptionMenu(tabLocatii, variableLoc, idList[0], *(idList[1:]))
optMLoc.place(x=55, y=470)

labelOras = Label(tabLocatii, text='Oras :')
labelOras.place(x=190, y=450)
entryOras = Entry(tabLocatii, bd=3, width=15)
entryOras.place(x=170, y=470)

labelAdresa = Label(tabLocatii, text='Adresa noua:')
labelAdresa.place(x=330, y=450)
entryAdresa = Entry(tabLocatii, bd=3, width=25)
entryAdresa.place(x=300, y=470)

labelCP = Label(tabLocatii, text='C. Postal nou:')
labelCP.place(x=500, y=450)
entryCP = Entry(tabLocatii, bd=3, width=10)
entryCP.place(x=500, y=470)


def modify_Loc(loc, oras, adres, cp):
    if loc!='' and oras !='' and adres != '' and cp != '':
        for item in locatii.get_children():
            locatii.delete(item)

        cur = conn.cursor()
        command = 'update locatii set oras = \''+oras+'\', adresa = \''+adres+'\', cod_postal = '+cp+'' \
                    ' where location_id = '+loc+''

        try:
            cur.execute(command)
        except Exception as error:
            errLoc.delete(1.0, END)
            errLoc.insert(1.0, str(error))
        selFromLoc('')
        cur.close()


buttUPLoc = Button(tabLocatii, text='Modifica', background='#23ffff',
                   command=lambda: [modify_Loc(variableLoc.get(), entryOras.get(), entryAdresa.get(), entryCP.get())])
buttUPLoc.place(x=620, y=465)


#commit
buttCommit = Button(tabCommit, text='Commit', font=font.Font(size=40),
                    command=lambda: cur.execute("commit"))
buttCommit.pack(fill = BOTH, expand = True)

if __name__ == '__main__':
    window.mainloop()
    conn.close()
    print('ok')
