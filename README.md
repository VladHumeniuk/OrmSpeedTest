* [Requery 1.0.0-beta22][1]
* [Cupboard 2.1.4][2]
* [Electra 1.0.0][3]
* [DBFlow 3.0.1][4]
* [Realm 2.2.0][5]
* [SQLCipher 3.5.4][6]

###LG G3 856 (5.0.1) Results (ms, avg of 3)

|           | Create 1K | Create 10K | Create Bulk 1K | Create Bulk 10K | Create Bulk 50k | Read 1K | Read 10K | Read 50k |
|-----------|-----------|------------|----------------|-----------------|-----------------|---------|----------|----------|
| Requery   | 6990      | 64295      | 312            | 2023            | 9633            | 150     | 2446     | 15008    |
| DBFlow    | 5357      | 60339      | 260            | 1664            | 7746            | 75      | 1042     | 10662    |
| Coupboard | 5301      | 52471      | 366            | 2483            | 11343           | 194     | 1582     | 12071    |
| Electra   | 2235      | 22226      | 259            | 1834            | 8804            | 101     | 948      | 7798     |

###LG Nexus 5 2013 (5.1.0) Results (ms, avg of 3)

|                 | Create 1K | Create 10K | Create Bulk 1K | Create Bulk 10K | Create Bulk 50k | Read 1K | Read 10K | Read 50k |
|-----------------|-----------|------------|----------------|-----------------|-----------------|---------|----------|----------|
| Requery         | 12074     | 136582     | 257            | 1704            | 9353            | 193     | 2073     | 16688    |
| DBFlow          | 11280     | 122936     | 248            | 1957            | 6963            | 88      | 1027     | 10484    |
| Coupboard       | 11892     | 137381     | 354            | 2813            | 16148           | 161     | 2292     | 18068    |
| Electra         | 10197     | 115356     | 275            | 2470            | 11926           | 113     | 1506     | 13381    |
| Electra +Cipher | 16470     | 185641     | 398            | 3248            | 16253           | 138     | 3297     | 16772    |
| Realm           | 12190     | 129147     | 302            | 2589            | 13632           | 3       | 6        | 47       |
| Realm Encrypted | 27649     | 312778     | 288            | 2835            | 14874           | 7       | 11       | 36       |
| Cipher          | 18038     | 179628     | 253            | 2829            | 15507           | 17      | 987      | 1257     |

[1]: https://github.com/requery/requery
[2]: https://bitbucket.org/littlerobots/cupboard
[3]: https://bitbucket.org/txdrive/electra
[4]: https://github.com/Raizlabs/DBFlow
[5]: https://realm.io/docs/java/2.2.0/
[6]: https://www.zetetic.net/sqlcipher/sqlcipher-for-android/

you can create pull request for new ORMs, or fixes or new results.
