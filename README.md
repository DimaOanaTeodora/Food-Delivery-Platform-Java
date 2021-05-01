### Cerinte a doua etapa:
1. 5 fisiere de tip CSV :ballot_box_with_check:
    - Users.csv -> User.class
    - Restauran.csv -> Restaurant.class
    - FastFood.csv -> FastFood.class
    - CakeShop.csv -> CakeShop.class
    - Audit.csv -> fisierul de audit
2. servicii singleton pentru scrierea si citirea din fisier :ballot_box_with_check:
    - Reading.class -> citire din fisier
    - Writing.class -> serviciu de audit

### Cerinte prima etapa:
1. 12 actiuni :ballot_box_with_check:
2. 19 clase :ballot_box_with_check:
3. atribute private/protected + metode de acces :ballot_box_with_check:
4. colectii utilizate: List -> ArrayList, Set -> HashSet, Map -> HashMap, TreeMap (colectie sortata) :ballot_box_with_check:
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

## Service (Singleton) (service class for Main)
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
    - Actiuni: *Specifice user* 
        - addOrder() -> plasare comanda
        - cancelOrder() -> anulare plasare comanda prin id-ul comenzii
        - rateAShop() -> oferire puncte de rating de la 0-5 unui magazin dat dupa nume
        - sortShops() -> afisare magazine sortate descrescator dupa rating

## Main (apeluri catre clasa de serviciu Service.class)
-> ca un meniu al programului