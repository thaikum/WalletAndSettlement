## Setup Instructions

### Requirements
- Java 21
- Docker
- Docker compose

##
1. Import gradle dependencies
2. Run `./gradle bootjar` to create the jar file
3. Use `docker compose up` to start the docker services which include mariadb, rabbit mq and the app

## Assumption
- For double entry to work in transactions, an initial account 'CASH' is created. All customer transactions are performed between the customer and the cash account. The cash account can be overdrawn since it is an office account.
- All wallets belong to customers apart from the office wallets.
- The external service dumps files in `resources/files` folder.
- The transaction id in the external file matches the one in this systems transactions. Its assumed the external file should be compared with the transactions happening on customer's wallet.
- The file dumped into our server has the format `extern-yyyyMMdd.csv`.
- Reconciliation report is not saved, its dumped as csv files instead, as per the requirements.

## NB
- The resources folder contains test data in a `data.sql` file.
Kindly review the data for testing.
- The files folder in the resources contains a file `extern-20250831.csv` to be used in reconciliation. This file contains transactions for 31st August. One transactions matches the ones in the test data in order to show how matching works. The id for the matching transaction is '5637'.
- The data contains wallets with ids `1-11` id 1 being id for cash account.
- The date where there exists data for reconciliation testing is `2025-08-31`
- Postman API documentation file is located in the root folder as `WalletAndSettlement.postman_collection.json`
- MariaDb is used instead of MySQL, just preference as they share a lot.
- All unit tests are in the folder `src/test/java/unit`
- All integration tests are in the folder `src/test/java/integration`

## APIs
GET wallet/{{id}}/balance

###
POST wallet/{{id}}/consume

###
POST wallet/{{id}}/topup

###
GET api/v1/reconciliation/report?
date={{}}