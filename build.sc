#!/bin/bash

printf "\n==++ Build and Run MemoryGame ++==\n\n"

if [ -e "cls" ]; then
    printf "Directory cls exists.\n\n"
else
    printf "Directory cls does not exist - creating it.\n\n"
    mkdir cls
fi

if [ -e "errlog.txt" ]; then
    rm errlog.txt
fi

printf "Compiling MemoryGame.java...\n\n"
javac -d cls/ MemoryGame.java 2> errlog.txt

if [ $? -eq 0 ]; then
    rm errlog.txt
    printf "\n****  No errors.  ****\n\n"
    printf "Running MemoryGame...\n\n"
    java -cp cls/ MemoryGame
else
    printf "\nERRORS EXIST !!!!\nSee errlog.txt for details.\n\n"
fi

exit 0
