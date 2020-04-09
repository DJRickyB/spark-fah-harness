#!/bin/bash
set -x

this_rpm='fahclient-7.5.1-1.x86_64.rpm'

curl https://download.foldingathome.org/releases/public/release/fahclient/centos-6.7-64bit/v7.5/$this_rpm \
 -o $this_rpm
mkdir fah
rpm2cpio ./$this_rpm | (cd fah; cpio -idmv)

rm $this_rpm