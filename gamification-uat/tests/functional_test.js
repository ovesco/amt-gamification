let usersData = require('../users.js');

Feature('TESTING_FUNCTIONAL');

Scenario('Devlopper creates account', (I, registerPage, loginPage) => {
    let user = usersData.users[1];

    I.amOnPage(registerPage.url);
    registerPage.register(user.email, user.firstName, user.lastName, user.street,user.npa, user.city, user.password);
    I.seeInCurrentUrl(loginPage.url);
});

Scenario('Devlopper logs in', (I, loginPage, applicationsPage) =>{
    let user = usersData.users[1];

    I.amOnPage(loginPage.url);
    loginPage.signIn(user.email, user.password);
    I.seeInCurrentUrl(applicationsPage.url);
    I.see('Applications');
});

Scenario('Devlopper creates 25 apps',(I, loginPage, applicationsPage, applicationPage) =>{
    let user = usersData.users[1];

    I.amOnPage(loginPage.url);
    loginPage.signIn(user.email, user.password);
    I.seeInCurrentUrl(applicationsPage.url);
    I.see('Applications');
    
    for(i = 0; i < 25; i++){

        I.click(applicationsPage.newApplicationButton);
        I.seeInCurrentUrl(applicationPage.url);

        applicationPage.createNewApp(`App ${i+1}`, `App description ${i+1}`);
        I.seeInCurrentUrl(applicationsPage.url);

        if(i > 4){
            I.amOnPage(applicationsPage.url + `?page=${Math.floor(i/5)+1}`);
        }
        I.see(`App ${i+1}`);
        I.see(`App description ${i+1}`);
    }

    I.click(applicationsPage.logoutButton);
    I.seeInCurrentUrl(loginPage.url);

});

Scenario('Developper logout needs to log back in ',(I, loginPage, applicationsPage, applicationPage) =>{
    let user = usersData.users[1];

    I.amOnPage(loginPage.url);
    loginPage.signIn(user.email, user.password);
    I.seeInCurrentUrl(applicationsPage.url);
    I.see('Applications');

    I.click(applicationsPage.logoutButton);
    I.seeInCurrentUrl(loginPage.url);

    I.amOnPage(applicationPage.url);
    I.seeInCurrentUrl(loginPage.url);
});

Scenario('Delete test account 1', (I, loginPage) => {
    let user = usersData.users[1];
    // make user admin so he can delete the other users in db
    I.amOnPage(`/auth/temp-admin?email=${user.email}`);
    I.see('OK');

    I.amOnPage(loginPage.url);
    loginPage.signIn(user.email, user.password);
    I.click('Accounts');
    I.click({name: `delete-${user.email}`});
    I.seeInCurrentUrl(loginPage.url);
});