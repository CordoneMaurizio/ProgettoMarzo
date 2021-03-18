# OceaniaTicket
Progetto Programmazione a Oggetti - Marzo 2021
## Descrizione
-API Reference: https://developer.ticketmaster.com/products-and-docs/apis/discovery-api/v2/#search-events-v2
il programma ha l'obiettivo di recuperare e filtrare dati riguardanti eventi di vario genere per infine visualizzare le informazioni essenziali e statistiche.
TicketMaster fornisce un servizio di APIrest per il recupero di dati su eventi in programma in diversi stati. OceaniaTicket si concentra sulla ricerca di eventi in Australia.
## Installazione
1. Clonare la repository `OceaniaTicket`
2. Aprire il Prompt dei Comandi (Windows, cmd.exe) nella cartella creata
3. Inserire il comando `mvn clean install`
4. Eseguire il server con `java -jar target/OceaniaTicket-0.0.1-SNAPSHOT.jar`
## Utilizzo
il framework di springboot permette al programma di interfacciarsi attraverso web server di tipo tomcat e di rispondere ad API con rotte predefinite:
| comando | descrizione |
|----|----------|
| /dati | restituisce le informazioni principali relative ad una lista di eventi filtrata |
| /stat | restituisce statistiche calcolate su una lista di eventi filtrata |
| /all  | restituisce contemporaneamente informazioni e statistiche |
### Formattazione del body
tutte le rotte sono do tipo **POST** e condividono un formato unico del body:
```json 
{
    "generi":"music,sport",
    "source":"ticketmaster,tmr",
    "stati":"wa,queensland",
    "range temporale":"3 mesi"
}
```
*separare il contenuto dei campi con una virgola permette di filtrare dati secondo entrambi*
chiavi e campi del body vanno scritti in *minuscolo*.
#### campi disponibili
##### generi:
aggiungendo '-' davanti ad uno dei filtri di questo campo permette di escludere gli eventi classificati secondo quel genere
-  music | sports | art & theatre | miscellaneous 
##### range temporale:
-  3 mesi | 6 mesi | anno corrente 
##### source:
i portali univerce e frontgate sono attualmente poco utilizzati, inserirli tra i filtri potreppe portare ad un messaggio di errore
-  ticketmaster | tmr | universe | frontgate 
##### stati:
questo campo permette l'inserimento del nome completo di uno stato o del suo codice identificativo
https://en.wikipedia.org/wiki/ISO_3166-2:AU.
### esempio di risposta
```json 
{
    "eventi": [
        {
            "nome": "North Queensland Toyota Cowboys v St.George Illawarra Dragons(Round 2)",
            "locale": {
                "indirizzo": "2 Pride Close",
                "nome": "Queensland Country Bank Stadium"
            },
            "stato": "Queensland",
            "citta": "Townsville",
            "data": "2021-03-20 18:35:00",
            "genere": "Sports"
        },
        {
            "altriEventi":"...."
        }
   ],     
   "Statistiche": {
        "statistiche globali": {
            "numero totale eventi": 108,
            "stato con meno eventi: Western Australia": 33,
            "stato con più eventi: Queensland": 75
        },
        "statistiche per stato": {
             "Queensland": {
                  "totale": 75,
                  "perGenere": {
                      "Music": 63,
                      "Sports": 12
                   }
             }
        }
   },
   "url": "https://app.ticketmaster.com/discovery/v2/events.json?&countryCode=AU&size=200
           &apikey=M3LyhpAl8jvIjH1k9t7VdKojV4WDyYAA
           &classificationName=Music,sport&stateCode=qld,wa,&source=ticketmaster
           &localStartEndDateTime=2021-03-18T23:33:16,2021-06-18T08:00:25"
}
```
## Diagrammi UML
<p>
  <img src = "https://github.com/CordoneMaurizio/ProgettoMarzo/blob/main/OceaniaTicket%20Use%20Case%20Diagram.png">
    <h6> diagramma dei casi d'uso
    </h6>
  <img>
 </p>
 <p>
  <img src = "https://github.com/CordoneMaurizio/ProgettoMarzo/blob/main/Oceania%20Ticket%20Class%20Diagram.png">
    <h6> diagramma dei casi d'uso
    </h6>
  <img>
 </p><p>
  <img src = "https://github.com/CordoneMaurizio/ProgettoMarzo/blob/main/API%20call.png">
    <h6> diagramma dei casi d'uso
    </h6>
  <img>
 </p><p>
  <img src = "https://github.com/CordoneMaurizio/ProgettoMarzo/blob/main/_dati.png">
    <h6> diagramma dei casi d'uso
    </h6>
  <img>
 </p><p>
  <img src = "https://github.com/CordoneMaurizio/ProgettoMarzo/blob/main/OceaniaTicket%20Use%20Case%20Diagram.png">
    <h6> diagramma dei casi d'uso
    </h6>
  <img>
 </p>
