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
- measurement
- quantity
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
## User
- name
- email
- phoneNumber
- password
    ### Owner:
        - shop -> composition
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
    - Actions:  
        - logIn() -> logare utilizator sau posibilitatea de a se inregistra in sistem (se pot loga cumparatorii sau adminul)
        - logOff() -> posibilitatea de sing in/up ca alt utilizator 
    - Actions -> *Admin can acces*
        - addShop() -> adaugare magazin in lista de magazine (implicit lista de produse si meniuri)
        - deleteShop() -> stergere magazin din lista existenta
        - listShops() -> afisare magazine impreuna cu lista produselor si a meniurilor 
        - addProduct() -> adaugare produs intr-un magazin dat prin nume
        - deleteProduct() -> stergere produs dintr-un magazin dat prin nume
        - addMenu() -> adaugare meniu
    - Actions -> *User can acces* 
        - listShops() -> afisare magazine impreuna cu lista produselor si a meniurilor 
        - addOrder() -> plasare comanda
        - cancelOrder() -> anulare plasare comanda prin id-ul comenzii
        - getPopularShops() -> afisare magazine sortate descrescator dupa rating

## Main (Calls for service class)
-> afiseaza lista de actiuni in functie de tipul utilizatorului logat admin/ cumparator