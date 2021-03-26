# THREADS
* A thread stack will use 16kB of physical ram if it does nothing else but sleep
That is the base overhead of a JVM thread. Further stack memory consumption depends on the things you put in the stack.

* The JVM has a flag called -Xss which specifies the stack size of a thread. By default in 64-bit JVMs, this is 1MB. That doesn’t mean each thread will actually consume 1MB of physical resources. All it means is the JVM will malloc 1MB for each thread for its stack

This is tested on my local machine against the 64-bit Hotspot 1.8 JVM, with a thread which simply does Thread.sleep(); using the default -Xss1024k.


# TEST

This application tested under 64-bit Hotspot 1.8 JVM with a -Xms1024m (1GB) -Xmx4096m (4GB) memory on single node MariaDB database.
Data size about 10k, 1k and 100 characters with a same ratio whic means that if 1000 records produced then records includes %33 percent is 10k, %33 percent is 1k and %33 percent is 100 characters

Data is huge such as including 10k, 1k and 100 characters in a row.


This application tested under 64-bit Hotspot 1.8 JVM with a -Xms1024m (1GB) -Xmx4096m (4GB) memory on single node MariaDB database.
Sequential Data Processing:

1k records ~ 6.5 MB / ~ 6 secs
10k records ~ 66 MB / ~ 71 sec
100k records  ~ 650 MB / ~ 1490 sec (24 mins)
1m records ~ 6.4 GB  / ~ 6000 secs (1.6 hrs)


Thread Based Processor ( all threads includes single save process to insert data)

1k records      / 2 threads     ~ 3.7 secs
1k records      / 10 threads    ~ 1.3 secs

10k records     / 2 threads     ~ 36 secs
10k records     / 10 threads    ~ 16 secs
10k records     / 100 threads   ~ 17 secs

100k records    / 2 threads     ~  630 secs (~ 10 mins)
100k records    / 10 threads    ~  541 secs (~ 9 mins)
100k records    / 100 threads   ~  380 secs (~ 6.5 mins)
100k records    / 500 threads   ~  !!! secs (Timeout error for connection to database !!!! I think because of connection duration > 30 secs )

1m records      / 2 threads     ~   secs
1m records      / 10 threads    ~   secs
1m records      / 100 threads   ~   secs
1m records      / 500 threads   ~ !!! secs (Timeout error for connection to database !!!! I think because of connection duration > 30 secs )


Thread Based Bulk Save ( 10 records with saveAll)

100k records    / 2 threads     ~  471 secs (~ 9 mins)
100k records    / 10 threads    ~  390 secs (~ 6.5 mins)
100k records    / 100 threads   ~  294 secs (~ 4.9 mins)
100k records    / 500 threads   ~  !!! secs (Timeout error for connection to database !!!! I think because of connection duration > 30 secs )

1m records      / 2 threads     ~   secs  (~  mins)
1m records      / 10 threads    ~  3540 secs  (~ 61 mins)
1m records      / 100 threads   ~   secs
1m records      / 500 threads   ~ !!! secs (Timeout error for connection to database !!!! I think because of connection duration > 30 secs )