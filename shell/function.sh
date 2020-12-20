#!/bin/sh

info()
{
    echo "$0"
    echo "$SHELL"
    whoami
    hostname
    uname -a
}

info