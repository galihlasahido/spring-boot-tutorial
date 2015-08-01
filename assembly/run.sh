#!/bin/sh
DIR=`dirname $0`
cd $DIR
rm -f deploy/shutdown.xml
java  -server \
    -Dappname=spring-tutorial \
    -Dcom.sun.management.jmxremote \
    -Xloggc:log/gc.log \
    -Xmx1G -Xms1G \
    -XX:+ExplicitGCInvokesConcurrentAndUnloadsClasses \
    -XX:+UseConcMarkSweepGC -XX:+CMSIncrementalMode \
    -XX:+UseCMSInitiatingOccupancyOnly \
    -XX:+CMSClassUnloadingEnabled \
    -XX:+CMSScavengeBeforeRemark \
    -XX:+AggressiveOpts \
    -XX:+ParallelRefProcEnabled \
    -XX:+TieredCompilation \
    -cp .:./config:./lib/* ${start-class} "$@"