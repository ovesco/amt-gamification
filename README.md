# Gamification
### Guillaume Hochet, Adrien Allemand, Loyse Krug & Vincent Guidoux

This project aims at giving the possibility for developers to include a gamification layer in their software.

## Technologies
This tool is a full JavaEE installation which currently includes
- Accounts and admins
- Password retrieval
- Notification system
- Applications, API Keys
- Personal profile
- Automatic emails

## How can this help you?
Imagine you develop an online store where people can buy some products. You want your users to receive some insight
and bonus based on how much they spend on your website, like levels or badges and earn rewards based on their profile.

### Enter gamification project
Grab an API key from this project and start working with our API! We dont provide much currently, but much more
will come! For example you'll be able to set custom rules based on what your customers buy, grant them points and more.
You will be also able to define web hooks that the server will call whenever something happens, like one of your user
earns a reward or something.

Such game, much fun



## Deploy

After cloning the repo run the command `docker-compose up`

(In case there is any modification in the code, run the `build.sh` script and  run the  `docker-compose up --build` )

Finally, go to the adresse:

http://192.168.99.100:8080/game/auth/login







