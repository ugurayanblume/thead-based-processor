# THREADS
* A thread stack will use 16kB of physical ram if it does nothing else but sleep
That is the base overhead of a JVM thread. Further stack memory consumption depends on the things you put in the stack.

* The JVM has a flag called -Xss which specifies the stack size of a thread. By default in 64-bit JVMs, this is 1MB. That doesn’t mean each thread will actually consume 1MB of physical resources. All it means is the JVM will malloc 1MB for each thread for its stack

This is tested on my local machine against the 64-bit Hotspot 1.8 JVM, with a thread which simply does Thread.sleep(); using the default -Xss1024k.


# TEST

This application tested under 64-bit Hotspot 1.8 JVM with a -Xms1024m (1GB) -Xmx4096m (4GB) memory on single node MariaDB database.
Data size about 10k, 1k and 100 characters with a same ratio whic means that if 1000 records produced then records includes %33 percent is 10k, %33 percent is 1k and %33 percent is 100 characters

Data is huge such as

Sequential Data Create:

1k records ~ 6.5 MB / ~ 6 secs
10k records ~ 66 MB / ~ 71 sec
100k records  ~ 650 MB / ~ 1490 sec (24 mins)
1m records ~ 6.4 GB  / ~ 6000 secs (1.6 hrs)
