### Cerinte a treia etapa
:o: Baza de date: MySQL

:large_orange_diamond: Mediu de lucru: MySQL Workbench
1. Clasa DatabaseConfiguration - Singleton - 2 metode statice:
   - getDatabaseConnection(): realizeaza conexiunea la baza de date si driver
   - closeDatabaseConnection(): realizeaza inchiderea bazei de date

2. Clasa RepositoryHelper - Singleton - "wrapper" pentru executarea queriurilor din DataSetup:
   - executeSql(Connection connection, String sql): pt operatii fara rezultat (insert, update, delete)
   - executeUpdateSql(Connection connection, String sql): pt operatii fara rezultat (insert, update, delete)
   - executeQuerySql(Connection connection, String sql): pt operatii cu rezultat (select)
   
3. Clasa DataSetup:
   
*instructiunile SQL sunt citite din fisierele text: QuerysAddRows.txt, QuerysCreateTables.txt, QuerysDeleteAllRows.txt, QuerysDropAllTables.txt*
 
  - createTables() : crearea automata(daca nu exista) a celor 4 tabele (CREATE :ballot_box_with_check:)
  - addRows(): popularea tabelelor cu date (INSERT :ballot_box_with_check:)
  - deleteAllRows(): stergerea din tabele a tuturor liniilor (DELETE :ballot_box_with_check:)
  - dropAllTables(): stergerea tuturor tabelelor din schema
  - displayTable(): afiseara continutului unui tabel, specificat dupa nume (READ: :ballot_box_with_check:)
   
4. Cele 4 clase: BurgerRepository, DrinkRepository, RFoodRepository, SweetRepository - au metode pentru :
 - inserarea unui obiect in tabel (INSERT :ballot_box_with_check:)
 - afisarea unui obiect din tabel cautat dupa nume (READ :ballot_box_with_check:)
 - updatarea unui obiect din tabel cautat dupa nume (UPDATE :ballot_box_with_check:)
 - stergerea unui obiect din tabel cautat dupa nume (DELETE :ballot_box_with_check:)
 - o functie auxiliara care transforma un rand din tabel intr-un obiect de tipul unuia dintre modelele copii ale clasei Porduct
    
### Cerinte a doua etapa
1. 5 fisiere de tip CSV :ballot_box_with_check:
    - Users.csv -> User.class
    - Restaurant.csv -> Restaurant.class
    - FastFood.csv -> FastFood.class
    - CakeShop.csv -> CakeShop.class
    - Audit.csv -> fisierul de audit
2. servicii singleton pentru scrierea si citirea din fisier :ballot_box_with_check:
    - ReaderCSV.java-> citire din fisier
    - AuditService.java -> serviciu de audit
    - WriterCSV.java -> scrierea unui shop ales intr-un CSV dat de utilizator
      (daca acesta nu exista se va crea automat unul la calea specificata)
3. METODE GENERICE: :ballot_box_with_check:
    - Clasa Singleton Reading
    - Apelata in costructor in functie de tipul restaurantelor pe care vreau sa le citesc
    - Rol : ajuta la o mai buna compactare a codului deoarece nu mai repet
    scrirea unor bucati de cod din program
```java
    private <T> void AuxiliaryMethod(String path, Class<T> classOf)
```
   - Clasa Singleton WritingCSV
   - Rol: Ajuta la afisarea shopurilor in functie de tip, alaturi de un mesaj corespunzator

```java
    public <T> void writeShop(Object shop, Class<T> classOf, String path) 
```
### Cerinte prima etapa
1. 12 actiuni :ballot_box_with_check:
2. 19 clase :ballot_box_with_check:
3. atribute private/protected + metode de acces :ballot_box_with_check:
4. colectii utilizate: List -> ArrayList, Set -> HashSet, Map -> HashMap, Set-> TreeSet (colectie sortata) :ballot_box_with_check:
5. 2 clase de serviciu : Service, Login :ballot_box_with_check:
6. clasa Main care face apeluri catre servicii :ballot_box_with_check:

## Product (abstract)
- name
- price
    ### Burger:
        - isVegan
        - ingredients
    ### Drink:
        - flavour  
    ### RFood:
        - ingredients
    ### Sweet:
        - calories
## Menu (abstract)
- name
- drinks (List -> ArrayList) -> composition
- price 
    ### Box:
        - burger -> composition
        - fries
    ### RMenu: 
        - sweets (List -> ArrayList) -> composition
        - rfoods (List -> ArrayList) -> composition
## Shop (abstract)
- name
- owner -> composition
- deliveryBoys (List -> ArrayList) -> composition
- rating
    ### CakeShop:
        - sweets (List -> ArrayList) -> composition
    ### FastFood:
        - boxes (List -> ArrayList) -> composition
        - drinks (List -> ArrayList) -> composition
        - burgers (List -> ArrayList) -> composition
    ### Restaurant:
        - rMenus (List -> ArrayList) -> composition
        - drinks (List -> ArrayList) -> composition
## User
- name
- email
- phoneNumber
- password
### Owner:
- name
- email
- phoneNumber
    ### DeliveryBoy:
        - carNumber
       
## Login (Singleton) -> service class for logging
- usersReg (Set -> HashSet) -> composition
- curentUser -> composition

## Order
- customer -> composition
- shop -> composition
- deliveryBoy  -> composition
- price
- menus (List -> ArrayList) -> composition
- products (List -> ArrayList) -> composition
- address

## Service (Singleton) - clasa de serviciu pentru actiuni
- login -> composition
- shops (List -> ArrayList) -> composition
- orders (List -> ArrayList) -> composition
- currentUser -> composition
- shopId
- orderId
- reading -> composition
- writing -> composition
    - Actiuni: *comune admin-user*
        - logIn() -> logare utilizator sau posibilitatea de a se inregistra in sistem (se pot loga cumparatorii sau adminul)
        - logOff() -> posibilitatea de sing in/up ca alt utilizator 
        - listShops() -> afisare magazine impreuna cu lista produselor si a meniurilor 
        - listOneShop() -> afsare un singur magazin dupa nume
    - Actiuni: *Specifice admin*
        - addShop() -> adaugare magazin in lista de magazine (implicit lista de produse si meniuri)
        - deleteShop() -> stergere magazin din lista existenta
        - addProduct() -> adaugare produs intr-un magazin dat prin nume
        - deleteProduct() -> stergere produs dintr-un magazin dat prin nume
        - addMenu() -> adaugare meniu
        - databaseMenu.DatabaseService() -> manageriere baza de date
    - Actiuni: *Specifice user* 
        - addOrder() -> plasare comanda
        - cancelOrder() -> anulare plasare comanda prin id-ul comenzii
        - rateAShop() -> oferire puncte de rating de la 0-5 unui magazin dat dupa nume
        - sortShops() -> afisare magazine sortate descrescator dupa rating
        - writeOneShop() -> scrie un shop dat dupa nume intr-un csv a carui cale este data de utilizator 

### Aplication 
- Singleton - clasa de serviciu
- apeluri catre clasa de serviciu Service.java

### DatabaseMenu 
- Singleton - clasa de serviciu
- apeluri catre actiunile care se pot face pe baza de date de catre adminul aplicatiei

### Main 
- se ocupa doar de startul aplicatiei

# Barem de corectare:
### Etapa I  (15 puncte)
:ballot_box_with_check: 10 actiuni: 4 puncte

:ballot_box_with_check: 8 obiecte: 3 puncte

:ballot_box_with_check: sa fie folosite 2 colectii, dintre care 1 sortata(ex: TreeMap, TreeSet :ballot_box_with_check:, insa am punctat si daca ati folosit Comparable/Comparator:ballot_box_with_check: in loc): 2 puncte

:ballot_box_with_check: sa fie folosita mostenirea: 1 punct

:ballot_box_with_check: clasa 'Main' (meniul de folosire al aplicatiei si structura claselor servicii aferente) : 2 puncte

:ballot_box_with_check: clean code (respectare coding standarsand principles, clean code, cum arata codul per total) : 3 puncte

### Etapa II (15 puncte)
:ballot_box_with_check: functionalitate CSV read: 3 puncte

:ballot_box_with_check: functionalitate CSV write: 3 puncte

:ballot_box_with_check: folosire genericitate : 3 puncte

:ballot_box_with_check: folosire singleton pattern pentru serviciile de scriere/citire in CSV : 3 puncte

:ballot_box_with_check: audit service : 2 puncte

:ballot_box_with_check: clean code : 1 punct

### Etapa III (20 puncte)
:hourglass: prezentare (sustinere prezentare + codul sa fie functional per total): 10 puncte
  
:ballot_box_with_check: 4 entitati (operatie create, read, update, delete pentru fiecare) : 4 * 2 puncte = 8 puncte
  
:ballot_box_with_check: clean code: 2 puncte
