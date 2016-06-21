* [Requery 1.0.0-beta22][1]
* [Cupboard 2.1.4][2]
* [Electra 1.0.0][3]
* [DBFlow 3.0.1][4]

###LG G3 856 (5.0.1) Results (ms, avg of 3)

|           | Create 1K | Create 10K | Create Bulk 1K | Create Bulk 10K | Create Bulk 50k | Read 1K | Read 10K | Read 50k |
|-----------|-----------|------------|----------------|-----------------|-----------------|---------|----------|----------|
| Requery   | 6990      | 64295      | 312            | 2023            | 9633            | 150     | 2446     | 15008    |
| DBFlow    | 5357      | 60339      | 260            | 1664            | 7746            | 75      | 1042     | 10662    |
| Coupboard | 5301      | 52471      | 366            | 2483            | 11343           | 194     | 1582     | 12071    |
| Electra   | 2235      | 22226      | 259            | 1834            | 8804            | 101     | 948      | 7798     |

[1]: https://github.com/requery/requery
[2]: https://bitbucket.org/littlerobots/cupboard
[3]: https://bitbucket.org/txdrive/electra
[4]: https://github.com/Raizlabs/DBFlow
