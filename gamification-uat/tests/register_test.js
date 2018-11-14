let usersData = require('../users.js');

Feature('Testing if register page is working');

Scenario('Register page is available', (I, registerPage) => {
    registerPage.validate();
});

Scenario('Register page form has correct fields', (I, registerPage) =>{
    registerPage.validateFields();
});


Scenario('Register fails if password is less than 8 characters', (I, registerPage) => {
    let user = usersData.users[0];

    I.amOnPage(registerPage.url);
    registerPage.register(user.email, user.firstName, user.lastName, user.street,user.npa, user.city, "1234567");
    I.see('Password must be at least 8 characters long');
})


Scenario('Register fails if email is not valide', (I, registerPage) => {
    let user = usersData.users[0];

    I.amOnPage(registerPage.url);
    registerPage.register("", user.firstName, user.lastName, user.street,user.npa, user.city,user.password);
    I.amOnPage(registerPage.url);
})



Scenario('Register fails a field is ommited', (I, registerPage) => {
    let user = usersData.users[0];

    I.amOnPage(registerPage.url);
    registerPage.register(user.email, "", user.lastName, user.street,user.npa, user.city,user.password);
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
    registerPage.register(user.email, user.firstName, user.lastName, user.street,user.npa, user.city, user.password);
    I.seeInCurrentUrl(loginPage.url);

    loginPage.signIn(user.email,user.password);
    I.seeInCurrentUrl(applicationsPage.url);
    I.see('My registered apps');
})

Scenario('Register fails if email already taken)', (I, registerPage) => {
    let user = usersData.users[0];

    I.amOnPage(registerPage.url);
    registerPage.register(user.email, user.firstName, user.lastName, user.street,user.npa, user.city, user.password);
    I.seeInCurrentUrl(registerPage.url);
    I.see('Email already taken');
})