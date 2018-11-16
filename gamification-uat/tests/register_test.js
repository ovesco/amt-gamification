let usersData = require('../users.js');

Feature('Testing if register page is working');

Scenario('Register page is available', (I, registerPage) => {
    registerPage.validate();
});

Scenario('Register page form has correct fields', (I, registerPage) => {
    registerPage.validateFields();
});


Scenario('Register fails if password is less than 8 characters', (I, registerPage) => {
    let user = usersData.users[0];

    I.amOnPage(registerPage.url);
    registerPage.register(user.email, user.firstName, user.lastName, user.street, user.npa, user.city, "1234567");
    I.see('Password must be at least 8 characters long');
})


Scenario('Register fails if email is not valide', (I, registerPage) => {
    let user = usersData.users[0];

    I.amOnPage(registerPage.url);
    registerPage.register("", user.firstName, user.lastName, user.street, user.npa, user.city, user.password);
    I.amOnPage(registerPage.url);
})



Scenario('Register fails a field is ommited', (I, registerPage) => {
    let user = usersData.users[0];

    I.amOnPage(registerPage.url);
    registerPage.register(user.email, "", user.lastName, user.street, user.npa, user.city, user.password);
    
    I.see('This field is mandatory');

    I.fillField(registerPage.fields.firstName, user.firstName);
    I.clearField(registerPage.fields.lastName);
    registerPage.submitForm();
    I.see('This field is mandatory');

    I.fillField(registerPage.fields.lastName, user.lastName);
    I.clearField(registerPage.fields.street);
    registerPage.submitForm();
    I.see('This field is mandatory');

    I.fillField(registerPage.fields.street, user.street);
    I.clearField(registerPage.fields.npa);
    registerPage.submitForm();
    I.see('This field is mandatory');

    I.fillField(registerPage.fields.npa, user.npa);
    I.clearField(registerPage.fields.city);
    registerPage.submitForm();
    I.see('This field is mandatory');
})

Scenario('Register Success with valide informaiton', (I, loginPage, registerPage, applicationsPage) => {
    let user = usersData.users[0];

    I.amOnPage(registerPage.url);
    registerPage.register(user.email, user.firstName, user.lastName, user.street, user.npa, user.city, user.password);
    I.seeInCurrentUrl(loginPage.url);

    loginPage.signIn(user.email, user.password);
    I.seeInCurrentUrl(applicationsPage.url);
    I.see('My registered apps');

    I.amOnPage(loginPage.url);
    loginPage.deleteUser(user.email, user.password);

})

Scenario('Register fails if email already taken)', (I, loginPage, registerPage) => {
    let user = usersData.users[0];

    //create user once
    I.amOnPage(registerPage.url);
    registerPage.register(user.email, user.firstName, user.lastName, user.street, user.npa, user.city, user.password);

    //recreate user 
    I.amOnPage(registerPage.url);
    registerPage.register(user.email, user.firstName, user.lastName, user.street, user.npa, user.city, user.password);
    I.seeInCurrentUrl(registerPage.url);
    I.see('Email already taken');

    //delete user
    I.amOnPage(loginPage.url);
    loginPage.deleteUser(user.email, user.password);
})


Scenario('Admin can delete other user account', (I, registerPage, loginPage) => {
    let user0 = usersData.users[0];
    let user1 = usersData.users[1];

    //two users are created
    I.amOnPage(registerPage.url);
    registerPage.register(user0.email, user0.firstName, user0.lastName, user0.street, user0.npa, user0.city, user0.password);
    I.amOnPage(registerPage.url);
    registerPage.register(user1.email, user1.firstName, user1.lastName, user1.street, user1.npa, user1.city, user1.password);

    // make user0 admin so he can delete the other users in db
    I.amOnPage(`/auth/temp-admin?email=${user0.email}`);
    I.see('OK');
    I.dontSee('POK');

    //user 0 logins and deletes user 1 account
    I.amOnPage(loginPage.url);
    loginPage.signIn(user0.email, user0.password);
    I.click('Accounts');
    I.click({name: `delete-${user1.email}`});
    I.dontSeeInCurrentUrl(user1.email);
    I.see('Account successfully deleted');

    I.click('Logout');

    //user1 tries to login but fail
    I.amOnPage(loginPage.url);    
    loginPage.signIn(user1.email, user1.password);
    I.seeInCurrentUrl(loginPage.url);

    //delete user
    loginPage.deleteUser(user0.email, user0.password);
});