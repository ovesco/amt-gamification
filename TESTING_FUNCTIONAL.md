# TESTING_FUNCTIONAL



## Run the tests



To run User Automated Tests,

Required : You need the use of the npm and node commands, if nedded, intall those. 



Follow the steps:

1) Clone the repository 

2)  Go to the gamification-uat folder. 

3) Open a terminal and run the command  `npm install`

4) Run the command  `node .\node_modules\codeceptjs\bin\codecept.js run`  



## Tested functionalities

Here are the tested functionalities:

**Functional tests** 

- A developper creates an account
- A developper logs in
- A developper creates 25 apps
- A developper who logs out needs to log back in
- An account can be deleted



**Login page**

- Login page is available
- Login page can redirect to register
- Login fails with wrong credentials



**Register page** 

- Register pages is available
- Register form has correct fields
- Register fails if password is less than 8 characters
- Register fails if email is not valid
- Register fails if a field is ommited
- Register success with valid informations
- Register fails if email is already taken
- Admin can delete another user



