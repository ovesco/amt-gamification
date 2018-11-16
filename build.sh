#!/usr/bin/env bash
cd gamification && mvn clean install
cd ..
cp $PWD/gamification/target/game.war $PWD/images/payara