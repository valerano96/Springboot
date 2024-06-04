# Gestione Magazzino Computer

## Descrizione
Questo progetto è un gestionale sviluppato in Java con Spring Boot, che consente ai negozianti di gestire il proprio magazzino di computer. Le funzionalità principali includono:

- Aggiunta di nuovi computer al magazzino
- Monitoraggio delle quantità di prodotti
- Acquisto di prodotti da parte degli utenti con ricezione di email di conferma
- Accesso a tabelle di vendite per monitorare l'andamento delle vendite

## Prerequisiti
- Java 11 o superiore
- Gradle
- Un server di posta configurato per l'invio di email

## Installazione

1. Clona il repository
   ```sh
   git clone https://github.com/username/nome-repository.git
   
2. Importa il progetto in un IDE come Eclipse o IntelliJ IDEA
3. Configura le dipendenze di Gradle
4. Configura il file 'application.properties' con le tue impostazioni di database e server di posta

<h3>Funzionalità Principali:</h3>

<h4>1) Aggiunta di un computer al magazzino</h4>
<p>Endpoint: /creaComputer</p>
<p>Metodo: POST</p>
<p>Descrizione: Aggiunge un nuovo computer al magazzino.</p>

<h4>2) Visualizzazione del Magazzino</h4>
<p>Endpoint: /magazzinoComputer</p>
<p>Metodo: GET</p>
<p>Descrizione: Visualizza tutti i computer presenti in magazzino.</p>

<h4>3) Modifica delle Quantità</h4>
<p>Endpoint: /change</p>
<p>Metodo: GET/POST</p>
Descrizione: Modifica le quantità dei computer presenti in magazzino.

<h4>4) Acquisto di Computer</h4>
<p>Endpoint: /acquista</p>
<p>Metodo: GET</p>
<p>Descrizione: Visualizza la lista dei computer disponibili per l'acquisto.</p>

<h4>5) Invio di Email di Conferma</h4>
<p>Endpoint: /sendEmail</p>
<p>Metodo: POST</p>
<p>Descrizione: Invia una email di conferma all'utente.</p>

<h4>6) Conferma degli Ordini</h4>
<p>Endpoint: /confPc</p>
<p>Metodo: POST</p>
<p>Descrizione: Conferma l'acquisto dei computer e invia una email di conferma all'utente.</p>

<h4>7) Visualizzazione degli Ordini di Computer</h4>
<p>Endpoint: /showPcOrders</p>
<p>Metodo: GET</p>
<p>Descrizione: Visualizza una tabella con gli ordini dei computer effettuati.</p>

![samsung](https://github.com/valerano96/Springboot/assets/167990036/00fac556-cfd7-4183-9228-e2850f618d79)

