# risk-decision-engine
The engine is used to analyze real time transactions from an API or TCP/IP message stream and apply a set of business rules to each transaction and make a risk based decision.

 Here are the technical requirements for a risk decision engine that I used.

### *1. Core Functionality*

The risk decision engine must process real-time transactions from an API or TCP/IP message stream to assess risk. The engine's core function is to apply a set of business rules to each transaction and return a risk decision.
- The system must be able to ingest transactions from two primary sources: a *RESTful API endpoint* (for synchronous requests) and a *TCP/IP message listener* (for high-volume, asynchronous messages).
- The system will use a *Rule Engine* to apply a predefined set of rules. This engine must be configurable to allow for easy updates to rules without requiring a code change or system restart.

### *2. Technology Stack*

The following technologies must be used for the development of the system:
- *Programming Language:* *Java* (version 11 or higher)
- *Database:* *Oracle DB* (version 19c or higher) for storing static data, historical transactions, and configurations.
- *Message Broker:* *Apache Kafka* for handling high-throughput, asynchronous messages and decoupling system components.
- *Rule Engine:* A rule engine must be integrated. The specific choice (e.g., Drools, OpenL Tablets) will be decided during the design phase. The engine should provide a user-friendly interface for rule management.
- *Observability:* *Elasticsearch, Logstash, and Kibana (ELK) stack* will be used for centralized logging, monitoring, and traceability.

### *3. Rule Engine Requirements*

The rule engine must be capable of making decisions based on a variety of data sources.
- *Payload Data:* The engine must parse the incoming transaction payload (from either the API or TCP/IP message) and extract relevant variables.
- *Database Data:* The engine must be able to perform lookups in the Oracle DB to enrich the transaction data with customer history, account status, or other static information.
- *Derived Variables:* The engine must be able to perform calculations or apply logic to create new variables (e.g., calculating a velocity score based on the number of transactions in the last hour).
- *External System Data:* The engine must be able to connect to and retrieve data from external systems (e.g., third-party fraud scoring services) via an API call.

### *4. Data Flow and Architecture*

- *Ingestion:* API requests will be directly processed. TCP/IP messages will be published to a dedicated Kafka topic.
- *Processing:* A Java service will consume messages from the Kafka topic, enrich the data from the DB and external systems, and then submit the enriched data to the rule engine.
- *Decisioning:* The rule engine will evaluate the data against the defined rules and return a decision (e.g., Approve, Decline, Review).
- *Persistence:* The final decision and all relevant transaction data points used for the decision must be stored in the Oracle DB for future reference and auditing.

### *5. Observability and Traceability*

- The *ELK stack* is mandatory for all logging.
- The system must log every step of the decision-making process, from ingestion to final decision.
- All log entries must be structured and include a unique *transaction ID* to enable full end-to-end traceability.
- Log data should include the incoming payload, the final risk decision, the rules that were triggered, and the values of the variables used in the decision.
- Dashboards in Kibana must be created to monitor key metrics, such as transaction volume, decision rates, and latency.



### *6. Performance and Scalability*

- The system must be designed for *high availability and horizontal scalability*. It should be able to handle a high volume of transactions per second.
- The response time for API requests must be less than *50 milliseconds*.
- The system should be resilient to external system failures; for example, it should have a fallback or timeout mechanism for calls to external APIs.

Below is the architecture diagram to build the engine.

<img width="721" height="501" alt="risk_decision_engine drawio" src="https://github.com/user-attachments/assets/8efa54d6-6ce5-4473-833c-b3fbe664659e" />

