#
#
#

    prj=MyQa77
    entry=MyQa77

    manifest=manifest.txt

    logFile=./build.log

    echo                >   $logFile   2>&1    
    date                >>  $logFile   2>&1    
    echo                >>   $logFile   2>&1    

    echo "compiles"     >>   $logFile   2>&1    

    rm *.class          >>   $logFile   2>&1    


    javac *.java
    if [ $? -ne 0 ]; then 
        msg="compiles failed"
        echo "$msg"     >>   $logFile   2>&1    
        exit 99
    fi 

    msg="compiled without incident"
    echo "$msg"     >>   $logFile   2>&1    


    echo "jar creation" >>   $logFile   2>&1    

    #		jar cvfm Xt57jLibQA.jar $manifest  *.class

    jar   cvfe	${entry}.jar	${entry}		*.class | grep -iv "^adding:"

    if [ $? -ne 0 ]; then 
        msg="jar creation failed"
        echo "$msg"     >>   $logFile   2>&1    
        exit 99
    fi

    msg="jar built without incident"
    echo "$msg"     >>   $logFile   2>&1    



    java -jar $entry.jar

    cat /tmp/cf.log     >>  /dev/null   2>&1

    rm *.class          >>  /dev/null   2>&1

    exit 0

